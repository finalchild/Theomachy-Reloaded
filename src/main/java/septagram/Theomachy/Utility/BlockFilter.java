package septagram.Theomachy.Utility;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import septagram.Theomachy.Message.T_Message;


public class BlockFilter
{
	public static boolean AirToFar(Player player, Block block)
	{
		if (!block.isEmpty())
			return true;
		else
		{
			T_Message.TooFarError(player,1);
			return false;
		}
	}
}
