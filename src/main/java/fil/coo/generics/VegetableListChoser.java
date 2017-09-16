package fil.coo.generics;

import java.util.ArrayList;
import java.util.List;

public class VegetableListChoser {

    /**
     * use the method ListChoser for the Vegetable objects
     * @param message String
     * @param list List of T
     * @return <T extends Vegetable>
     */
	public <T extends Vegetable> T chose(String message, List<T> list){
		return new ListChoser().chose(message, list);
	}
	
    public static void main(String[] args) {
		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		VegetableListChoser lcl = new VegetableListChoser();

		Carrot chosenCarrot = lcl.chose("which carrot ? ", lCarrots);
		System.out.println("You have chosen : " + chosenCarrot);

		// NE COMPILE PAS
		// Apple chosenApple = lcl.chose("which apple ? ",lApples);


    }
}
