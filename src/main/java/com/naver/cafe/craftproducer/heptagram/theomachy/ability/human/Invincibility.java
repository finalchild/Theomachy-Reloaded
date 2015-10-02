package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Invincibility extends Ability {
    private final int coolTime1 = 80;
    private final int coolTime2 = 120;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 1;
    private final int stack2 = 5;
	
    public Invincibility(String playerName) {
        super(playerName, "Invincibility", 111, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 무적 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active, Buff  " + ChatColor.GREEN + "Rank[ A ]");
        player.sendMessage("일정시간 데미지를 받지 않을 수 있는 능력입니다.\n" + "블레이즈로드를 이용해 능력을 사용할 수 있습니다.\n" + "좌클릭을 이용해 일정시간 자신을 무적 상태로 만듭니다.\n" + "우클릭은 자신에게 체력회복 버프를 시전합니다.\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
    }
	
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.checkInHandItem(player, Material.BLAZE_ROD)) {
            switch (EventFilter.sortInteraction(event)) {
            case 0:
            case 1:
                leftAction(player);
                break;

            case 2:
            case 3:
                rightAction(player);
                break;
            }
        }
    }

    private void leftAction(Player player) {
        if (CoolTimeChecker.check(player, 1) && PlayerInventory.checkItem(player, material, stack1)) {
            Skill.use(player, material, stack1, 1, coolTime1); {
                CoolTimer.cool0.put(playerName + "1", 7);
            }
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.check(player, 2) && PlayerInventory.checkItem(player, material, stack2)) {
            Skill.use(player, material, stack2, 2, coolTime2);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 0), true);
        }
    }
	
    public void onEntityDamage(EntityDamageEvent event) {
        if (CoolTimer.cool0.containsKey(playerName + "1")) {
            event.setCancelled(true);
            event.getEntity().setFireTicks(0);
        }
    }
}
