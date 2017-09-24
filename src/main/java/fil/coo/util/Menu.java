package fil.coo.util;

import java.util.List;
import java.util.Map;

import fil.coo.character.Player;
import fil.coo.controller.Direction;
import fil.coo.game.Room;

public class Menu {

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
		System.out.println("Stats :");
		System.out.println("  -Hp : \t"+player.getHp());
		System.out.println("  -Strenght : \t"+player.getStrenght());
		System.out.println("  -Gold : \t"+player.getGold());
		System.err.println("\n");
	}

	public void drawGame(Room currentRoom) {
		Map<Direction, Room> neighbour = currentRoom.getNeighbours();
		String gameScreen = "";
		boolean write = false;
		int taille = 10;

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {

				if (i == 0 && (j == 4 || j == 5) && neighbour.containsKey(Direction.NORTH)) {
					gameScreen += "d";
					write = true;
				}
				if (i == taille - 1 && (j == 4 || j == 5) && neighbour.containsKey(Direction.SOUTH)) {
					gameScreen += "d";
					write = true;
				}
				if (j == 0 && (i == 4 || i == 5) && neighbour.containsKey(Direction.WEST)) {
					gameScreen += "d";
					write = true;
				}
				if (j == taille - 1 && (i == 4 || i == 5) && neighbour.containsKey(Direction.EAST)) {
					gameScreen += "d";
					write = true;
				}

				if (!(write) && (i == 0 || j == 0 || i == taille - 1 || j == taille - 1)) {
					gameScreen += "*";
					write = true;
				}
				if (!write)
					gameScreen += " ";

				write = false;
			}
			gameScreen += "\n";
		}

		System.out.println(gameScreen);
	}

}
