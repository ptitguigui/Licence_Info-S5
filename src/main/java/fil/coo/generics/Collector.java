package fil.coo.generics;

/**
 * define collectors able to collect (and carry) one specific type T of objects
 * only one T object can be carried at a time
 * 
 * @author guillaume
 * @param <T>
 */

public class Collector<T> {

	/**
	 * constructor of Collector
	 * @param name
	 */
	public Collector(String name) {
		this.name = name;
	}

	private String name;

	private T carriedObject = null;

	public String toString() {
		return this.name;
	}

	/**
	 * method to describe which object the colletor carries
	 * @return String
	 */
	public String description() {
		return this.name + " carries " + this.carriedObject;
	}

	/**
	 * method to get the object carries
	 * @return T
	 */
	public T getCarriedObject() {
		return carriedObject;
	}
	
	/**
	 * method to set the object carries
	 * @param carriedObject
	 */
	public void setCarriedObject(T carriedObject) {
		this.carriedObject = carriedObject;
	}

	/**
	 * method to know if the collector can take an object
	 * @return boolean
	 */
	public boolean canTake() {
		return getCarriedObject() == null;
	}
	
	/**
	 * method to take an object
	 * @param t
	 * @throws AlreadyCarryingException
	 */
	public void take(T t) throws AlreadyCarryingException {
		if (canTake())
			setCarriedObject(t);
		else
			throw new AlreadyCarryingException();
	}

	/**
	 * method to drop the object carries
	 * @return the object carries or null
	 */
	public T drop() {
		T tmp = getCarriedObject();
		if (tmp != null) {
			setCarriedObject(null);
			return tmp;
		}
		return null;
	}

	/**
	 * method to give the object carries to another collector
	 * @param c the other collector
	 * @return the object given or null
	 * @throws AlreadyCarryingException
	 */
	public T giveTo(Collector<? super T> c) throws AlreadyCarryingException {
		if (!this.canTake()) {
			if (!c.canTake())
				throw new AlreadyCarryingException();
			else {
				c.setCarriedObject(this.getCarriedObject());
				return this.drop();
			}
		}
		return null;
	}
	/**
	 * is the the same method than take ?
	 * @param t
	 * @throws AlreadyCarryingException
	 */
	public void collect(T t) throws AlreadyCarryingException {
		this.take(t);
	}

	public static void main(String[] args) throws AlreadyCarryingException {

		Carrot c1 = new Carrot(1);
		Carrot c2 = new Carrot(2);
		Carrot c3 = new Carrot(3);
		Apple p1 = new Apple(1);
		Apple p2 = new Apple(2);

		Collector<Carrot> carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Carrot> carrotCollector2 = new Collector<Carrot>("carrot-collector-2");
		Collector<Apple> appleCollector1 = new Collector<Apple>("apple-collector-1");
		Collector<Vegetable> vegetableCollector = new Collector<Vegetable>("vegetable-collector");

		carrotCollector1.take(c3);
		System.out.println(carrotCollector1.description());
		// NE COMPILE PAS
		//carrotCollector2.take(p1);

		// NE COMPILE PAS
		//carrotCollector1.giveTo(appleCollector1);

		// COMPILE :
		carrotCollector1.giveTo(vegetableCollector);

		// NE COMPILE PAS
		// vegetableCollector.giveTo(carrotCollector1);
		// NE COMPILE PAS
		// appleCollector1.giveTo(vegetableCollector);

		carrotCollector1.collect(c1);
		carrotCollector1.giveTo(carrotCollector2);
		System.out.println(carrotCollector1.description());
		System.out.println(carrotCollector2.description());
		carrotCollector1.collect(c2);

		try {
			carrotCollector1.giveTo(carrotCollector2);
		} catch (AlreadyCarryingException e) {
			System.out.println(" * " + e.getMessage());
		}

		appleCollector1.collect(p2);
		System.out.println(appleCollector1.description());
		try {
			appleCollector1.collect(p1);
		} catch (AlreadyCarryingException e) {
			System.out.println(" * " + e.getMessage());
		}
		appleCollector1.drop();
		System.out.println(appleCollector1.description());
		appleCollector1.collect(p1);

	}
}
