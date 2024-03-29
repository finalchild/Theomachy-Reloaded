package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.manager.EventManager;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Athena extends Ability {
    private final int coolTime1 = 30;
    private final int coolTime2 = 3;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 1;
    private final int stack2 = 32;
    private int abilityLimitCounter = 2;
    public Athena(String playerName) {
        super(playerName, "Athena", 5, true, true, false);
        Theomachy.log.info(playerName + abilityName);
		
    }
	
    public void description() {
        Player player = GameData.onlinePlayers.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 아테나 ]  " + ChatColor.RED + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ S ]");
        player.sendMessage("지혜의 신입니다.\n" + "플레이어들이 사망할 때 마다 경험치를 얻습니다.\n" + "자신이 사망하면 경험치는 초기화됩니다.\n" + "좌클릭으로 책을 얻을 수 있으며 우클릭으로 인챈트 테이블을 얻을수 있습니다.(게임당 2번)\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
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
            player.getInventory().addItem(new ItemStack(Material.BOOK, 3));
        }
    }
	
    private void rightAction(Player player) {
        if (abilityLimitCounter > 0) {
            if (CoolTimeChecker.check(player, 2) && PlayerInventory.checkItem(player, material, stack2)) {
                if (abilityLimitCounter > 1) {
                    Skill.use(player, material, stack2, 2, coolTime2);
                    player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
                    player.sendMessage("남은 교환 횟수 : " + --abilityLimitCounter);
                } else {
                    Skill.use(player, material, stack2, 2, 0);
                    player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
                    player.sendMessage("남은 교환 횟수 : " + --abilityLimitCounter);
                }
            }
        } else {
            player.sendMessage("이 능력은 더 이상 사용할 수 없습니다.");
        }
    }
	
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause().getCause() != DamageCause.SUICIDE) {
            Player player = GameData.onlinePlayers.get(playerName);

            player.setLevel(player.getLevel() + 1);
        }
    }
	
    public void conditionSet() {
        EventManager.playerDeathEventList.add(this); // 나중에 콘디셧셋으로 바꾸기
    }

    public void conditionReset() {
        EventManager.playerDeathEventList.remove(this); // 나중에 콘디션 리셋으로 바꾸기
    }
}

