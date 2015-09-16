package septagram.Theomachy.Ability.GOD;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Demeter extends Ability
{
	private final int coolTime0=30;
	private final int material=4;//나무
	private final int stack0=10;
	public Demeter(String playerName)
	{
		super(playerName,"Demeter", 4, true, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 데메테르 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ B ]");
		player.sendMessage("수확의 신입니다.\n"+
						   "코블스톤을 이용해서 빵을 얻을 수 있습니다.\n" +
						   "기본적으로 배고픔이 닳지 않으며 체력 회복속도가 4배로 상승합니다.\n" +
						   ChatColor.GREEN+"(좌,우클릭) "+ChatColor.WHITE+" 코블스톤 "+stack0+"개 소모, 쿨타임 "+coolTime0+"초\n");
	}

	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:case 2:case 3:
				Action(player);
				break;
			}
		}
	}

	private void Action(Player player)
	{
		if (CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0))
		{
			Skill.Use(player, material, stack0, 0,coolTime0);
			Inventory inventory = player.getInventory();
			inventory.addItem(new ItemStack(Material.BREAD.getId(),stack0));
		}
	}
	
	public void T_Passive(FoodLevelChangeEvent event)
	{
		((Player)event.getEntity()).setFoodLevel(20);
		event.setCancelled(true);
	}
	
	public void T_Passive(EntityRegainHealthEvent event)
	{
		event.setAmount(event.getAmount() * 4);
	}
}
