package fil.coo.character;

import fil.coo.game.Room;

public abstract class GameCharacter {

	private int hp;
	private int strenght;
	private int gold;
	private Room currentRoom;
	
	public GameCharacter(int hp, int strenght, int gold, Room currentRoom) {
		super();
		this.hp = hp;
		this.strenght = strenght;
		this.gold = gold;
		this.currentRoom = currentRoom;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStrenght() {
		return strenght;
	}

	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public boolean isAlive() {
		return hp > 0;
	}
	
	public void changeHp(int bonusHp) {
		this.hp += bonusHp;
	}
	
	public void changeStrenght(int bonusStrenght) {
		this.strenght += bonusStrenght;
	}
	
	public void changeGold(int bonusGold) {		
		this.gold += bonusGold;
	}
}
