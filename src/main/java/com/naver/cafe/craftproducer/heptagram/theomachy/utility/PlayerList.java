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
        String teamName = GameData.playerTeam.get(player.getName());
        ArrayList<String> memberName = new ArrayList<String>();

        if (GameData.playerTeam.containsValue(teamName)) {
            Iterator<Entry<String, String>> iterator = GameData.playerTeam.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();

                if (entry.getValue().equals(teamName)) {
                    memberName.add(entry.getKey());
                }
            }
        }
        ArrayList<Player> memberPlayer = new ArrayList<Player>();

        for (String e : memberName) {
            if (GameData.onlinePlayer.containsKey(e)) {
                memberPlayer.add(GameData.onlinePlayer.get(e));
            }
        }
        return memberPlayer;
    }
	
    public static List<Player> getNearbyTeammates(Player player, double x, double y, double z) {
        String playerName = player.getName();
        String playerTeamName = GameData.playerTeam.get(playerName);

        ArrayList<Player> nearByTeamMembers = new ArrayList<Player>();

        if (playerTeamName != null) {
            List<Entity> nearByEntityList = player.getNearbyEntities(x, y, z);

            if (!nearByEntityList.isEmpty()) {
                for (Entity e : nearByEntityList) {
                    if (e instanceof Player) {
                        String memberName = ((Player) e).getName();
                        String memberTeamName = GameData.playerTeam.get(memberName);

                        if (memberTeamName.equals(playerTeamName)) {
                            nearByTeamMembers.add((Player) e);
                        }
                    }
                }
            }
        }
        return nearByTeamMembers;
    }
	
    public static List<Player> getNearbyEnemies(Player player, double x, double y, double z) {
        String playerName = player.getName();
        String playerTeamName = GameData.playerTeam.get(playerName);

        ArrayList<Player> nearByNotTeamMembers = new ArrayList<Player>();
        List<Entity> nearByEntityList = player.getNearbyEntities(x, y, z);

        if (playerTeamName != null) {
            if (!nearByEntityList.isEmpty()) {
                for (Entity e : nearByEntityList) {
                    if (e instanceof Player) {
                        String memberName = ((Player) e).getName();
                        String memberTeamName = GameData.playerTeam.get(memberName);

                        if (!memberTeamName.equals(playerTeamName)) {
                            nearByNotTeamMembers.add((Player) e);
                        }
                    }
                }
            }
        } else {
            if (!nearByEntityList.isEmpty()) {
                for (Entity e : nearByEntityList) {
                    if (e instanceof Player) {
                        nearByNotTeamMembers.add((Player) e);
                    }
                }
            }
        }
        return nearByNotTeamMembers;
    }
}