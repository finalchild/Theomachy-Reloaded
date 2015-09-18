package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;


import java.util.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.skill.ApollonPlayerScorching;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;


public class Apollon extends Ability {
    private final int coolTime1 = 90;
    private final int coolTime2 = 180;
    private final int material = 4;
    private final int stack1 = 1;
    private final int stack2 = 10;
	
    public Apollon(String playerName) {
        super(playerName, "Apollon", 6, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 아폴론 ]  " + ChatColor.RED
                + "[ 신 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN
                + "Rank[ B ]");
        player.sendMessage(
                "태양의 신입니다.\n"
                        + "밤을 낮으로 바꿀 수 있으며 플레이어들을 태울 수도 있습니다.\n"
                        + "일반능력은 밤을 낮으로 바꿀 수 있으며.\n"
                        + "고급능력은 밤을 낮으로 바꿈과 동시에 내리고 있는 비와 눈을 증발시키며 자신을 제외한 모든 플레이어들을 태웁니다.(화염속성의 능력자, 그늘, 물속에 있는 플레이어는 피해를 입지 않습니다.)\n"
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

            world.setTime(6000);
            Bukkit.broadcastMessage(
                    ChatColor.YELLOW
                            + "태양의 신이 해를 띄웠습니다.");
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2)
                && PlayerInventory.ItemCheck(player, material, stack2)) {
            Skill.Use(player, material, stack2, 2, coolTime2);
            World world = player.getWorld();

            world.setTime(6000);
            world.setStorm(false);
            Timer t = new Timer();

            t.schedule(new ApollonPlayerScorching(player, 15), 5000, 2000);
        }
    }
}
