package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Demeter extends Ability {
    private final int coolTime0 = 30;
    private final Material material = Material.COBBLESTONE; // 나무
    private final int stack0 = 10;
    public Demeter(String playerName) {
        super(playerName, "Demeter", 4, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 데메테르 ]  " + ChatColor.RED + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ B ]");
        player.sendMessage("수확의 신입니다.\n" + "코블스톤을 이용해서 빵을 얻을 수 있습니다.\n" + "기본적으로 배고픔이 닳지 않으며 체력 회복속도가 4배로 상승합니다.\n" + ChatColor.GREEN + "(좌,우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack0 + "개 소모, 쿨타임 " + coolTime0 + "초\n");
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.checkInHandItem(player, Material.BLAZE_ROD)) {
            switch (EventFilter.sortInteraction(event)) {
            case 0:
            case 1:
            case 2:
            case 3:
                Action(player);
                break;
            }
        }
    }

    private void Action(Player player) {
        if (CoolTimeChecker.check(player, 0) && PlayerInventory.checkItem(player, material, stack0)) {
            Skill.use(player, material, stack0, 0, coolTime0);
            Inventory inventory = player.getInventory();

            inventory.addItem(new ItemStack(Material.BREAD, stack0));
        }
    }
	
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        ((Player) event.getEntity()).setFoodLevel(20);
        event.setCancelled(true);
    }
	
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        event.setAmount(event.getAmount() * 4);
    }
}
