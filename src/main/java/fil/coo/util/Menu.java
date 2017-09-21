package fil.coo.util;

import java.util.List;

import fil.coo.character.Player;
import fil.coo.game.Room;

public class Menu {

	
	public <T> T choice(String message, List<T> list){
		int i=1;
		System.out.println(message);
		System.out.println("0- Rien");
		for (T object : list) {
			System.out.println(i+"- "+object);
			i++;
		}
		int choix = ScannerInt.readInt(i);
		
		if(choix == 0)
			return null;
		else
			return list.get(choix-1);
	}
	
	public void gameScreen(Room room) {
		//to do
	}
	
	public void statusPlayer(Player player) {
		//to do
	}
	
}
