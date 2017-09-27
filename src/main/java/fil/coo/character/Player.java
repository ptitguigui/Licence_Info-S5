package fil.coo.character;

import java.util.ArrayList;
import java.util.List;

import fil.coo.component.Item;
import fil.coo.controller.Action;
import fil.coo.game.AdventureGame;

public class Player extends GameCharacter {

	List<Action> listActions;
	List<Item> listItems;
	
	public Player(String nom, int hp, int strenght, int gold, List<Action> listAction) {
		super(nom, hp, strenght, gold);
		this.listActions = listAction;
		this.listItems = new ArrayList<Item>();
	}
	
	public String toString() {
		return this.nom;
	}
	
	public Action act(AdventureGame g){
		List<Action> availableActions = new ArrayList<Action>();
		
		addActionPossible(g, availableActions);
		
		Action actionChoose = chooseAnAction(g, availableActions);
		
		if(actionChoose != null)
			actionChoose.execute(g, this);
		else
			this.act(g);
		
		return actionChoose;
	}

	public void addActionPossible(AdventureGame g, List<Action> availableActions) {
		for (Action action : this.getActionPossible()) {
			if(action.isPossible(g))
				availableActions.add(action);				
		}
	}

	public Action chooseAnAction(AdventureGame g, List<Action> availableActions) {
		Action actionChoose = g.getMenu().choice("\n"+this.nom+"... What do you want to do ?\n", availableActions);
		return actionChoose;
	}
	
	public List<Action> getActionPossible(){
		return this.listActions;
	}
	
	public void addItem(Item i) {
		listItems.add(i);
	}
	
	public boolean gotItem() {
		return this.listItems.size() > 0;
	}

	public List<Item> getItems() {
		return this.listItems;
	}
}
