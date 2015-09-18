package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.ETC;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.GameData;


public class JJuni_ extends Ability {
    public JJuni_(String playerName) {
        super(playerName, "쭈니", 1869, false, false, false);
        Theomachy.log.info(playerName + abilityName);
    }

    public void description() {
        Player player = GameData.OnlinePlayer.get(playerName);

        player.sendMessage(
                ChatColor.DARK_GREEN + "=================== " + ChatColor.YELLOW
                + "능력 정보" + ChatColor.DARK_GREEN
                + " ===================");
        player.sendMessage(
                ChatColor.YELLOW + "[ 쭈니 ]  " + ChatColor.RED + "[ BJ ]  "
                + ChatColor.BLUE + "Unknown  " + ChatColor.GREEN + "Rank[ ? ]");
        player.sendMessage(
                "아프리카 BJ입니다.\n" + "잘가야지?ㅋㅋㅋ\n");
    }
}
