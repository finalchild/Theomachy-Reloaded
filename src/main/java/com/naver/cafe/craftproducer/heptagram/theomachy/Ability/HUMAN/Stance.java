package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;


public class Stance extends Ability {
	
    public Stance(String playerName) {
        super(playerName, "Stance", 103, false, true, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 스탠스 ]  " + ChatColor.RED
                + "[ 인간 ]  " + ChatColor.BLUE + "Passive  "
                + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage(
                "강한 의지를 갖고 있는 능력입니다.\n"
                        + "모든 데미지 증폭 효과, 연타효과를 무시하며 모든 공격에 100퍼센트 확률로 밀려나지 않습니다.\n"
                        + "패널티로 모든 자신의 방어 효과는 무시됩니다.");
    }
	
    public void T_Passive(EntityDamageEvent event) {
        if (event.getCause() == DamageCause.ENTITY_ATTACK
                || event.getCause() == DamageCause.PROJECTILE) {
            Player player = (Player) event.getEntity();
            double damage = event.getDamage();

            player.damage(damage);
            event.setCancelled(true);
        }
    }
	
}
