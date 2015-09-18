package com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule;


import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PermissionChecker;


public class AbilityInfo {
    public static void showAllAbility(CommandSender sender) {
        if (PermissionChecker.Sender(sender)) {
            if (!GameData.PlayerAbility.isEmpty()) {
                Collection<Ability> ability = GameData.PlayerAbility.values();

                for (Ability e : ability) {
                    sender.sendMessage(
                            ChatColor.GOLD + e.playerName + "  :  "
                            + ChatColor.RED + e.abilityName);
                }
						
            } else {
                sender.sendMessage(
                        "능력이 있는 플레이어가 없습니다.");
            }
        }
    }
}
