package com.naver.cafe.craftproducer.heptagram.theomachy.manager;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.commands.GameHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;

public class EventManager implements Listener {
	
    public static ArrayList<Ability> playerDeathEventList = new ArrayList<Ability>();
    
    @EventHandler
    public static void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();

            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                Ability ability = GameData.playerAbilities.get(player.getName());

                if (ability != null && ability.abilityCode == 118) {
                    ability.onProjectileLaunch(event, player);
                }
            }
        }
    }
	
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event) {
        if (GameHandler.start) {
            String playerName = event.getPlayer().getName();
            Ability ability = GameData.playerAbilities.get(playerName);

            if (ability != null && ability.activeType) {
                ability.onPlayerInteract(event);
            }
        }
    }
	
    @EventHandler
    public static void onEntityDamage(EntityDamageEvent event) {
        if (GameHandler.start) {
            if (event.getEntity() instanceof Player) {
                String playerName = ((Player) event.getEntity()).getName();

                if (GameData.playerAbilities.containsKey(playerName)) {
                    GameData.playerAbilities.get(playerName).onEntityDamage(event);
                }
            }
            if (event.getCause() == DamageCause.LIGHTNING && event.getEntity() instanceof LivingEntity) {
                LivingEntity le = (LivingEntity) event.getEntity();

                le.setNoDamageTicks(0);
            }
        }
    }
	
    @EventHandler
    public static void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        try {
            if (GameHandler.start) {
                if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
                    String key1 = ((Player) event.getEntity()).getName();
                    String key2 = ((Player) event.getDamager()).getName();
                    Ability ability1 = GameData.playerAbilities.get(key1);
                    Ability ability2 = GameData.playerAbilities.get(key2);

                    if (ability1 != null) {
                        ability1.onEntityDamageByEntity(event);
                    }
                    if (ability2 != null) {
                        ability2.onEntityDamageByEntity(event);
                    }
                } else if (event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
                    Arrow arrow = (Arrow) event.getDamager();

                    if (arrow.getShooter() instanceof Player) {
                        Player player = (Player) arrow.getShooter();
                        String key = player.getName();
                        Ability ability = GameData.playerAbilities.get(key);

                        if (ability != null && ability.abilityCode == 7 || ability.abilityCode == 101) {
                            ability.onEntityDamageByEntity(event);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Theomachy.log.info("onEntityDamageByEntity Error\n" + e.getLocalizedMessage());
        }
    }
    
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        if (GameHandler.start) {
            for (Ability e : playerDeathEventList) {
                e.onPlayerDeath(event);
            }
            Player player = event.getEntity();
            Ability ability = GameData.playerAbilities.get(player.getName());

            if (ability != null) {
                if (ability.abilityCode == 106 || ability.abilityCode == 3) {
                    ability.onPlayerDeath(event);
                }
            }
					
        }
    }

    @EventHandler
    public static void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (GameHandler.start) {
            if (event.getEntity() instanceof Player) {
                String playerName = ((Player) event.getEntity()).getName();

                if (GameData.playerAbilities.containsKey(playerName)) {
                    GameData.playerAbilities.get(playerName).onFoodLevelChange(event);
                }
            }
        }
    }	

    @EventHandler
    public static void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (GameHandler.start) {
            if (event.getEntity() instanceof Player) {
                String playerName = ((Player) event.getEntity()).getName();

                if (GameData.playerAbilities.containsKey(playerName)) {
                    GameData.playerAbilities.get(playerName).onEntityRegainHealth(event);
                }
            }
        }
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        if (GameHandler.start) {
            String playerName = event.getPlayer().getName();
            Ability ability = GameData.playerAbilities.get(playerName);

            if (ability != null) {
                ability.onBlockBreak(event);
            }
        }
    }	

    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event) {
        if (GameHandler.start) {
            Player player = event.getPlayer();

            if (Theomachy.ignoreBed) {
                if (GameData.TeamMembers.containsKey(player.getName())) {
                    String teamName = GameData.TeamMembers.get(player.getName());
                    Location respawnLocation = GameData.spawnAreas.get(teamName);

                    if (respawnLocation != null) {
                        event.setRespawnLocation(respawnLocation);
                    }
                }
            } else {
                if (!event.isBedSpawn() && GameData.TeamMembers.containsKey(player.getName())) {
                    String teamName = GameData.TeamMembers.get(player.getName());
                    Location respawnLocation = GameData.spawnAreas.get(teamName);

                    if (respawnLocation != null) {
                        event.setRespawnLocation(respawnLocation);
                    }
                }
            }
            Ability ability = GameData.playerAbilities.get(player.getName());

            if (ability != null) {
                if (ability.buffType) {
                    ability.buff();
                }
                if (ability.abilityCode == 3) {
                    ability.onPlayerRespawn(event);
                }
            }
			
            /* if (!Theomachy.IGNORE_BED )
             {
             Location bedSpawnLocation = player.getBedSpawnLocation();
             if (bedSpawnLocation == null)
             {
             if (GameData.PlayerTeam.containsKey(player.getName()))
             {
             String teamName=GameData.PlayerTeam.get(player.getName());
             Location respawnLocation = GameData.SpawnArea.get(teamName);
             if (respawnLocation != null)
             event.setRespawnLocation(respawnLocation);
             }
             }	
             }
             else
             {
             
             if (GameData.PlayerTeam.containsKey(player.getName()))
             {
             String teamName=GameData.PlayerTeam.get(player.getName());
             Location respawnLocation = GameData.SpawnArea.get(teamName);
             if (respawnLocation != null)
             event.setRespawnLocation(respawnLocation);
             }
             }
             */
        }
    }
	
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (GameHandler.start) {
            Ability ability = GameData.playerAbilities.get(event.getPlayer().getName());

            if (ability != null && ability.abilityCode == 119) {
                ability.onSignChange(event);
            }
        }
    }
	
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (GameHandler.start) {
            Ability ability = GameData.playerAbilities.get(event.getPlayer().getName());

            if (ability != null && ability.abilityCode == 119) {
                ability.onBlockPlace(event);
            }
        }
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        GameData.onlinePlayers.put(player.getName(), player);
        if (GameHandler.start) {
            Ability ability = GameData.playerAbilities.get(player.getName());

            if (ability != null && (ability.abilityCode == 2 || ability.abilityCode == 9)) {
                ability.conditionSet();
            }
        }
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        GameData.onlinePlayers.remove(player.getName());
    }
	
    @EventHandler
    public static void onPlayerKick(PlayerKickEvent event) {
        Theomachy.log.info(event.getReason());
    }
	
    @EventHandler
    public static void onEntityExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if (GameHandler.start && entity != null && entity.getType() == EntityType.FIREBALL) {
            event.blockList().clear();
        }
    }

}
