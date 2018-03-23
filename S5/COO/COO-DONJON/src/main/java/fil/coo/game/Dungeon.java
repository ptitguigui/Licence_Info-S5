package fil.coo.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import fil.coo.character.Monster;
import fil.coo.items.GoldPotion;
import fil.coo.items.Item;
import fil.coo.items.LifePotion;
import fil.coo.items.StrenghtPotion;

public class Dungeon {

	private Room[][] dungeon;
	private int size;
	private boolean[][] neighboursVisited;
	private Random r = new Random();

	/**
	 * Constructor of Dungeon to initialize Array and get a random Room from the
	 * Dungeon
	 * 
	 * @param size int
	 */
	public Dungeon(int size) {
		this.size = size;
		dungeon = new Room[size][size];
		neighboursVisited = new boolean[size][size];
		this.initializeDungeon();
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
	 * method to get the room in coordinnate 0-0
	 * 
	 * @return Room
	 */
	public Room getBeginningRoom() {
		return dungeon[0][0];
	}
	
	/**
	 * method to the room in coordinnate y-x
	 * @param x int
	 * @param y int
	 * @return Room
	 */
	public Room getRoom(int x, int y) {
		return dungeon[y][x];
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
				neighboursVisited[y][x] = false;
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
		return x == size - 1 && y == size - 1;
	}

	/**
	 * method to add items or not in the room
	 * 
	 * @param room Room
	 */
	public void initializeItem(Room room) {
		List<Item> itemsPossible = Arrays.asList(new LifePotion(10), new StrenghtPotion(5), new GoldPotion(20));
		Item item;
		
		int chanceItems = r.nextInt(3);
		if(chanceItems == 2) {
			int nbItems = r.nextInt(2);
			
			for(int i=0; i<nbItems; i++) {
				item = itemsPossible.get(r.nextInt(itemsPossible.size()));
				room.addItem(item);
			}
		}
	}

	/**
	 * method to add monsters or not in the room
	 * 
	 * @param room Room
	 */
	public void initializeMonster(Room room) {
		Monster monster;
		int nbMonster = r.nextInt(3);
		for (int i = 0; i < nbMonster; i++) {
			monster = new Monster(getRandomName(), 20, 2, 5); // after create a random monster
			room.addMonster(monster);
		}
	}

	/**
	 * Method to get a random name into 3 name : Romain, Quentin or Jean Christophe
	 * @return String
	 */
	public String getRandomName() {
		String[] names = new String[] {"Romain", "Jean Christopĥe", "Quentin"};
		return names[r.nextInt(names.length)];
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
		neighboursVisited[firstRoom.getY()][firstRoom.getX()] = true;
		return firstRoom;
	}

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * 
	 * @param stackRoom Stack<Room>
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
	 * @param currentRoom Room
	 * @return Map< Direction, Room >
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
			if (!neighboursVisited[currentRoom.getY()][currentRoom.getX() - 1]) {
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
			if (!neighboursVisited[currentRoom.getY() + 1][currentRoom.getX()]) {
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
			if (!neighboursVisited[currentRoom.getY()][currentRoom.getX() + 1]) {
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
			if (!neighboursVisited[currentRoom.getY() - 1][currentRoom.getX()]) {
				listRooms.add(dungeon[currentRoom.getY() - 1][currentRoom.getX()]);
			}
		}
	}

	/**
	 * method to get the direction possible of the current room
	 * 
	 * @param currentRoom Room
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
	 * @param currentRoom Room
	 * @param listDirection List<Direction>
	 */
	public void removeImpossibleDirection(Room currentRoom, List<Direction> listDirection) {
		if (currentRoom.getY() == size - 1)
			listDirection.remove(Direction.SOUTH);
		if (currentRoom.getY() == 0)
			listDirection.remove(Direction.NORTH);
		if (currentRoom.getX() == size - 1)
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

		neighboursVisited[neighbourRoom.getY()][neighbourRoom.getX()] = true;
	}
}
