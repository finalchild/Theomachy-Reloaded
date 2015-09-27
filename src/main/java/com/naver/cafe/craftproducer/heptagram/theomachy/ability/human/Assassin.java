package com.naver.cafe.craftproducer.heptagram.theomachy.ability.human;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTime;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CoolTimeChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.DirectionChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.Skill;

public class Assassin extends Ability {
    private final int coolTime1 = 1;
    private final int coolTime2 = 5;
    private final Material material = Material.COBBLESTONE;
    private final int stack1 = 0;
    private final int stack2 = 0;
	
    public Assassin(String playerName) {
        super(playerName, "Assassin", 108, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 암살자 ]  " + ChatColor.RED + "[ 인간 ]  " + ChatColor.BLUE + "Active  " + ChatColor.GREEN + "Rank[ B ]");
        player.sendMessage("민첩한 몸놀림을 가지고있는 능력입니다.\n" + "점프한 후 능력을 사용하면 현재 보는 방향으로 점프를 한번 더 할 수 있습니다.\n" + "좌클릭으로 해당방향으로 점프를 합니다.\n" + "우클릭으로 주변에 있는 적의 등으로 순간이동 합니다.\n" + ChatColor.AQUA + "일반(좌클릭) " + ChatColor.WHITE + " 코블스톤 " + stack1 + "개 소모, 쿨타임 " + coolTime1 + "초\n" + ChatColor.RED + "고급(우클릭) " + ChatColor.WHITE + " 코블스톤 " + stack2 + "개 소모, 쿨타임 " + coolTime2 + "초\n");
    }
	
    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (PlayerInventory.InHandItemCheck(player, Material.BLAZE_ROD)) {
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
        Location temp = player.getLocation();
        Block b = temp.add(0, -1, 0).getBlock();

        if ((b.isEmpty()) || (b.getType() == Material.SNOW) || (b.getType() == Material.STEP)) {	
            if ((!CoolTime.COOL0.containsKey(playerName + "0") && (PlayerInventory.ItemCheck(player, material, stack1)))) {
                CoolTime.COOL0.put(playerName + "0", coolTime1);
                PlayerInventory.ItemRemove(player, material, stack1);
                World world = player.getWorld();
                Location location = player.getLocation();
                Vector v = player.getEyeLocation().getDirection();

                v.setY(0.5);			
                player.setVelocity(v);
                world.playEffect(location, Effect.ENDER_SIGNAL, 1);
            }
        }
    }
	
    private void rightAction(Player player) {
        if (CoolTimeChecker.Check(player, 2) && PlayerInventory.ItemCheck(player, material, stack2)) {
            boolean flag = true;
            List<Entity> entityList = player.getNearbyEntities(10, 10, 10);

            for (Entity e : entityList) {
                if (e instanceof Player) {
                    Player target = (Player) e;
					
                    String targetTeamName = GameData.PlayerTeam.get(target.getName());
                    String playerTeamName = GameData.PlayerTeam.get(player.getName());

                    if ((targetTeamName == null) || !(targetTeamName.equals(playerTeamName))) {
                        Skill.Use(player, material, stack2, 2, coolTime2);
                        Location fakeLocation = player.getLocation();
                        Location location = target.getLocation();
                        World world = player.getWorld();
                        List<Player> playerlist = world.getPlayers();

                        for (Player each : playerlist) {
                            each.hidePlayer(player);
                        }
                        switch (DirectionChecker.PlayerDirection(target)) {
                        case 0:
                            location.add(0, 0, -1);
                            break;

                        case 1:
                            location.add(0.7, 0, -0.7);
                            break;

                        case 2:
                            location.add(1, 0, 0);
                            break;

                        case 3:
                            location.add(0.7, 0, 0.7);
                            break;

                        case 4:
                            location.add(0, 0, 1);
                            break;

                        case 5:
                            location.add(-0.7, 0, 0.7);
                            break;

                        case 6:
                            location.add(-1, 0, 0);
                            break;

                        case 7:
                            location.add(-0.7, 0, -0.7);
                            break;
                        }
                        player.teleport(location);
                        world.dropItem(fakeLocation.add(0, 1, 0), new ItemStack(Material.RED_ROSE, 1));
                        for (Player each : playerlist) {
                            each.showPlayer(player);
                        }
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                player.sendMessage("스킬을 사용 할 수 있는 상대가 없습니다.");
            }
        }
    }
}
