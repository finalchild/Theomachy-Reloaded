package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;


public class Archer extends Ability {
    private final int coolTime1 = 20;
    private final int coolTime2 = 60;
    private final int material = 4;
    private final int stack1 = 4;
    private final int stack2 = 15;
	
    public Archer(String playerName) {
        super(playerName, "Archer", 101, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 아처 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Active,Passive  "
                + ChatColor.GREEN + "Rank[ B ]");
        player.sendMessage(
                "궁수 입니다.\n"
                        + "활공격 데미지가 1.2배로 상승합니다.\n"
                        + "좌클릭으로 화살을 얻을수있으며 우클릭으로 활을 얻을수 있습니다.\n"
                        + ChatColor.AQUA + "일반(좌클릭) "
                        + ChatColor.WHITE + " 코블스톤 " + stack1
                        + "개 소모, 쿨타임 " + coolTime1 + "초\n"
                        + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE
                        + " 코블스톤 " + stack2 + "개 소모, 쿨타임 "
                        + coolTime2 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, 369)) {
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
        if (CoolTimeChecker.Check(player, 1)
                && PlayerInventory.ItemCheck(player, material, stack1)) {
            Skill.Use(player, material, stack1, 1, coolTime1);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.ARROW.getId(), 1));
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2)
                && PlayerInventory.ItemCheck(player, material, stack2)) {
            Skill.Use(player, material, stack2, 2, coolTime2);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.BOW.getId(), 1));
        }
    }
	
    public void T_Passive(EntityDamageByEntityEvent event) {
        int damage = (int) (event.getDamage());

        event.setDamage((int) (damage * 1.2));
    }
}
