package fil.coo.items;

import fil.coo.character.GameCharacter;

public class GoldPotion implements Item {

	int bonusGold;
	
	public GoldPotion(int bonusGold) {
		this.bonusGold = bonusGold;
	}
	
	/**
	 * Method to use the gold potion
	 * @param g GameChracter
	 */
	public void use(GameCharacter g) {
		g.changeGold(bonusGold);
		System.out.println("You win "+ bonusGold +" golds");
	}

	public String toString() {
		return "gold potion";
	}
}
