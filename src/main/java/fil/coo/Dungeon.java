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
	DungeonFrame frame;

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

		Map<Direction, Room> neighbours = new HashMap<Direction, Room>();
		List<Item> listItems = new ArrayList<Item>();
		List<Monster> listMonsters = new ArrayList<Monster>();

		// initialize all the rooms
		for (int y = 0; y < dungeon.length; y++) {
			for (int x = 0; x < dungeon.length; x++) {
				// can add monster and items in the list
				// to do
				if (x == taille - 1 && y == taille - 1)
					dungeon[y][x] = new Room(listMonsters, listItems, neighbours, true, x, y);
				else
					dungeon[y][x] = new Room(listMonsters, listItems, neighbours, false, x, y);
				// remove all monsters and items in the list

			}
		}

		// choose a random room
		int xRandom = r.nextInt(dungeon.length);
		int yRandom = r.nextInt(dungeon.length);
		Room firstRoom = dungeon[yRandom][xRandom];
		voisinsVisité[firstRoom.getY()][firstRoom.getX()] = true;

		Stack<Room> stackRoom = new Stack<Room>();
		stackRoom.push(firstRoom);

		frame = new DungeonFrame(this);
		try {
			recursiveProceduration(stackRoom);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		// right
		if (currentRoom.getY() == taille - 1)
			listDirection.remove(Direction.SOUTH);
		// left
		if (currentRoom.getY() == 0)
			listDirection.remove(Direction.NORTH);
		// bottom
		if (currentRoom.getX() == taille - 1)
			listDirection.remove(Direction.EAST);
		// top
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
			dungeon[currentRoom.getY()][currentRoom.getX()].addDirection(Direction.EAST, neighbourRoom);
			dungeon[neighbourRoom.getY()][neighbourRoom.getX()].addDirection(Direction.WEST, currentRoom);
			System.out.println("lier est");
		}

		if (currentRoom.getY() - 1 == neighbourRoom.getY() && currentRoom.getX() == neighbourRoom.getX()) {
			dungeon[currentRoom.getY()][currentRoom.getX()].addDirection(Direction.WEST, neighbourRoom);
			dungeon[neighbourRoom.getY()][neighbourRoom.getX()].addDirection(Direction.EAST, currentRoom);
			System.out.println("lier ouest");
		}

		if (currentRoom.getY() == neighbourRoom.getY() && currentRoom.getX() + 1 == neighbourRoom.getX()) {
			dungeon[currentRoom.getY()][currentRoom.getX()].addDirection(Direction.SOUTH, neighbourRoom);
			dungeon[neighbourRoom.getY()][neighbourRoom.getX()].addDirection(Direction.NORTH, currentRoom);
			System.out.println("lier sud");
		}

		if (currentRoom.getY() == neighbourRoom.getY() && currentRoom.getX() - 1 == neighbourRoom.getX()) {
			dungeon[currentRoom.getY()][currentRoom.getX()].addDirection(Direction.NORTH, neighbourRoom);
			dungeon[neighbourRoom.getY()][neighbourRoom.getX()].addDirection(Direction.SOUTH, currentRoom);
			System.out.println("lier nord");
		}

		voisinsVisité[neighbourRoom.getY()][neighbourRoom.getX()] = true;

		System.out.println("curent room :" + currentRoom.getY() + " " + currentRoom.getX());
		System.out.println("neighbour room :" + neighbourRoom.getY() + " " + neighbourRoom.getX());

	}

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * 
	 * @param stackRoom
	 * @throws InterruptedException
	 */
	public void recursiveProceduration(Stack<Room> stackRoom) throws InterruptedException {
		//Thread.sleep(4000);
		//frame.repaint();
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

	public static void main(String[] args) {
		Dungeon d = new Dungeon(10);
		d.initializeDungeon();
	}

}
