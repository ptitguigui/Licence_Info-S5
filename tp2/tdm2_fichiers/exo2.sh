valeurAttribut="(\"[[:alnum:]]*\")|(\"$&[[:alnum:]]*\")"
nomXML="([[:alpha:]]|:|_)([[:alnum:]]|:|_|:|-|\.)*"
refEntite="&\w*;"
baliseOuvrante="<$nomXML[[:space:]](*$nomXML\=$valeurAttribut[[:space:]]?)*>"


numero33='+33[[:space:]](0)[[:space:]][0-9].([0-9]{2}.[0-9]{2}){2}'

echo -e "\n** Q1 :\n"

egrep -m 10 $valeurAttribut html/* --color=auto

echo -e "\n** Q2 :\n"

egrep -m 10 $baliseOuvrante html/* --color=auto

echo -e "\n** Q3 :\n"

egrep  $numero33 html/* --color=auto

echo -e "\n** Q4 :\n"

#egrep  ' ' html/* --color=auto
