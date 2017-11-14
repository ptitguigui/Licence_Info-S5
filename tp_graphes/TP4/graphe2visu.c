#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"


void plusCourteChaine(tGraphe graphe, char *nom_depart)
{

}

int main(int argc, char **argv)
{
  tGraphe graphe;

  if (argc != 3) {
    halt("Usage : %s <fichier_graphe> <nom_sommet_depart>\n", argv[0]);
  }

  graphe = grapheAlloue();
  grapheChargeFichier(graphe, argv[1]);

  parcours_profondeur(graphe, argv[2]);

  grapheLibere(graphe);
  return 0;
}
