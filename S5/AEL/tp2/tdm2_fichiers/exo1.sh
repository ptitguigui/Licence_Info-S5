echo -e "\n** Q1 :\n"

egrep 'nez' Cyrano.txt --color=auto

echo -e "\n** Q2 :\n"

egrep '\(.*\)' Cyrano.txt --color=auto

echo -e "\n** Q3 :\n"

egrep -w '[[:alpha:]]{4}' Cyrano.txt --color=auto

echo -e "\n** Q4 :\n"

egrep '[[:alpha:]]*[[:space:]]:' Cyrano.txt --color=auto
