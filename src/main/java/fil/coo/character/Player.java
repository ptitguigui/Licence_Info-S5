package fil.coo.character;

import java.util.ArrayList;
import java.util.List;

import fil.coo.actions.Action;
import fil.coo.actions.Move;
import fil.coo.game.AdventureGame;
import fil.coo.items.Item;

public class Player extends GameCharacter {

	List<Action> listActions;
	List<Item> listItems;
	
	/**
	 * create a player
	 * @param nom String
	 * @param hp int 
	 * @param strenght int 
	 * @param gold int 
	 * @param listAction List<Action>
	 */
	public Player(String nom, int hp, int strenght, int gold, List<Action> listAction) {
		super(nom, hp, strenght, gold);
		this.listActions = listAction;
		this.listItems = new ArrayList<Item>();
	}
	
	public String toString() {
		return this.nom;
	}
	
	/**
	 * in this method, the player do some actions possible depends of the situation in the room
	 * @param g AdventureGame
	 * @return Action
	 */
	public Action act(AdventureGame g){
		printGame(g);	
		List<Action> availableActions = new ArrayList<Action>();
		
		//a changer
		addActionPossible(g, availableActions);
		
		Action actionChoose = chooseAnAction(g, availableActions);
		
		if(actionChoose != null) {
			actionChoose.execute(g, this);
		}else
			this.act(g);
		
		if(!(actionChoose instanceof Move))
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return actionChoose;
	}

	/**
	 * Method to print to the game 
	 * @param g AdventureGame
	 */
	public void printGame(AdventureGame g) {
		g.getMenu().drawGame(g.getCurrentRoom(), 16);
		g.getMenu().stats(g.getPlayer());
	}

	/**
	 * method to add the action possible
	 * @param g AdventureGame
	 * @param availableActions
	 */
	public void addActionPossible(AdventureGame g, List<Action> availableActions) {

		for (Action action : this.getActionPossible()) {
			if(action.isPossible(g))
				availableActions.add(action);				
		}
	}

	/**
	 * method to choose an action by the player
	 * @param g AdventureGame
	 * @param availableActions List<Action>
	 * @return
	 */
	public Action chooseAnAction(AdventureGame g, List<Action> availableActions) {
		Action actionChoose = g.getMenu().choice("\n"+this.nom+"... What do you want to do ?\n", availableActions);
		return actionChoose;
	}
	
	/**
	 * method to get the actions
	 * @return List<Action>
	 */
	public List<Action> getActionPossible(){
		return this.listActions;
	}
	
	/**
	 * method to add an item
	 * @param i Item
	 */
	public void addItem(Item i) {
		listItems.add(i);
	}
	
	/**
	 * method to know if the player get item(s)
	 * @return boolean
	 */
	public boolean gotItem() {
		return this.listItems.size() > 0;
	}

	/**
	 * method to get the items
	 * @return LIst<Item>
	 */
	public List<Item> getItems() {
		return this.listItems;
	}
}
