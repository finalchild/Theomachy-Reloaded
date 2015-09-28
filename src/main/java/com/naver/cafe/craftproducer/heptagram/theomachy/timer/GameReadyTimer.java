package com.naver.cafe.craftproducer.heptagram.theomachy.timer;

import java.util.Collection;
import java.util.List;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.GameHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.utility.PlayerInventory;

public class GameReadyTimer extends TimerTask {
    private int count = 1;
    private Player[] playerList;
    private String[] setting = new String[8];
    private Difficulty difficulty;
    private World world;
    public GameReadyTimer() {
        this.playerList = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        setting[0] = Theomachy.inventoryClear ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[1] = Theomachy.giveItem ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[2] = Theomachy.ignoreBed ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[3] = Theomachy.autoSave ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[4] = Theomachy.animal ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[5] = Theomachy.monster ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        setting[7] = Theomachy.entitiesRemove ? ChatColor.AQUA + "ON" : ChatColor.RED + "OFF";
        difficulty = null;
        switch (Theomachy.difficulty) {
        case 0:
            setting[6] = ChatColor.GREEN + "평화로움";
            difficulty = Difficulty.PEACEFUL;
            break;

        case 1:
            setting[6] = ChatColor.AQUA + "쉬움";
            difficulty = Difficulty.EASY;
            break;

        case 2:
            setting[6] = ChatColor.YELLOW + "보통";
            difficulty = Difficulty.NORMAL;
            break;

        case 3:
            setting[6] = ChatColor.GREEN + "어려움";
            difficulty = Difficulty.HARD;
            break;

        default:
            setting[6] = ChatColor.GREEN + "쉬움";
            difficulty = Difficulty.EASY;
        }
        world = Bukkit.getWorlds().get(0);
    }
	
