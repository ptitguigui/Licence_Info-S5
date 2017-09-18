package fil.coo;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Donjon {

	private Room[][] donjon;
	private int taille;
	private boolean[][] tab;

	Random r = new Random();

	/**
	 * ce qui reste a faire:
	 * 
	 * - initialiser les deux tableaux
	 * - methods
	 */
	public void linkNeighbours() {
		int horizontale = r.nextInt(donjon.length);
		int verticale = r.nextInt(donjon.length);

		Room firstRoom = donjon[verticale][horizontale];

		Stack<Room> stackRoom = new Stack();
		stackRoom.push(firstRoom);

		recursive(stackRoom);

	}

	public List<Room> salleAdjacente(Room firstRoom) {

		return null;
	}

	public void linkRoom(Room room, Room room2) {
		// to Do
	}

	public void recursive(Stack<Room> stackRoom) {

		if (!stackRoom.isEmpty()) {

			Room firstRoom = stackRoom.peek();

			List<Room> listRoom = salleAdjacente(firstRoom);

			if (!listRoom.isEmpty()) {

				int roomRandom = r.nextInt(salleAdjacente(firstRoom).size());
				Room roomNeighbour = salleAdjacente(firstRoom).get(roomRandom);
				linkRoom(firstRoom, roomNeighbour);

				stackRoom.push(roomNeighbour);

				recursive(stackRoom);
			} else {
				stackRoom.pop();
				recursive(stackRoom);
			}
		}
	}

}
