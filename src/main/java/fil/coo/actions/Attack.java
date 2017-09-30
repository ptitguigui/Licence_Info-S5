package fil.coo.actions;

import fil.coo.character.Monster;
import fil.coo.character.Player;
import fil.coo.game.AdventureGame;

public class Attack implements Action {

	/**
	 * Method to execute the attack action
	 * @param g AdventureGame
	 * @param player Player
	 */
	public void execute(AdventureGame g, Player player) {
		Monster target = chooseAMonster(g);

		if (target != null) {
			attackTarget(g, player, target);
		} else
			player.act(g);
	}
	
	/**
	 * Method to attack the monster
	 * @param g AdventureGame
	 * @param player Player
	 * @param target Monster
	 */
	public void attackTarget(AdventureGame g, Player player, Monster target) {
		target.changeHp(-player.getStrenght());
		System.out.println("\n\n"+target+" lost "+ player.getStrenght()+" hp..");

		if (target.isAlive()) {
			player.changeHp(-target.getStrenght());
			System.out.println("It counter attack !");
			System.out.println(player + " lost "+ target.getStrenght()+" hp\n\n");
		}else{
			System.out.println("And You kill it !");
			System.out.println("You find "+target.getGold()+" gold !\n\n");
			player.changeGold(target.getGold());
			g.getCurrentRoom().getMonsters().remove(target);
		}
	}
	
	/**
	 * Method to choose a monster from a list
	 * @param g AdventureGame
	 * @return Monster
	 */
	public Monster chooseAMonster(AdventureGame g) {
		Monster target = g.getMenu().choice("\nWho is the target ?\n",
				g.getCurrentRoom().getMonsters());
		return target;
	}

	/**
	 * Method to know if the player can Attack
	 * @param g AdventureGame
	 * @return boolean
	 */
	public boolean isPossible(AdventureGame g) {
		return g.getCurrentRoom().hasMonster();
	}

	public String toString() {
		return "Attack a monster";
	}
}
