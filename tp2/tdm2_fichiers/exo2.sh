valeurAttribut="(\"[[:alnum:]]*\")|(\"$&[[:alnum:]]*\")"
nomXML="([[:alpha:]]|:|_)([[:alnum:]]|:|_|:|-|\.)*"
refEntite="&\w*;"
baliseOuvrante="<$nomXML[[:space:]](*$nomXML\=$valeurAttribut[[:space:]]?)*>"

echo -e "\n** Q1 :\n"

egrep -m 10 $valeurAttribut html/* --color=auto

echo -e "\n** Q2 :\n"

egrep -m 10 $baliseOuvrante html/* --color=auto

echo -e "\n** Q3 :\n"

#egrep  ' ' html/* --color=auto

echo -e "\n** Q4 :\n"

#egrep  ' ' html/* --color=auto
