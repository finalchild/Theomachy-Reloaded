package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.GOD;

import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.BlockFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;

public class Zeus extends Ability
{
	private final int coolTime1=120;
	private final int coolTime2=180;
	private final int material=4;
	private final int stack1=1;
	private final int stack2=5;
	
	public Zeus(String playerName)
	{
		super(playerName,"Zeus", 1, true, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 제우스 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ S ]");
		player.sendMessage("신들의 왕입니다.\n"+
						   "번개를 사용하며 블레이즈 로드를 들었을때 발동 시킬 수 있습니다.\n" +
						   "패시브 능력으로 번개와 폭발 데미지를 받지 않습니다.\n" +
						   "일반능력은 타겟지역(거리제한 50)에 번개를 떨어뜨리며\n" +
						   "고급능력은 타겟지역(거리제한 30)에 대량의 번개를 떨어뜨립니다.\n" +
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
			case 4:
				player.sendMessage(event.getAction().toString());
			}
		}
	}

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material, stack1))
		{
			Block block = player.getTargetBlock((Set<Material>) null, 50);
			if (BlockFilter.AirToFar(player, block))
			{
				Skill.Use(player, material, stack1, 1, coolTime1);
				World world = player.getWorld();
				Location location = block.getLocation();
				world.strikeLightning(location);
			}
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			Block block = player.getTargetBlock((Set<Material>) null, 30);
			if (BlockFilter.AirToFar(player, block))
			{
				Skill.Use(player, material, stack2, 2, coolTime2);
				World world = player.getWorld();
				Location location = block.getLocation();
				Random random = new Random();
				for (int i=0; i<5; i++)
				{
					int X = random.nextInt(11)-5;
					int Z = random.nextInt(11)-5;
					location.add(X, 0, Z);
					world.strikeLightning(location);
					location.add(-X, 0, -Z);
				}
			}
		}
	}
	
	public void T_Passive(EntityDamageEvent event)
	{
		if (event.getCause() == DamageCause.LIGHTNING || event.getCause() == DamageCause.ENTITY_EXPLOSION)
			event.setCancelled(true);
	}
}
