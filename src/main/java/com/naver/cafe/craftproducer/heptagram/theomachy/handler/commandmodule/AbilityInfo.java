package com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PermissionChecker;

public class AbilityInfo {
    public static void showAllAbility(CommandSender sender) {
        if (PermissionChecker.checkSender(sender)) {
            if (!GameData.playerAbility.isEmpty()) {
                Collection<Ability> ability = GameData.playerAbility.values();

                for (Ability e : ability) {
                    sender.sendMessage(ChatColor.GOLD + e.playerName + "  :  " + ChatColor.RED + e.abilityName);
                }
						
            } else {
                sender.sendMessage("능력이 있는 플레이어가 없습니다.");
            }
        }
    }
}
