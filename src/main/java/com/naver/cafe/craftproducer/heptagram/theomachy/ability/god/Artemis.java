package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTime;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Artemis extends Ability {	
    private final int coolTime1 = 20;
    private final int coolTime2 = 180;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 4;
    private final int stack2 = 15;
	
    public Artemis(String playerName) {
        super(playerName, "Artemis", 7, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 아르테미스 ]  " + ChatColor.RED + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage("사냥과 달의 신입니다.\n" + "화살과 활을 코블스톤과 교환할수 있습니다.(좌클릭 : 화살 1개, 우클릭 : 활 1개)\n" + "화살로 공격당한 플레이어는 20퍼센트의 확률로 즉사합니다.\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD)) {
            switch (EventFilter.PlayerInteract(event)) {
            case 0:
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
        if (CoolTimeChecker.Check(player, 1) && PlayerInventory.ItemCheck(player, material, stack1)) {
            Skill.Use(player, material, stack1, 1, coolTime1);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.ARROW, 1));
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2) && PlayerInventory.ItemCheck(player, material, stack2)) {
            Skill.Use(player, material, stack2, 2, coolTime2);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.BOW, 1));
        }
    }
	
    public void T_Passive(EntityDamageByEntityEvent event) {
		
        Arrow arrow = (Arrow) event.getDamager();
        Player player = (Player) arrow.getShooter();
        Player target = (Player) event.getEntity();

        if (!CoolTime.cool0.containsKey(target.getName() + "1")) // 무적 코드 1번
        {
            Random random = new Random();

            if (random.nextInt(5) == 0) // 1/5확률
            {
				
                event.setDamage(100);
                player.sendMessage("화살이 상대방의 심장을 꿰뚫었습니다!");
                target.sendMessage("아르테미스의 화살에 즉사하였습니다.");
            }
        }
    }	
}
