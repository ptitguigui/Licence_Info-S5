package fil.coo.main;

import java.util.Arrays;
import java.util.List;

import fil.coo.character.Player;
import fil.coo.controller.Action;
import fil.coo.controller.Attack;
import fil.coo.controller.Look;
import fil.coo.controller.Move;
import fil.coo.controller.Use;
import fil.coo.game.AdventureGame;
import fil.coo.game.Dungeon;
import fil.coo.util.Menu;

public class App {

	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(5);
		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		Player player = new Player("Guillaume", 200, 10, 0, listActions);
		Menu menu = new Menu();
		
		dungeon.initializeDungeon();
		
		AdventureGame game = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
		game.play();
	}
}
