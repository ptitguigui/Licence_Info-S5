package fil.coo.character;

public abstract class GameCharacter {

	protected String nom;
	private int hp;
	private int strenght;
	private int gold;
	
	/**
	 * Create a game Character
	 * @param nom String
	 * @param hp int
	 * @param strenght int
	 * @param gold int
	 */
	public GameCharacter(String nom, int hp, int strenght, int gold) {
		this.nom = nom;
		this.hp = hp;
		this.strenght = strenght;
		this.gold = gold;
	}

	

	/**
	 * get the name of the character
	 * @return String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * get the hp of the character
	 * @return int 
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * get the strenght of the character
	 * @return int
	 */
	public int getStrenght() {
		return strenght;
	}
	
	/**
	 * get the gold of the character
	 * @return int
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * verify if the character is alive
	 * @return boolean
	 */
	public boolean isAlive() {
		return hp > 0;
	}
	
	/**
	 * to change the hp of the character
	 * @param bonusHp int
	 */
	public void changeHp(int bonusHp) {
		this.hp += bonusHp;
	}
	
	/**
	 * to change the strenght of the character
	 * @param bonusStrenght int
	 */
	public void changeStrenght(int bonusStrenght) {
		this.strenght += bonusStrenght;
	}
	
	/**
	 * to change the gold of the character
	 * @param bonusGold
	 */
	public void changeGold(int bonusGold) {		
		this.gold += bonusGold;
	}
}
