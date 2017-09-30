package fil.coo.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fil.coo.character.Monster;
import fil.coo.items.Item;

public class Room {

	private int x;
	private int y;
	private List<Monster> listMonsters;
	private List<Item> listItems;
	private Map<Direction, Room> neighbours;
	private boolean isExit;
	
	/**
	 * Method to create a room
	 * @param isExit boolean
	 * @param x int
	 * @param y int
	 */
	public Room(boolean isExit, int x, int y) {
		listItems = new ArrayList<Item>();
		listMonsters = new ArrayList<Monster>();
		neighbours = new HashMap<Direction, Room>();
		this.isExit = isExit;
		this.x = x;
		this.y = y;
	}

	/**
	 * Method to know if the room is an exit
	 * @return boolean
	 */
	public boolean isExit() {
		return isExit;
	}
	
	/**
	 * Method to get the coordinate x
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method to get the coordinate y
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * Method get the neighbours
	 * @return Map<Direction, Room>
	 */
	public Map<Direction, Room> getNeighbours() {
		return neighbours;
	}
	
	/**
	 * Method to add a direction in a room
	 * @param direction Direction
	 * @param neighbour Room
	 */
	public void addDirection(Direction direction, Room neighbour){
		this.neighbours.put(direction, neighbour);
	}

	/**
	 * Method to know if the room has linkded with the neighbour
	 * @param direction Direction
	 * @return boolean
	 */
	public boolean hasLinkedNeighbourForDirection(Direction direction) {
		return neighbours.containsKey(direction);
	}
	
	/**
	 * Method to get the directions possible of the room
	 * @return List<Direction>
	 */
	public List<Direction> getDirections(){
		List<Direction> listDirections = new ArrayList<Direction>();
	
		verifyDirections(listDirections);
		
		return listDirections;
	}

	/** Method to verify the direction possible
	 * @param listDirections List<Direction>
	 */
	public void verifyDirections(List<Direction> listDirections) {
		if(this.neighbours.containsKey(Direction.NORTH))
			listDirections.add(Direction.NORTH);
		if(this.neighbours.containsKey(Direction.SOUTH))
			listDirections.add(Direction.SOUTH);
		if(this.neighbours.containsKey(Direction.EAST))
			listDirections.add(Direction.EAST);
		if(this.neighbours.containsKey(Direction.WEST))
			listDirections.add(Direction.WEST);
	}
	
	/**
	 * Method to add a monster in a room
	 * @param monster Monster
	 */
	public void addMonster(Monster monster) {
		this.listMonsters.add(monster);
	}
	
	/**
	 * Method to know if a monster is in the room
	 * @return boolean
	 */
	public boolean hasMonster() {
		return this.listMonsters.size() > 0;
	}
	
	/**
	 * Method to get the monsters from the room
	 * @return List<Monster>
	 */
	public List<Monster> getMonsters(){
		return this.listMonsters;
	}
	
	/**
	 * Method to remove all monster from the room
	 */
	public void removeAllMonsters() {
		this.listMonsters = new ArrayList<Monster>();
	}
	
	/**
	 * Method to add an item in the room
	 * @param item Item
	 */
	public void addItem(Item item) {
		this.listItems.add(item);
	}
	
	/**
	 * Method to know if an item is in the room
	 * @return boolean
	 */
	public boolean hasItem() {
		return this.listItems.size() > 0;
	}
	
	/**
	 * Method to get the items from the room
	 * @return List<Item>
	 */
	public List<Item> getItems(){
		return this.listItems;
	}
	
	/**
	 * Method to remove all item from the room
	 */
	public void removeAllItems() {
		this.listItems = new ArrayList<Item>();
	}
	
}
