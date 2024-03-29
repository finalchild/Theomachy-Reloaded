package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Blacksmith extends Ability {
    private final int coolTime1 = 300;
    private final int coolTime2 = 600;
    private final Material material1 = Material.COBBLESTONE;
    private final Material material2 = Material.IRON_INGOT; // 철괴
    private final int stack1 = 20;
    private final int stack2 = 20;
	
    public Blacksmith(String playerName) {
        super(playerName, "Blacksmith", 5, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayers.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 대장장이 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN + "Rank[ S ]");
        player.sendMessage("철,다이아를 만들어 낼 수 있는 능력입니다.\n" + "일반능력으로 코블스톤을 소비하여 철괴 10개를 획득 할 수 있습니다.\n" + "고급능력으로 철괴를 소비하여 다이아 5개를 얻을 수 있습니다.\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 철괴 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
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
        if (CoolTimeChecker.check(player, 1) && PlayerInventory.checkItem(player, material1, stack1)) {
            Skill.use(player, material1, stack1, 1, coolTime1);
            World world = player.getWorld();

            world.dropItem(player.getLocation().add(0, 2, 0), new ItemStack(Material.IRON_INGOT, 10));
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.check(player, 2) && PlayerInventory.checkItem(player, material2, stack2)) {
            Skill.use(player, material2, stack2, 2, coolTime2);
            World world = player.getWorld();

            world.dropItem(player.getLocation().add(0, 2, 0), new ItemStack(Material.DIAMOND, 5));
        }
    }
}
