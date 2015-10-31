package com.naver.cafe.craftproducer.heptagram.theomachy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTarget implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) { // 설명 보기
            sender.sendMessage(ChatColor.YELLOW + ("/x ") + ChatColor.RED + ("<Player>     ") + ChatColor.WHITE + ("해당 플레이어를 자신의 타겟으로 등록합니다."));
            return true;
        } else {
            CommandHandler.handleX(sender, cmd, label, args);
            return true;
        }
	}

}
