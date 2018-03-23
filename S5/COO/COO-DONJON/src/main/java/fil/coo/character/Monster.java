package fil.coo.character;

public class Monster extends GameCharacter {

	/**
	 * create a monster 
	 * @param nom int
	 * @param hp int 
	 * @param strenght int 
	 * @param gold int
	 */
	public Monster(String nom, int hp, int strenght, int gold) {
		super(nom, hp, strenght, gold);
	}
	
	public String toString(){
		return this.nom + " the Monster";
	}

}
