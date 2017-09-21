package fil.coo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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
	 * Methods to initialize the dungeon with recursive backtracking method
	 */
	public void initializeDungeon() {

		// initialize all the rooms
		for (int y = 0; y < dungeon.length; y++) {
			for (int x = 0; x < dungeon[0].length; x++) {
				// can add monster and items in the list
				// to do
				if (x == taille - 1 && y == taille - 1)
					dungeon[y][x] = new Room(true, x, y);
				else
					dungeon[y][x] = new Room(false, x, y);
				// remove all monsters and items in the list
				voisinsVisité[y][x] = false;

			}
		}

		// choose a random room
		int xRandom = r.nextInt(dungeon.length);
		int yRandom = r.nextInt(dungeon.length);
		Room firstRoom = dungeon[yRandom][xRandom];
		voisinsVisité[firstRoom.getY()][firstRoom.getX()] = true;

		Stack<Room> stackRoom = new Stack<Room>();
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
				if (!voisinsVisité[currentRoom.getY() - 1][currentRoom.getX()]) {
					listRooms.add(dungeon[currentRoom.getY() - 1][currentRoom.getX()]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.EAST) {
				if (!voisinsVisité[currentRoom.getY()][currentRoom.getX() + 1]) {
					listRooms.add(dungeon[currentRoom.getY()][currentRoom.getX() + 1]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.SOUTH) {
				if (!voisinsVisité[currentRoom.getY() + 1][currentRoom.getX()]) {
					listRooms.add(dungeon[currentRoom.getY() + 1][currentRoom.getX()]);
				}
			}

			if (listDirectionPossible.get(i) == Direction.WEST) {
				if (!voisinsVisité[currentRoom.getY()][currentRoom.getX() - 1]) {
					listRooms.add(dungeon[currentRoom.getY()][currentRoom.getX() - 1]);
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

		if (currentRoom.getY() == taille - 1)
			listDirection.remove(Direction.SOUTH);
		if (currentRoom.getY() == 0)
			listDirection.remove(Direction.NORTH);
		if (currentRoom.getX() == taille - 1)
			listDirection.remove(Direction.EAST);
		if (currentRoom.getX() == 0)
			listDirection.remove(Direction.WEST);

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

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * 
	 * @param stackRoom
	 * @throws InterruptedException
	 */
	public void recursiveProceduration(Stack<Room> stackRoom){
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
}
