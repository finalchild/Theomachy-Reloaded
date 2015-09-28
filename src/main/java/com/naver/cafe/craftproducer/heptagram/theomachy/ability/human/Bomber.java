package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Bomber extends Ability {
    private final int coolTime0 = 27;
    private final Material material = Material.COBBLESTONE;
    private final int stack0 = 2;
    private Location tntLocation;
	
    public Bomber(String playerName) {
        super(playerName, "Bomber", 105, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 봄버 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage("폭발을 다루는 능력입니다.\n" + "지정된 위치에 1.5의 폭발을 일으킵니다.\n" + "우클릭으로 해당 위치에 보이지 않는 tnt를 설치하며\n" + "좌클릭으로 어디서든 폭발시킬 수 있습니다.\n" + ChatColor.GREEN + "(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack0 + "개 소모, 쿨타임 " + coolTime0 + "초"); 
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.checkInHandItem(player, Material.BLAZE_ROD)) {
            switch (EventFilter.sortInteraction(event)) {
            case 1:
                leftAction(player);
                break;

            case 2:
            case 3:
                rightAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        Block block = player.getTargetBlock((Set<Material>) null, 5);

        if (!block.isEmpty()) {
            Location location = block.getLocation();

            location.setY(location.getY() + 1);
            this.tntLocation = location;
            player.sendMessage("해당 블럭에 폭탄이 설치되었습니다.");			
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.check(player, 0) && PlayerInventory.checkItem(player, material, stack0)) {
            if (tntLocation != null) {
                Skill.use(player, material, stack0, 0, coolTime0);
                World world = player.getWorld();

                world.createExplosion(tntLocation, 1.5f, true);
                tntLocation = null;
                player.sendMessage("TNT가 폭발했습니다!");
				
            } else {
                player.sendMessage("TNT가 설치되지 않았습니다.");
            }
        }
    }
}
