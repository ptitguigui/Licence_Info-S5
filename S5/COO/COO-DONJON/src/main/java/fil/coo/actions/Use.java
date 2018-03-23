package fil.coo.actions;

import java.util.List;

import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.items.Item;

public class Use implements Action{

	/**
	 * Method to execute the use action
	 * @param g AdventureGame
	 * @param player Player
	 */
	public void execute(AdventureGame g, Player player) {
		Item i = chooseAnItem(g, player);
		
		if(i != null) {
			i.use(player);
			player.getItems().remove(i);
		}else
			player.act(g);
	}

	/**
	 * Method to choose an item from a list
	 * @param g AdventureGame
	 * @param player Player
	 * @return Item
	 */
	public Item chooseAnItem(AdventureGame g, Player player) {
		List<Item> listItems = player.getItems();
		
		Item i = g.getMenu().choice("\n What item do you want use ?\n", listItems);
		return i;
	}

	/**
	 * Method to know if the player can use something in another room
	 * @param g AdventureGame
	 * @return boolean
	 */
	public boolean isPossible(AdventureGame g) {
		return g.getPlayer().gotItem();
	}
	
	public String toString() {
		return "Use an item";
	}
}
