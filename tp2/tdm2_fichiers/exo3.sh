
echo -e "\n** Q1 :\n"

egrep 'BIS|TER' bano-59009.csv --color=auto

echo -e "\n** Q2 :\n"

egrep 'Ruelle' bano-59009.csv --color=auto

echo -e "\n** Q3 (avec un compte 10) :\n"

egrep  -m 10 '([[:upper:]]*[[:space:]][[:upper:]]*[[:space:]][[:upper:]]*)' bano-59009.csv --color=auto
