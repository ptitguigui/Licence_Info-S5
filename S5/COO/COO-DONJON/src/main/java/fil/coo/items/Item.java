package fil.coo.items;

import fil.coo.character.GameCharacter;

public interface Item {

	/**
	 * Method to use an item
	 * @param g GameChracter
	 */
	public void use(GameCharacter g);
}
