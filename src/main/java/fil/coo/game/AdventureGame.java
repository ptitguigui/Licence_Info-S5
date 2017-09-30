package fil.coo.game;

import fil.coo.character.Player;
import fil.coo.util.Menu;

public class AdventureGame {

	private Room currentRoom;
	private Player player;
	private Dungeon dungeon;
	private Menu menu;

	/**
	 * Method to create the game
	 * @param currentRoom Room
	 * @param player Player
	 * @param dungeon Dungeon
	 * @param menu Menu
	 */
	public AdventureGame(Room currentRoom, Player player, Dungeon dungeon, Menu menu) {
		this.currentRoom = currentRoom;
		this.player = player;
		this.dungeon = dungeon;
		this.menu = menu;
	}
	
	/**
	 * Method to get the menu
	 * @return Menu
	 */
	public Menu getMenu(){
		return this.menu;
	}
	
	/**
	 * Method to get the dungeon
	 * @return Dungeon
	 */
	public Dungeon getDungeon(){
		
		return this.dungeon;
	}

	/**
	 * Method to get the current room
	 * @return Room
	 */
	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	/**
	 * Method to get the player
	 * @return Player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Method to play the game
	 */
	public void play() {
		while (!isFinished()) {
			this.player.act(this);		
		}
		
		win();
	}

	/**
	 * Method to know if the game is finished
	 * @return
	 */
	public boolean isFinished() {
		return (player.isAlive() && currentRoom.isExit()) || !(player.isAlive());
	}

	/**
	 * Method to move the player in a direction
	 * @param d Direction
	 */
	public void playerMoveTo(Direction d) {
		currentRoom = currentRoom.getNeighbours().get(d);
	}
	
	/**
	 * Method to congrats the player when he wins
	 */
	public void win() {
		System.out.println("\nCongratulation you win with "+this.player.getGold()+" gold !!!! \n");
	}
}
