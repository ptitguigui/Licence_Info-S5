package fil.coo.component;

import fil.coo.character.GameCharacter;

public class StrenghtPotion implements Item {

	int bonusStrenght = 5;
	

	/**
	 * Method to use the strenght potion
	 * @param g GameChracter
	 */
	public void use(GameCharacter g) {
		g.changeStrenght(bonusStrenght);
		System.out.println("You gain "+ bonusStrenght +" strenght");
	}
	
	public String toString() {
		return "strenght potion";
	}

}
