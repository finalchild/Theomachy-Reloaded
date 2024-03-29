package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Archer extends Ability {
    private final int coolTime1 = 20;
    private final int coolTime2 = 60;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 4;
    private final int stack2 = 15;
	
    public Archer(String playerName) {
        super(playerName, "Archer", 101, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayers.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 아처 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ B ]");
        player.sendMessage("궁수 입니다.\n" + "활공격 데미지가 1.2배로 상승합니다.\n" + "좌클릭으로 화살을 얻을수있으며 우클릭으로 활을 얻을수 있습니다.\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
    }
	
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.checkInHandItem(player, Material.BLAZE_ROD)) {
            switch (EventFilter.sortInteraction(event)) {
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
        if (CoolTimeChecker.check(player, 1) && PlayerInventory.checkItem(player, material, stack1)) {
            Skill.use(player, material, stack1, 1, coolTime1);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.ARROW, 1));
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.check(player, 2) && PlayerInventory.checkItem(player, material, stack2)) {
            Skill.use(player, material, stack2, 2, coolTime2);
            World world = player.getWorld();
            Location location = player.getLocation();

            world.dropItem(location, new ItemStack(Material.BOW, 1));
        }
    }
	
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        int damage = (int) (event.getDamage());

        event.setDamage((int) (damage * 1.2));
    }
}
