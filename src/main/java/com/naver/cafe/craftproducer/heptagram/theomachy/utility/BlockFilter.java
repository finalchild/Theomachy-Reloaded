package com.naver.cafe.craftproducer.heptagram.theomachy.utility;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;

public class BlockFilter {
    public static boolean AirToFar(Player player, Block block) {
        if (!block.isEmpty()) {
            return true;
        } else {
            T_Message.onTooFar(player, 1);
            return false;
        }
    }
}
