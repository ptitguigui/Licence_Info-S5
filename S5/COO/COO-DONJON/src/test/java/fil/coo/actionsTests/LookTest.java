package fil.coo.actionsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
import fil.coo.game.Dungeon;
import fil.coo.items.Item;
import fil.coo.items.LifePotion;
import fil.coo.util.Menu;

public class LookTest {
	Look l;
	Player player;
	AdventureGame g;

	@Before
	public void setUp() {
		l = new Look();

		Dungeon dungeon = new Dungeon(10);
		dungeon.getBeginningRoom().removeAllItems();
		dungeon.getBeginningRoom().removeAllMonsters();

		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		player = new Player("player", 10, 10, 0, listActions);

		Menu menu = new Menu();

		g = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
	}
	
	@Test
	public void testIsPossible() {
		assertEquals(true, l.isPossible(g));
	}
	@Test
	public void testFindMOnsterWithNoMonster() {
		List<Monster> listMonsters = l.findMonsters(g);
		assertTrue(listMonsters.isEmpty());
	}
	
	@Test
	public void testFindMOnsterWithOneMonster() {
		Monster monster = new Monster("monster", 20, 1, 10);
		g.getCurrentRoom().addMonster(monster);
		List<Monster> listMonsters = l.findMonsters(g);
		
		assertTrue(!listMonsters.isEmpty());
		assertEquals(1, listMonsters.size());
		assertEquals(monster, listMonsters.get(0));
	}
	
	@Test
	public void testFindMOnsterWithSeveralMonsters() {
		Monster monster1= new Monster("monster", 20, 1, 10);
		Monster monster2 = new Monster("monster", 20, 1, 10);
		Monster monster3 = new Monster("monster", 20, 1, 10);
		
		g.getCurrentRoom().addMonster(monster1);
		g.getCurrentRoom().addMonster(monster2);
		g.getCurrentRoom().addMonster(monster3);
		List<Monster> listMonsters = l.findMonsters(g);
		
		assertTrue(!listMonsters.isEmpty());
		assertEquals(3, listMonsters.size());
		assertEquals(monster1, listMonsters.get(0));
		assertEquals(monster2, listMonsters.get(1));
		assertEquals(monster3, listMonsters.get(2));
	}
	
	@Test
	public void testFindItemWithNoItem() {
		List<Item> listItems = l.findItems(g, player);
		assertTrue(listItems.isEmpty());
	}
	
	@Test
	public void testFindItemWithOneItem() {
		LifePotion i = new LifePotion(10);
		g.getDungeon().getBeginningRoom().addItem(i);
		List<Item> listItems = l.findItems(g, player);

		assertTrue(!listItems.isEmpty());
		assertEquals(1,listItems.size());
		assertEquals(i, listItems.get(0));
	}
	
	@Test
	public void testFindItemWithSeveralItems() {
		LifePotion i1 = new LifePotion(10);
		LifePotion i2 = new LifePotion(10);
		LifePotion i3 = new LifePotion(10);
		
		g.getCurrentRoom().addItem(i1);
		g.getCurrentRoom().addItem(i2);
		g.getCurrentRoom().addItem(i3);
		List<Item> listItems = l.findItems(g, player);

		assertTrue(!listItems.isEmpty());
		assertEquals(3,listItems.size());
		assertEquals(i1, listItems.get(0));
		assertEquals(i2, listItems.get(1));
		assertEquals(i3, listItems.get(2));
	}

}
