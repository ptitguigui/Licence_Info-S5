valeurAttribut="(\"[[:alnum:]]*\")|(\"$&[[:alnum:]]*\")"
nomXML="([[:alpha:]]|:|_)([[:alnum:]]|:|_|:|-|\.)*"
refEntite="&\w*;"
baliseOuvrante="<$nomXML[[:space:]](*$nomXML\=$valeurAttribut[[:space:]]?)*>"


numero33="(+33[[:space:]]\(0\)[[:space:]][1-9].[0-9]{2}.[0-9]{2}.[0-9]{2}.[0-9]{2})"
numeroNormal="(0[0-9].[0-9]{2}.[0-9]{2}.[0-9]{2}.[0-9]{2})"
numeros="$numero33|$numeroNormal"

echo -e "\n** Q1 (avec un compte 10):\n"

egrep -m 10 $valeurAttribut html/* --color=auto

echo -e "\n** Q2 (avec un compte 10):\n"

egrep -m 10 $baliseOuvrante html/* --color=auto

echo -e "\n** Q3 :\n"

egrep -o $numeros html/* --color=auto