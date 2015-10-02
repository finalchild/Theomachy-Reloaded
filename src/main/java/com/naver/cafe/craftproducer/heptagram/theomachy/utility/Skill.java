package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTimer;

public class Skill {
	
    public static void use(Player player, Material material, int stack, int abilityCase, int coolTime) {
        player.getInventory().removeItem(new ItemStack(material, stack));
        if (coolTime > 0) {
            switch (abilityCase) {
            case 0:
                CoolTimer.cool0.put(player.getName(), coolTime);
                break;

            case 1:
                CoolTimer.cool1.put(player.getName(), coolTime);
                break;

            case 2:
                CoolTimer.cool2.put(player.getName(), coolTime);
                break;
            }
        }
        T_Message.onSkillUsed(player, abilityCase);
    }
}
