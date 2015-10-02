package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTimer;

public class CoolTimeChecker {
    public static boolean check(Player player, int abilityCase) {
        String key = player.getName();
		
        if (abilityCase == 0) {
            if (CoolTimer.cool0.containsKey(key)) {
                int cool = CoolTimer.cool0.get(key);

                T_Message.tellCooltime(player, abilityCase, cool);
                return false;
            } else {
                return true;
            }
        } else if (abilityCase == 1) {
            if (CoolTimer.cool1.containsKey(key)) {
                int cool = CoolTimer.cool1.get(key);

                T_Message.tellCooltime(player, abilityCase, cool);
                return false;
            } else {
                return true;
            }
        } else if (abilityCase == 2) {
            if (CoolTimer.cool2.containsKey(key)) {
                int cool = CoolTimer.cool2.get(key);

                T_Message.tellCooltime(player, abilityCase, cool);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
