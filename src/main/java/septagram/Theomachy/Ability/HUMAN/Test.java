package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;

public class Test extends Ability
{	
	public Test(String playerName)
	{
		super(playerName,"제작자", -1, true, false, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 칠각별 ]  "+ChatColor.RED+"[ 제작자 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ F ]");
		player.sendMessage("제작자입니다.\n");
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:
				leftAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player)
	{		
		Location location = player.getTargetBlock(null, 40).getLocation();
		Vector v = new Vector(0,-10,0);
		Fireball fireball = (Fireball)player.getWorld().spawn(location.add(0, 70, 0), Fireball.class);
		fireball.setDirection(v);
		fireball.setShooter(player);
		
	}
	
}
