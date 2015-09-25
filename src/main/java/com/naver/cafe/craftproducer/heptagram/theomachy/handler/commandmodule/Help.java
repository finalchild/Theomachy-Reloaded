package com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;


public class Help {

    public static void Module(CommandSender sender, Command command, String label, String[] data) {
        String playerName = sender.getName();
        Ability ability = GameData.PlayerAbility.get(playerName);

        if (ability != null) {
            ability.description();
        } else {
            sender.sendMessage("능력이 없습니다.");
        }
    }

}
