package com.naver.cafe.craftproducer.heptagram.theomachy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;

public class CommandManager implements CommandExecutor {
    public CommandManager(Theomachy t) {
        t.getCommand("t").setExecutor(this);
        t.getCommand("x").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equals("t")) {
            if (args.length == 0) // 설명 보기
            {
                sender.sendMessage(ChatColor.GOLD + ("====================신들의 전쟁===================="));
                sender.sendMessage(ChatColor.YELLOW + ("/t start    ") + ChatColor.WHITE + ("게임을 시작합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t stop     ") + ChatColor.WHITE + ("게임을 중지합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t ability(a)     ") + ChatColor.WHITE + ("능력을 설정합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t help     ") + ChatColor.WHITE + ("자신의 능력을 확인합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t spawn(s) ") + ChatColor.AQUA + ("<TeamName>   ") + ChatColor.WHITE + ("해당 팀의 스폰 지역을 설정합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t team(t)  ") + ChatColor.AQUA + ("<TeamName>  ") + ChatColor.RED + ("<Player>  ") + ChatColor.WHITE + ("플레이어를 팀에 등록합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t info(i)  ") + ChatColor.AQUA + ("<TeamName>     ") + ChatColor.WHITE + ("해당 팀의 멤버를 확인합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/t clear(c) ") + ChatColor.WHITE + ("쿨타임을 초기화합니다."));
                sender.sendMessage(ChatColor.YELLOW + ("/x ") + ChatColor.RED + ("<Player>     ") + ChatColor.WHITE + ("해당 플레이어를 자신의 타겟으로 등록합니다."));
            } else {
                CommandHandler.handleT(sender, command, label, args);
            }
        } else if (label.equalsIgnoreCase("x")) {
            if (args.length == 0) { // 설명 보기
                sender.sendMessage(ChatColor.YELLOW + ("/x ") + ChatColor.RED + ("<Player>     ") + ChatColor.WHITE + ("해당 플레이어를 자신의 타겟으로 등록합니다."));
            } else {
                CommandHandler.handleX(sender, command, label, args);
            }
        }
        return true;
    }
}
