package com.naver.cafe.craftproducer.heptagram.theomachy.ability.god;


import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;


public class Hades extends Ability {	
    private final int coolTime1 = 100;
    private final int coolTime2 = 300;
    private final int material = 4;
    private final int stack1 = 5;
    private final int stack2 = 10;
    public Hades(String playerName) {
        super(playerName, "Hades", 3, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 하데스 ]  " + ChatColor.RED
                + "[ 신 ]  " + ChatColor.BLUE + "Active,Passive  "
                + ChatColor.GREEN + "Rank[ S ]");
        player.sendMessage(
                "죽음의 신입니다.\n"
                        + "패시브 능력으로 사망시 80% 확률로 아이템을 잃지 않습니다.\n"
                        + "액티브 능력으로 상대를 나락으로 떨어뜨릴 수 있으며 블레이즈 로드를 들었을때 발동 시킬 수 있습니다.\n"
                        + "일반능력은 주변 2칸에 있는 자신을 포함한 모든 플레이어,몬스터와 함께 나락으로 떨어지며\n"
                        + "고급능력은 주변 4칸에 있는 자신을 제외한 모든 플레이어,몬스터를 나락으로 떨어뜨립니다.\n"
                        + ChatColor.AQUA + "일반(좌클릭) "
                        + ChatColor.WHITE + " 코블스톤 " + stack1
                        + "개 소모, 쿨타임 " + coolTime1 + "초\n"
                        + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE
                        + " 코블스톤 " + stack2 + "개 소모, 쿨타임 "
                        + coolTime2 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, 369)) {
            switch (EventFilter.PlayerInteract(event)) {
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
        if (CoolTimeChecker.Check(player, 1)
                && PlayerInventory.ItemCheck(player, material, stack1)) {
            Skill.Use(player, material, stack1, 1, coolTime1);
            Entity entity = player;
            Location location = player.getLocation();

            location.setY(-2.0d);
            List<Entity> entitylist = entity.getNearbyEntities(2, 2, 2);

            for (Entity e : entitylist) {
                if (e instanceof LivingEntity) {
                    e.teleport(location);
                    if (e.getType() == EntityType.PLAYER) {
                        ((Player) e).sendMessage(
                                "죽음의 신의 능력에 의해 나락으로 떨어집니다.");
                    }
                }
            }
            player.teleport(location);
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2)
                && PlayerInventory.ItemCheck(player, material, stack2)) {
            Skill.Use(player, material, stack2, 2, coolTime2);
            Entity entity = player;
            Location location = player.getLocation();

            location.setY(-2.0d);
            List<Entity> entitylist = entity.getNearbyEntities(4, 4, 4);

            for (Entity e : entitylist) {
                if (e instanceof LivingEntity) {
                    e.teleport(location);
                    if (e.getType() == EntityType.PLAYER) {
                        ((Player) e).sendMessage(
                                ChatColor.RED
                                        + "죽음의 신의 능력에 의해 나락으로 떨어집니다.");
                    }
                }
            }
        }
    }
	
    private ItemStack[] inventory;
    private ItemStack[] armor;
    public void T_Passive(PlayerDeathEvent event) {
        int rn = (int) (Math.random() * 5);

        if (rn != 0) {
            this.inventory = event.getEntity().getInventory().getContents();
            this.armor = event.getEntity().getInventory().getArmorContents();
            event.getDrops().clear();
        } else {
            event.getEntity().sendMessage("아이템을 모두 잃었습니다.");
        }
    }

    public void T_Passive(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (inventory != null) {
            player.getInventory().setContents(inventory);
        }
        if (armor != null) {
            player.getInventory().setArmorContents(armor);
        }
        inventory = null;
        armor = null;
    }
}

