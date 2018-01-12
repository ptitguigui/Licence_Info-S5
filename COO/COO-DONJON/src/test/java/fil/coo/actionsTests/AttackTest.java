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
import fil.coo.game.Dungeon;
import fil.coo.util.Menu;

public class AttackTest {

	Attack a;
	Player player;
	AdventureGame g;

	@Before
	public void setUp() {
		a = new Attack();

		Dungeon dungeon = new Dungeon(10);
		dungeon.getBeginningRoom().removeAllItems();
		dungeon.getBeginningRoom().removeAllMonsters();

		List<Action> listActions = Arrays.asList(new Attack(), new Move(), new Look(), new Use());
		player = new Player("player", 10, 10, 0, listActions);

		Menu menu = new Menu();

		g = new AdventureGame(dungeon.getBeginningRoom(), player, dungeon, menu);
	}

	@Test
	public void testAttackTargetWithCounterAttack() {
		Monster monster = new Monster("monster", 20, 1, 10);
		g.getCurrentRoom().addMonster(monster);
		
		
		int hpMonsterBefore = monster.getHp();
		int hpPLayerBefore  = player.getHp();
		
		a.attackTarget(g, player, monster);
		
		assertEquals(hpPLayerBefore-monster.getStrenght(), player.getHp());
		assertEquals(hpMonsterBefore-player.getStrenght(), monster.getHp());
		assertEquals(true, g.getCurrentRoom().hasMonster());
	}
	
	@Test
	public void testAttackTargetWhenTargetDie() {
		Monster monster = new Monster("monster", 10, 1, 10);
		g.getCurrentRoom().addMonster(monster);
		
		a.attackTarget(g, player, monster);
		
		assertEquals(false, g.getCurrentRoom().hasMonster());
		
	}

	@Test
	public void testIsPossible() {
		Monster monster = new Monster("monster", 20, 1, 10);
		g.getCurrentRoom().addMonster(monster);

		assertEquals(true, a.isPossible(g));
	}
	
	@Test
	public void testIsNotPossible() {
		assertEquals(false, a.isPossible(g));
	}
	
	@Test
	public void testChooseMonster() {
		Monster monster = new Monster("monster", 10, 1, 10);
		g.getCurrentRoom().addMonster(monster);
		
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		/*
		Methode qui fonctionne lors de l'execution de la classe uniquement...
		
		Monster sameMonster = a.chooseAMonster(g);
	    
	    assertEquals(monster, sameMonster);*/
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testDontChooseMonster() {
		Monster monster = new Monster("monster", 10, 1, 10);
		g.getCurrentRoom().addMonster(monster);
		
		String input = "0";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		a.chooseAMonster(g);
	}
}
