package fil.coo.generics;

import java.util.ArrayList;
import java.util.List;

import fil.coo.scanner.ScannerInt;

public class ListChoser {

	
	/**
	 * Method to choose an element from the list 
	 * @param message String
	 * @param list List of T
	 * @return T or null
	 */
	public <T> T chose(String message, List<T> list){
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

	public static void main(String[] args) {
		// JEU DE TEST

		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		ListChoser lc = new ListChoser();

		Carrot chosenCarrot = lc.chose("which carrot ? ", lCarrots);
		System.out.println("you have chosen : " + chosenCarrot);

		Apple chosenApple = lc.chose("which appel? ", lApples);
		System.out.println("you have chosen : " + chosenApple);
	}
}
