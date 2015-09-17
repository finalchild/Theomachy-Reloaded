package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.GOD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.Message.T_Message;
import com.naver.cafe.craftproducer.heptagram.theomachy.Timer.CoolTime;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.Utility.Skill;

public class Poseidon extends Ability
{
	private boolean flag = true;
	private final int coolTime0=200;
	private final int material=4;
	private final int stack0=5
			;
	public Poseidon(String playerName)
	{
		super(playerName,"Poseidon", 2, true, true, false);
		Theomachy.log.info(playerName+abilityName);
	}

	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 포세이돈 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Active,Passive  "+ChatColor.GREEN+"Rank[ S ]");
		player.sendMessage("물의 신입니다.\n"+
						   "물 속에 있을때 일정확률로 모든 피격을 33% 확률로 회피합니다. 나온 직후 7초 동안 효과가 지속됩니다.\n" +
						   "블레이즈 로드를 이용한 능력으로 자신의 앞으로 물을 생성하며 이후 7초 동안 물에 있는 플레이어는 모두 날려버립니다.\n" +
						   "물벽은 코블스톤을 뚫을 수 있습니다.\n" +
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
			if (flag)
			{
				Skill.Use(player, material, stack0, 0, coolTime0);
				Location location = player.getLocation();
				Vector v = player.getEyeLocation().getDirection();
				v.setX(Math.round(v.getX()));
				v.setY(0);
				v.setZ(Math.round(v.getZ()));
				KnockBack  k = new KnockBack(player,v); 
				Wave w = new Wave(player, location, v);
				k.start();
				w.start();
			}
			else
				player.sendMessage("스킬의 지속시간이 끝나지 않아 사용할 수 없습니다.");
		}
	}
	class KnockBack extends Thread
	{
		final Player player;
		Vector v;
		
		KnockBack(Player player, Vector v)
		{
			this.player=player;
			this.v=v.clone();
			this.v.multiply(10);
			this.v.setY(10);
		}

		public void run()
		{
			flag = false;
			Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
			for (int i=0; i<5; i++)
			{
				for (Player player : players)
					if (player != this.player && (player.getLocation().getBlock().getTypeId() == 9 ||
							player.getLocation().getBlock().getTypeId() == 8))
						player.setVelocity(v);
				try {
					sleep(1500);
				} catch (InterruptedException e) {
				}
			}
			flag = true;
		}
	}
	class Wave extends Thread
	{
		final Player player;
		final Location location;
		final Location remove;
		final Vector v;
		
		
		
		Wave(Player player, Location location, Vector v)
		{
			this.player = player;
			this.location = location.add(0,2,0);
			this.remove = location.clone();
			this.v=v;
		}
		
		public void run()
		{
			try
			{
				for (int i=0; i<9; i++)
				{
					Block b = location.add(v).getBlock();
					if (b.getTypeId() == 0 || b.getTypeId() == 4)
						b.setTypeId(8);
				}
				sleep(3000);
				for (int i=0; i<9; i++)
				{
					Block b = remove.add(v).getBlock();
					if (b.getTypeId() == 8 || b.getTypeId() == 9)
						b.setTypeId(0);
				}
			}
			catch(Exception e)
			{}
		}
	}


	public void T_Passive(EntityDamageEvent event)
	{
		Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.DROWNING)
		{
			event.setCancelled(true);
			CoolTime.COOL0.put(playerName+"0", 7);
			T_Message.PassiveEnable(player, 0);
		}
		else if (CoolTime.COOL0.containsKey(player.getName()+"0"))
		{
			int rn = (int) (Math.random()*3);
			if (rn == 0)
			{
				event.setCancelled(true);
				player.sendMessage("회피");
			}
		}
	}
	public void conditionSet()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.setMaximumAir(0);
		player.setRemainingAir(0);
	}
	
	public void conditionReSet()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.setMaximumAir(300);
		player.setRemainingAir(300);
	}
}
