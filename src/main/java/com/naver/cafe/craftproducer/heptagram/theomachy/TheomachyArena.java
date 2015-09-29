package com.naver.cafe.craftproducer.heptagram.theomachy;

import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.minigamesapi.ArenaType;

public class TheomachyArena extends Arena {

	public TheomachyArena(JavaPlugin plugin, String name) {
		super(plugin, name, ArenaType.REGENERATION);
	}
	
}
