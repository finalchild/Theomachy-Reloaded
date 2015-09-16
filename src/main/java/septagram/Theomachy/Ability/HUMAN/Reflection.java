package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Reflection extends Ability
{	
	public Reflection(String playerName)
	{
		super(playerName,"Refection", 109, false, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 반사 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Passive  "+ChatColor.GREEN+"Rank[ B ]");
		player.sendMessage("공격 받은 데미지를 공격자에게 반사하는 능력입니다..\n"+
						   "33% 확률로 자신이 받은 데미지의 반을 상대방에게 반사합니다.(방어무시)\n" +
						   "화살등 간접적으로 받는데미지는 반사하지 못합니다.");
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		Player player = (Player) event.getEntity();
		if (player.getName().equals(playerName))
		{
			int rn = (int) (Math.random()*3);
			if (rn == 0)
			{
				Player damager = (Player) event.getDamager();
				damager.damage(event.getDamage()>>1);
			}
		}
	}
	
}