    public void run() {		
        if (GameHandler.ready && count < 45) {
            switch (count) {
            case 4:
                Bukkit.broadcastMessage(ChatColor.RED + "신들의 전쟁 플러그인은 스카이블록 전용이며 야생, 하드코어로는 부적합합니다.");
                Bukkit.broadcastMessage(ChatColor.RED + "이 점을 숙지하시고 게임을 즐기시길 바랍니다.");
                break;

            case 8:
                Bukkit.broadcastMessage(ChatColor.AQUA + "플러그인 배포 중인 곳");
                Bukkit.broadcastMessage(ChatColor.GREEN + "https://github.com/fcreloaded/Theomachy-Reloaded/releases");
                Bukkit.broadcastMessage(ChatColor.WHITE + "채팅창 여시고 마우스 클릭하시면 해당 주소로 이동합니다.");
                break;

            case 12:
                Bukkit.broadcastMessage(ChatColor.GREEN + "****** 서버 세팅 상태 ******");
                Bukkit.broadcastMessage(ChatColor.WHITE + "게임 시작할 때 인벤토리 클리어 : " + setting[0]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "게임 시작할 때 기본 아이템 지급 : " + setting[1]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "게임 시작할 때 몬스터, 동물, 아이템 삭제 : " + setting[7]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "부활할 때 침대 무시 : " + setting[2]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "오토 세이브 : " + setting[3]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "동물 스폰 : " + setting[4]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "몬스터 스폰 : " + setting[5]);
                Bukkit.broadcastMessage(ChatColor.WHITE + "서버 난이도 : " + setting[6]);
                Bukkit.broadcastMessage(ChatColor.GREEN + "***************************");
                break;

            case 16:
                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "현재 인식된 플레이어 목록");
                for (int i = 0; i < playerList.length; i++) {
                    Bukkit.broadcastMessage(i + 1 + ".  " + ChatColor.GOLD + playerList[i].getName());
                }
                break;

            case 20:
                Bukkit.broadcastMessage(ChatColor.AQUA + "현재 능력이 적용되지 않은 플레이어 목록");
                for (int i = 0; i < playerList.length; i++) {
                    if (!GameData.playerAbility.containsKey(playerList[i].getName())) {
                        Bukkit.broadcastMessage(i + 1 + ".  " + ChatColor.GOLD + playerList[i].getName());
                    }
                }
                break;

            case 24:
                Bukkit.broadcastMessage(ChatColor.BLUE + "현재 팀이 적용되지 않은 플레이어 목록");
                for (int i = 0; i < playerList.length; i++) {
                    if (!GameData.playerTeam.containsKey(playerList[i].getName())) {
                        Bukkit.broadcastMessage(i + 1 + ".  " + ChatColor.GOLD + playerList[i].getName());
                    }
                }
                break;

            case 29:
                Bukkit.broadcastMessage("제작자 : " + ChatColor.AQUA + "Final Child(파차)");
                Bukkit.broadcastMessage("Github: https://github.com/fcreloaded/Theomachy-Reloaded");
                Bukkit.broadcastMessage("원작자: Septagram(칠각별)");
                Bukkit.broadcastMessage("원작자 블로그 : http://blog.naver.com/septagram/");
                Bukkit.broadcastMessage("버그 신고는 Github에서 해주세요.");
                break;

            case 34:
                Bukkit.broadcastMessage(ChatColor.AQUA + "잠시 후 능력이 활성화되며 팀 스폰지역으로 텔레포트 됩니다.");
                break;

            case 35: 
                Bukkit.broadcastMessage(ChatColor.RED + "5초 전");
                break;

            case 36:
                Bukkit.broadcastMessage(ChatColor.RED + "4초 전");
                break;

            case 37:
                Bukkit.broadcastMessage(ChatColor.RED + "3초 전");
                break;

            case 38:
                Bukkit.broadcastMessage(ChatColor.RED + "2초 전");
                break;

            case 39:
                Bukkit.broadcastMessage(ChatColor.RED + "1초 전");
                break;

            case 40:
                if (Theomachy.entitiesRemove) {
                    try {
                        List<Entity> entities = world.getEntities();

                        for (Entity e : entities) {
                            if (e instanceof Item || e instanceof Monster || e instanceof Animals) {
                                e.remove();
                            }
                        }
                    } catch (NullPointerException e) {}
                }
                Location spawnLocation = world.getSpawnLocation();

                while (true) {
                    if (spawnLocation.getBlock().isEmpty()) {
                        break;
                    } else {
                        spawnLocation.setY(spawnLocation.getY() + 1);
                    }
                }
                Bukkit.broadcastMessage(ChatColor.AQUA + "스폰 지역으로 텔레포트 합니다");
                for (Player e : playerList) {
                    e.setFoodLevel(20);
                    e.setSaturation(10f);
                    e.setLevel(0);
                    e.setExhaustion(0.0F);
                    e.setExp(0.0F);
                    e.setHealth(20);
                    PlayerInventory.addSkyblockBasicItems(e);					
                    String teamName = GameData.playerTeam.get(e.getName());

                    if (teamName != null) {
                        Location location = GameData.spawnArea.get(teamName);

                        if (location != null) {
                            e.teleport(location);
                        } else {
                            e.sendMessage(ChatColor.RED + "팀의 스폰 지역이 설정되지 않아 기본 스폰 지역으로 이동합니다.");
                            e.teleport(spawnLocation);
                        }
                    } else {
                        e.sendMessage(ChatColor.RED + "팀이 지정되지 않아 기본 스폰 지역으로 이동합니다.");
                        e.teleport(spawnLocation);
                    }
                }
                break;

            case 41:
                world.setPVP(true);
                world.setAutoSave(Theomachy.autoSave);
                world.setSpawnFlags(Theomachy.monster, Theomachy.animal);
                world.setDifficulty(this.difficulty);
                world.setTime(6000);
                Collection<Ability> playerAbilityList = GameData.playerAbility.values();

                for (Ability e : playerAbilityList) {
                    e.conditionSet();
                    e.buff();
                }
                GameHandler.start = true;
                Bukkit.broadcastMessage(ChatColor.GOLD + "게임 시작!");
				
            }
        } else {
            this.cancel();
        }
        count++;
    }
}
