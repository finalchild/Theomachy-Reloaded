package com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.etc.Septagram;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Apollon;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Ares;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Artemis;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Asclepius;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Athena;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Demeter;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Dionysus;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Hades;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Hephaestus;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Hermes;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Poseidon;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.god.Zeus;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Archer;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Assassin;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Blacksmith;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Blinder;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Bomber;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Boxer;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Cloaking;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Creeper;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Invincibility;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Meteor;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Miner;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Priest;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Reflection;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Sniper;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Stance;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Teleporter;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Voodoo;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Witch;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.human.Wizard;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.AbilityData;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.CodeHelper;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PermissionChecker;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.RandomNumberConstuctor;

public class AbilitySet {
    public static void onCommand(CommandSender sender, Command command, String label, String[] data) {
        if (PermissionChecker.Sender(sender)) {
            if (!GameHandler.ready) {
                if (data.length <= 1) {
                    sender.sendMessage("/t a help   모든 능력의 코드표를 확인합니다.");
                    sender.sendMessage("/t a random 현재 접속한 모든 플레이어에게 랜덤으로 능력을 할당합니다.");
                    sender.sendMessage("/t a remove <Player> 해당 플레이어의 능력을 삭제합니다.");
                    sender.sendMessage("/t a reset  모든 능력을 초기화 합니다");
                    sender.sendMessage("/t a <AbilityCode> <Player>  플레이어에게 해당 능력을 적용합니다.");
                } else if (data[1].equalsIgnoreCase("help")) {
                    CodeHelper.showAbilityCodeNumber(sender);
                } else if (data[1].equalsIgnoreCase("remove")) // 삭제
                {
                    if (data[2] != null) {
                        remove(sender, data[2]);
                    } else {
                        sender.sendMessage("능력을 삭제할 플레이어의 이름을 적어주세요.");
                    }
                } else if (data[1].equalsIgnoreCase("reset")) { // 리셋
                    reset();
                } else if (data[1].equalsIgnoreCase("random")) { // 랜덤
                    assignRandomly(sender);
                } else if (data.length >= 3) {
                    forceAssignment(sender, data);
                } else {
                    sender.sendMessage("잘못된 입력입니다.");
                    sender.sendMessage("/t a 로 명령어를 확인하세요.");
                }
            } else {
                sender.sendMessage("게임 시작 후에는 능력을 변경 할 수 없습니다.");
            }
        }
    }
	
    public static void remove(CommandSender sender, String playerName) {
        Ability ability = GameData.playerAbility.get(playerName);

        if (ability != null) {
            GameData.playerAbility.remove(playerName);
            sender.sendMessage("플레이어의 능력을 삭제하였습니다.");
        } else {
            sender.sendMessage("플레이어의 능력이 없습니다.");
        }
    }
	
    public static void reset() {
        GameData.playerAbility.clear();
        Bukkit.broadcastMessage(ChatColor.AQUA + "관리자가 모두의 능력을 초기화 하였습니다.");
    }
	
