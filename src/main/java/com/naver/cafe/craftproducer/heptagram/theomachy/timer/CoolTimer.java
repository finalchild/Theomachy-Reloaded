package com.naver.cafe.craftproducer.heptagram.theomachy.timer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.ability.Ability;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.GameData;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.GameHandler;
import com.naver.cafe.craftproducer.heptagram.theomachy.message.T_Message;

import java.util.TimerTask;

public class CoolTimer extends TimerTask {
    public static boolean ini = false;
    public static HashMap<String, Integer> cool0 = new HashMap<String, Integer>();
    public static HashMap<String, Integer> cool1 = new HashMap<String, Integer>();
    public static HashMap<String, Integer> cool2 = new HashMap<String, Integer>();
    private int count = 1;
    public void run() {
        if (!GameHandler.ready) {
            this.cancel();
        }
		
        try {
            if (!cool0.isEmpty()) {
                Iterator<Entry<String, Integer>> iter = cool0.entrySet().iterator();

                while (iter.hasNext()) {
                    Entry<String, Integer> entry = iter.next();
                    String playerName = entry.getKey();
                    int value = entry.getValue() - 1;

                    if (value <= 0) {
                        cool0.remove(playerName);
                        T_Message.onCooltimeEnd(0, playerName);
                    } else {
                        cool0.put(playerName, value);
                        if (value <= 3) {
                            T_Message.tellCooltimeCount(0, playerName, value);
                        }
                    }
                }
            }
		
            if (!cool1.isEmpty()) {
                Iterator<Entry<String, Integer>> iter = cool1.entrySet().iterator();

                while (iter.hasNext()) {
                    Entry<String, Integer> entry = iter.next();
                    String playerName = entry.getKey();
                    int value = entry.getValue() - 1;

                    if (value <= 0) {
                        cool1.remove(playerName);
                        T_Message.onCooltimeEnd(1, playerName);
                    } else {
                        cool1.put(playerName, value);
                        if (value <= 3) {
                            T_Message.tellCooltimeCount(1, playerName, value);
                        }
                    }
                }
            }
            if (!cool2.isEmpty()) {
                Iterator<Entry<String, Integer>> iter = cool2.entrySet().iterator();

                while (iter.hasNext()) {
                    Entry<String, Integer> entry = iter.next();
                    String playerName = entry.getKey();
                    int value = entry.getValue() - 1;

                    if (value <= 0) {
                        cool2.remove(playerName);
                        T_Message.onCooltimeEnd(2, playerName);
                    } else {
                        cool2.put(playerName, value);
                        if (value <= 3) {
                            T_Message.tellCooltimeCount(2, playerName, value);
                        }
                    }
                }
            }
            if (ini) {
                cool0.clear();
                cool1.clear();
                cool2.clear();
                ini = false;
            }
            if (count % 150 == 0) {
                Collection<Ability> playerAbilityList = GameData.playerAbility.values();

                for (Ability e : playerAbilityList) {
                    if (e.buffType) {
                        e.buff();
                    }
                }
            }
        } catch (Exception e) {
            Theomachy.log.info("쿨타이머에 에러가 발생하여 쿨타임이 1초 느려집니다.");
            Theomachy.log.info(e.getLocalizedMessage() + "");
			
        }
        count++;
    }
}
