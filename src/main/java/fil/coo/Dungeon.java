package fil.coo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Dungeon {

	
	private Room[][] dungeon;
	private int taille;
	private boolean[][] voisinsVisité;
	private Random r = new Random();

	/**
	 * Constructor of Dungeon to initialize Array and get a random Room from the
	 * Dungeon
	 * 
	 * @param taille int
	 */
	public Dungeon(int taille) {
		this.taille = taille;
		dungeon = new Room[taille][taille];
		voisinsVisité = new boolean[taille][taille];

	}
	
	/**
	 * method to get the dungeon
	 * @return Room[][]
	 */
	public Room[][] getDungeon() {
		return dungeon;
	}
	
	/**
	 * Methods to initialize the dungeon with recursive backtracking method
	 */
	public void initializeDungeon() {

		Map<Direction, Room> neighbours = new HashMap<Direction, Room>();
		List<Item> listItems = new ArrayList<Item>();
		List<Monster> listMonsters = new ArrayList<Monster>();

		// initialize all the rooms
		for (int x = 0; x < dungeon.length; x++) {
			for (int y = 0; y < dungeon.length; y++) {
				// can add monster and items in the list
				// to do
				if (x == taille - 1 && y == taille - 1)
					dungeon[x][y] = new Room(listMonsters, listItems, neighbours, true);
				else
					dungeon[x][y] = new Room(listMonsters, listItems, neighbours, true);
				// remove all monsters and items in the list

			}
		}

		// choose a random room
		int xRandom = r.nextInt(dungeon.length);
		int yRandom = r.nextInt(dungeon.length);
		Room firstRoom = dungeon[xRandom][yRandom];
		voisinsVisité[firstRoom.getX()][firstRoom.getY()] = true;

		Stack<Room> stackRoom = new Stack();
		stackRoom.push(firstRoom);

		recursiveProceduration(stackRoom);

	}

	/**
	 * Method to get the adjacent rooms of a room
	 * 
	 * @param currentRoom
	 * @return Map<Direction, Room>
	 */
	public List<Room> adjacentRoomsNotVisited(Room currentRoom) {

		List<Room> listRooms = new ArrayList<Room>();
		List<Direction> listDirectionPossible = directionPossible(currentRoom);

		for (int i = 0; i < listDirectionPossible.size(); i++) {

			if (listDirectionPossible.get(i) == Direction.NORTH) {
				if (!voisinsVisité[currentRoom.getX() - 1][currentRoom.getY()]) {
					listRooms.add(dungeon[currentRoom.getX() - 1][currentRoom.getY()]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.EAST) {
				if (!voisinsVisité[currentRoom.getX()][currentRoom.getY() + 1]) {
					listRooms.add(dungeon[currentRoom.getX()][currentRoom.getY() + 1]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.SOUTH) {
				if (!voisinsVisité[currentRoom.getX() + 1][currentRoom.getY()]) {
					listRooms.add(dungeon[currentRoom.getX() + 1][currentRoom.getY()]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.WEST) {
				if (!voisinsVisité[currentRoom.getX()][currentRoom.getY() - 1]) {
					listRooms.add(dungeon[currentRoom.getX()][currentRoom.getY() - 1]);
				}
			}
		}

		return listRooms;
	}

	/**
	 * method to get the direction possible of the current room
	 * 
	 * @return list<Direction>
	 */
	private List<Direction> directionPossible(Room currentRoom) {
		List<Direction> listDirection = new ArrayList<Direction>();

		listDirection.add(Direction.NORTH);
		listDirection.add(Direction.EAST);
		listDirection.add(Direction.SOUTH);
		listDirection.add(Direction.WEST);

		// right
		if (currentRoom.getX() == taille - 1)
			listDirection.remove(Direction.EAST);
		// left
		if (currentRoom.getX() == 0)
			listDirection.remove(Direction.WEST);
		// bottom
		if (currentRoom.getY() == taille - 1)
			listDirection.remove(Direction.SOUTH);
		// top
		if (currentRoom.getY() == 0)
			listDirection.remove(Direction.NORTH);

		return listDirection;
	}

	/**
	 * method to link 2 rooms
	 * 
	 * @param currentRoom
	 *            Room
	 * @param neighbourRoom
	 *            Room
	 */
	public void linkNeighbourRoom(Room currentRoom, Room neighbourRoom) {

		if (currentRoom.getX() + 1 == neighbourRoom.getX() && currentRoom.getY() == neighbourRoom.getY()) {
			dungeon[currentRoom.getX()][currentRoom.getY()].addDirection(Direction.SOUTH, neighbourRoom);
			dungeon[neighbourRoom.getX()][neighbourRoom.getY()].addDirection(Direction.NORTH, currentRoom);
		}
		
		if (currentRoom.getX() - 1 == neighbourRoom.getX() && currentRoom.getY() == neighbourRoom.getY()) {
			dungeon[currentRoom.getX()][currentRoom.getY()].addDirection(Direction.NORTH, neighbourRoom);
			dungeon[neighbourRoom.getX()][neighbourRoom.getY()].addDirection(Direction.SOUTH, currentRoom);
		}
		
		if (currentRoom.getX() == neighbourRoom.getX() && currentRoom.getY() + 1 == neighbourRoom.getY()) {
			dungeon[currentRoom.getX()][currentRoom.getY()].addDirection(Direction.EAST, neighbourRoom);
			dungeon[neighbourRoom.getX()][neighbourRoom.getY()].addDirection(Direction.WEST, currentRoom);
		}
		
		if (currentRoom.getX() + 1 == neighbourRoom.getX() && currentRoom.getY() -1 == neighbourRoom.getY()) {
			dungeon[currentRoom.getX()][currentRoom.getY()].addDirection(Direction.WEST, neighbourRoom);
			dungeon[neighbourRoom.getX()][neighbourRoom.getY()].addDirection(Direction.EAST, currentRoom);
		}
		
		voisinsVisité[neighbourRoom.getX()][neighbourRoom.getY()] = true;
		
	}

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * 
	 * @param stackRoom
	 */
	public void recursiveProceduration(Stack<Room> stackRoom) {

		if (!stackRoom.isEmpty()) {

			Room currentRoom = stackRoom.peek();
			List<Room> listRoomAdjacent = adjacentRoomsNotVisited(currentRoom);

			if (!listRoomAdjacent.isEmpty()) {

				int roomRandom = r.nextInt(adjacentRoomsNotVisited(currentRoom).size());
				Room neighbourRoom = adjacentRoomsNotVisited(currentRoom).get(roomRandom);
				linkNeighbourRoom(currentRoom, neighbourRoom);

				stackRoom.push(neighbourRoom);

				recursiveProceduration(stackRoom);
			} else {
				stackRoom.pop();
				recursiveProceduration(stackRoom);
			}
		}
	}

}
