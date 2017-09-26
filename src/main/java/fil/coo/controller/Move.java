package fil.coo.controller;

import fil.coo.character.Player;
import fil.coo.game.AdventureGame;

public class Move implements Action {

	public void execute(AdventureGame g, Player player) {
		Direction d = g.getMenu().choice("What direction ?", g.getCurrentRoom().getDirections());
		if (d != null)
			g.playerMoveTo(d);
		else
			player.act(g);
	}

	public boolean isPossible(AdventureGame g) {
		return !g.getCurrentRoom().hasMonster();
	}

	public String toString() {
		return "Move to a room";
	}
}
