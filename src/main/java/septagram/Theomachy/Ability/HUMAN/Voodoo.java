package septagram.Theomachy.Ability.HUMAN;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Voodoo extends Ability
{
	private final int coolTime0=180;
	private final int material=4;
	private final int stack0=5;
	private String targetName=null;
	private Block postSign=null;
	
	
	public Voodoo(String playerName)
	{
		super(playerName, "Voodoo", 119, true, true, false);
	}
	
	public void description()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.sendMessage(ChatColor.DARK_GREEN+"=================== "+ChatColor.YELLOW+"능력 정보"+ChatColor.DARK_GREEN+" ===================");
		player.sendMessage(ChatColor.YELLOW+"[ 부두술사 ]  "+ChatColor.RED+"[ 인간 ]  "+ChatColor.BLUE+"Active  "+ChatColor.GREEN+"Rank[ A ]");
		player.sendMessage("팻말을 이용해서 상대를 타격할 수 있는 능력입니다.\n"+
						   "팻말에 타격할 상대의 이름을 적을시 그 아이디를 가진 플레이어는 팻말과 연결되며\n" +
						   "팻말을 두들겨 팰시 대상 플레이어가 데미지를 입습니다.\n" +
						   "설치후 7초 동안 효과가 지속되며 7초 이후에 자동으로 팻말이 부숴집니다.\n" +
						   "데미지는 무기의 영향을 받지 않습니다.\n" +
						   "쿨타임은 팻말을 든채 좌클릭하면 좀 더 쉽게 확인 할 수 있습니다.\n" +
						   ChatColor.GREEN+"펫말에 이름을 적을시 "+ChatColor.WHITE+" 코블스톤 "+stack0+"개 소모, 쿨타임 "+coolTime0+"초");
	}

	public void T_Passive(BlockPlaceEvent event)
	{
		int blockId = event.getBlock().getTypeId();
		if (blockId == 63 || blockId == 68)
		{
			Player player = event.getPlayer();

			if (!(CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0)))
				event.setCancelled(true);
		}
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		if (this.postSign != null)
		{
			if (event.getAction() == Action.LEFT_CLICK_BLOCK)
			{
				Block block = event.getClickedBlock();
				if ((block.getTypeId()==63 || block.getTypeId()==68)&& this.postSign.getState().equals(block.getState()))
				{
					Player player = GameData.OnlinePlayer.get(targetName);
					if (player != null)
						player.damage(1, event.getPlayer());

				}
			}
		}
		else if (event.getPlayer().getItemInHand().getTypeId() == 323)
		{
			Action action = event.getAction();
			if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
			{
				Player player = event.getPlayer();
				if (CoolTimeChecker.Check(player, 0) && PlayerInventory.ItemCheck(player, material, stack0))
					player.sendMessage("스킬을 사용 할 수 있습니다.");
			}
		}
	}
	
	public void T_Passive(SignChangeEvent event)
	{
		Player player = event.getPlayer();
		String targetName = event.getLine(0);
		Player target = GameData.OnlinePlayer.get(targetName);
		if (target != null)
		{
			Skill.Use(player, material, stack0, 0, coolTime0);
			this.targetName=targetName;
			this.postSign=event.getBlock();
			player.sendMessage(ChatColor.RED+targetName+ChatColor.WHITE+" 를(을) 팻말과 연결시켰습니다.");
			target.sendMessage(ChatColor.RED+"부두술사가 당신을 위협합니다.");
			Timer t = new Timer();
			t.schedule(new Duration(), 7000);
		}
		else
			player.sendMessage(ChatColor.RED+targetName+ChatColor.WHITE+" 그런 플레이어는 없는데요...");
	}

	
	private class Duration extends TimerTask
	{
		@Override
		public void run()
		{
			targetName=null;
			postSign.breakNaturally();
			postSign=null;
		}
		
	}
}
