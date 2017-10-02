package fil.coo.actions;

import java.util.List;

import fil.coo.character.Monster;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.items.Item;

public class Look implements Action {


	/**
	 * Method to execute the look action
	 * @param g AdventureGame
	 * @param player Player
	 */
	public void execute(AdventureGame g, Player player) {
		findMonsters(g);		
		findItems(g, player);
	}


	/**
	 * Method to show if some monsters are in the room
	 * @param g AdventureGame
	 */
	public List<Monster> findMonsters(AdventureGame g) {
		List<Monster> listMonster = g.getCurrentRoom().getMonsters();
		
		if(listMonster.isEmpty()) {
			System.out.println("That luck ! There is no monster here");
		}else {
			System.out.println("The different monsters present in the room:");
			for (Monster monster : listMonster) {
				System.out.println("\t-"+monster +": "+ monster.getHp()+" hp");
			}
		}
		return listMonster;
	}
	
	/**
	 * Method to show if some item are in the room, the player get the items
	 * @param g
	 * @param player
	 */
	public List<Item> findItems(AdventureGame g, Player player) {
		List<Item> listItems = g.getCurrentRoom().getItems();
		
		if( listItems.isEmpty()) {
			System.out.println("There is no item here...");
		}else {
			System.out.println("You find some items:");
			for (Item i : listItems) {
				System.out.println("\t-"+i);
				player.addItem(i);				
			}
			g.getCurrentRoom().removeAllItems();
		}
		return listItems;
	}

	/**
	 * Method to know if the player can Look the room
	 * @param g AdventureGame
	 * @return boolean
	 */
	public boolean isPossible(AdventureGame g) {
		return true;
	}
	
	public String toString() {
		return "Look around yourself";
	}

}
