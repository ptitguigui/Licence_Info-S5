#! /bin/sh -uf

# le dossier de tests
TEST_DIR="testing"

# La commande qui genere le genome
ALEA=./aleazard
# Le fichier de sortie du genome
ALEA_OUTPUT=$TEST_DIR/genome

# La commande à tester
COMPT=./compteur-gc
# le fichier à lui mettre en entrée
COMPT_INPUT=$ALEA_OUTPUT


# la commande gnu time
TIME_CMD="/usr/bin/time"
# les options de cette commande
TIME_OPT="-f %e"
# Le fichier de temps à générer
TIME_FILE=$TEST_DIR/COMPT-TM.dat

# initialisation du fichier de résultats
rm -f $TIME_FILE && echo -ne "# genome_count\tthread_count\tcount_time" > $TIME_FILE

# pour taille genome de 10² a 10⁹
for genome_size in `awk 'BEGIN { for( i=100; i<=1000000000; i*=10 ) print i }'`; do
    # initialisation du genome
    rm -rf $ALEA_OUTPUT
    $ALEA $genome_size > $ALEA_OUTPUT

    # pour n_threads de 2⁰ à 2⁵
    for thread_count in `awk 'BEGIN { for( i=1; i<=32; i*=2 ) print i }'`; do
        echo -ne "\t$genome_size\t$thread_count" >> $TIME_FILE
        $TIME_CMD "$TIME_OPT" $COMPT $COMPT_INPUT $thread_count> /dev/null 2>> $TIME_FILE
    done
done

rm -rf $TIME_FILE
