package fil.coo.actionsTests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import fil.coo.actions.Action;
import fil.coo.actions.Attack;
import fil.coo.actions.Look;
import fil.coo.actions.Move;
import fil.coo.actions.Use;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.game.Dungeon;
import fil.coo.items.LifePotion;
import fil.coo.util.Menu;

public class UseTest {

	
	Use u;
	Player player;
	AdventureGame g;

	@Before
	public void setUp() {
		u = new Use();

		Dungeon dungeon = new Dungeon(10);
		dungeon.getBeginningRoom().removeAllItems();
		dungeon.getBeginningRoom().removeAllMonsters();

		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		player = new Player("player", 10, 10, 0, listActions);

		Menu menu = new Menu();

		g = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
	}
	
	@Test
	public void testIsNotPossible() {
		assertEquals(false, u.isPossible(g));
	}
	
	@Test
	public void testIsPossible() {
		LifePotion item = new LifePotion();
		player.addItem(item);
		
		assertEquals(true, u.isPossible(g));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testChooseAnItemFail() {
		
		String input = "0";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		u.chooseAnItem(g, player);
	}

	@Test
	public void testChooseAnItem() {
		LifePotion item = new LifePotion();
		player.addItem(item);
		String input;
		
		input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    assertEquals(item, u.chooseAnItem(g, player));
	}

}
