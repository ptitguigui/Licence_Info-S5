package fil.coo.character;

import java.util.ArrayList;
import java.util.List;

import fil.coo.controller.Action;
import fil.coo.game.AdventureGame;

public class Player extends GameCharacter {

	List<Action> listActions;
	
	public Player(String nom, int hp, int strenght, int gold, List<Action> listAction) {
		super(nom, hp, strenght, gold);
		this.listActions = listAction;
	}
	
	public void act(AdventureGame g){
		List<Action> availableActions = new ArrayList<Action>();
		
		for (Action action : this.getActionPossible()) {
			if(action.isPossible(g))
				availableActions.add(action);				
		}
		Action actionChoose = g.getMenu().choice("\n"+this.nom+"... What do you want to do ?\n", availableActions);
		actionChoose.execute(g, this);
	}
	
	public List<Action> getActionPossible(){
		return this.listActions;
	}
}
