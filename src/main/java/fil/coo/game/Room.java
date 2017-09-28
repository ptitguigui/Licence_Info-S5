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
	
	public List<Direction> getDirections(){
		List<Direction> listDirections = new ArrayList<Direction>();
	
		verifyDirections(listDirections);
		
		return listDirections;
	}

	private void verifyDirections(List<Direction> listDirections) {
		if(this.neighbours.containsKey(Direction.NORTH))
			listDirections.add(Direction.NORTH);
		if(this.neighbours.containsKey(Direction.SOUTH))
			listDirections.add(Direction.SOUTH);
		if(this.neighbours.containsKey(Direction.EAST))
			listDirections.add(Direction.EAST);
		if(this.neighbours.containsKey(Direction.WEST))
			listDirections.add(Direction.WEST);
	}
	
	public void addMonster(Monster monster) {
		this.listMonsters.add(monster);
	}
	
	public boolean hasMonster() {
		return this.listMonsters.size() > 0;
	}
	public List<Monster> getMonsters(){
		return this.listMonsters;
	}
	
	public void addItem(Item item) {
		this.listItems.add(item);
	}
	
	public boolean hasItem() {
		return this.listItems.size() > 0;
	}
	
	public List<Item> getItems(){
		return this.listItems;
	}
	
	public void removeAllItems() {
		this.listItems = new ArrayList<Item>();
	}
	
}
