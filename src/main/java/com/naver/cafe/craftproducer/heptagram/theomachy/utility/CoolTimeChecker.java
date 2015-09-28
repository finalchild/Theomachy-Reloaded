package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTime;

public class CoolTimeChecker {
    public static boolean check(Player player, int abilityCase) {
        String key = player.getName();
		
        if (abilityCase == 0) {
            if (CoolTime.cool0.containsKey(key)) {
                int cool = CoolTime.cool0.get(key);

                T_Message.tellCooltime(player, abilityCase, cool);
                return false;
            } else {
                return true;
            }
        } else if (abilityCase == 1) {
            if (CoolTime.cool1.containsKey(key)) {
                int cool = CoolTime.cool1.get(key);

                T_Message.tellCooltime(player, abilityCase, cool);
                return false;
            } else {
                return true;
            }
        } else if (abilityCase == 2) {
            if (CoolTime.cool2.containsKey(key)) {
                int cool = CoolTime.cool2.get(key);

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
