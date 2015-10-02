package com.naver.cafe.craftproducer.heptagram.theomachy.timer;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.db.AbilityData;
import com.naver.cafe.craftproducer.heptagram.theomachy.handler.commandmodule.GameHandler;

public class TipTimer extends TimerTask {
    int count = 1;
	
    public void run() {
        if (!GameHandler.ready) {
            this.cancel();
        }
        if (count % 75 == 0) {
            if (Theomachy.autoSave) {
                Bukkit.getServer().savePlayers();
                Theomachy.log.info("[신들의전쟁] 오토세이브 완료");
            }
            switch (count) {
            case 75:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("능력은 크게 액티브, 패시브(버프) 능력으로 나뉩니다.");
                Bukkit.broadcastMessage("패시브 능력은 항상 혹은 특정 조건 시 활성화되며 액티브 능력은 블레이즈 막대 또는 능력 설명에 적힌 아이템으로 사용할 수 있습니다.");
                Bukkit.broadcastMessage("능력 발동 조건이 없는 능력은 블레이즈 막대를 이용해서 사용할 수 있습니다.");
                Bukkit.broadcastMessage("블레이즈 막대는 막대기 3개를 제작대에 세로 일렬로 놓으시면 얻으실 수 있습니다.");
                break;

            case 150:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("신들의 전쟁 플러그인은 스카이블록 기준으로 만들어진 플러그인입니다.");
                Bukkit.broadcastMessage("야생, 하드코어(한번 죽으면 밴)에선 부적합합니다.");
                break;

            case 225:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("능력은 크게 액티브, 패시브로 나뉘며 액티브에는 " + ChatColor.AQUA + "[일반]" + ChatColor.WHITE + "능력과 " + ChatColor.RED + "[고급]" + ChatColor.WHITE + "능력이 있습니다.\n" + "대부분 " + ChatColor.AQUA + "[일반]" + ChatColor.WHITE + "능력은 좌클릭이며 " + ChatColor.RED + "[고급]" + ChatColor.WHITE + "능력은 우클릭입니다.");
                break;

            case 300:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("인원이 많고 맵이 넓다면 서버 메모리를 많이(2G 이상) 잡아주세요.");
                Bukkit.broadcastMessage("메모리 사용이 급증해 서버가 튕길 수도 있습니다.");
                break;

            case 375:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("가끔 서버 설정으로 인해 능력을 핵으로 인식하여 강퇴하는 경우가 있습니다.");
                Bukkit.broadcastMessage("원활한 게임 플레이를 위해 버킷 최신버전(R4.0, 2222 이상) 을 사용해주세요");
                Bukkit.broadcastMessage("이전버전 버킷은 능력을 핵으로 오인해 킥을 할 수도 있습니다.");
                break;

            case 450:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("어? 아이템을 얻는 능력을 사용했는데 코블스톤만 사라지고 아이템이 없잖아? 이런 제작자 X");
                Bukkit.broadcastMessage("하지 마시고 재접속 해보세요.. 버킷 자체 버그입니다. ㅠ");
                Bukkit.broadcastMessage("대장장이 능력은 자신의 위치에 아이템이 떨어지는 형식입니다. 주의하세요.");
                break;

            case 525:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("팀 정하실때 이름 적느라 힘드시죠? ㅠㅠ 다음 플러그인에선 좀더 쉽게 만들도록 하겠습니다.");
                Bukkit.broadcastMessage("플레이어 이름을 어느정도 적고 [Tab] 키를 누르시면 이름이 전체가 적힙니다.");
                Bukkit.broadcastMessage("예) sept > [Tab] > Septagram");
                break;

            case 600:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage(ChatColor.RED + "신들의 전쟁 (Theomachy) - Reloaded");
                Bukkit.broadcastMessage("버전  : " + Theomachy.getPlugin().getDescription().getVersion());
                Bukkit.broadcastMessage("제작자 : 파차(Final Child)");
                Bukkit.broadcastMessage("원작자  : 칠각별(Heptagram)");
                Bukkit.broadcastMessage(ChatColor.GREEN + "http://blog.naver.com/septagram/");
                break;

            case 675:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("현재 등록된 능력은 " + AbilityData.ABILITY_NUMBER + "개 입니다.");
                Bukkit.broadcastMessage("이 중 신의 능력은 " + AbilityData.GOD_ABILITY_NUMBER + "개이며");
                Bukkit.broadcastMessage("인간의 능력은 " + AbilityData.HUMAN_ABILITY_NUMBER + "개입니다.");
                break;

            case 750:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("팀 스폰 지역은 기반암이 좋습니다.");
                Bukkit.broadcastMessage("블록이 부서질 경우 공중에서 스폰되어 부활하자마자 떨어져 죽는 상황이 발생할 수 있습니다.");
                break;

            case 825:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("스카이 블럭에서는 창고 숨기기와 코블스톤 생성기를 잘 만드는게 반입니다.");
                Bukkit.broadcastMessage("코블스톤 생성기는 듀얼(1인용) 쿼드(2인용), 한번에 8개를 캘 수 있는 옥타도 있습니다.");
                break;

            case 900:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("스블에서는 창고 관리를 최우선적으로 합시다.");
                Bukkit.broadcastMessage("창고 테러를 당하면 아무리 고급능력이라도 승리하기가 쉽지 않습니다.");
                Bukkit.broadcastMessage("능력자전에서 육탄전으로 변하는 상황이 오게 됩니다.");
                break;

            case 975:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("대부분의 패시브 능력은 서로 어느 정도 상성 관계가 있습니다.");
                Bukkit.broadcastMessage("디오니소스 > 복서 > 스탠스");
                break;

            case 1050:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("컨트롤이 좋으면 웬만한 S랭크 효과를 넘는 능력들이 있습니다. 랭크가 낮다고 실망하지 마세요.");
                break;

            case 1125:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("플러그인을 이용한 게임은 서버에게 상당한 고사양을 요구합니다.");
                Bukkit.broadcastMessage("플레이하는 인원수가 적다면 영향이 없겠지만 인원수가 많을 시에는 저사양 컴퓨터에서 서버를 돌리는 것을 추천하진 않습니다.");
                break;

            case 1200:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("플러그인 제작에 도움을 주신 분들");
                Bukkit.broadcastMessage("원작자: 칠각별 (어 그러니까... 코드 스타일과 맞춤법 노가다를 주셔서 감사합니다.)");
                Bukkit.broadcastMessage("소스 참고 및 조언 : 제온 (많은 충고 감사합니다.)");
                Bukkit.broadcastMessage("테스터 : Headings, Patrasue, raito_, 김정민, 박위선, 해럴드, ZackLee 그 외...");
                break;

            case 1275:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("신과 인간 능력의 차이는");
                Bukkit.broadcastMessage("이름 뿐.");
                Bukkit.broadcastMessage("오히려 인간이 강한 능력을 가진 경우도 있습니다. 신이 아니라고 해서 실망하지 마세요.");
                break;

            case 1350:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("플러그인 라이선스: MIT License");
                Bukkit.broadcastMessage("저작권은 칠각별, 파차에게 있습니다.");
                Bukkit.broadcastMessage(ChatColor.GREEN + "http://cafe.naver.com/craftproducer");
                Bukkit.broadcastMessage("현재 배포 중인 곳은 없습니다.");
                break;

            case 1425:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("(능력) 크리퍼에게 번개 사용 시 주의하세요 ");
                Bukkit.broadcastMessage("폭발력이 두 배로 늘어납니다.");
                break;

            case 1500:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("시간이 되면 공성전 모드로도 개발할 예정입니다.");
                Bukkit.broadcastMessage("업데이트를 기다려주세요.");
                break;

            case 1575:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("원작자의 또 다른 플러그인");
                Bukkit.broadcastMessage("꼬리잡기, 팀플러그인, 위스퍼, 스나이퍼, NoFoul(접속시 3초무적 무시), DogFight, ServerControler");
                break;

            case 1650:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("서버가 다운되면 능력도 다운됩니다. 주의하세요.");
                break;

            case 1725:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage(ChatColor.GREEN + "http://cafe.naver.com/craftproducer");
                Bukkit.broadcastMessage("게임 개발자 카페입니다.");
                Bukkit.broadcastMessage("관심이 있으신 분들은 한 번씩 들러보세요.");
                break;

            case 1800:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("플러그인을 빼면 뺄수록 서버의 성능은 올라갑니다.");
                Bukkit.broadcastMessage(ChatColor.RED + "*될 수 있는 한 VisualAbility와 신들의전쟁을 넣거나 동시 실행하지마세요*");
                Bukkit.broadcastMessage(ChatColor.RED + "*트래픽이 엄청납니다.*");
                break;

            case 1875:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("다이아몬드 블록은 돌 곡괭이로 캐는 속도와 손으로 캐는 속도가 동일합니다.");
                Bukkit.broadcastMessage("라고 칠각별님이 말하셨으나 사실은 돌 곡괭이가 네 배 더 빠르게 캡니다.");
                break;

            case 1950:
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[ Tip ]");
                Bukkit.broadcastMessage("플레이하시는 모든 분들에게 감사 말씀 드립니다.");
                Bukkit.broadcastMessage("재밌게 플레이해 주세요.");				
                count = 1;
                break;
            }
        }
        if (count % 180 == 0) {
            long max = (int) (Runtime.getRuntime().maxMemory() / 1048576);
            long free = (int) (Runtime.getRuntime().freeMemory() / 1048576);
            long use = max - free;

            Bukkit.broadcastMessage(ChatColor.WHITE + "메모리(MB)   " + ChatColor.AQUA + String.valueOf(use) + ChatColor.WHITE + " / " + ChatColor.YELLOW + String.valueOf(max));
			
            if (free < 375) {
                System.out.println("메모리 부족, 메모리 청소중...");
                System.gc();
                long free2 = (Runtime.getRuntime().freeMemory() / 1048576);

                System.out.println("현재 사용 가능 메모리 : " + free2 + "MB");
            }
        }
        count++;
    }
}
