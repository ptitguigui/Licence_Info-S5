
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

void plusCourteChaine(tGraphe graphe, char *nom_depart)
{
  /*
  tabCouleurs[i] correspond à la couleur du sommet i
  */
  tTabCouleurs tabCouleurs;
  tFileSommets file;
  tNumeroSommet numeroDepart;
  int nbSommets;

  int d[MAX_SOMMETS];
  tNumeroSommet pred[MAX_SOMMETS];

  tNumeroSommet sommetActuel;
  int i;
  int nbVoisins;
  tNumeroSommet numeroVoisin;
  tNomSommet nomSommet;


  numeroDepart = grapheChercheSommetParNom(graphe, nom_depart);
  nbSommets = grapheNbSommets(graphe);
  colorieSauf(graphe, tabCouleurs, BLEU, numeroDepart);

  file = fileSommetsAlloue();
  for (i=0;i<nbSommets;i++) {
    d[i] = 0;
    pred[i] = 0;
  }

  tabCouleurs[numeroDepart] = VERT;

  fileSommetsEnfile(file, numeroDepart);

  while (!fileSommetsEstVide(file))
  {
      sommetActuel = fileSommetsDefile(file);
      nbVoisins = grapheNbVoisinsSommet(graphe, sommetActuel);
      for (i=0;i<nbVoisins;i++)
      {
        numeroVoisin = grapheVoisinSommetNumero(graphe, sommetActuel, i);
        if (tabCouleurs[numeroVoisin] == BLEU)
        {
          tabCouleurs[numeroVoisin] = VERT;
          fileSommetsEnfile(file, numeroVoisin);
          d[numeroVoisin] = d[sommetActuel] + 1;
          pred[numeroVoisin] = sommetActuel;
        }
      }
      tabCouleurs[sommetActuel] = ROUGE;
  }

  for (i=0;i<nbSommets;i++) {
    grapheRecupNomSommet(graphe, i, nomSommet);
    printf("d[%s]=%d\n", nomSommet, d[i]);
    printf("pred[%s]=%d\n", nomSommet, pred[i]);
  }


  return;
}


int main(int argc, char **argv)
{
  tGraphe graphe;

  if (argc != 3) {
    halt("Usage : %s <fichier_graphe> <nom_sommet_depart>\n", argv[0]);
  }

  graphe = grapheAlloue();
  if (strncmp("alea", argv[1], 4) == 0)
  {
    printf("aleatoire\n");
    grapheAleatoire(graphe, 15, 1, 1);
  } else
  {
    grapheChargeFichier(graphe, argv[1]);
  }


  plusCourteChaine(graphe, argv[2]);

  grapheLibere(graphe);
  return 0;
}
