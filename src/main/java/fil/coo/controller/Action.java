package fil.coo.controller;

import fil.coo.character.Player;
import fil.coo.game.AdventureGame;

public interface Action {

	/**
	 * Method to execute the action
	 * @param g AdventureGame
	 * @param player Player
	 */
	public void execute(AdventureGame g, Player player);
	
	/**
	 * Method to know if the action is possible
	 * @param g AdventureGame
	 * @return boolean
	 */
	public boolean isPossible(AdventureGame g);
}
