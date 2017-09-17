package fil.coo;

public class LifePotion implements Item {

	int bonusHp = 10;
	
	public void use(GameCharacter g) {
		g.changeHp(bonusHp);
	}

}
