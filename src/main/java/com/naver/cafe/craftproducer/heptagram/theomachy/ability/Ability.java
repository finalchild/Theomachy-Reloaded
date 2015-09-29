package com.naver.cafe.craftproducer.heptagram.theomachy.ability;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Ability {
	
    public final String playerName;
    public final String abilityName;
    public final int abilityCode;
    public final boolean activeType;
    public final boolean passiveType;
    public final boolean buffType;
    public boolean flag = false;
	
    public Ability(String playerName, String abilityName, int abilityCode, boolean activeType, boolean passiveType, boolean buffType) {
        this.playerName = playerName;
        this.abilityName = abilityName;
        this.abilityCode = abilityCode;
        this.activeType = activeType;
        this.passiveType = passiveType;
        this.buffType = buffType;
    }
	
    public void description() {}
	
    public void onPlayerInteract(PlayerInteractEvent event) {}
	
    public void onBlockBreak(BlockBreakEvent event) {}
	
    public void onPlayerDeath(PlayerDeathEvent event) {}
	
    public void onFoodLevelChange(FoodLevelChangeEvent event) {}
	
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {}
	
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {}
	
    public void onEntityDamage(EntityDamageEvent event) {}
	
    public void onSignChange(SignChangeEvent event) {}
	
    public void onBlockPlace(BlockPlaceEvent event) {}
	
    public void onPlayerRespawn(PlayerRespawnEvent event) {}
	
    public void onPlayerMove(PlayerMoveEvent event) {}

    public void onProjectileLaunch(ProjectileLaunchEvent event, Player player) {}

    public void conditionSet() {}
	
    public void conditionReset() {}
    
    public void buff() {}
    
    public void targetSet(CommandSender sender, String targetName) {
        sender.sendMessage("타겟을 사용하는 능력이 아닙니다.");
    }
    
}
