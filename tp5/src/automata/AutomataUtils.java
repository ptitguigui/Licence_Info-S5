package automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author Bruno.Bogaert (at) univ-lille.fr
 *
 */
public class AutomataUtils {

	/**
	 * Extends automaton a, so that it accepts also this word. Created states
	 * are prefixed by 'q_'
	 * 
	 * @param word
	 *            : word to be accepted
	 * @param a
	 *            : target automaton
	 */
	public static void addSingleton(String word, AutomatonBuilder a) {
		addSingleton(word, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also this word. Created states are
	 * prefixed by namePrefix followed by '_'
	 * 
	 * @param word
	 *            : word to be accepted
	 * @param a
	 *            : target automaton
	 * @param namePrefix
	 *            : prefix to use for state names.
	 */
	public static void addSingleton(String word, AutomatonBuilder a, String namePrefix) {
		a.addNewState(namePrefix + "_epsilon");
		for (int i = 0; i < word.length(); i++) {
			a.addNewState(namePrefix + "_" + word.substring(0, i + 1));
		}
		a.setInitial(namePrefix + "_epsilon");
		a.addTransition(namePrefix + "_epsilon", word.charAt(0), namePrefix + "_" + word.substring(0, 1));
		for (int i = 0; i < word.length() - 1; i++) {
			a.addTransition(namePrefix + "_" + word.substring(0, i + 1), word.charAt(i),
					namePrefix + "_" + word.substring(0, i + 2));
		}
		a.setAccepting(namePrefix + "_" + word);
	}

	/**
	 * Extends automaton a so that it accepts also this finite language created
	 * states are prefixed by namePrefix followed by '_'
	 * 
	 * @param finiteLanguage
	 *            : set of words to be accepted
	 * @param a
	 *            : target automaton
	 */
	public static void addFiniteSet(Iterable<String> finiteLanguage, AutomatonBuilder a) {
		int i=0;
		for (String word : finiteLanguage) {
			addSingleton(word, a, "q"+i);
			i++;
		}
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * 
	 * @param exp
	 *            : flat regular expression (only letters and *)
	 * @param a
	 *            : target automaton
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a) {
		addFlatExp(exp, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * 
	 * @param exp
	 *            : flat regular expression (only letters and *)
	 * @param a
	 *            : target automaton
	 * @param namePrefix
	 *            : prefix to use for state names.
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a, String namePrefix) {
		char[] transtion = new char[exp.length()];
		int index=0;
		
		a.addNewState(namePrefix + "_epsilon");
		for (int i = 0; i < exp.length()-1; i++) {
			if(exp.charAt(i)!= '*' && exp.charAt(i+1)!= '*'){
				a.addNewState(namePrefix + "_" + exp.substring(0, i+1));
				System.out.println("new state "+ namePrefix + "_" + exp.substring(0, i+1) );
			}else{
				transtion[index] = exp.charAt(i+1);
				index++;
			}
		}
		a.addNewState(namePrefix+"_"+exp);
		
		a.setInitial(namePrefix + "_epsilon");
		a.addTransition(namePrefix + "_epsilon", exp.charAt(0), namePrefix + "_" + exp.substring(0, 1));
		
		/*
		 * faux
		 */
		for (int i = 0; i < exp.length() - 2; i++) {
			if(exp.charAt(i+1)!= '*' && exp.charAt(i+2)!= '*'){
			a.addTransition(namePrefix + "_" + exp.substring(0, i + 1), exp.charAt(i),
					namePrefix + "_" + exp.substring(0, i + 2));
			}else if(exp.charAt(i+2) == '*'){
				a.addTransition(namePrefix + "_" + exp.substring(0, i + 1), exp.charAt(i),
						namePrefix + "_" + exp.substring(0, i + 1));
			}
		}
		a.setAccepting(namePrefix + "_" + exp);
	}

	/**
	 * Transpose automaton Note : mirror is cleared before the operation.
	 * 
	 * @param original
	 *            : automaton to be transposed
	 * @param mirror
	 *            : receive the transposed automaton
	 */
	public static void transpose(Automaton original, AutomatonBuilder mirror) {
		// TODO
	}

	/**
	 * Determinization of nfa automaton. Note : dfa is cleared before the
	 * operation.
	 * 
	 * @param nfa
	 *            : non deterministic automaton (to be determinize)
	 * @param dfa
	 *            : receive determinization result
	 */
	public static void determinize(Automaton nfa, AutomatonBuilder dfa) {
		// For each computed state set from nfa, a corresponding state has to be
		// created in dfa
		// map represents relationship between nfa state set (key) and created
		// dfa state (value)
		Map<Set<State>, State> map = new HashMap<Set<State>, State>();

		// stack todo contains state sets whose transitions have not yet been
		// computed
		Stack<Set<State>> todo = new Stack<Set<State>>();

		dfa.clear();

		Set<State> startSet = nfa.getInitialStates();

		// create matching state in DFA
		State start = dfa.addNewState(startSet.toString()); // state creation
		map.put(startSet, start); // record relationship in map

		dfa.setAccepting(start); // start is the unique initial state of dfa

		todo.push(startSet); // put it in todo list.

		while (!todo.isEmpty()) {
			Set<State> fromSet = todo.pop(); // pick a state from todo list
			/*
			 * TODO : for each letter of alphabet : compute transitionSet from
			 * fromSet if computed set is a new one : create corresponding state
			 * in dfa record relationship in map add it to the todo list end if
			 * create corresponding transition in dfa
			 */
		}
		for (Set<State> qSet : map.keySet()) { // foreach computed state set
			/*
			 * TODO : if qset contains accepting state (from nfa) in dfa, set
			 * corresponding state as accepting state
			 */
		}
	}

}
