package septagram.Theomachy.Ability.HUMAN;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Snowman extends Ability
{

	public Snowman(String playerName)
	{
		super(playerName,"Snowman", 118, true, false, false);
		Theomachy.log.info(playerName+abilityName);
	}
	
}
