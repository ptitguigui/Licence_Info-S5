package fil.coo.items;

import fil.coo.character.GameCharacter;

public class LifePotion implements Item {

	int bonusHp;
	
	public LifePotion(int bonusHp) {
		this.bonusHp= bonusHp;
	}

	/**
	 * Method to use the life potion
	 * @param g GameChracter
	 */
	public void use(GameCharacter g) {
		g.changeHp(bonusHp);
		System.out.println("You heal "+ bonusHp +" hp");
	}
	
	public String toString() {
		return "life potion";
	}

}
