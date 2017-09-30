Bienvenue sur mon TP Donjon
===================

Dans ce tp vous pouvez jouer à une partie de donjon...

----------

#Comment lancer le jeu :

	- Lancer la commande executable `COO-Donjon-1.0-SNAPSHOT.jar` dans le dossier target
	- Celui-ci vous montrera le donjon que vous devez parcourir
	- De base, le donjon fait une taille de 5
	- Vous pouvez choisir la taille du donjon en rajoutant la taille que vous voulez en option
	- Par exemple si vous voulez une taille de 10 lancez `COO-Donjon-1.0-SNAPSHOT.jar 10`
	- Attention, la taille doit etre `supérieur ou égale à 5`

#Déroulement d'une partie :

	- Une fois la commande lancé, vous pouvez apercevoir le donjon que vous devez traverser
	- Vous vous trouvez tout en haut à gauche du donjon et la sortie se trouve en bas à droite
	- Le but est de `sortir du donjon vivant`
	- Ainsi, vous vous devez vous déplacer de salle en salle
	- Cependant vous ne pouvez sortir d'une salle si un monstre est présent
	- Vous devez alors `l'affronter jusqu'à sa mort`
	- Vous pouvez fouiller les alentours de la salle 
	- Ainsi, vous pouvez `dégoter des informations sur la salle` et `trouver des objets`
	- Lorsque vous trouvez des objets, vous le `garder dans votre inventaire`
	- Vous pouvez `utiliser vos objets` pour mener à bien votre expedition
 	
# Differentes actions possibles :

## Attaquer:
	- Le joueur peut attaquer des monstres
	- Pour cela il faut choisir l'action `Attack a monster`
	- Si le monstre attaqué n'est pas mort alors celui-ci riposte

## Se déplacer:
	- Le joueur peut se déplacer de salle en salle
	- Pour cela il faut choisir l'action `Move to a room`
	- Si un monstre est présent alors le joueur ne peux se déplacer

## Fouiller la salle:
	- Le joueur peut fouille une salle
	- Pour cela il faut choisir l'action `Look around yourself`
	- Cette action permet de donner des infos sur la salle
	- Si un objet est present, le joueur le ramasse

## Utiliser des objets:
	- Le joueur peut utiliser des objets
	- Pour cela il faut choisir la method `Use an item`
	- Les objets sont des potions `rare`
	- Elles permettent d'améliorer un attribut du joueur
	

