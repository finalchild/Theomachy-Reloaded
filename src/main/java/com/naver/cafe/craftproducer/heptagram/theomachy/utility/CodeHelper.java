package com.naver.cafe.craftproducer.heptagram.theomachy.utility;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;


public class CodeHelper {

    public static void ShowAbilityCodeNumber(CommandSender sender) {
        if (sender instanceof Player) {
            showCode(sender);
        } else {
            showCode();
        }
    }
	
    private static void showCode() {
        int[] w = new int[] { 9, 2, 17, 3, 32};

        Theomachy.log.info(
                String.format("%" + w[0] + "s %23s", "======god=====",
                "======human====="));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Zeus",
                        "1",
                        "Archer",
                        "101"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Poseidon",
                        "2",
                        "Miner",
                        "102"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Hades",
                        "3",
                        "Stance",
                        "103"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Demeter",
                        "4",
                        "Teleporter",
                        "104"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Athena",
                        "5",
                        "Bomber",
                        "105"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Apollon",
                        "6",
                        "Creeper",
                        "106"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Artemis",
                        "7",
                        "Wizard",
                        "107"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Ares",
                        "8",
                        "Assassin",
                        "108"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Hepaestus",
                        "9",
                        "Reflection",
                        "109"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Asclipius",
                        "10",
                        "Blinder",
                        "110"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Hermes",
                        "11",
                        "Invincibility",
                        "111"));
        Theomachy.log.info(
                String.format(
                        "%" + w[0] + "s : %" + w[1] + "s %" + w[2] + "s : %"
                        + w[3] + "s",
                        "Dionysus",
                        "12",
                        "Cloaking",
                        "112"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Blacksmith",
                "113"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Boxer", "114"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Priest", "115"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Witch", "116"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Meteor", "117"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Sniper", "118"));
        Theomachy.log.info(
                String.format("%" + w[4] + "s : %" + w[3] + "s", "Voodoo", "119"));
    }
	
    private static void showCode(CommandSender sender) {
        sender.sendMessage(
                ChatColor.YELLOW + "신-------\n" + ChatColor.RED + "제우스 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 1\n"
                + ChatColor.RED + "포세이돈 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 2\n" + ChatColor.RED + "하데스 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 3\n"
                + ChatColor.RED + "데메테르 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 4\n" + ChatColor.RED + "아테나 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 5\n"
                + ChatColor.RED + "아폴론 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 6\n" + ChatColor.RED + "아르테미스 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 7\n"
                + ChatColor.RED + "아레스 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 8\n" + ChatColor.RED + "헤파이토스 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 9\n"
                + ChatColor.RED + "아스클레피오스 " + ChatColor.WHITE
                + ":" + ChatColor.GOLD + " 10\n" + ChatColor.RED
                + "헤르메스 " + ChatColor.WHITE + ":" + ChatColor.GOLD
                + " 11\n" + ChatColor.RED + "디오니소스 " + ChatColor.WHITE
                + ":" + ChatColor.GOLD + " 12\n" + ChatColor.YELLOW
                + "인간-------\n" + ChatColor.RED + "아처 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 101\n"
                + ChatColor.RED + "광부 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 102\n" + ChatColor.RED + "스탠스 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 103\n"
                + ChatColor.RED + "텔레포터 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 104\n" + ChatColor.RED + "봄버 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 105\n"
                + ChatColor.RED + "크리퍼 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 106\n" + ChatColor.RED + "마법사 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 107\n"
                + ChatColor.RED + "암살자 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 108\n" + ChatColor.RED + "반사 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 109\n"
                + ChatColor.RED + "블라인더 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 110\n" + ChatColor.RED + "무적 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 111\n"
                + ChatColor.RED + "클로킹 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 112\n" + ChatColor.RED + "대장장이 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 113\n"
                + ChatColor.RED + "복서 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 114\n" + ChatColor.RED + "사제 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 115\n"
                + ChatColor.RED + "마녀 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 116\n" + ChatColor.RED + "메테오 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 117\n"
                + ChatColor.RED + "스나이퍼 " + ChatColor.WHITE + ":"
                + ChatColor.GOLD + " 118\n" + ChatColor.RED + "부두술사 "
                + ChatColor.WHITE + ":" + ChatColor.GOLD + " 119");
    }
}
