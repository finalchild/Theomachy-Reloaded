package septagram.Theomachy.Ability.GOD;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Ares extends Ability
{
	public Ares(String playerName)
	{
		super(playerName,"Ares", 8, false, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 아레스 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Passive  "+ChatColor.GREEN+"Rank[ A ]");
		player.sendMessage("전쟁의 신입니다.\n"+
						   "모든 공격 데미지가 1.5배 상승합니다.\n" +
						   "추가 패시브 능력으로 10퍼센트 확률로 공격을 회피합니다");
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{		
		Player player = (Player) event.getEntity();
		if (!player.getName().equals(playerName)) //공격
			event.setDamage((int) (event.getDamage()*1.5));
		else											//피격
		{
			Random random = new Random();
			if (random.nextInt(10) == 0) 	//1/2 확률
			{
				event.setCancelled(true);
				player.sendMessage("회피했습니다!");
			}
		}
	}
}
