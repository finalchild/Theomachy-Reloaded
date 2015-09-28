package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;

public class PlayerInventory {
	
    public static boolean checkInHandItem(Player player, Material material) {
        if (player.getItemInHand().getType() == material) {
            return true;
        } else {
            return false;
        }
    }
	
    public static boolean checkItem(Player player, Material material, int stack) {
        Inventory inventory = player.getInventory();

        if (inventory.contains(material, stack)) {
            return true;
        } else {
            T_Message.onItemLack(player, material, stack);
            return false;
        }
    }
	
    public static void removeItem(Player player, Material material, int stack) {
        Inventory inventory = player.getInventory();

        inventory.removeItem(new ItemStack(material, stack));
    }
	
    public static void addSkyblockBasicItems(Player player) {
        Inventory inventory = player.getInventory();

        if (Theomachy.inventoryClear) {
            inventory.clear();
            player.getInventory().setHelmet(new ItemStack(Material.AIR));
            player.getInventory().setChestplate(new ItemStack(Material.AIR));
            player.getInventory().setLeggings(new ItemStack(Material.AIR));
            player.getInventory().setBoots(new ItemStack(Material.AIR));
        }
        if (Theomachy.giveItem) {
            inventory.addItem(new ItemStack(Material.CHEST));
            inventory.addItem(new ItemStack(Material.LAVA_BUCKET));
            inventory.addItem(new ItemStack(Material.LAVA_BUCKET));
            inventory.addItem(new ItemStack(Material.ICE, 2));
            inventory.addItem(new ItemStack(Material.SAPLING));
            inventory.addItem(new ItemStack(Material.SEEDS, 8));
            inventory.addItem(new ItemStack(Material.INK_SACK, 1, (short) 15));
        }
    }
}
