package com.naver.cafe.craftproducer.heptagram.theomachy;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.naver.cafe.craftproducer.heptagram.theomachy.commands.CommandTarget;
import com.naver.cafe.craftproducer.heptagram.theomachy.commands.CommandTheomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.AbilityData;
import com.naver.cafe.craftproducer.heptagram.theomachy.manager.EventManager;

public class Theomachy extends JavaPlugin {
    public static boolean inventoryClear = true;
    public static boolean giveItem = true;
    public static boolean ignoreBed = true;
    public static boolean entitiesRemove = true;
    public static boolean autoSave = false;
    public static boolean animal = true;
    public static boolean monster = true;
    public static int difficulty = 1;
	
    private static Theomachy plugin;
    public static Logger log;
	
    public void onEnable() {
    	plugin = this;
        log = getLogger();
        getLogger().info("플러그인 활성화  " + this.getDescription().getVersion());
        getLogger().info("등록된 능력");
        getLogger().info("신: " + AbilityData.GOD_ABILITY_NUMBER + ", 인간: " + AbilityData.HUMAN_ABILITY_NUMBER);
        getLogger().info("총합: " + AbilityData.ABILITY_NUMBER);
        getLogger().info("플러그인 스크립트 적용중");
        getCommand("theomachy").setExecutor(new CommandTheomachy());
        getCommand("t").setExecutor(new CommandTheomachy());
        getCommand("x").setExecutor(new CommandTarget());
        
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.BLAZE_ROD)).shape(new String[] { "|", "|", "|"}).setIngredient('|', Material.STICK);
        getServer().addRecipe(recipe);
        getServer().getPluginManager().registerEvents(new EventManager(), this);
		
        getLogger().info("게임의 설정 불러오는 중");
        getConfig().options().copyDefaults(true);
        saveConfig();
        inventoryClear = getConfig().getBoolean("Inventory Clear");
        giveItem = getConfig().getBoolean("Give SkyBlock Item");
        entitiesRemove = getConfig().getBoolean("Remove Entities");
        ignoreBed = getConfig().getBoolean("Ignore Bed");
        autoSave = getConfig().getBoolean("Auto Save");
        animal = getConfig().getBoolean("CommandSpawn Animal");
        monster = getConfig().getBoolean("CommandSpawn Monster");
        difficulty = getConfig().getInt("Difficulty");
		
        getLogger().info("---------------------------------------");
        getLogger().info("게임 시작 시 인벤토리 클리어 : " + String.valueOf(inventoryClear));
        getLogger().info("게임 시작 시 스카이블록 기본 아이템 지급 : " + String.valueOf(giveItem));
        getLogger().info("게임 시작 시 몬스터,동물,아이템 삭제 : " + String.valueOf(entitiesRemove));
        getLogger().info("리스폰 시 침대 무시 : " + String.valueOf(ignoreBed));
        getLogger().info("서버 자동 저장 : " + String.valueOf(autoSave));
        getLogger().info("동물 스폰 : " + String.valueOf(animal));
        getLogger().info("몬스터 스폰 : " + String.valueOf(monster));
        getLogger().info("난이도 : " + String.valueOf(difficulty));
        getLogger().info("---------------------------------------");
    }
    
    public static Theomachy getPlugin() {
    	return plugin;
    }
}
