package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Timer.Skill.WizardDisasterTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.Timer.Skill.WizardWindTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.DirectionChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;


public class Wizard extends Ability {
    private final int coolTime1 = 150;
    private final int coolTime2 = 200;
    private final int material = 4;
    private final int stack1 = 5;
    private final int stack2 = 10;
	
    public Wizard(String playerName) {
        super(playerName, "Wizard", 107, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 마법사 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN
                + "Rank[ S ]");
        player.sendMessage(
                "신의 능력을 빌려 쓰는 능력입니다.\n"
                        + "일반능력은 주변 10칸 모든 플레이어를 자신이 보는 방향으로 모두 날려버리며\n"
                        + "고급능력은 주변 5칸 모든 플레이어를 공중으로 띄운 후 플레이어에게 번개를 떨어뜨립니다. 패널티로 자신의 체력이 반으로 줄어듭니다.\n"
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
            List<Entity> entityList = player.getNearbyEntities(10, 10, 10);
            ArrayList<Player> targetList = new ArrayList<Player>(); 

            for (Entity e : entityList) {
                if (e instanceof Player) {
                    targetList.add((Player) e);
                }
            }
            if (!targetList.isEmpty()) {
                Skill.Use(player, material, stack1, 1, coolTime1);
                Timer t = new Timer();
                Vector v = new Vector(0, 0.5, 0);
                double vertical = 2.4d;
                double diagonal = vertical * 1.4d;

                for (Player e : targetList) {
                    e.setVelocity(v);
                    e.sendMessage(
                            "마법사의 능력에 의해 날아갑니다.");
                }
                switch (DirectionChecker.PlayerDirection(player)) {
                case 0:
                    v.add(new Vector(0, 0, diagonal));
                    break;

                case 1:
                    v.add(new Vector(-vertical, 0, vertical));
                    break;

                case 2:
                    v.add(new Vector(-diagonal, 0, 0));
                    break;

                case 3:
                    v.add(new Vector(-vertical, 0, -vertical));
                    break;

                case 4:
                    v.add(new Vector(0, 0, -diagonal));
                    break;

                case 5:
                    v.add(new Vector(vertical, 0, -vertical));
                    break;

                case 6:
                    v.add(new Vector(diagonal, 0, 0));
                    break;

                case 7:
                    v.add(new Vector(vertical, 0, vertical));
                    break;
                }
                t.schedule(new WizardWindTimer(targetList, v), 200);
            } else {
                player.sendMessage(
                        "능력을 사용할 수 있는 대상이 없습니다.");
            }
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2)
                && PlayerInventory.ItemCheck(player, material, stack2)) {
            List<Entity> entityList = player.getNearbyEntities(5, 5, 5);
            ArrayList<Player> targetList = new ArrayList<Player>(); 

            for (Entity e : entityList) {
                if (e instanceof Player) {
                    targetList.add((Player) e);
                }
            }
            if (!targetList.isEmpty()) {
                Skill.Use(player, material, stack2, 2, coolTime2);
                player.setHealth(player.getHealth() / 2);
                Vector v = new Vector(0, 1.6, 0);

                for (Player e : targetList) {
                    e.setVelocity(v);
                    e.sendMessage(
                            ChatColor.RED
                                    + "마법사의 고급능력에 당했습니다!");
                }
                Timer t = new Timer();

                t.schedule(new WizardDisasterTimer(targetList, player), 1000);
            }
            player.sendMessage(
                    "능력을 사용할 수 있는 대상이 없습니다.");
        }
    }
}
