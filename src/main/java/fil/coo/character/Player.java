package fil.coo.character;

import java.util.ArrayList;
import java.util.List;

import fil.coo.controller.Action;
import fil.coo.game.AdventureGame;
import fil.coo.game.Room;

public class Player extends GameCharacter {

	List<Action> listActions;
	
	public Player(int hp, int strenght, int gold) {
		super(hp, strenght, gold);
		listActions = new ArrayList<Action>();
	}
	
	public void act(AdventureGame g){
		this.listActions = getActionPossible();
		
		for (Action action : listActions) {
			
		}
	}
	
	public List<Action> getActionPossible(){
		List<Action> listActions = new ArrayList<Action>();
		
		return listActions;
	}

}
