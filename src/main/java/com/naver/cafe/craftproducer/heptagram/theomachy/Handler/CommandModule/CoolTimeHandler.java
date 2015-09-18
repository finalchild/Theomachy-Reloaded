package com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.Timer.CoolTime;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PermissionChecker;


public class CoolTimeHandler {
    public static void Module(CommandSender sender, Command command, String label, String[] data) {
        if (PermissionChecker.Sender(sender)) {
            CoolTime.ini = true;
            Bukkit.broadcastMessage(
                    "관리자가 모든 쿨타임을 초기화 하였습니다.");
			
        }
    }
}
