package automata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestND {

    /*
     * Écriture de la représentation graphviz de l'automate dans un fichier
     *
     */
    public static void dotToFile(Automaton a, String fileName) {
        File f = new File(fileName);
        try {
            PrintWriter sortieDot = new PrintWriter(f);
            sortieDot.println(a.toGraphviz());
            sortieDot.close();
        } catch (IOException e) {
            System.out.println("création du fichier " + fileName + " impossible");
        }
    }

    /*
     * Test de la méthode accept()
     */
    private static void testAccept(Automaton a) {
        if (a.accept(""))
            System.out.println("Le mot vide est accepté. ");
        else
            System.out.println("Le mot vide n'est pas accepté. ");

        Scanner in = new Scanner(System.in);
        System.out.println("Mot non vide à tester ? (mot vide pour terminer)");
        String word = in.nextLine();
        while (word.length() != 0) {
            System.out.print("> " + word);
            if (a.accept(word))
                System.out.print(" est accepté. ");
            else
                System.out.print(" n'est pas accepté.");
            System.out.println("Mot non vide à tester ? (mot vide pour terminer)");
            word = in.nextLine();
        }

    }

    public static void main(String[] args) throws StateException {

		/* Fabrication de l'automate */

        AutomatonBuilder original = new NDAutomaton();
        AutomatonBuilder determinize = new NDAutomaton();
        AutomatonBuilder singleton = new NDAutomaton();
        AutomatonBuilder exp = new NDAutomaton();
        AutomatonBuilder transpose = new NDAutomaton();


		/*
         * Définition des états Notez que les états sont numérotés 0, 1, 2, ...
		 * dans l'ordre de leur création dans l'automate par défaut les états
		 * sont nommés "qi", où i est leur numéro On peut leur choisir un autre
		 * nom en le passant en paramètre de la méthode addNewState
		 */

        original.addNewState("q1");
        original.addNewState("q2");
        original.addNewState("q3");
        original.addNewState("q4");
        original.addNewState("q5");

		/*
		 * Définition des états initiaux et des états acceptants Le paramètre
		 * est indifféremment le numéro ou le nom d'un état
		 */
        original.setInitial("q1");
        original.setAccepting("q3");
        original.setAccepting("q4");

		/*
		 * Définition des transitions
		 */
        original.addTransition("q1", 'a', "q2");
        original.addTransition("q2", 'b', "q3");
        original.addTransition("q3", 'a', "q3");
        original.addTransition("q2", 'b', "q4");
        original.addTransition("q4", 'a', "q5");
        original.addTransition("q5", 'b', "q4");

        /**
         * Creations et manipulation d'automate
         */
        AutomataUtils.addSingleton("hello", singleton);
        dotToFile(singleton, "automate-test-singleton.dot");
        AutomataUtils.addFlatExp("10*1", exp);
        dotToFile(exp, "automate-test-exp.dot");
        AutomataUtils.transpose(singleton, transpose);
        dotToFile(transpose, "automate-test-transpose.dot");

		/*
		 * Dessin de l'automate (fabrication d'un fichier Graphviz)
		 */

        AutomataUtils.determinize(original, determinize);

        dotToFile(original, "automate-test-original.dot");
        dotToFile(determinize, "automate-test-determinize.dot");

		/*
		 * Affichage de l'automate, en mode texte
		 */

        System.out.println(original);
        System.out.println(determinize);

		/*
		 * Test de la méthode accept() à réactiver quand vous aurez développé
		 * une classe avec une vraie méthode accept()
		 */

        testAccept(singleton);

        System.out.println("That's all folks");

    }
}
