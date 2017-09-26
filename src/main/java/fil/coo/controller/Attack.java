package fil.coo.controller;

import fil.coo.character.Monster;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;

public class Attack implements Action{

	public void execute(AdventureGame g, Player player) {
		Monster target = g.getMenu().choice("\nQuel monstre voulait vous attaquer ?\n", g.getCurrentRoom().getMonsters());
		
		//attack
		target.changeHp(-player.getStrenght());
		
		if(target.isAlive())
			player.changeHp(-target.getStrenght());
		else
			g.getCurrentRoom().getMonsters().remove(target);
	}
	
	public boolean isPossible(AdventureGame g) {
		return g.getCurrentRoom().hasMonster();
	}
	
	public String toString(){		
		return "Attack a monster";
	}
}
