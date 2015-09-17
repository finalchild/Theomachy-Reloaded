package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.GOD;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Manager.EventManager;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;

public class Athena extends Ability
{
	private final int coolTime1=30;
	private final int coolTime2=3;
	private final int material=4;
	private final int stack1=1;
	private final int stack2=32;
	private int abilityLimitCounter=2;
	public Athena(String playerName)
	{
		super(playerName,"Athena", 5, true, true, false);
		Theomachy.log.info(playerName+abilityName);
		
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 아테나 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ S ]");
		player.sendMessage("지혜의 신입니다.\n"+
						   "플레이어들이 사망할 때 마다 경험치를 얻습니다.\n" +
						   "자신이 사망하면 경험치는 초기화됩니다.\n" +
						   "좌클릭으로 책을 얻을 수 있으며 우클릭으로 인챈트 테이블을 얻을수 있습니다.(게임당 2번)\n" +
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
			player.getInventory().addItem(new ItemStack(Material.BOOK.getId(),3));
		}
	}
	
	private void rightAction(Player player)
	{
		if (abilityLimitCounter>0)
		{
			if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
			{
				if (abilityLimitCounter>1)
				{
					Skill.Use(player, material, stack2, 2, coolTime2);
					player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE.getId(),1));
					player.sendMessage("남은 교환 횟수 : "+--abilityLimitCounter);
				}
				else
				{
					Skill.Use(player, material, stack2, 2, 0);
					player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE.getId(),1));
					player.sendMessage("남은 교환 횟수 : "+--abilityLimitCounter);
				}
			}
		}
		else
			player.sendMessage("이 능력은 더이상 사용할 수 없습니다.");
	}
	
	public void T_Passive(PlayerDeathEvent event)
	{
		if (event.getEntity().getLastDamageCause().getCause() != DamageCause.SUICIDE)
		{
			Player player = GameData.OnlinePlayer.get(playerName);
			player.setLevel(player.getLevel()+1);
		}
	}
	
	public void conditionSet()
	{
		EventManager.PlayerDeathEventList.add(this);//나중에 콘디셧셋으로 바꾸기
	}
	public void conditionReSet()
	{
		EventManager.PlayerDeathEventList.remove(this);//나중에 콘디션 리셋으로 바꾸기
	}
}

