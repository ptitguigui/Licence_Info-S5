package fil.coo.controller;

import java.util.List;

import fil.coo.character.Monster;
import fil.coo.character.Player;
import fil.coo.component.Item;
import fil.coo.game.AdventureGame;

public class Look implements Action {

	public void execute(AdventureGame g, Player player) {
		findMonsters(g);		
		findItems(g, player);
	}


	public void findMonsters(AdventureGame g) {
		List<Monster> listMonster = g.getCurrentRoom().getMonsters();
		
		if(listMonster.isEmpty()) {
			System.out.println("That luck ! There is no monster here");
		}else {
			System.out.println("The different monsters present in the room:");
			for (Monster monster : listMonster) {
				System.out.println("\t-"+monster);
			}
		}
	}
	
	public void findItems(AdventureGame g, Player player) {
		List<Item> listItems = g.getCurrentRoom().getItems();
		
		if( listItems.isEmpty()) {
			System.out.println("There is no item here...");
		}else {
			System.out.println("You find some items:");
			for (Item i : listItems) {
				System.out.println("\t-"+i);
				player.addItem(i);
				g.getCurrentRoom().removeAllItems();;
				
			}
		}
	}

	public boolean isPossible(AdventureGame g) {
		return true;
	}
	
	public String toString() {
		return "Look around yourself";
	}

}
