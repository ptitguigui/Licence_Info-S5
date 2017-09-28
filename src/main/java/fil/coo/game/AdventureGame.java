package fil.coo.game;

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
	
	public Menu getMenu(){
		return this.menu;
	}
	
	public Dungeon getDungeon(){
		
		return this.dungeon;
	}

	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void play() {
		while (!isFinished()) {
			this.player.act(this);		
		}
		
		win();
	}

	public boolean isFinished() {
		return (player.isAlive() && currentRoom.isExit()) || !(player.isAlive());
	}

	public void playerMoveTo(Direction d) {
		currentRoom = currentRoom.getNeighbours().get(d);
	}
	
	public void win() {
		System.out.println("\nCongratulation you win with "+this.player.getGold()+" gold !!!! \n");
	}
}
