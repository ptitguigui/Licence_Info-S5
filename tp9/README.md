AEL : TP9 LL(1)
===================

Dans ce TP, nous faisons des manipulations sur les analyse lexicales

----------

# Composition du TP:

- Les différentes classe présents dans `src`
- Le `README.md` de ce tp

# Utilisation du TP :

Pour testet l'ensemble du tp:

- Exécuter les classes présents dans le pakage `main`
- Testet pour chacun des cas des valeurs correcte et non correcte de la table LL(1)


# Liste des mots qui sont ou non dans le langage de la table :


## Mots qui fonctionnent :

- truc
-(truc)
- !truc
- truc && truc
- truc || truc
- (truc)|| truc && truc || !(truc && truc)
- .. etc

## Mots qui ne  fonctionnent pas :
- !!
- && && 
- || ||
- truc & truc
- truc et truc
- truc ou truc
- truc truc truc
- .. etc