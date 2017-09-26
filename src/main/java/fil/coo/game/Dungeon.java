package fil.coo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import fil.coo.character.Monster;
import fil.coo.controller.Direction;

public class Dungeon {

	private Room[][] dungeon;
	private int taille;
	private boolean[][] voisinsVisité;
	private Random r = new Random();

	/**
	 * Constructor of Dungeon to initialize Array and get a random Room from the
	 * Dungeon
	 * 
	 * @param taille
	 *            int
	 */
	public Dungeon(int taille) {
		this.taille = taille;
		dungeon = new Room[taille][taille];
		voisinsVisité = new boolean[taille][taille];

	}

	/**
	 * method to get the dungeon
	 * 
	 * @return Room[][]
	 */
	public Room[][] getDungeon() {
		return dungeon;
	}

	/**
	 * method to the room in coordinnate 0-0
	 * 
	 * @return Room[][]
	 */
	public Room getBeginningRoom() {
		return dungeon[0][0];
	}

	/**
	 * Methods to initialize the dungeon with recursive backtracking method
	 */
	public void initializeDungeon() {

		// initialize all the rooms
		initializeArray();

		// choose a random room
		Room firstRoom = beginInARandomRoom();

		Stack<Room> stackRoom = new Stack<Room>();
		stackRoom.push(firstRoom);

		recursiveProceduration(stackRoom);

	}

	/**
	 * method to initialize the array dungeon and add some monsters and items
	 */
	public void initializeArray() {
		for (int y = 0; y < dungeon.length; y++) {
			for (int x = 0; x < dungeon[0].length; x++) {

				if (isExit(y, x))
					dungeon[y][x] = new Room(true, x, y);
				else
					dungeon[y][x] = new Room(false, x, y);

				initializeMonster(dungeon[y][x]);
				initializeItem(dungeon[y][x]);
				voisinsVisité[y][x] = false;
			}
		}
	}

	/**
	 * method to know if is the exit with the coordiante x and y
	 * 
	 * @param y
	 *            int
	 * @param x
	 *            int
	 * @return boolean
	 */
	public boolean isExit(int y, int x) {
		return x == taille - 1 && y == taille - 1;
	}

	/**
	 * method to add items or not in the room
	 * 
	 * @param room
	 */
	private void initializeItem(Room room) {
		// TODO Auto-generated method stub

	}

	/**
	 * method to add monsters or not in the room
	 * 
	 * @param room
	 */
	public void initializeMonster(Room room) {
		Monster monster;
		int nbMonster = r.nextInt(3);
		for (int i = 0; i < nbMonster; i++) {
			monster = new Monster("Romain", 20, 2, 5); // after create a random monster
			room.addMonster(monster);
		}
	}

	/**
	 * method to get an Random room in the dungeon
	 * 
	 * @return Room
	 */
	public Room beginInARandomRoom() {
		int xRandom = r.nextInt(dungeon.length);
		int yRandom = r.nextInt(dungeon.length);
		Room firstRoom = dungeon[yRandom][xRandom];
		voisinsVisité[firstRoom.getY()][firstRoom.getX()] = true;
		return firstRoom;
	}

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * 
	 * @param stackRoom
	 * @throws InterruptedException
	 */
	public void recursiveProceduration(Stack<Room> stackRoom) {
		if (!stackRoom.isEmpty()) {

			Room currentRoom = stackRoom.peek();
			List<Room> listRoomAdjacent = adjacentRoomsNotVisited(currentRoom);

			if (!listRoomAdjacent.isEmpty()) {

				int roomRandom = r.nextInt(listRoomAdjacent.size());
				Room neighbourRoom = listRoomAdjacent.get(roomRandom);
				linkNeighbourRoom(currentRoom, neighbourRoom);

				stackRoom.push(neighbourRoom);
			} else {
				stackRoom.pop();
			}
			recursiveProceduration(stackRoom);
		}
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

			verifyNorth(currentRoom, listRooms, listDirectionPossible, i);

			verifyEast(currentRoom, listRooms, listDirectionPossible, i);

			verifySouth(currentRoom, listRooms, listDirectionPossible, i);

			verifyWest(currentRoom, listRooms, listDirectionPossible, i);
		}

