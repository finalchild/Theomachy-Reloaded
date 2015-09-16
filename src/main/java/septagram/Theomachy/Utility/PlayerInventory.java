package septagram.Theomachy.Utility;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Message.T_Message;

public class PlayerInventory
{
	public static boolean InHandItemCheck(Player player, int material)
	{
		if (player.getItemInHand().getTypeId() == material)
			return true;
		else
			return false;
	}
	
	public static boolean ItemCheck(Player player, int material, int stack)
	{
		Inventory inventory = player.getInventory();
		if (inventory.contains(material, stack))
			return true;
		else
		{
			T_Message.LackItemError(player, material, stack);
			return false;
		}
	}
	
	public static void ItemRemove(Player player, int material, int stack)
	{
		Inventory inventory = player.getInventory();
		inventory.removeItem(new ItemStack(material, stack));
	}
	
	public static void skyBlockBasicItemAdd(Player player)
	{
		Inventory inventory = player.getInventory();
		if (Theomachy.INVENTORY_CLEAR)
		{
			inventory.clear();
			player.getInventory().setHelmet(new ItemStack(0));
			player.getInventory().setChestplate(new ItemStack(0));
			player.getInventory().setLeggings(new ItemStack(0));
			player.getInventory().setBoots(new ItemStack(0));
		}
		if (Theomachy.GIVE_ITEM)
		{
			inventory.addItem(new ItemStack(54,1));
			inventory.addItem(new ItemStack(327,1));
			inventory.addItem(new ItemStack(327,1));
			inventory.addItem(new ItemStack(79,2));
			inventory.addItem(new ItemStack(6,1));
			inventory.addItem(new ItemStack(295,8));
			inventory.addItem(new ItemStack(351,1,(short)15));
		}
	}
}

