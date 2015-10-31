package com.naver.cafe.craftproducer.heptagram.theomachy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTheomachy implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) // 설명 보기
        {
            sender.sendMessage(ChatColor.GOLD + ("====================신들의 전쟁===================="));
            sender.sendMessage(ChatColor.YELLOW + ("/t start    ") + ChatColor.WHITE + ("게임을 시작합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t stop     ") + ChatColor.WHITE + ("게임을 중지합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t ability(a)     ") + ChatColor.WHITE + ("능력을 설정합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t help     ") + ChatColor.WHITE + ("자신의 능력을 확인합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t spawn(s) ") + ChatColor.AQUA + ("<TeamName>   ") + ChatColor.WHITE + ("해당 팀의 스폰 지역을 설정합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t team(t)  ") + ChatColor.AQUA + ("<TeamName>  ") + ChatColor.RED + ("<Player>  ") + ChatColor.WHITE + ("플레이어를 팀에 등록합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t info(i)  ") + ChatColor.AQUA + ("<TeamName>     ") + ChatColor.WHITE + ("해당 팀의 멤버를 확인합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/t clear(c) ") + ChatColor.WHITE + ("쿨타임을 초기화합니다."));
            sender.sendMessage(ChatColor.YELLOW + ("/x ") + ChatColor.RED + ("<Player>     ") + ChatColor.WHITE + ("해당 플레이어를 자신의 타겟으로 등록합니다."));
            return true;
        } else {
        	switch (args[0]) {
        	case "start":
        		GameHandler.startGame(sender);
        		break;
        	case "stop":
        		GameHandler.stopGame(sender);
        		break;
        	case "ability": case "a":
        		AbilitySet.onCommand(sender, cmd, label, args);
        		break;
        	case "aaaaa":
        		AbilityInfo.showAllAbilities(sender);
        		break;
        	case "help":
        		Help.onCommand(sender, cmd, label, args);
        		break;
        	case "spawn": case "s":
        		Spawn.onCommand(sender, cmd, label, args);
        		break;
        	case "team": case "t":
        		Team.onCommand(sender, cmd, label, args);
        		break;
        	case "info": case "i":
        		TeamInfo.onCommand(sender, cmd, label, args);
        		break;
        	case "clear": case "c":
        		CoolTimeHandler.onCommand(sender, cmd, label, args);
        		break;
        	default:
        		sender.sendMessage("잘못된 명령입니다.");
        		return false;
        	}
        	return true;
        }
	}

}
