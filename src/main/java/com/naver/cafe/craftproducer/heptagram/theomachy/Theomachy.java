package com.naver.cafe.craftproducer.heptagram.theomachy;


import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.naver.cafe.craftproducer.heptagram.theomachy.db.AbilityData;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.PluginData;
import com.naver.cafe.craftproducer.heptagram.theomachy.manager.CommandManager;
import com.naver.cafe.craftproducer.heptagram.theomachy.manager.EventManager;


public class Theomachy extends JavaPlugin {
    public static boolean INVENTORY_CLEAR = true;
    public static boolean GIVE_ITEM = true;
    public static boolean IGNORE_BED = true;
    public static boolean ENTITIES_REMOVE = true;
    public static boolean AUTO_SAVE = false;
    public static boolean ANIMAL = true;
    public static boolean MONSTER = true;
    public static int DIFFICULTY = 1;
	
    public CommandManager cm;
	
    public void onEnable() {
        getLogger().info(
                "플러그인 활성화  "
                        + PluginData.buildnumber + "  " + PluginData.version);
        getLogger().info("등록된 능력");
        getLogger().info(
                "신: " + AbilityData.GOD_ABILITY_NUMBER
                + ", 인간: " + AbilityData.HUMAN_ABILITY_NUMBER);
        getLogger().info("총합: " + AbilityData.ABILITY_NUMBER);
        getLogger().info("플러그인 스크립트 적용중");
        cm = new CommandManager(this);
        ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.BLAZE_ROD)).shape(new String[] { "|", "|", "|"}).setIngredient(
                '|', Material.STICK);

        getServer().addRecipe(recipe);
        getServer().getPluginManager().registerEvents(new EventManager(), this);
		
        getLogger().info("게임의 설정 불러오는중");
        getConfig().options().copyDefaults(true);
        saveConfig();
        INVENTORY_CLEAR = getConfig().getBoolean("Inventory Clear");
        GIVE_ITEM = getConfig().getBoolean("Give SkyBlock Item");
        ENTITIES_REMOVE = getConfig().getBoolean("Remove Entities");
        IGNORE_BED = getConfig().getBoolean("Ignore Bed");
        AUTO_SAVE = getConfig().getBoolean("Auto Save");
        ANIMAL = getConfig().getBoolean("Spawn Animal");
        MONSTER = getConfig().getBoolean("Spawn Monster");
        DIFFICULTY = getConfig().getInt("Difficulty");
		
        getLogger().info("---------------------------------------");
        getLogger().info(
                "게임 시작시 인벤토리 클리어 : "
                        + String.valueOf(INVENTORY_CLEAR));
        getLogger().info(
                "게임 시작시 스카이블럭 기본 아이템 지급 : "
                        + String.valueOf(GIVE_ITEM));
        getLogger().info(
                "게임 시작시 몬스터,동물,아이템삭제 : "
                        + String.valueOf(ENTITIES_REMOVE));
        getLogger().info(
                "리스폰시 침대 무시 : "
                        + String.valueOf(IGNORE_BED));
        getLogger().info(
                "서버 자동저장 : "
                        + String.valueOf(AUTO_SAVE));
        getLogger().info("동물 스폰 : " + String.valueOf(ANIMAL));
        getLogger().info(
                "몬스터 스폰 : "
                        + String.valueOf(MONSTER));
        getLogger().info("난이도 : " + String.valueOf(DIFFICULTY));
        getLogger().info("---------------------------------------");
    }
}
