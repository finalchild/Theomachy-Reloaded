package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;

public class PlayerList {
    public static List<Player> getTeammates(Player player) {
        String teamName = GameData.TeamMembers.get(player.getName());
        ArrayList<String> memberName = new ArrayList<String>();

        if (GameData.TeamMembers.containsValue(teamName)) {
            Iterator<Entry<String, String>> iterator = GameData.TeamMembers.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();

                if (entry.getValue().equals(teamName)) {
                    memberName.add(entry.getKey());
                }
            }
        }
        ArrayList<Player> memberPlayer = new ArrayList<Player>();

        for (String e : memberName) {
            if (GameData.onlinePlayers.containsKey(e)) {
                memberPlayer.add(GameData.onlinePlayers.get(e));
            }
        }
        return memberPlayer;
    }
	
    public static List<Player> getNearbyTeammates(Player player, double x, double y, double z) {
        String playerName = player.getName();
        String playerTeamName = GameData.TeamMembers.get(playerName);

        ArrayList<Player> nearbyTeammates = new ArrayList<Player>();

        if (playerTeamName != null) {
            List<Entity> nearbyEntities = player.getNearbyEntities(x, y, z);

            if (!nearbyEntities.isEmpty()) {
                for (Entity e : nearbyEntities) {
                    if (e instanceof Player) {
                        String memberName = ((Player) e).getName();
                        String memberTeamName = GameData.TeamMembers.get(memberName);

                        if (memberTeamName.equals(playerTeamName)) {
                            nearbyTeammates.add((Player) e);
                        }
                    }
                }
            }
        }
        return nearbyTeammates;
    }
	
    public static List<Player> getNearbyEnemies(Player player, double x, double y, double z) {
        String playerName = player.getName();
        String playerTeamName = GameData.TeamMembers.get(playerName);

        ArrayList<Player> nearbyEnemies = new ArrayList<Player>();
        List<Entity> nearbyEntities = player.getNearbyEntities(x, y, z);

        if (playerTeamName != null) {
            if (!nearbyEntities.isEmpty()) {
                for (Entity e : nearbyEntities) {
                    if (e instanceof Player) {
                        String memberName = ((Player) e).getName();
                        String memberTeamName = GameData.TeamMembers.get(memberName);

                        if (!memberTeamName.equals(playerTeamName)) {
                            nearbyEntities.add((Player) e);
                        }
                    }
                }
            }
        } else {
            if (!nearbyEntities.isEmpty()) {
                for (Entity e : nearbyEntities) {
                    if (e instanceof Player) {
                        nearbyEnemies.add((Player) e);
                    }
                }
            }
        }
        return nearbyEnemies;
    }
}
