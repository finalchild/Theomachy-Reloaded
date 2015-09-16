package septagram.Theomachy.Handler.CommandModule;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Ability.ETC.Septagram;
import septagram.Theomachy.Ability.GOD.Apollon;
import septagram.Theomachy.Ability.GOD.Ares;
import septagram.Theomachy.Ability.GOD.Artemis;
import septagram.Theomachy.Ability.GOD.Asclepius;
import septagram.Theomachy.Ability.GOD.Athena;
import septagram.Theomachy.Ability.GOD.Demeter;
import septagram.Theomachy.Ability.GOD.Dionysus;
import septagram.Theomachy.Ability.GOD.Hades;
import septagram.Theomachy.Ability.GOD.Hephaestus;
import septagram.Theomachy.Ability.GOD.Hermes;
import septagram.Theomachy.Ability.GOD.Poseidon;
import septagram.Theomachy.Ability.GOD.Zeus;
import septagram.Theomachy.Ability.HUMAN.Archer;
import septagram.Theomachy.Ability.HUMAN.Assasin;
import septagram.Theomachy.Ability.HUMAN.Blacksmith;
import septagram.Theomachy.Ability.HUMAN.Blinder;
import septagram.Theomachy.Ability.HUMAN.Bomber;
import septagram.Theomachy.Ability.HUMAN.Boxer;
import septagram.Theomachy.Ability.HUMAN.Clocking;
import septagram.Theomachy.Ability.HUMAN.Creeper;
import septagram.Theomachy.Ability.HUMAN.Invincibility;
import septagram.Theomachy.Ability.HUMAN.Meteor;
import septagram.Theomachy.Ability.HUMAN.Miner;
import septagram.Theomachy.Ability.HUMAN.Priest;
import septagram.Theomachy.Ability.HUMAN.Reflection;
import septagram.Theomachy.Ability.HUMAN.Sniper;
import septagram.Theomachy.Ability.HUMAN.Stance;
import septagram.Theomachy.Ability.HUMAN.Teleporter;
import septagram.Theomachy.Ability.HUMAN.Voodoo;
import septagram.Theomachy.Ability.HUMAN.Witch;
import septagram.Theomachy.Ability.HUMAN.Wizard;
import septagram.Theomachy.DB.AbilityData;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.CodeHelper;
import septagram.Theomachy.Utility.PermissionChecker;
import septagram.Theomachy.Utility.RandomNumberConstuctor;

public class AbilitySet
{
	public static void Module(CommandSender sender, Command command, String label, String[] data)
	{
		if (PermissionChecker.Sender(sender))
		{
			if (!GameHandler.Ready)
			{
				if (data.length<=1)
				{
					sender.sendMessage("/t a help   모든 능력의 코드표를 확인합니다.");
					sender.sendMessage("/t a random 현재 접속한 모든 플레이어에게 랜덤으로 능력을 할당합니다.");
					sender.sendMessage("/t a remove <Player> 해당 플레이어의 능력을 삭제합니다.");
					sender.sendMessage("/t a reset  모든 능력을 초기화 합니다");
					sender.sendMessage("/t a <AbilityCode> <Player>  플레이어에게 해당 능력을 적용합니다.");
				}
				else if (data[1].equalsIgnoreCase("help"))
					CodeHelper.ShowAbilityCodeNumber(sender);
				else if (data[1].equalsIgnoreCase("remove"))//삭제
				{
					if (data[2] != null)
						Remove(sender, data[2]);
					else
						sender.sendMessage("능력을 삭제할 플레이어의 이름을 적어주세요.");
				}
				else if (data[1].equalsIgnoreCase("reset"))//리셋
					Reset();
				else if (data[1].equalsIgnoreCase("random"))//랜덤
					RandomAssignment(sender);
				else if (data.length >= 3)
					forceAssignment(sender, data);	
				else
				{
					sender.sendMessage("잘못된 입력입니다.");
					sender.sendMessage("/t a 로 명령어를 확인하세요.");
				}
			}
			else
				sender.sendMessage("게임 시작 후에는 능력을 변경 할 수 없습니다.");
		}
	}
	
	public static void Remove(CommandSender sender, String playerName)
	{
		Ability ability = GameData.PlayerAbility.get(playerName);
		if (ability != null)
		{
			GameData.PlayerAbility.remove(playerName);
			sender.sendMessage("플레이어의 능력을 삭제하였습니다.");
		}
		else
			sender.sendMessage("플레이어의 능력이 없습니다.");
	}
	
	public static void Reset()
	{
		GameData.PlayerAbility.clear();
		Bukkit.broadcastMessage(ChatColor.AQUA+"관리자가 모두의 능력을 초기화 하였습니다.");
	}
	
	private static void RandomAssignment(CommandSender sender)
	{
		if (!GameData.PlayerAbility.isEmpty())
		{
			Bukkit.broadcastMessage("모든 능력을 삭제 한후 재 추첨합니다.");
			GameData.PlayerAbility.clear();
		}
		Player[] playerlist=Bukkit.getOnlinePlayers();
		Bukkit.broadcastMessage(ChatColor.DARK_AQUA+"인식된 플레이어 목록");
		for(Player e : playerlist)
			Bukkit.broadcastMessage(ChatColor.GOLD+"  "+e.getName());
		int[] rn = RandomNumberConstuctor.nonDuplicate();
		int length = playerlist.length>AbilityData.ABILITY_NUMBER ? AbilityData.ABILITY_NUMBER : playerlist.length;
		for (int i=0; i<length ;i++)
		{
			String playerName = playerlist[i].getName();
			abiltiyAssignment(rn[i],playerName,sender);
		}
		Bukkit.broadcastMessage("모두에게 능력이 적용되었습니다.");
		Bukkit.broadcastMessage("/t help 로 확인해보세요.");
		if (playerlist.length>AbilityData.ABILITY_NUMBER)
			Bukkit.broadcastMessage("인원이 너무 많습니다. 전부에게 능력을 할당 하지 못했습니다.");
	}
	
