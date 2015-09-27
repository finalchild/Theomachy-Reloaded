package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.skill.SnipingDuration;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Sniper extends Ability {
    private final int coolTime0 = 70;
    private final Material material = Material.COBBLESTONE;
    private final int stack0 = 1;
    public boolean ready = false;
    public boolean sniping = false;
	
    public Sniper(String playerName) {
        super(playerName, "Sniper", 118, true, false, false);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 저격수 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage("빠른 화살을 이용해 상대방을 공격하는 능력입니다.\n" + "게임 시작시 활1개 화살 20개를 지급합니다. \n" + "활을 들고 앉은채(shift) 좌클릭하면 4초뒤 스나이핑 모드가 활성화됩니다.\n" + "스나이핑 모드일시 쏜 화살이 타겟방향으로 보이지 않는속도로 날아가며 맞은 적은 약 100~200의 데미지를 입습니다.\n" + ChatColor.AQUA + "스나이핑모드일때 화살한발 " + ChatColor.WHITE + " 코블스톤 " + stack0 + "개 소모, 쿨타임 " + coolTime0 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, Material.BOW)) {
            switch (EventFilter.PlayerInteract(event)) {
            case 0:
            case 1:
                leftAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        if (player.isSneaking() && !ready) {
            ready = true;
            Timer t = new Timer();

            t.schedule(new SnipingDuration(player, this), 0, 1000);
        }
    }
	
    @Override
    public void T_Passive(ProjectileLaunchEvent event, Player player) {
        if (this.sniping && (CoolTimeChecker.Check(player, 0) && PlayerInventory.ItemCheck(player, material, stack0))) {
            Entity entity = event.getEntity();

            if (entity instanceof Arrow) {
                Skill.Use(player, material, stack0, 0, coolTime0);
                entity.remove();
                @SuppressWarnings("deprecation")
                Arrow arrow = player.shootArrow();

                arrow.setVelocity(player.getEyeLocation().getDirection().multiply(100));
            }
        }
    }
	
    @Override
    public void conditionSet() {
        Player player = GameData.onlinePlayer.get(this.playerName);

        player.getInventory().addItem(new ItemStack(Material.BOW, 1));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 10));
    }
}
