package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.skill.HermesFlying;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Hermes extends Ability {
    private final int coolTime0 = 80;
    private final Material material = Material.COBBLESTONE;
    private final int stack0 = 2;
	
    public Hermes(String playerName) {
        super(playerName, "Hermes", 11, true, true, true);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 헤르메스 ]  " + ChatColor.RED + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive,Buff  " + ChatColor.GREEN + "Rank[ S ]");
        player.sendMessage("여행자의 신입니다.\n" + "기본적으로 이동속도가 빠르며 블레이즈 로드를 사용한 능력을 통해 비행 할 수 있습니다.(점프하면서 쓰시면 바로 날 수 있습니다.)\n" + "비행 중에는 낙사 데미지를 받지 않습니다.\n" + ChatColor.GREEN + "좌클릭 : 5초간 비행 할 수 있습니다.\n" + ChatColor.AQUA + "(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack0 + "개 소모, 쿨타임 " + coolTime0 + "초\n");
    }
	
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.checkInHandItem(player, Material.BLAZE_ROD)) {
            switch (EventFilter.sortInteraction(event)) {
            case 0:
            case 1:
                leftAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        if (CoolTimeChecker.check(player, 0) && PlayerInventory.checkItem(player, material, stack0)) {
            Skill.use(player, material, stack0, 0, coolTime0);
            player.setAllowFlight(true);
            player.setFlying(true);
            Timer t = new Timer();

            t.schedule(new HermesFlying(player), 2000, 1000);
        }
    }
	
    public void buff() {
        Player player = GameData.onlinePlayer.get(playerName);

        if (player != null) {
            Timer t = new Timer();

            t.schedule(new buff(player), 1000);
        }
    }
	
    private class buff extends TimerTask {
        final Player player;
		
        buff(Player player) {
            this.player = player;	
        }

        public void run() {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0), true);
        }
    }
}
