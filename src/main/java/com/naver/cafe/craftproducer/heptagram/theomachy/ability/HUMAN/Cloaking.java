package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;


import java.util.List;
import java.util.Random;
import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.skill.ClockingTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;


public class Cloaking extends Ability {
    private final int coolTime0 = 30;
    private final int material = 4;
    private final int stack0 = 5;
    private List<Player> targetList;
	
    public Cloaking(String playerName) {
        super(playerName, "Cloaking", 112, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 클로킹 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Active,Passive  "
                + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage(
                "일정시간 자신의 몸을 숨길 수 있는 능력입니다.\n"
                        + "일반능력을 이용해 자신의 모습을 7초간 감출 수 있습니다. 감춘상태에서 상대방을 공격할 시 다시 모습이 나타나게 되며 공격 당한 상대는 20%확률로 사망합니다.\n"
                        + ChatColor.GREEN + "(우클릭) " + ChatColor.WHITE
                        + " 코블스톤 " + stack0 + "개 소모, 쿨타임 "
                        + coolTime0 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, 369)) {
            switch (EventFilter.PlayerInteract(event)) {
            case 2:
            case 3:
                leftAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        if (CoolTimeChecker.Check(player, 0)
                && PlayerInventory.ItemCheck(player, material, stack0)) {
            Skill.Use(player, material, stack0, 0, coolTime0);
            targetList = player.getWorld().getPlayers();
            for (Player e : targetList) {
                e.hidePlayer(player);
            }			
            Timer timer = new Timer();

            timer.schedule(new ClockingTimer(targetList, player), 7000);
            super.flag = true;
        }
    }
	
    public void T_Passive(EntityDamageByEntityEvent event) {
        if (flag) {
            Player player = (Player) event.getDamager();

            if (player.getName().equals(this.playerName)) {
                targetList = player.getWorld().getPlayers();
                for (Player e : targetList) {
                    e.showPlayer(player);
                }
                Random random = new Random();

                if (random.nextInt(5) == 0) {
                    Player target = (Player) event.getEntity();

                    event.setDamage(100);
                    target.sendMessage(
                            "알 수 없는 이유로 인해 즉사 하였습니다.");
                    player.sendMessage("상대가 즉사 하였습니다.");
                }
            }
            super.flag = false;
        }
    }
}
