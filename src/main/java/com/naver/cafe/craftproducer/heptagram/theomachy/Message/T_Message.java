package com.naver.cafe.craftproducer.heptagram.theomachy.Message;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;


public class T_Message {
    public static void CoolTimeTeller(Player player, int abilityCase, int cool) {
        switch (abilityCase) {
        case 0:
            player.sendMessage("재사용 대기시간 : " + cool + "초");
            break;

        case 1:
            player.sendMessage(
                    ChatColor.AQUA + "[일반]  " + ChatColor.WHITE
                    + "재사용 대기시간 : " + cool + "초");
            break;

        case 2:
            player.sendMessage(
                    ChatColor.RED + "[고급]  " + ChatColor.WHITE
                    + "재사용 대기시간 : " + cool + "초");
            break;
        }
    }

    public static void CoolTimeCountTeller(int switcher, String playerName, int cool) {
        Player player = GameData.OnlinePlayer.get(playerName);

        if (player != null) {
            switch (switcher) {
            case 0:
                player.sendMessage(cool + "초 전");
                break;

            case 1:
                player.sendMessage(
                        ChatColor.AQUA + "[일반]  " + ChatColor.WHITE + cool
                        + "초 전");
                break;

            case 2:
                player.sendMessage(
                        ChatColor.RED + "[고급]  " + ChatColor.WHITE + cool
                        + "초 전");
                break;
            }
        }
    }
	
    public static void AbilityReset(int switcher, String playerName) {
        Player player = GameData.OnlinePlayer.get(playerName);

        if (player != null) {
            switch (switcher) {
            case 0:
                player.sendMessage(
                        ChatColor.GOLD
                                + "능력을 다시 사용할 수 있습니다.");
                break;

            case 1:
                player.sendMessage(
                        ChatColor.AQUA + "[일반]  " + ChatColor.GOLD
                        + "능력을 다시 사용할 수 있습니다.");
                break;

            case 2:
                player.sendMessage(
                        ChatColor.RED + "[고급]  " + ChatColor.GOLD
                        + "능력을 다시 사용할 수 있습니다.");
                break;
            }
        }
    }

    public static void Skill_Used(Player player, int abilityCase) {
        switch (abilityCase) {
        case 0:
            player.sendMessage(
                    ChatColor.YELLOW + "능력을 사용하였습니다!");
            break;

        case 1:
            player.sendMessage(
                    ChatColor.AQUA + "[일반]  " + ChatColor.YELLOW
                    + "능력을 사용하였습니다!");
            break;

        case 2:
            player.sendMessage(
                    ChatColor.RED + "[고급]  " + ChatColor.YELLOW
                    + "능력을 사용하였습니다!");
            break;
        }
    }
	
    @SuppressWarnings("incomplete-switch")
    public static void LackItemError(Player player, Material material, int stack) {
        switch (material) {
        case COBBLESTONE:
            player.sendMessage("코블스톤이 부족합니다.");
            player.sendMessage("필요갯수 : " + ChatColor.RED + stack);
            break;

        case WOOD:
            player.sendMessage("가공된 나무가 부족합니다.");
            player.sendMessage("필요갯수 : " + ChatColor.RED + stack);
            break;

        case IRON_INGOT:
            player.sendMessage("철괴가 부족합니다.");
            player.sendMessage("필요갯수 : " + ChatColor.RED + stack);
        }
    }
	
    public static void TooFarError(Player player, int targetType) {
        switch (targetType) {
        case 0:
            player.sendMessage(
                    ChatColor.RED + "대상과의 거리가 너무 멉니다.");
            break;

        case 1:
            player.sendMessage(
                    ChatColor.RED + "타겟과 거리가 너무 멉니다.");
            break;
        }
		
    }

    public static void PassiveEnable(Player player, int passiveCase) {
        switch (passiveCase) {
        case 0:
            player.sendMessage(
                    ChatColor.YELLOW + "능력이 활성화 되었습니다.");
            break;
        }
		
    }
}
