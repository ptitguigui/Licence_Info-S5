
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "sys/wait.h"

#include "graphe.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

/*
graphe: le graphe a parcourir
tabCouleurs: le tableau ou stocker les Couleurs
couleur: la couleur à stocker
num_exclu: le numero du sommet a exclure
*/
void colorieSauf(tGraphe graphe, tTabCouleurs tabCouleurs, tCouleur couleur, int nom_exclu)
{
  int i;
  int nbSommets;

  nbSommets = grapheNbSommets(graphe);
  for (i=0;i<nbSommets;i++)
  {
    if (i != nom_exclu)
    {
      tabCouleurs[i] = couleur;
    }
  }
}

/*
affiche le parcours en largeur du graphe en partant de nom_depart
*/
void parcours_largeur(tGraphe graphe, char *nom_depart)
{
  /*
  tabCouleurs[i] correspond à la couleur du sommet i
  */
  tTabCouleurs tabCouleurs;
  tNumeroSommet numeroSommet;
  tFileSommets file;
  tNumeroSommet sommetActuel;
  int nbVoisins;
  tNumeroSommet numeroVoisin;
  tNomSommet nomActuel;
  int i;

  numeroSommet = grapheChercheSommetParNom(graphe, nom_depart);
  colorieSauf(graphe, tabCouleurs, BLEU, numeroSommet);

  file = fileSommetsAlloue();
  tabCouleurs[numeroSommet] = VERT;
  fileSommetsEnfile(file, numeroSommet);

  while (!fileSommetsEstVide(file))
  {
    sommetActuel = fileSommetsDefile(file);
    nbVoisins = grapheNbVoisinsSommet(graphe, sommetActuel);
    for (i=0;i<nbVoisins;i++)
    {
      numeroVoisin = grapheVoisinSommetNumero(graphe, sommetActuel, i);
      if (tabCouleurs[numeroVoisin] == BLEU)
      {
        grapheRecupNomSommet(graphe, numeroVoisin, nomActuel);
        printf("%s\n", nomActuel);
        tabCouleurs[numeroVoisin] = VERT;
        fileSommetsEnfile(file, numeroVoisin);
      }
      tabCouleurs[sommetActuel] = ROUGE;
    }
  }

}


int main(int argc, char **argv)
{
  tGraphe graphe;

  if (argc != 3) {
    halt("Usage : %s FichierGraphe\n nom_sommet_depart", argv[0]);
  }

  graphe = grapheAlloue();
  grapheChargeFichier(graphe, argv[1]);

  parcours_largeur(graphe, argv[2]);

  grapheLibere(graphe);
  return 0;
}
