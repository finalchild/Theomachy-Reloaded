package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionChecker {
    public static boolean checkPlayer(Player player) {
        if (player.isOp()) {
            return true;
        } else {
            player.sendMessage("권한이 없습니다.");
            return false;
        }
    }
	
    public static boolean checkSender(CommandSender sender) {
        if (sender.isOp()) {
            return true;
        } else {
            sender.sendMessage("권한이 없습니다.");
            return false;
        }
    }
}
