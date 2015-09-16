package septagram.Theomachy.Ability.HUMAN;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Miner extends Ability
{
	public Miner(String playerName)
	{
		super(playerName,"Miner", 102, false, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 광부 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Passive  "+ChatColor.GREEN+"Rank[ B ]");
		player.sendMessage("돌을 효율적으로 캐는 능력입니다.\n"+
						   "코블스톤을 캘 때 일정 5% 확률로 한번에 10개를 얻을 수 있습니다.\n");
	}
	
	public void T_Passive(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		if (block.getTypeId() == 4)
		{
			Location location = block.getLocation();
			World world = event.getPlayer().getWorld();
			Random random = new Random();
			if (random.nextInt(20) == 0)
			{
				Player player = event.getPlayer();
				player.sendMessage("잭팟!");
				world.dropItemNaturally(location, new ItemStack(4, 9));
			}
		}
	}
}
