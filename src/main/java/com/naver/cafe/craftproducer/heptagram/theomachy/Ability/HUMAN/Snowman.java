package com.naver.cafe.craftproducer.heptagram.theomachy.Ability.HUMAN;


import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.Ability.Ability;


public class Snowman extends Ability {

    public Snowman(String playerName) {
        super(playerName, "Snowman", 118, true, false, false);
        Theomachy.log.info(playerName + abilityName);
    }
	
}
