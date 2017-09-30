package fil.coo.charactersTests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fil.coo.actions.Action;
import fil.coo.actions.Attack;
import fil.coo.actions.Look;
import fil.coo.actions.Move;
import fil.coo.actions.Use;
import fil.coo.character.GameCharacter;
import fil.coo.character.Player;
import fil.coo.items.GoldPotion;
import fil.coo.items.Item;
import fil.coo.items.LifePotion;
import fil.coo.items.StrenghtPotion;

public class PlayerTest extends GameCharacterTest {

	Player p;

	@Override
	public GameCharacter createGameCharacther() {
		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		return new Player("player", 10, 10, 10, listActions);
	}
	
	@Test
	public void testAddItem() {
		MockPlayer p = new MockPlayer();

		LifePotion i = new LifePotion();
		p.addItem(i);
		assertEquals(i, p.getItems().get(0));
	}
	
	@Test
	public void testAddSeveralItems() {
		MockPlayer p = new MockPlayer();

		LifePotion i1 = new LifePotion();
		StrenghtPotion i2 = new StrenghtPotion();
		GoldPotion i3 = new GoldPotion();
		List<Item> items = Arrays.asList(i1,i2, i3);
		
		p.addItem(i1);
		p.addItem(i2);
		p.addItem(i3);
		
		assertEquals(i1, p.getItems().get(0));
		assertEquals(i2, p.getItems().get(1));
		assertEquals(i3, p.getItems().get(2));
		assertEquals(items, p.getItems());
	}

	@Test
	public void testGotItem() {
		MockPlayer p = new MockPlayer();

		LifePotion i = new LifePotion();
		p.addItem(i);
		assertEquals(true, p.gotItem());
	}
	
	@Test
	public void testDoesntGotItem() {
		MockPlayer p = new MockPlayer();

		assertEquals(false, p.gotItem());
	}

	private class MockPlayer extends Player {

		public MockPlayer() {
			super("player", 10, 10, 10, Arrays.asList(new Attack(), new Move(), new Look(), new Use()));
		}
	}
}
