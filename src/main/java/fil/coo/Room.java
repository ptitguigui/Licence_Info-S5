package fil.coo;

import java.util.List;
import java.util.Map;

public class Room {

	private int x;
	private int y;
	private List<Monster> listMonsters;
	private List<Item> listItems;
	private Map<Direction, Room> neighbours;
	private boolean isExit;
	
	
	public Room(List<Monster> listMonsters, List<Item> listItems, Map<Direction, Room> neighbours, boolean isExit) {
		super();
		this.listMonsters = listMonsters;
		this.listItems = listItems;
		this.neighbours = neighbours;
		this.isExit = isExit;
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
