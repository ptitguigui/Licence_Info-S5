package fil.coo;

import java.util.List;
import java.util.Map;

public class Room {

	List<Monster> listMonsters;
	List<Item> listItems;
	Map<Direction, Room> neighbours;
	boolean isExit;
	
	
	public Room(List<Monster> listMonsters, List<Item> listItems, Map<Direction, Room> neighbours, boolean isExit) {
		super();
		this.listMonsters = listMonsters;
		this.listItems = listItems;
		this.neighbours = neighbours;
		this.isExit = isExit;
	}


	public Room(Map<Direction, Room> neighbours, boolean isExit) {
		super();
		this.neighbours = neighbours;
		this.isExit = isExit;
	}

	public boolean isExit() {
		return isExit;
	}		
	
}
