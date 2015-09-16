package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Timer.CoolTime;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Invincibility extends Ability
{
	private final int coolTime1=50;
	private final int coolTime2=120;
	private final int material=4;
	private final int stack1=1;
	private final int stack2=5;
	
	public Invincibility(String playerName)
	{
		super(playerName,"Invincibility", 111, true, false, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 무적 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Active, Buff  "+ChatColor.GREEN+"Rank[ A ]");
		player.sendMessage("일정시간 데미지를 받지 않을 수 있는 능력입니다.\n" +
						   "블레이즈로드를 이용해 능력을 사용할 수 있습니다.\n" +
						   "좌클릭을 이용해 일정시간 자신을 무적 상태로 만듭니다.\n" +
						   "우클릭은 자신에게 체력회복 버프를 시전합니다.\n" +
						   ChatColor.AQUA+"일반(좌클릭) "+ChatColor.WHITE+" 코블스톤 "+stack1+"개 소모, 쿨타임 "+coolTime1+"초\n" +
						   ChatColor.RED+"고급(우클릭) "+ChatColor.WHITE+" 코블스톤 "+stack2+"개 소모, 쿨타임 "+coolTime2+"초\n");
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
			case 2:case 3:
				rightAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material, stack1))
		{
			Skill.Use(player, material, stack1, 1, coolTime1);
			{
				CoolTime.COOL0.put(playerName+"1", 7);
			}
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			Skill.Use(player, material, stack2, 2, coolTime2);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0), true);
		}
	}
	
	public void T_Passive(EntityDamageEvent event)
	{
		if (CoolTime.COOL0.containsKey(playerName+"1"))
		{
			event.setCancelled(true);
			event.getEntity().setFireTicks(0);
		}
	}
}
