package fil.coo;

import java.util.List;

public class Player extends GameCharacter {

	List<Action> listActions;
	
	public Player(int hp, int strenght, int gold, Room currentRoom) {
		super(hp, strenght, gold, currentRoom);
	}

}
