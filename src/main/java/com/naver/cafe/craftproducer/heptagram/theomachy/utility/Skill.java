package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTime;

public class Skill {
	
    public static void use(Player player, Material material, int stack, int abilityCase, int coolTime) {
        player.getInventory().removeItem(new ItemStack(material, stack));
        if (coolTime > 0) {
            switch (abilityCase) {
            case 0:
                CoolTime.cool0.put(player.getName(), coolTime);
                break;

            case 1:
                CoolTime.cool1.put(player.getName(), coolTime);
                break;

            case 2:
                CoolTime.cool2.put(player.getName(), coolTime);
                break;
            }
        }
        T_Message.onSkillUsed(player, abilityCase);
    }
}
