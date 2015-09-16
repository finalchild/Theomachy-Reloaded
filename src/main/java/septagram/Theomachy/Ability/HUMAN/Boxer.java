package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Boxer extends Ability
{
	
	public Boxer(String playerName)
	{
		super(playerName,"Boxer", 5, false, true, true);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 복서 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Passive  "+ChatColor.GREEN+"Rank[ S ]");
		player.sendMessage("빠른 주먹을 사용하는 능력입니다.\n"+
						   "주먹을 이용해서 공격하면 아주 빠른 속도로 공격할 수 있습니다.\n" +
						   "상대가 블로킹 중이라면 효과를 받지 않습니다.\n" +
						   "당신의 광클실력을 보여주세요.");
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		Player player = (Player) event.getDamager();
		if (player.getItemInHand().getTypeId()==0 && player.getName().equals(this.playerName))
		{
			Player target = (Player) event.getEntity();
			if (target.isBlocking()) {
				event.setCancelled(true);
				return;
			}

			if(target.getNoDamageTicks() < 15)
				target.setNoDamageTicks(0);
		}
	}
}
