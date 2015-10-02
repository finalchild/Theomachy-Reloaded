package com.naver.cafe.craftproducer.heptagram.theomachy.db;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;

public class GameData {
    public static HashMap<String, Player> onlinePlayers = new HashMap<String, Player>(); // 온라인플레이어
    public static HashMap<String, Ability> playerAbilities = new HashMap<String, Ability>(); // 플레이어 지정 능력
    public static HashMap<String, String> TeamMembers = new HashMap<String, String>(); // 플레이어, 팀이름
    public static HashMap<String, Location> spawnAreas = new HashMap<String, Location>(); // 팀 스폰 지역
}
