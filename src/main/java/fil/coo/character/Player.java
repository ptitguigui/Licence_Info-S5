package fil.coo.character;

import java.util.List;

import fil.coo.controller.Action;
import fil.coo.game.Room;

public class Player extends GameCharacter {

	List<Action> listActions;
	
	public Player(int hp, int strenght, int gold) {
		super(hp, strenght, gold);
	}

}
