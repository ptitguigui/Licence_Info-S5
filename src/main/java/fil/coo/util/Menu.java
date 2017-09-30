package fil.coo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fil.coo.character.Player;
import fil.coo.game.Direction;
import fil.coo.game.Room;

public class Menu {

	private static boolean write = false;

	
	/**
	 * Method to choose a element from a list 
	 * @param message String
	 * @param list List<T>
	 * @return T
	 */
	public <T> T choice(String message, List<T> list) {
		int i = 1;
		System.out.println(message);
		System.out.println("0- Retour");
		for (T object : list) {
			System.out.println(i + "- " + object);
			i++;
		}
		int choix = ScannerInt.readInt(i);

		if (choix == 0)
			return null;
		else
			return list.get(choix - 1);
	}
	
	/**
	 * Method to display the stats of the player
	 * @param player Player
	 */
	public void stats(Player player) {
		System.out.println("+--------------------+");
		System.out.println("Stats :");
		System.out.println("  -Hp : \t" + player.getHp());
		System.out.println("  -Strenght : \t" + player.getStrenght());
		System.out.println("  -Gold : \t" + player.getGold());
		System.out.println("+--------------------+\n");
		}

	/**
	 * method to display the current room
	 * @param currentRoom ROom
	 * @param taille int
	 */
	public void drawGame(Room currentRoom, int taille) {
		Map<Direction, Room> neighbour = currentRoom.getNeighbours();
		String gameScreen = "";

		try {
			clearScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {

				if (isACorner(taille, i, j)) {
					gameScreen = printCorner(gameScreen);
				}
				if (isNotWrite() && i == 0 && ( j== taille/2) && neighbour.containsKey(Direction.NORTH)) {
					gameScreen = printDoorHorizontal(gameScreen);
				}
				if (isNotWrite() && i == taille - 1 && (j == taille/2) && neighbour.containsKey(Direction.SOUTH)) {
					gameScreen = printDoorHorizontal(gameScreen);
				}
				if (isNotWrite() && j == 0 && (i == taille/2) && neighbour.containsKey(Direction.WEST)) {
					gameScreen = printDoorVertical(gameScreen);
				}
				if (isNotWrite() && j == taille - 1 && (i == taille/2) && neighbour.containsKey(Direction.EAST)) {
					gameScreen = printDoorVertical(gameScreen);
				}
				if(isNotWrite() && (i == 0 || i == taille-1)) {
					gameScreen = printHorizontal(gameScreen);
				}
				if(isNotWrite() && (j==0 || j == taille -1)) {
					gameScreen = printVertical(gameScreen);
				}
				if (isNotWrite())
					gameScreen += "  ";

				write = false;
			}
			gameScreen += "\n";
		}

		System.out.println(gameScreen);
	}
	
	/**
	 * Method to clear the console
	 * @throws IOException
	 */
	public void clearScreen() throws IOException {
		System.out.print("\033[H\033[2J");
		System.out.flush();		
	}

	/**
	 * Method to print horizontal wall of the room
	 * @param gameScreen String	
	 * @return String
	 */
	public String printHorizontal(String gameScreen) {
		gameScreen+= "==";
		write = true;
		return gameScreen;
	}

	/**
	 * Method to print vertical wall of the room
	 * @param gameScreen String
	 * @return String
	 */
	public String printVertical(String gameScreen) {
		gameScreen+= "|";
		write = true;
		return gameScreen;
	}

	/**
	 * Method to know if the room is a corner
	 * @param taille int
	 * @param i int
	 * @param j int 
	 * @return boolean
	 */
	public boolean isACorner(int taille, int i, int j) {
		return i == 0 && j == 0 || i == 0 && j == taille - 1 || i == taille - 1 && j == 0
				|| i == taille - 1 && j == taille - 1;
	}

	/**
	 * Method to print a corner
	 * @param gameScreen String
	 * @return String
	 */
	public String printCorner(String gameScreen) {
		gameScreen += "+";
		write = true;
		return gameScreen;
	}

	/**
	 * Method to print a door when it's horizontal
	 * @param gameScreen String
	 * @return String
	 */
	private String printDoorHorizontal(String gameScreen) {
		gameScreen += "d=";
		write = true;
		return gameScreen;
	}

	/**
	 * Method to print a door when it's vertical
	 * @param gameScreen String
	 * @return String
	 */
	public String printDoorVertical(String gameScreen) {
		gameScreen += "d";
		write = true;
		return gameScreen;
	}

	/**
	 * Method to know if is already write or not
	 * @return boolean
	 */
	public  boolean isNotWrite() {
		return !(write);
	}
}
