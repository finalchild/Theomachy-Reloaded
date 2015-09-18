package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;


import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Timer.Skill.MeteorTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;


public class Meteor extends Ability {
    private final int coolTime0 = 110;
    private final int material = 4;
    private final int stack0 = 5;
	
    public Meteor(String playerName) {
        super(playerName, "Meteor", 117, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 메테오 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN
                + "Rank[ S ]");
        player.sendMessage(
                "유성을 소환하는 능력입니다.\n"
                        + "블레이즈 로드를 사용한 능력으로 유성을 소환합니다.\n"
                        + "능력 사용시 자신의 위치를 기준으로 넓은 범위에 유성을 30번 떨어뜨립니다.\n"
                        + "블럭은 메테오의 폭발에 부숴지지 않습니다.\n"
                        + ChatColor.GREEN + "(좌클릭) " + ChatColor.WHITE
                        + " 코블스톤 " + stack0 + "개 소모, 쿨타임 "
                        + coolTime0 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, 369)) {
            switch (EventFilter.PlayerInteract(event)) {
            case 0:
            case 1:
                leftAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {	
        if (CoolTimeChecker.Check(player, 0)
                && PlayerInventory.ItemCheck(player, material, stack0)) {
            Skill.Use(player, material, stack0, 0, coolTime0);
            Location location = player.getLocation();
            Timer t = new Timer();

            t.schedule(new MeteorTimer(player, location, 30), 0, 200);
        }
    }
}
