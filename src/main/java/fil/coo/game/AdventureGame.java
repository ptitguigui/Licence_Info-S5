package fil.coo.game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fil.coo.character.Player;
import fil.coo.controller.Action;
import fil.coo.controller.Attack;
import fil.coo.controller.Direction;
import fil.coo.controller.Look;
import fil.coo.controller.Move;
import fil.coo.controller.Use;
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
		this.menu.drawGame(this.currentRoom, 17);
		while (!isFinished()) {

			this.menu.stats(this.player);
			this.player.act(this);
			
		}
		
		System.out.println("Congratulation you win with "+this.player.getGold()+" gold !");
	}

	public boolean isFinished() {
		return (player.isAlive() && currentRoom.isExit()) || !(player.isAlive());
	}

	public void playerMoveTo(Direction d) {
		currentRoom = currentRoom.getNeighbours().get(d);
	}
}
