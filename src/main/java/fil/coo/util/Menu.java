package fil.coo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fil.coo.character.Player;
import fil.coo.controller.Direction;
import fil.coo.game.Room;

public class Menu {

	private static boolean write = false;

	
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

	public void stats(Player player) {
		System.out.println("+--------------------+");
		System.out.println("Stats :");
		System.out.println("  -Hp : \t" + player.getHp());
		System.out.println("  -Strenght : \t" + player.getStrenght());
		System.out.println("  -Gold : \t" + player.getGold());
		System.out.println("+--------------------+\n");
		}

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
	
	public void clearScreen() throws IOException {
		System.out.print("\033[H\033[2J");
		System.out.flush();		
	}


	public String printHorizontal(String gameScreen) {
		gameScreen+= "==";
		write = true;
		return gameScreen;
	}

	public String printVertical(String gameScreen) {
		gameScreen+= "|";
		write = true;
		return gameScreen;
	}

	public boolean isACorner(int taille, int i, int j) {
		return i == 0 && j == 0 || i == 0 && j == taille - 1 || i == taille - 1 && j == 0
				|| i == taille - 1 && j == taille - 1;
	}

	public String printCorner(String gameScreen) {
		gameScreen += "+";
		write = true;
		return gameScreen;
	}

	private String printDoorHorizontal(String gameScreen) {
		gameScreen += "dd";
		write = true;
		return gameScreen;
	}
	
	public String printDoorVertical(String gameScreen) {
		gameScreen += "d";
		write = true;
		return gameScreen;
	}

	public  boolean isNotWrite() {
		return !(write);
	}
}
