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
import fil.coo.game.DungeonFrame;
import fil.coo.util.Menu;

public class App {

	public static void main(String[] args) {

		Dungeon dungeon = initDungeon(args);
		
		showDungeon(dungeon, 6000);

		Player player = initPlayer();

		playTheGame(dungeon, player);
	}

	private static void playTheGame(Dungeon dungeon, Player player) {
		Menu menu = new Menu();

		AdventureGame game = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
		game.play();
	}

	private static Player initPlayer() {
		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		Player player = new Player("Guillaume", 200, 10, 0, listActions);
		return player;
	}

	private static void showDungeon(Dungeon dungeon, int tps) {
		DungeonFrame frame = new DungeonFrame(dungeon);
		
		try {
			Thread.sleep(tps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		frame.dispose();
	}

	private static Dungeon initDungeon(String[] args) {
		int taille = 5;
		if(args.length == 1) {
			String arg0 = args[0];
			taille = getTailleofDungeon(args.length, arg0);
		}
		
		Dungeon dungeon = new Dungeon(taille);
		dungeon.initializeDungeon();
		return dungeon;
	}

	public static int getTailleofDungeon(int nb, String arg0) {
		int taille = 5;
		try {
			taille = Integer.parseInt(arg0);
		} catch (NumberFormatException e) {
			taille = 5;
		}

		return taille;
	}
}
