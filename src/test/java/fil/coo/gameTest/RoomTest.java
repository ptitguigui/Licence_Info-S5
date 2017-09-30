package fil.coo.gameTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fil.coo.character.Monster;
import fil.coo.game.Direction;
import fil.coo.game.Room;
import fil.coo.items.LifePotion;

public class RoomTest {

	Room room;

	@Before
	public void setUp() {
		room = new Room(false, 0, 0);
	}

	@Test
	public void testIsExit() {
		Room room = new Room(true, 0, 0);
		assertTrue(room.isExit());
	}

	@Test
	public void testIsNotExit() {
		Room room = new Room(false, 0, 0);
		assertTrue(!room.isExit());
	}

	@Test
	public void testHasLinkedNeighbourForDirection() {
		Room currentRoom = new Room(false, 0, 1);
		Room neighbourRoom = new Room(false, 1, 1);

		currentRoom.addDirection(Direction.SOUTH, neighbourRoom);
		neighbourRoom.addDirection(Direction.NORTH, currentRoom);

		assertTrue(currentRoom.hasLinkedNeighbourForDirection(Direction.SOUTH));
		assertTrue(neighbourRoom.hasLinkedNeighbourForDirection(Direction.NORTH));
	}

	@Test
	public void testAddOneMonster() {
		Monster monster = new Monster("monster", 10, 10, 10);
		room.addMonster(monster);

		assertEquals(monster, room.getMonsters().get(0));
	}

	@Test
	public void testAddSeveralMonsters() {
		Monster monster1 = new Monster("monster", 10, 10, 10);
		Monster monster2 = new Monster("monster", 10, 10, 10);
		Monster monster3 = new Monster("monster", 10, 10, 10);

		room.addMonster(monster1);
		room.addMonster(monster2);
		room.addMonster(monster3);

		assertEquals(monster1, room.getMonsters().get(0));
		assertEquals(monster2, room.getMonsters().get(1));
		assertEquals(monster3, room.getMonsters().get(2));
		assertEquals(3, room.getMonsters().size());
	}

	@Test
	public void testHasMonster() {
		Monster monster = new Monster("monster", 10, 10, 10);
		room.addMonster(monster);

		assertTrue(room.hasMonster());
	}

	@Test
	public void testHasNoMonster() {
		assertTrue(!room.hasMonster());
	}

	@Test
	public void testRemoveAllMonsters() {
		Monster monster1 = new Monster("monster", 10, 10, 10);
		Monster monster2 = new Monster("monster", 10, 10, 10);
		Monster monster3 = new Monster("monster", 10, 10, 10);

		room.addMonster(monster1);
		room.addMonster(monster2);
		room.addMonster(monster3);

		assertTrue(room.hasMonster());

		room.removeAllMonsters();

		assertTrue(!room.hasMonster());
	}

	@Test
	public void testAddOneItem() {
		LifePotion item = new LifePotion(10);
		room.addItem(item);

		assertEquals(item, room.getItems().get(0));
	}

	@Test
	public void testAddSeveralItems() {
		LifePotion item1 = new LifePotion(10);
		LifePotion item2 = new LifePotion(10);
		LifePotion item3 = new LifePotion(10);
		room.addItem(item1);
		room.addItem(item2);
		room.addItem(item3);

		assertEquals(3, room.getItems().size());
		assertEquals(item1, room.getItems().get(0));
		assertEquals(item2, room.getItems().get(1));
		assertEquals(item3, room.getItems().get(2));
	}

	@Test
	public void testHasNoItem() {
		assertTrue(!room.hasItem());
	}

	@Test
	public void testHasItem() {
		LifePotion item = new LifePotion(10);
		room.addItem(item);
		
		assertTrue(room.hasItem());
	}

	@Test
	public void testRemoveAllItems() {
		LifePotion item1 = new LifePotion(10);
		LifePotion item2 = new LifePotion(10);
		LifePotion item3 = new LifePotion(10);
		room.addItem(item1);
		room.addItem(item2);
		room.addItem(item3);
		
		assertTrue(room.hasItem());
		
		room.removeAllItems();

		assertTrue(!room.hasItem());
	}

}
