package com.naver.cafe.craftproducer.heptagram.theomachy.ability.etc;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.EventFilter;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;

// TODO Reload this class
public class Septagram extends Ability {	
    public Septagram(String playerName) {
        super(playerName, "제작자", -1, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
    public void description() {
        Player player = GameData.onlinePlayers.get(playerName);

        player.sendMessage(ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW + "능력 정보" + ChatColor.DARK_GREEN + " ===================");
        player.sendMessage(ChatColor.YELLOW + "[ 칠각별 ]  " + ChatColor.RED + "[ 제작자 ]  " + ChatColor.BLUE + "Active,Passive  " + ChatColor.GREEN + "Rank[ F ]");
        player.sendMessage("제작자입니다.\n");
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
        Location location = player.getTargetBlock((Set<Material>) null, 40).getLocation();
        Vector v = new Vector(0, -10, 0);
        Fireball fireball = (Fireball) player.getWorld().spawn(location.add(0, 70, 0), Fireball.class);

        fireball.setDirection(v);
        fireball.setShooter(player);
		
    }
	
}
