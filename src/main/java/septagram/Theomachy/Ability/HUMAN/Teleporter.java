package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.BlockFilter;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Teleporter extends Ability
{
	private final int coolTime1=25;
	private final int coolTime2=30;
	private final int material=4;
	private final int stack1=2;
	private final int stack2=2;
	private String abilitytarget;
	
	public Teleporter(String playerName)
	{
		super(playerName,"Teleporter", 5, true, false, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 텔레포터 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Active  "+ChatColor.GREEN+"Rank[ B ]");
		player.sendMessage("순간이동을 돕는 마법사입니다.\n"+
						   "블레이즈 로드를 이용해 자신이 원하는 위치(25칸)에 텔레포트 할 수 있으며 같은 팀원 위치와 스위칭도 가능합니다.\n" +
						   "좌클릭으로 자신이 가리키고 있는곳으로 텔레포트 하며.\n" +
						   "우클릭으로 타겟에 등록해 둔 자신의 팀원과 위치를 치환합니다.(타겟 등록법 : /x <Player>)\n" +
						   ChatColor.AQUA+"일반(좌클릭) "+ChatColor.WHITE+" 코블스톤 "+stack1+"개 소모, 쿨타임 "+coolTime1+"초\n" +
						   ChatColor.RED+"고급(우클릭) "+ChatColor.WHITE+" 코블스톤 "+stack2+"개 소모, 쿨타임 "+coolTime2+"초\n");
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:
				leftAction(player);
				break;
			case 2:case 3:
				rightAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material, stack1))
		{
			Block block = player.getTargetBlock(null, 25);
			if (BlockFilter.AirToFar(player, block))
			{
				Location location0 = block.getLocation();
				Location location1 = block.getLocation();
				location0.setY(location0.getY()+1);
				location1.setY(location1.getY()+2);
				Block block0 = location0.getBlock();
				Block block1 = location1.getBlock();
				if ((block0.getTypeId()==0 || block1.getTypeId() == 78)&&block1.getTypeId()==0)
				{
					Skill.Use(player, material, stack1, 1, coolTime1);
					Location plocation = player.getLocation();
					Location tlocation = block.getLocation();
					tlocation.setPitch(plocation.getPitch());
					tlocation.setYaw(plocation.getYaw());
					tlocation.setY(tlocation.getY()+1);
					tlocation.setX(tlocation.getX()+0.5);
					tlocation.setZ(tlocation.getZ()+0.5);
					player.teleport(tlocation);
				}
				else
					player.sendMessage("텔레포트 할 수 있는 공간이 없어 텔레포트에 실패 했습니다.");
			}
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			if (abilitytarget != null)
			{
				Player target = GameData.OnlinePlayer.get(abilitytarget);
				if (target != null)
				{
					Location location = player.getLocation();
					location.setY(location.getY()-1);
					Skill.Use(player, material, stack2, 2, coolTime2);
					Location tloc = target.getLocation();
					Location ploc = player.getLocation();
					target.teleport(ploc);
					player.teleport(tloc);
					target.sendMessage("텔레포터의 능력에 의해 위치가 텔레포터의 위치로 변경되었습니다.");
				}
				else
					player.sendMessage("플레이어가 온라인이 아닙니다.");
			}
			else
				player.sendMessage("타겟이 지정되지 않았습니다. (타겟 등록법 : /x <Player>)");
		}
	}
	
	public void targetSet(CommandSender sender, String targetName)
	{
		String playerTeamName = GameData.PlayerTeam.get(playerName);
		String targetTeamName = GameData.PlayerTeam.get(targetName);
		if (playerTeamName != null &&
			targetTeamName != null &&
			playerTeamName.equals(targetTeamName))
		{
			if (!playerName.equals(targetName))
			{
				this.abilitytarget = targetName;
				sender.sendMessage("타겟을 등록했습니다.   "+ChatColor.GREEN+targetName);
			}
			else
				sender.sendMessage("자기 자신을 타겟으로 등록 할 수 없습니다.");
		}
		else
			sender.sendMessage("자신의 팀의 멤버가 아닙니다.");		
	}
}
