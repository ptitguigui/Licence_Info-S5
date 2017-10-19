package automata;

import java.util.Set;

public class NDAutomaton extends AbstractNDAutomaton implements Recognizer, AutomatonBuilder {
	
	/**
	 * Permet de savoir si le mot fait partie de l'automate
	 * 
	 */
	public boolean accept(String word) {
		Set<State> allStates = new PrintSet<>();
		allStates.addAll(getInitialStates());
		
		for (int i=0; i<word.length(); i++){
			allStates = getTransitionSet(allStates, word.charAt(i));
		}
		return acceptingStates.stream().anyMatch(allStates::contains);
	}

	/**
	* Calcul de l’ensemble des états pouvant être obtenus depuis un ensemble d’états
	* @param fromSet : ensemble d’états
	* @param letter : lettre de l’alphabet
	* @return ensemble d’états pouvant être obtenus en lisant letter,
	* en partant de n’importe lequel des états de l’ensemble fromSet
	*/
	public Set<State> getTransitionSet(Set<State> fromSet, char letter) {
		Set<State> transitionSet = new PrintSet<>();
		Automaton automate;
		for (State state : fromSet) {
			automate = state.getAutomaton();
			transitionSet.addAll(automate.getTransitionSet(state, letter));
		}
		return transitionSet;
	}
}
