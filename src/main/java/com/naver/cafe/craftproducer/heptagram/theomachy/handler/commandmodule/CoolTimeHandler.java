package com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PermissionChecker;

public class CoolTimeHandler {
    public static void onCommand(CommandSender sender, Command command, String label, String[] data) {
        if (PermissionChecker.checkSender(sender)) {
            CoolTimer.ini = true;
            Bukkit.broadcastMessage("관리자가 모든 쿨타임을 초기화 하였습니다.");
			
        }
    }
}
