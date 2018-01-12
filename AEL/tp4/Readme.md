TP AEL n4
===================



# Composition du TP :

- Une archive `exemple1`
- Une arche `postfixees`
- Un jar `jflex-1.6.1.jar` 
- Un `Readme.md`

Dans les deux archives vous trouverez :

- Le code source dans `/src`
- Des fichiers compilé dans `/bin`
- Des tests pour montrer que tout cela fonctionne


# Utilisé les tests :

Exemple pour utilisés les tests dans `exemple1` :

- Se placer dans le dossier exemple1
- Lancer la commance : `java -jar ../jflex-1.6.1.jar src/exemple1/exemple1.lex` pour générer l'analyseur
- Lancer la commande : `javac -cp src -d bin src/exemple1/TestEx1.java` pour compiler
- Lancer la commande : `java -cp bin exemple1.TestEx1 test.txt` pour exécuter

Pour `postfixees`, faire de même avec les fichier tests dans le dossier `/test`