package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Hephaestus extends Ability {
    private final int coolTime0 = 10;
    private final Material material = Material.COBBLESTONE;
    private final int stack0 = 1;
	
    public Hephaestus(String playerName) {
        super(playerName, "Hephaestus", 9, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 헤파이스토스 ]  " + ChatColor.RED + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ B ]");
        player.sendMessage("불의 신입니다.\n" + "기본적으로 화염데미지를 받지 않으며 용암을 자유자재로 다룰 수 있습니다.\n" + "좌클릭을 통해 해당 지역에 용암을 놓을 수 있습니다. 놓은 용암은 2초뒤 사라집니다.\n" + "하지만 물에는 약하여 물에 들어갈시 데미지를 입습니다.\n" + ChatColor.GREEN + "(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack0 + "개 소모, 쿨타임 " + coolTime0 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD)) {
            switch (EventFilter.PlayerInteract(event)) {
            case 1:
                leftAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        Location location = player.getTargetBlock((Set<Material>) null, 5).getLocation();

        location.setY(location.getY() + 1);
        Block block = location.getBlock();

        if (block.isEmpty()) {
            if (CoolTimeChecker.Check(player, 0) && PlayerInventory.ItemCheck(player, material, stack0)) {
                Skill.Use(player, material, stack0, 0, coolTime0);
                block.setType(Material.LAVA);
                Timer t = new Timer();

                t.schedule(new LavaTimer(block), 2000);
            }
        }
    }
	
    public void T_Passive(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        DamageCause dc = event.getCause();

        if (dc.equals(DamageCause.LAVA) || dc.equals(DamageCause.FIRE) || dc.equals(DamageCause.FIRE_TICK)) {
            event.setCancelled(true);
            player.setFireTicks(0);
        } else if (dc.equals(DamageCause.DROWNING)) {
            event.setDamage(event.getDamage() * 2);
        }
    }
	
    public void conditionSet() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.setMaximumAir(0);
        player.setRemainingAir(0);
    }
	
    public void conditionReSet() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.setMaximumAir(300);
        player.setRemainingAir(300);
    }
	
    class LavaTimer extends TimerTask {
        Block block;
		
        LavaTimer(Block block) {
            this.block = block;
        }
		
        public void run() {
            block.setType(Material.AIR);
        }
    }
	
}
