package fil.coo.game;

import fil.coo.character.Player;

public class AdventureGame {

	Room currentRoom;
	Player player;
	Dungeon dungeon;
	
	public AdventureGame(Room currentRoom, Player player, Dungeon dungeon) {
		this.currentRoom = currentRoom;
		this.player = player;
		this.dungeon = dungeon;
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
