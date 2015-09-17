package com.naver.cafe.craftproducer.heptagram.theomachy.Handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.AbilityInfo;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.AbilitySet;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.CoolTimeHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.GameHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.Help;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.Spawn;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.Team;
import com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule.TeamInfo;

public class CommandHandler
{
	public static void T_Handler(CommandSender sender, Command command, String label, String[] data)
	{
		if (data[0].equals("start"))
			GameHandler.GameReady(sender);
		else if (data[0].equals("stop"))
			GameHandler.GameStop(sender);
		else if (data[0].equals("ability") || data[0].equals("a"))
			AbilitySet.Module(sender, command, label, data);
		else if (data[0].equals("aaaaa"))
			AbilityInfo.showAllAbility(sender);
		else if (data[0].equals("help"))
			Help.Module(sender, command, label, data);
		else if (data[0].equals("spawn") || data[0].equals("s"))
			Spawn.Module(sender, command, label, data);
		else if (data[0].equals("team") || data[0].equals("t" ))
			Team.Module(sender, command, label, data);
		else if (data[0].equals("info") || data[0].equals("i"))
			TeamInfo.Module(sender, command, label, data);
		else if (data[0].equals("clear") || data[0].equals("c"))
			CoolTimeHandler.Module(sender, command, label, data);
		else
			sender.sendMessage("잘못된 명령입니다.");
	}
	
	public static void X_Handler(CommandSender sender, Command command, String label, String[] data)
	{
		String playerName = sender.getName();
		String targetName = data[0];
		Ability ability = GameData.PlayerAbility.get(playerName);
		if (ability != null)
		{
			if (GameData.OnlinePlayer.containsKey(targetName))
				ability.targetSet(sender, targetName);
			else
				sender.sendMessage("온라인 플레이어가 아닙니다.  "+targetName);
		}
		else
			sender.sendMessage("능력이 없습니다.");
	}
}
