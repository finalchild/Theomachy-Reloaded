package com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule;

import java.util.Collection;
import java.util.List;
import java.util.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.ServerSetting;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.CoolTime;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.GameReadyTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.timer.TipTimer;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PermissionChecker;

public class GameHandler {
    public static boolean ready = false;
    public static boolean start = false;
	
    public static void startGame(CommandSender sender) {
        if (PermissionChecker.checkSender(sender)) {
            if (!ready) {
                ready = true;
                Bukkit.broadcastMessage(ChatColor.GOLD + "관리자(" + sender.getName() + ")가 게임을 시작하였습니다.");
                Timer t = new Timer();

                t.schedule(new GameReadyTimer(), 0, 1000);
                t.schedule(new TipTimer(), 0, 1000);
                t.schedule(new CoolTime(), 0, 1000);
            } else {
                sender.sendMessage("게임이 이미 시작되었습니다.");
            }
        }
    }
	
    public static void stopGame(CommandSender sender) {
        if (PermissionChecker.checkSender(sender)) {
            if (ready) {
                Collection<Ability> playerAbilityList = GameData.playerAbility.values();

                for (Ability e : playerAbilityList) {
                    e.conditionReset();
                }
                ready = false;
                start = false;
                CoolTime.ini = true;
                List<World> worlds = Bukkit.getWorlds();

                for (World world : worlds) {
                    world.setPVP(ServerSetting.pvp);
                    world.setSpawnFlags(ServerSetting.monster, ServerSetting.animal);
                    world.setAutoSave(ServerSetting.autoSave);
                    world.setDifficulty(ServerSetting.difficulty);
                }
                Bukkit.broadcastMessage(ChatColor.RED + "관리자(" + sender.getName() + ") 가 게임을 종료하였습니다.");
            } else {
                sender.sendMessage("게임이 시작되지 않았습니다.");
            }
        }
    }
	
}
