package fil.coo.game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fil.coo.character.Player;
import fil.coo.controller.Action;
import fil.coo.controller.Attack;
import fil.coo.controller.Direction;
import fil.coo.controller.Move;
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
	
	public Menu getMenu(){
		return this.menu;
	}
	
	public Dungeon getDungeon(){
		
		return this.dungeon;
	}

	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	public void play() {
		while (!isFinished()) {

			Scanner scanner = new Scanner(System.in);
			this.menu.drawGame(this.currentRoom, 17);
			this.menu.stats(this.player);
			
			//act move... need to change
			/*Direction d = this.menu.choice("What direction ?", currentRoom.getDirections());
			playerMoveTo(d);*/
			this.player.act(this);
			
		}
	}

	public boolean isFinished() {
		return (player.isAlive() && currentRoom.isExit()) || !(player.isAlive());
	}

	public void playerMoveTo(Direction d) {
		currentRoom = currentRoom.getNeighbours().get(d);
	}

	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(5);
		dungeon.initializeDungeon();
		List<Action> listActions = Arrays.asList(new Attack(), new Move());
		Player player = new Player("Guillaume", 200, 10, 0, listActions);
		Menu menu = new Menu();
		AdventureGame game = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
		game.play();
	}

}