	private static void forceAssignment(CommandSender sender, String[] data)
	{
		for (int i=2; i<data.length; i++)
		{
		String abilityName = data[1];
		String playerName=data[i];
		if (GameData.OnlinePlayer.containsKey(playerName))
		{
			try{
				int abilityCode = Integer.parseInt(abilityName);
				abiltiyAssignment(abilityCode, playerName, sender);
				Player player = GameData.OnlinePlayer.get(playerName);
				Bukkit.broadcastMessage("관리자가 "+ChatColor.RED+playerName+ChatColor.WHITE+" 에게 능력을 할당하였습니다.");
				player.sendMessage("능력이 할당되었습니다. /t help로 능력을 확인해보세요.");
			}
			catch (NumberFormatException e)
			{sender.sendMessage("능력코드는 정수를 입력해 주세요");}
		}
		else
			sender.sendMessage(playerName+" 에 해당하는 온라인 유저가 없습니다.");
		}
	}
	
	private static void abiltiyAssignment(int abilityCode, String playerName, CommandSender sender)
	{
		if (abilityCode == 1)
			GameData.PlayerAbility.put(playerName, new Zeus(playerName));
		else if (abilityCode == 2)
			GameData.PlayerAbility.put(playerName, new Poseidon(playerName));
		else if (abilityCode == 3)
			GameData.PlayerAbility.put(playerName, new Hades(playerName));
		else if (abilityCode == 4)
			GameData.PlayerAbility.put(playerName, new Demeter(playerName));
		else if (abilityCode == 5)
			GameData.PlayerAbility.put(playerName, new Athena(playerName));
		else if (abilityCode == 6)
			GameData.PlayerAbility.put(playerName, new Apollon(playerName));
		else if (abilityCode == 7)
			GameData.PlayerAbility.put(playerName, new Artemis(playerName));
		else if (abilityCode == 8)
			GameData.PlayerAbility.put(playerName, new Ares(playerName));
		else if (abilityCode == 9)
			GameData.PlayerAbility.put(playerName, new Hephaestus(playerName));
		else if (abilityCode == 10)
			GameData.PlayerAbility.put(playerName, new Asclepius(playerName));
		else if (abilityCode == 11)
			GameData.PlayerAbility.put(playerName, new Hermes(playerName));
		else if (abilityCode == 12)
			GameData.PlayerAbility.put(playerName, new Dionysus(playerName));
		else if (abilityCode == 101)
			GameData.PlayerAbility.put(playerName, new Archer(playerName));
		else if (abilityCode == 102)
			GameData.PlayerAbility.put(playerName, new Miner(playerName));
		else if (abilityCode == 103)
			GameData.PlayerAbility.put(playerName, new Stance(playerName));
		else if (abilityCode == 104)
			GameData.PlayerAbility.put(playerName, new Teleporter(playerName));
		else if (abilityCode == 105)
			GameData.PlayerAbility.put(playerName, new Bomber(playerName));
		else if (abilityCode == 106)
			GameData.PlayerAbility.put(playerName, new Creeper(playerName));
		else if (abilityCode == 107)
			GameData.PlayerAbility.put(playerName, new Wizard(playerName));
		else if (abilityCode == 108)
			GameData.PlayerAbility.put(playerName, new Assasin(playerName));
		else if (abilityCode == 109)
			GameData.PlayerAbility.put(playerName, new Reflection(playerName));
		else if (abilityCode == 110)
			GameData.PlayerAbility.put(playerName, new Blinder(playerName));
		else if (abilityCode == 111)
			GameData.PlayerAbility.put(playerName, new Invincibility(playerName));
		else if (abilityCode == 112)
			GameData.PlayerAbility.put(playerName, new Clocking(playerName));
		else if (abilityCode == 113)
			GameData.PlayerAbility.put(playerName, new Blacksmith(playerName));
		else if (abilityCode == 114)
			GameData.PlayerAbility.put(playerName, new Boxer(playerName));
		else if (abilityCode == 115)
			GameData.PlayerAbility.put(playerName, new Priest(playerName));
		else if (abilityCode == 116)
			GameData.PlayerAbility.put(playerName, new Witch(playerName));
		else if (abilityCode == 117)
			GameData.PlayerAbility.put(playerName, new Meteor(playerName));
		else if (abilityCode == 118)
			GameData.PlayerAbility.put(playerName, new Sniper(playerName));
		else if (abilityCode == 119)
			GameData.PlayerAbility.put(playerName, new Voodoo(playerName));
		else if (abilityCode == 940523)
			GameData.PlayerAbility.put(playerName, new Septagram(playerName));
		else
		{
			sender.sendMessage("능력 혹은 능력 코드 번호를 잘못 입력하셨습니다.");
			sender.sendMessage("/t a help 명령어로 능력 코드를 확인하실 수 있습니다.");
		}
	}
}