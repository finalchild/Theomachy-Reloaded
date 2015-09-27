package com.naver.cafe.craftproducer.heptagram.theomachy.db;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;

public interface ServerSetting {
    public boolean pvp = Bukkit.getWorlds().get(0).getPVP();
    public boolean autoSave = Bukkit.getWorlds().get(0).isAutoSave();
    public boolean animal = Bukkit.getWorlds().get(0).getAllowAnimals();
    public boolean monster = Bukkit.getWorlds().get(0).getAllowMonsters();
    public Difficulty difficulty = Bukkit.getWorlds().get(0).getDifficulty();
}
