package fil.coo.main;

import java.util.Arrays;
import java.util.List;

import fil.coo.actions.Action;
import fil.coo.actions.Attack;
import fil.coo.actions.Look;
import fil.coo.actions.Move;
import fil.coo.actions.Use;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.game.Dungeon;
import fil.coo.util.DungeonFrame;
import fil.coo.util.Menu;

public class App {

	public static void main(String[] args) {

		Dungeon dungeon = initDungeon(args);
		
		showDungeon(dungeon, 6000);

		Player player = initPlayer();

		playTheGame(dungeon, player);
	}

	/**
	 * Method to launch the game
	 * @param dungeon Dungeon
	 * @param player Player
	 */
	private static void playTheGame(Dungeon dungeon, Player player) {
		Menu menu = new Menu();

		AdventureGame game = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
		game.play();
	}

	/**
	 * method to init the player
	 * @return Player
	 */
	private static Player initPlayer() {
		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		Player player = new Player("Guillaume", 200, 10, 0, listActions);
		return player;
	}

	/**
	 * Method to display the Dungeon in graphical before play the game
	 * @param dungeon Dungeon
	 * @param time int
	 */
	private static void showDungeon(Dungeon dungeon, int time) {
		DungeonFrame frame = new DungeonFrame(dungeon);
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		frame.dispose();
	}

	/**
	 * Method to init the dungeon
	 * @param args String[]
	 * @return Dungeon
	 */
	private static Dungeon initDungeon(String[] args) {
		int taille = 5;
		if(args.length == 1) {
			String arg0 = args[0];
			taille = getSizeeofDungeon(args.length, arg0);
		}
		
		Dungeon dungeon = new Dungeon(taille);
		return dungeon;
	}

	/**
	 * Method to get the size of Dungeon
	 * @param nb int 
	 * @param arg0 String[]
	 * @return int
	 */
	public static int getSizeeofDungeon(int nb, String arg0) {
		int size = 5;
		try {
			size = Integer.parseInt(arg0);
		} catch (NumberFormatException e) {
			size = 5;
		}
		
		if(size >= 5)
			return size;
		else 
			return 5;
	}
}
