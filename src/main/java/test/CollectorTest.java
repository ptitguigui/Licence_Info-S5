package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fil.coo.generics.AlreadyCarryingException;
import fil.coo.generics.Apple;
import fil.coo.generics.Carrot;
import fil.coo.generics.Collector;
import fil.coo.generics.Vegetable;

public class CollectorTest {

	protected Carrot c1;
	protected Carrot c2;
	protected Carrot c3;
	protected Apple p1;

	protected Collector<Carrot> carrotCollector1;
	protected Collector<Carrot> carrotCollector2;
	protected Collector<Apple> appleCollector1;
	protected Collector<Vegetable> vegetableCollector;

	@Before
	public void setUp() throws Exception {
		c1 = new Carrot(1);
		c2 = new Carrot(2);
		c3 = new Carrot(3);
		p1 = new Apple(1);

		carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		carrotCollector2 = new Collector<Carrot>("carrot-collector-2");
		appleCollector1 = new Collector<Apple>("apple-collector-1");
		vegetableCollector = new Collector<Vegetable>("vegetable-collector");
	}

	@Test
	public void takeAndGiveTest() throws AlreadyCarryingException {
		carrotCollector1.take(c3);

		assertEquals("carrot-collector-1 carries Carot-3", carrotCollector1.description());

		Carrot ctmp = carrotCollector1.giveTo(vegetableCollector);

		assertEquals(ctmp, c3);
		assertEquals("carrot-collector-1 carries null", carrotCollector1.description());
		assertEquals("vegetable-collector carries Carot-3", vegetableCollector.description());

	}

	@Test
	public void collectAndGiveTest() throws AlreadyCarryingException {

		carrotCollector1.collect(c1);

		assertEquals("carrot-collector-1 carries Carot-1", carrotCollector1.description());

		Carrot ctmp = carrotCollector1.giveTo(carrotCollector2);

		assertEquals(ctmp, c1);
		assertEquals("carrot-collector-1 carries null", carrotCollector1.description());
		assertEquals("carrot-collector-2 carries Carot-1", carrotCollector2.description());
	}

	@Test(expected = AlreadyCarryingException.class)
	public void cantTakeObject() throws AlreadyCarryingException {
		carrotCollector1.collect(c2);
		carrotCollector2.collect(c1);
		

		assertEquals("carrot-collector-1 carries Carot-2", carrotCollector1.description());
		assertEquals("carrot-collector-2 carries Carot-1", carrotCollector2.description());
		
		carrotCollector1.giveTo(carrotCollector2);

	}
	
	@Test
	public void dropTest() throws AlreadyCarryingException {
		appleCollector1.take(p1);
		
		assertEquals("apple-collector-1 carries Apple-1", appleCollector1.description());
		
		appleCollector1.drop();
		
		assertEquals("apple-collector-1 carries null", appleCollector1.description());		
	}

}
