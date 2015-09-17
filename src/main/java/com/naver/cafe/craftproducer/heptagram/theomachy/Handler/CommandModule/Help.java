package com.naver.cafe.craftproducer.heptagram.theomachy.Handler.CommandModule;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;

public class Help {

	public static void Module(CommandSender sender, Command command, String label, String[] data)
	{
		String playerName=sender.getName();
		Ability ability=GameData.PlayerAbility.get(playerName);
		if (ability != null)
			ability.description();
		else
			sender.sendMessage("능력이 없습니다.");
	}


}