    private static void assignRandomly(CommandSender sender) {
        if (!GameData.playerAbility.isEmpty()) {
            Bukkit.broadcastMessage("모든 능력을 삭제 한후 재 추첨합니다.");
            GameData.playerAbility.clear();
        }
        Player[] playerlist = Bukkit.getOnlinePlayers().toArray(new Player[0]);

        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "인식된 플레이어 목록");
        for (Player e : playerlist) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "  " + e.getName());
        }
        int[] rn = RandomNumberConstuctor.nonDuplicate();
        int length = playerlist.length > AbilityData.ABILITY_NUMBER ? AbilityData.ABILITY_NUMBER : playerlist.length;

        for (int i = 0; i < length; i++) {
            String playerName = playerlist[i].getName();

            assignAbility(rn[i], playerName, sender);
        }
        Bukkit.broadcastMessage("모두에게 능력이 적용되었습니다.");
        Bukkit.broadcastMessage("/t help 로 확인해보세요.");
        if (playerlist.length > AbilityData.ABILITY_NUMBER) {
            Bukkit.broadcastMessage("인원이 너무 많습니다. 전부에게 능력을 할당 하지 못했습니다.");
        }
    }
	
    private static void forceAssignment(CommandSender sender, String[] data) {
        for (int i = 2; i < data.length; i++) {
            String abilityName = data[1];
            String playerName = data[i];

            if (GameData.onlinePlayer.containsKey(playerName)) {
                try {
                    int abilityCode = Integer.parseInt(abilityName);

                    assignAbility(abilityCode, playerName, sender);
                    Player player = GameData.onlinePlayer.get(playerName);

                    Bukkit.broadcastMessage("관리자가 " + ChatColor.RED + playerName + ChatColor.WHITE + " 에게 능력을 할당하였습니다.");
                    player.sendMessage("능력이 할당되었습니다. /t help로 능력을 확인해보세요.");
                } catch (NumberFormatException e) {
                    sender.sendMessage("능력코드는 정수를 입력해 주세요");
                }
            } else {
                sender.sendMessage(playerName + " 에 해당하는 온라인 유저가 없습니다.");
            }
        }
    }
	
    private static void assignAbility(int abilityCode, String playerName, CommandSender sender) {
        if (abilityCode == 1) {
            GameData.playerAbility.put(playerName, new Zeus(playerName));
        } else if (abilityCode == 2) {
            GameData.playerAbility.put(playerName, new Poseidon(playerName));
        } else if (abilityCode == 3) {
            GameData.playerAbility.put(playerName, new Hades(playerName));
        } else if (abilityCode == 4) {
            GameData.playerAbility.put(playerName, new Demeter(playerName));
        } else if (abilityCode == 5) {
            GameData.playerAbility.put(playerName, new Athena(playerName));
        } else if (abilityCode == 6) {
            GameData.playerAbility.put(playerName, new Apollon(playerName));
        } else if (abilityCode == 7) {
            GameData.playerAbility.put(playerName, new Artemis(playerName));
        } else if (abilityCode == 8) {
            GameData.playerAbility.put(playerName, new Ares(playerName));
        } else if (abilityCode == 9) {
            GameData.playerAbility.put(playerName, new Hephaestus(playerName));
        } else if (abilityCode == 10) {
            GameData.playerAbility.put(playerName, new Asclepius(playerName));
        } else if (abilityCode == 11) {
            GameData.playerAbility.put(playerName, new Hermes(playerName));
        } else if (abilityCode == 12) {
            GameData.playerAbility.put(playerName, new Dionysus(playerName));
        } else if (abilityCode == 101) {
            GameData.playerAbility.put(playerName, new Archer(playerName));
        } else if (abilityCode == 102) {
            GameData.playerAbility.put(playerName, new Miner(playerName));
        } else if (abilityCode == 103) {
            GameData.playerAbility.put(playerName, new Stance(playerName));
        } else if (abilityCode == 104) {
            GameData.playerAbility.put(playerName, new Teleporter(playerName));
        } else if (abilityCode == 105) {
            GameData.playerAbility.put(playerName, new Bomber(playerName));
        } else if (abilityCode == 106) {
            GameData.playerAbility.put(playerName, new Creeper(playerName));
        } else if (abilityCode == 107) {
            GameData.playerAbility.put(playerName, new Wizard(playerName));
        } else if (abilityCode == 108) {
            GameData.playerAbility.put(playerName, new Assassin(playerName));
        } else if (abilityCode == 109) {
            GameData.playerAbility.put(playerName, new Reflection(playerName));
        } else if (abilityCode == 110) {
            GameData.playerAbility.put(playerName, new Blinder(playerName));
        } else if (abilityCode == 111) {
            GameData.playerAbility.put(playerName, new Invincibility(playerName));
        } else if (abilityCode == 112) {
            GameData.playerAbility.put(playerName, new Cloaking(playerName));
        } else if (abilityCode == 113) {
            GameData.playerAbility.put(playerName, new Blacksmith(playerName));
        } else if (abilityCode == 114) {
            GameData.playerAbility.put(playerName, new Boxer(playerName));
        } else if (abilityCode == 115) {
            GameData.playerAbility.put(playerName, new Priest(playerName));
        } else if (abilityCode == 116) {
            GameData.playerAbility.put(playerName, new Witch(playerName));
        } else if (abilityCode == 117) {
            GameData.playerAbility.put(playerName, new Meteor(playerName));
        } else if (abilityCode == 118) {
            GameData.playerAbility.put(playerName, new Sniper(playerName));
        } else if (abilityCode == 119) {
            GameData.playerAbility.put(playerName, new Voodoo(playerName));
        } else if (abilityCode == 940523) {
            GameData.playerAbility.put(playerName, new Septagram(playerName));
        } else {
            sender.sendMessage("능력 혹은 능력 코드 번호를 잘못 입력하셨습니다.");
            sender.sendMessage("/t a help 명령어로 능력 코드를 확인하실 수 있습니다.");
        }
    }
}
