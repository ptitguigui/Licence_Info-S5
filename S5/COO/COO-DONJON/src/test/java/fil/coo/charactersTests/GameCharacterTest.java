package fil.coo.charactersTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fil.coo.character.GameCharacter;

public abstract class GameCharacterTest {

	protected GameCharacter character;
	public abstract GameCharacter createGameCharacther();
		
	@Before
	public void setUp() {
		this.character = this.createGameCharacther();
	}
	
	@Test
	public void testIsAlive() {
		assertEquals(true, character.isAlive());
	}
	
	@Test
	public void testIsNotAlive() {
		character.changeHp(-character.getHp());
		assertEquals(false, character.isAlive());
	}
	

	@Test
	public void testGainHp() {
		character.changeHp(5);
		assertEquals(15, character.getHp());
	}
	
	@Test
	public void testLostHp() {
		character.changeHp(-5);
		assertEquals(5, character.getHp());
	}

	@Test
	public void testGainStrenght() {
		character.changeStrenght(5);
		assertEquals(15, character.getStrenght());
	}
	
	@Test
	public void testLostStrenght() {
		character.changeStrenght(-5);
		assertEquals(5, character.getStrenght());
	}
	@Test
	public void testGainGold() {
		character.changeGold(5);
		assertEquals(15, character.getGold());
	}
	
	@Test
	public void testLostGold() {
		character.changeGold(-5);
		assertEquals(5, character.getGold());
	}
}
