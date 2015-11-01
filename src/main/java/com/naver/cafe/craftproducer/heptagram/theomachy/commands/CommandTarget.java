package com.naver.cafe.craftproducer.heptagram.theomachy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;

public class CommandTarget implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) { // 설명 보기
            sender.sendMessage(ChatColor.YELLOW + ("/x ") + ChatColor.RED + ("<Player>     ") + ChatColor.WHITE + ("해당 플레이어를 자신의 타겟으로 등록합니다."));
            return true;
        } else {
            String playerName = sender.getName();
            String targetName = args[0];
            Ability ability = GameData.playerAbilities.get(playerName);

            if (ability != null) {
                if (GameData.onlinePlayers.containsKey(targetName)) {
                    ability.targetSet(sender, targetName);
                } else {
                    sender.sendMessage("온라인 플레이어가 아닙니다.  " + targetName);
                }
            } else {
                sender.sendMessage("능력이 없습니다.");
            }
            return true;
        }
	}

}
