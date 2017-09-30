package fil.coo.charactersTests;

import fil.coo.character.GameCharacter;
import fil.coo.character.Monster;

public class MonsterTest extends GameCharacterTest {


	@Override
	public GameCharacter createGameCharacther() {
		return new Monster("monster", 10, 10, 10);
	}
}
