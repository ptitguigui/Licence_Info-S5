package fil.coo.generics;

/**
 * define collectors able to collect (and carry) one specific type T of objects
 * only one T object can be carried at a time
 * 
 * @author guillaume
 * @param <T>
 */

public class Collector<T> {

	public Collector(String name) {
		this.name = name;
	}

	private String name;

	private T carriedObject = null;

	public String toString() {
		return this.name;
	}

	public String description() {
		return this.name + " carries " + this.carriedObject;
	}

	public T getCarriedObject() {
		return carriedObject;
	}

	public void setCarriedObject(T carriedObject) {
		this.carriedObject = carriedObject;
	}

	public boolean canTake() {
		return getCarriedObject() == null;
	}

	public void take(T t) throws AlreadyCarryingException {
		if (canTake())
			setCarriedObject(t);
		else
			throw new AlreadyCarryingException();
	}

	public T drop() {
		T tmp = getCarriedObject();
		if (tmp != null) {
			setCarriedObject(null);
			return tmp;
		}
		return null;
	}

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

		// attention ici le type d'objets ramasses est Legume :
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
			// System.out.println("*** exception : " + carrotCollector2 + " porte deja qque
			// chose");
			System.out.println(" * " + e.getMessage());
		}

		appleCollector1.collect(p2);
		System.out.println(appleCollector1.description());
		try {
			appleCollector1.collect(p1);
		} catch (AlreadyCarryingException e) {
			// System.out.println("*** exception : " + appleCollector1 + " porte deja qque
			// chose");
			System.out.println(" * " + e.getMessage());
		}
		appleCollector1.drop();
		System.out.println(appleCollector1.description());
		appleCollector1.collect(p1);

	}
}
