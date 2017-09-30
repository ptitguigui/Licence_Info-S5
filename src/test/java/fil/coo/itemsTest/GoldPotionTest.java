package fil.coo.itemsTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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
import fil.coo.items.GoldPotion;
import fil.coo.util.Menu;

public class GoldPotionTest {

	GoldPotion potion;
	Use use;
	Player player;
	AdventureGame g;

	@Before
	public void setUp() {
		use = new Use();

		Dungeon dungeon = new Dungeon(10);
		dungeon.getBeginningRoom().removeAllItems();
		dungeon.getBeginningRoom().removeAllMonsters();

		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		player = new Player("player", 100, 10, 0, listActions);

		Menu menu = new Menu();

		g = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
	}
	
	@Test
	public void testUseBonus() {

		potion = new GoldPotion(20);
		player.addItem(potion);
		
		assertEquals(0, player.getGold());
		
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
		/*use.execute(g, player);
		
		assertEquals(20, player.getGold());	*/	
	}

}
