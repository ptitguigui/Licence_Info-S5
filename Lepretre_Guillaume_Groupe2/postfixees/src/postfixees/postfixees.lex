package postfixees;

%%

%unicode
%line
%column

ENTIER_SIMPLE=[0-9]+
PLUS=[+]|plus
QUO=[/]|quo
MINUS=[-]|minus
MULT=[*]|mult
OPP=opp

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
{OPP}
      { return new Opp(yytext()); }

/* ajouter le cas des espaces et fins de ligne */
[\s\n]
  {}
