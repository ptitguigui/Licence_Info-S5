package fil.coo;

public class StrenghtPotion implements Item {

	int bonusStrenght = 5;
	
	public void use(GameCharacter g) {
		g.changeStrenght(bonusStrenght);
	}

}