		return listRooms;
	}

	/**
	 * method to verify if we can link the room on the west
	 * 
	 * @param currentRoom
	 *            Room
	 * @param listRooms
	 *            list<Room>
	 * @param listDirectionPossible
	 *            list<Direction>
	 * @param i
	 *            int
	 */
	public void verifyWest(Room currentRoom, List<Room> listRooms, List<Direction> listDirectionPossible, int i) {
		if (listDirectionPossible.get(i) == Direction.WEST) {
			if (!voisinsVisité[currentRoom.getY()][currentRoom.getX() - 1]) {
				listRooms.add(dungeon[currentRoom.getY()][currentRoom.getX() - 1]);
			}
		}
	}

	/**
	 * method to verify if we can link the room on the south
	 * 
	 * @param currentRoom
	 *            Room
	 * @param listRooms
	 *            list<Room>
	 * @param listDirectionPossible
	 *            list<Direction>
	 * @param i
	 *            int
	 */
	public void verifySouth(Room currentRoom, List<Room> listRooms, List<Direction> listDirectionPossible, int i) {
		if (listDirectionPossible.get(i) == Direction.SOUTH) {
			if (!voisinsVisité[currentRoom.getY() + 1][currentRoom.getX()]) {
				listRooms.add(dungeon[currentRoom.getY() + 1][currentRoom.getX()]);
			}
		}
	}

	/**
	 * method to verify if we can link the room on the east
	 * 
	 * @param currentRoom
	 *            Room
	 * @param listRooms
	 *            list<Room>
	 * @param listDirectionPossible
	 *            list<Direction>
	 * @param i
	 *            int
	 */
	public void verifyEast(Room currentRoom, List<Room> listRooms, List<Direction> listDirectionPossible, int i) {
		if (listDirectionPossible.get(i) == Direction.EAST) {
			if (!voisinsVisité[currentRoom.getY()][currentRoom.getX() + 1]) {
				listRooms.add(dungeon[currentRoom.getY()][currentRoom.getX() + 1]);
			}
		}
	}

	/**
	 * method to verify if we can link the room on the north
	 * 
	 * @param currentRoom
	 *            Room
	 * @param listRooms
	 *            list<Room>
	 * @param listDirectionPossible
	 *            list<Direction>
	 * @param i
	 *            int
	 */
	public void verifyNorth(Room currentRoom, List<Room> listRooms, List<Direction> listDirectionPossible, int i) {
		if (listDirectionPossible.get(i) == Direction.NORTH) {
			if (!voisinsVisité[currentRoom.getY() - 1][currentRoom.getX()]) {
				listRooms.add(dungeon[currentRoom.getY() - 1][currentRoom.getX()]);
			}
		}
	}

	/**
	 * method to get the direction possible of the current room
	 * 
	 * @return list<Direction>
	 */
	public List<Direction> directionPossible(Room currentRoom) {
		List<Direction> listDirection = new ArrayList<Direction>();

		addAlldirections(listDirection);

		removeImpossibleDirection(currentRoom, listDirection);

		return listDirection;
	}

	/**
	 * method to add all the directions in a List
	 * 
	 * @param listDirection
	 *            List<Direction>
	 */
	public void addAlldirections(List<Direction> listDirection) {
		listDirection.add(Direction.NORTH);
		listDirection.add(Direction.EAST);
		listDirection.add(Direction.SOUTH);
		listDirection.add(Direction.WEST);
	}

	/**
	 * method to remove a direction from if we can't move here
	 * 
	 * @param currentRoom
	 * @param listDirection
	 */
	public void removeImpossibleDirection(Room currentRoom, List<Direction> listDirection) {
		if (currentRoom.getY() == taille - 1)
			listDirection.remove(Direction.SOUTH);
		if (currentRoom.getY() == 0)
			listDirection.remove(Direction.NORTH);
		if (currentRoom.getX() == taille - 1)
			listDirection.remove(Direction.EAST);
		if (currentRoom.getX() == 0)
			listDirection.remove(Direction.WEST);
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

		if (currentRoom.getY() + 1 == neighbourRoom.getY() && currentRoom.getX() == neighbourRoom.getX()) {
			currentRoom.addDirection(Direction.SOUTH, neighbourRoom);
			neighbourRoom.addDirection(Direction.NORTH, currentRoom);
		}

		if (currentRoom.getY() - 1 == neighbourRoom.getY() && currentRoom.getX() == neighbourRoom.getX()) {
			currentRoom.addDirection(Direction.NORTH, neighbourRoom);
			neighbourRoom.addDirection(Direction.SOUTH, currentRoom);
		}

		if (currentRoom.getY() == neighbourRoom.getY() && currentRoom.getX() + 1 == neighbourRoom.getX()) {
			currentRoom.addDirection(Direction.EAST, neighbourRoom);
			neighbourRoom.addDirection(Direction.WEST, currentRoom);
		}

		if (currentRoom.getY() == neighbourRoom.getY() && currentRoom.getX() - 1 == neighbourRoom.getX()) {
			currentRoom.addDirection(Direction.WEST, neighbourRoom);
			neighbourRoom.addDirection(Direction.EAST, currentRoom);
		}

		voisinsVisité[neighbourRoom.getY()][neighbourRoom.getX()] = true;
	}
}
