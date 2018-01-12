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
import fil.coo.character.Monster;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;
import fil.coo.game.Direction;
import fil.coo.game.Dungeon;
import fil.coo.util.Menu;

public class MoveTest {
	Move m;
	Player player;
	AdventureGame g;

	@Before
	public void setUp() {
		m = new Move();

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
		Monster monster = new Monster("monster", 20, 1, 10);
		g.getCurrentRoom().addMonster(monster);

		assertEquals(false, m.isPossible(g));
	}
	
	@Test
	public void testIsPossible() {
		assertEquals(true, m.isPossible(g));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testChooseDirectionFail() {
		
		String input = "0";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		m.chooseDirection(g);
	}

	@Test
	public void testChooseDirection() {
		String input;
		
		input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);

		if(g.getCurrentRoom().hasLinkedNeighbourForDirection(Direction.SOUTH))
			assertEquals(Direction.SOUTH, m.chooseDirection(g));
		else
			assertEquals(Direction.EAST, m.chooseDirection(g));
	}
}
