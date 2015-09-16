package septagram.Theomachy.Handler.CommandModule;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

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
