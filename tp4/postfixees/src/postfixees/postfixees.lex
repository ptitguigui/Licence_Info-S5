package postfixees;

%%

%unicode
%line
%column

ENTIER_SIMPLE=[0-9]+
PLUS=[+]|plus
QUO=[/]|plus
MINUS=[-]|plus
MULT=[*]|plus

%%

{ENTIER_SIMPLE}
      { return new Valeur(yytext()); }

{PLUS}
      { return new Plus(yytext()); }

{QUO}
      { return new Quo(yytext()); }

{MINUS}
      { return new Minus(yytext()); }

{MULT}
      { return new Mult(yytext()); }

/* ajouter le cas des espaces et fins de ligne */
[\s]
  {}

/* ajouter les autres tokens */
