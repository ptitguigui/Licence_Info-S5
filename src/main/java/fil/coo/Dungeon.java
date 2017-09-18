package fil.coo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Dungeon {

	private Room[][] donjon;
	private int taille;
	private boolean[][] voisinsVisité;
	private Random r = new Random();
	private int x;
	private int y;

	
	/**
	 * Constructor of Dungeon to initialize Array and get a random Room from the Dungeon
	 * @param taille int
	 */
	public Dungeon(int taille) {
		this.taille = taille;
		donjon = new Room[taille][taille];
		voisinsVisité = new boolean[taille][taille];
		x = r.nextInt(donjon.length);
		y = r.nextInt(donjon.length);
	}

	/**
	 * Methods to initialize the dungeon with recursive backtracking method
	 */
	public void initializeDungeon() {

		Room firstRoom = donjon[y][x];

		Stack<Room> stackRoom = new Stack();
		stackRoom.push(firstRoom);

		recursiveProceduration(stackRoom);

	}

	/**
	 * Method to get the adjacent rooms of a room
	 * @param firstRoom
	 * @return
	 */
	public List<Room> adjacentRoom(Room firstRoom) {
		List<Room> listRooms = new ArrayList<Room>();
		List<Direction> listDirectionPossible = directionPossible();
		
		for (int i=0; i<listDirectionPossible.size(); i++) {
			
			if(listDirectionPossible.get(i) == Direction.NORTH) {
				if(!voisinsVisité[x-1][y]) {
					//add room north
				}
			}
			
			if(listDirectionPossible.get(i) == Direction.EAST) {
				if(!voisinsVisité[x][y+1]) {
					//add room north
				}
			}
			
			if(listDirectionPossible.get(i) == Direction.SOUTH) {
				if(!voisinsVisité[x+1][y]) {
					//add room north
				}
			}
			
			if(listDirectionPossible.get(i) == Direction.WEST) {
				if(!voisinsVisité[x][y-1]) {
					//add room north
				}
			}
		}

		return listRooms;
	}

	/**
	 * method to get the direction possible of the current room
	 * @return list<Direction>
	 */
	private List<Direction> directionPossible() {
		List<Direction> listDirection = new ArrayList<Direction>();
		
		listDirection.add(Direction.NORTH);
		listDirection.add(Direction.EAST);
		listDirection.add(Direction.SOUTH);
		listDirection.add(Direction.WEST);
		
		//right
		if(x == taille -1)
			listDirection.remove(Direction.EAST);
		//left
		if(x== 0)
			listDirection.remove(Direction.WEST);
		//bottom
		if(y==taille -1)
			listDirection.remove(Direction.SOUTH);
		//top
		if(y==0)
			listDirection.remove(Direction.NORTH);
		
		return listDirection;
	}

	/**
	 * method to link 2 rooms
	 * @param room Room
	 * @param room2 Room
	 */
	public void linkNeighbourRoom(Room room, Room room2) {
		// to Do
	}

	/**
	 * recursive method to create the proceduration of the Dungeon
	 * @param stackRoom
	 */
	public void recursiveProceduration(Stack<Room> stackRoom) {

		if (!stackRoom.isEmpty()) {

			Room firstRoom = stackRoom.peek();

			List<Room> listRoomAdjacent = adjacentRoom(firstRoom);

			if (!listRoomAdjacent.isEmpty()) {

				int roomRandom = r.nextInt(adjacentRoom(firstRoom).size());
				Room roomNeighbour = adjacentRoom(firstRoom).get(roomRandom);
				linkNeighbourRoom(firstRoom, roomNeighbour);

				stackRoom.push(roomNeighbour);

				recursiveProceduration(stackRoom);
			} else {
				stackRoom.pop();
				recursiveProceduration(stackRoom);
			}
		}
	}

}
