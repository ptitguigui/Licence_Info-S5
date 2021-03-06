package fil.coo.actions;

import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.game.Direction;

public class Move implements Action {

	/**
	 * Method to execute the move action
	 * @param g AdventureGame
	 * @param player Player
	 */
	public void execute(AdventureGame g, Player player) {
		Direction d = chooseDirection(g);
		if (d != null)
			g.playerMoveTo(d);
		else
			player.act(g);		
	}

	/**
	 * Method to choose a direction in the current room
	 * @param g AdventureGame
	 * @return Direction
	 */
	public Direction chooseDirection(AdventureGame g) {
		Direction d = g.getMenu().choice("What direction ?", g.getCurrentRoom().getDirections());
		return d;
	}

	/**
	 * Method to know if the player can Move to another room
	 * @param g AdventureGame
	 * @return boolean
	 */
	public boolean isPossible(AdventureGame g) {
		return !g.getCurrentRoom().hasMonster();
	}

	public String toString() {
		return "Move to a room";
	}
}
