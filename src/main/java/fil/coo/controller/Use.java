package fil.coo.controller;

import java.util.List;

import fil.coo.character.Player;
import fil.coo.component.Item;
import fil.coo.game.AdventureGame;

public class Use implements Action{

	public void execute(AdventureGame g, Player player) {
		Item i = chooseAnItem(g, player);
		
		if(i != null) {
			i.use(player);
		}else
			player.act(g);
	}

	private Item chooseAnItem(AdventureGame g, Player player) {
		List<Item> listItems = player.getItems();
		
		Item i = g.getMenu().choice("\n What item do you want use ?\n", listItems);
		return i;
	}

	public boolean isPossible(AdventureGame g) {
		return g.getPlayer().gotItem();
	}
	
	public String toString() {
		return "Use an item";
	}
}
