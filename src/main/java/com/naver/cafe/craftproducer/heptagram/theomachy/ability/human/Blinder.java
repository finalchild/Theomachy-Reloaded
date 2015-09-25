package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;


import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.GetPlayerList;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;


public class Blinder extends Ability {
    private final int coolTime0 = 30;
    private final int material = 4;
    private final int stack0 = 0;
	
    public Blinder(String playerName) {
        super(playerName, "Blindes", 5, true, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 블라인더 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Active,Passive  "
                + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage(
                "상대방의 시야를 가리는 능력입니다.\n"
                        + "자신을 공격한 상대는 33% 확률로 시야가 가려집니다.(4초 지속)\n"
                        + "블레이즈 로드를 이용한 능력으로 자신의 팀원을 제외한 10칸 안에 있는 유저를 블라인드 할 수 있습니다.(8초 지속)\n"
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
            List<Player> targetList = GetPlayerList.getNearByNotTeamMembers(
                    player, 5, 5, 5);

            if (!targetList.isEmpty()) {
                Skill.Use(player, material, stack0, 0, coolTime0);
                player.sendMessage(
                        "자신의 팀원을 제외한 모든 플레이어를 블라인드 합니다.");
                for (Player e : targetList) {
                    e.addPotionEffect(
                            new PotionEffect(PotionEffectType.BLINDNESS, 160, 0),
                            true);
                    e.sendMessage(
                            "블라인더에 의해 시야가 어두워집니다.");
                }
            } else {
                player.sendMessage("사용 가능한 대상이 없습니다.");
            }
        }
    }
	
    public void T_Passive(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();

        if (player.getName().equals(this.playerName)) {
            Random random = new Random();

            if (random.nextInt(3) == 0) {
                Player target = (Player) event.getDamager();

                target.addPotionEffect(
                        new PotionEffect(PotionEffectType.BLINDNESS, 80, 0),
                        true);
                target.sendMessage(
                        "블라인더에 의해 시야가 어두워집니다.");
            }
        }
    }
}
