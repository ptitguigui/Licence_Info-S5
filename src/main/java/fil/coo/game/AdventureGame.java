package fil.coo.game;

import java.util.Scanner;

import fil.coo.character.Player;
import fil.coo.controller.Direction;
import fil.coo.util.Menu;

public class AdventureGame {

	private Room currentRoom;
	private Player player;
	private Dungeon dungeon;
	private Menu menu;

	public AdventureGame(Room currentRoom, Player player, Dungeon dungeon, Menu menu) {
		this.currentRoom = currentRoom;
		this.player = player;
		this.dungeon = dungeon;
		this.menu = menu;
	}

	public void play() {
		while (!isFinished()) {

			Scanner scanner = new Scanner(System.in);
			this.menu.drawGame(this.currentRoom);
			this.menu.stats(this.player);
			
			Direction d = this.menu.choice("What direction ?", currentRoom.getDirections());
		}
	}

	public boolean isFinished() {
		return (player.isAlive() && currentRoom.isExit()) || !(player.isAlive());
	}

	public void playerMoveTo(Direction d) {

	}

	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(5);
		dungeon.initializeDungeon();
		Player player = new Player(200, 10, 0);
		Menu menu = new Menu();
		AdventureGame game = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
		game.play();
	}

}
