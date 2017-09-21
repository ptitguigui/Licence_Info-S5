package fil.coo.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fil.coo.character.Monster;
import fil.coo.component.Item;
import fil.coo.controller.Direction;

public class Room {

	private int x;
	private int y;
	private List<Monster> listMonsters;
	private List<Item> listItems;
	private Map<Direction, Room> neighbours;
	private boolean isExit;
	
	
	public Room(boolean isExit, int x, int y) {
		listItems = new ArrayList<Item>();
		listMonsters = new ArrayList<Monster>();
		neighbours = new HashMap<Direction, Room>();
		this.isExit = isExit;
		this.x = x;
		this.y = y;
	}

	public boolean isExit() {
		return isExit;
	}
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public Map<Direction, Room> getNeighbours() {
		return neighbours;
	}
	
	public void addDirection(Direction direction, Room neighbour){
		this.neighbours.put(direction, neighbour);
	}

	public boolean hasLinkedNeighbourForDirection(Direction direction) {
		return neighbours.containsKey(direction);
	}

	
}
