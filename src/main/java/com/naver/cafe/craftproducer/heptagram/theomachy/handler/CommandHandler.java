package com.naver.cafe.craftproducer.heptagram.theomachy.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.AbilityInfo;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.AbilitySet;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.CoolTimeHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.GameHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.Help;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.Spawn;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.Team;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.TeamInfo;

public class CommandHandler {
    public static void handleT(CommandSender sender, Command command, String label, String[] data) {
        if (data[0].equals("start")) {
            GameHandler.startGame(sender);
        } else if (data[0].equals("stop")) {
            GameHandler.stopGame(sender);
        } else if (data[0].equals("ability") || data[0].equals("a")) {
            AbilitySet.onCommand(sender, command, label, data);
        } else if (data[0].equals("aaaaa")) {
            AbilityInfo.showAllAbilities(sender);
        } else if (data[0].equals("help")) {
            Help.onCommand(sender, command, label, data);
        } else if (data[0].equals("spawn") || data[0].equals("s")) {
            Spawn.onCommand(sender, command, label, data);
        } else if (data[0].equals("team") || data[0].equals("t")) {
            Team.onCommand(sender, command, label, data);
        } else if (data[0].equals("info") || data[0].equals("i")) {
            TeamInfo.onCommand(sender, command, label, data);
        } else if (data[0].equals("clear") || data[0].equals("c")) {
            CoolTimeHandler.onCommand(sender, command, label, data);
        } else {
            sender.sendMessage("잘못된 명령입니다.");
        }
    }
	
    public static void handleX(CommandSender sender, Command command, String label, String[] data) {
        String playerName = sender.getName();
        String targetName = data[0];
        Ability ability = GameData.playerAbility.get(playerName);

        if (ability != null) {
            if (GameData.onlinePlayer.containsKey(targetName)) {
                ability.targetSet(sender, targetName);
            } else {
                sender.sendMessage("온라인 플레이어가 아닙니다.  " + targetName);
            }
        } else {
            sender.sendMessage("능력이 없습니다.");
        }
    }
}
