package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.GOD;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;

public class Dionysus extends Ability
{	
	public Dionysus(String playerName)
	{
		super(playerName,"Dionysus", 12, false, true, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 디오니소스 ]  "+ChatColor.RED+"[ 신 ]  "+ChatColor.BLUE+"Passive,DeBuff  "+ChatColor.GREEN+"Rank[ A ]");
		player.sendMessage("술의 신입니다.\n"+
						   "10% 확률로 자신을 공격한 10초간 상대의 시야를 어지럽히며\n" +
						   "동시에 상대의 이동속도, 공격력을 낮춥니다.\n");
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		Player player = (Player)event.getEntity();
		if (player.getName().equals(playerName))
		{
			Random random = new Random();
			int rn = random.nextInt(10);
			if (rn == 0)
			{
				Player target = (Player) event.getDamager();
				target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,0), true);
				target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,0), true);
				target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,240,0), true);
				target.sendMessage("술에 취해서 정신이 없습니다!");
				player.sendMessage("상대방에게 술을 먹였습니다.");
			}
		}
	}
}
