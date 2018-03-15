package ex2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Q5 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("---------------------------------------------------");
			System.out.println("Saisissez une adresse symbolique :  (\"stop\" pour arrêter)" );
			System.out.println("---------------------------------------------------");
			String hote = sc.nextLine();
			if (hote.equalsIgnoreCase("stop")) {
				break;
			}
			System.out.println("Voici le résultat trouvé : ");
			System.out.println(adressToIP(hote));
		}

		System.out.println("Bye !!");
	}

	public static String adressToIP(String host) {
		String result = "";

		try {
				result = InetAddress.getByName(host).getHostAddress();
		} catch (UnknownHostException e) {
			return "Erreur : impossible de trouver une correspondance pour l'entrée " + host;
		}
		return result;
	}

}
