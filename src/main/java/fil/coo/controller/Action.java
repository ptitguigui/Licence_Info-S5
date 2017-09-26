package fil.coo.controller;

import fil.coo.character.Player;
import fil.coo.game.AdventureGame;

public interface Action {

	public void execute(AdventureGame g, Player player);
	public boolean isPossible(AdventureGame g);
}
