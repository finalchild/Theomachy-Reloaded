package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;

public class Creeper extends Ability
{
	private final int coolTime0=60;
	private final int material=4;
	private final int stack0=5;
	private boolean plasma = false;
	
	public Creeper(String playerName)
	{
		super(playerName,"Creeper", 106, true, false, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 크리퍼 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ C ]");
		player.sendMessage("몬스터형 능력입니다.\n"+
						   "블레이즈 로드를 통해 능력을 사용하면\n" +
						   "1.0의 폭발을 일으킵니다.\n" +
						   "번개를 맞은적이 있다면 폭발력이 두배로 증가합니다.(죽으면 초기화됩니다.)\n" +
						   ChatColor.GREEN+"(좌클릭) "+ChatColor.WHITE+" 코블스톤 "+stack0+"개 소모, 쿨타임 "+coolTime0+"초\n");
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
		if (CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0))
		{
			Skill.Use(player, material, stack0, 0, coolTime0);
			World world = player.getWorld();
			Location location = player.getLocation();
			float power = plasma ? 1.0f : 2.0f;
			player.setHealth(0);
			world.createExplosion(location, power);
		}
	}
	
	public void T_Passive(EntityDamageEvent event)
	{
		if (event.getCause() == DamageCause.LIGHTNING)
		{
			this.plasma = true;
			((Player)event.getEntity()).sendMessage(ChatColor.AQUA+"플라즈마 크리퍼 모드 활성화!");
		}
	}
	
	public void T_Passive(PlayerDeathEvent event)
	{
		this.plasma=false;
	}
}
