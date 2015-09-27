package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;


import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.GetPlayerList;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;


public class Asclepius extends Ability {
    private final int coolTime1 = 30;
    private final int coolTime2 = 120;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 1;
    private final int stack2 = 5;
	
    public Asclepius(String playerName) {
        super(playerName, "Asclepius", 10, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 아스클레피오스 ]  " + ChatColor.RED
                + "[ 신 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN
                + "Rank[ B ]");
        player.sendMessage(
                "의술의 신입니다.\n"
                        + "자신의 체력회복 혹은 주변 팀원의 체력을 회복합니다.\n"
                        + "일반능력으로 자신을 모두 회복 시킬 수 있으며\n"
                        + "고급능력으로 주변 5칸에 있는 자신을 제외한 팀원의 체력을 모두 회복 시킬 수 있습니다.\n"
                        + ChatColor.AQUA + "일반(좌클릭) "
                        + ChatColor.WHITE + " 코블스톤 " + stack1
                        + "개 소모, 쿨타임 " + coolTime1 + "초\n"
                        + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE
                        + " 코블스톤 " + stack2 + "개 소모, 쿨타임 "
                        + coolTime2 + "초\n");
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
        if (CoolTimeChecker.Check(player, 1)
                && PlayerInventory.ItemCheck(player, material, stack1)) {
            Skill.Use(player, material, stack1, 1, coolTime1);
            player.setHealth(20);
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2)
                && PlayerInventory.ItemCheck(player, material, stack2)) {
            List<Player> targetList = GetPlayerList.getNearByTeamMembers(player,
                    5, 5, 5);

            if (!targetList.isEmpty()) {
                Skill.Use(player, material, stack2, 2, coolTime2);
                player.sendMessage(
                        "자신을 제외한 모든 팀원의 체력을 회복합니다.");
                player.sendMessage(
                        ChatColor.GREEN
                                + "체력을 회복한 플레이어 목록");
                for (Player e : targetList) {
                    e.setHealth(20);
                    e.sendMessage(
                            ChatColor.YELLOW
                                    + "의술의 신의 능력으로 모든 체력을 회복합니다.");
                    player.sendMessage(ChatColor.GOLD + e.getName());
                }
            } else {
                player.sendMessage("사용 가능한 대상이 없습니다.");
            }
        }
    }
}
