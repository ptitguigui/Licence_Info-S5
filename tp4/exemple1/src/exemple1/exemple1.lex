/* Exemple 1 */
package exemple1;

%%

%unicode

OPERATEUR=[-\+*/]
ENTIER_SIMPLE=[0-9]+
IDENTIFICATEUR=[A-Za-z][A-Za-z0-9]*

%%

{OPERATEUR}|{ENTIER_SIMPLE}|{IDENTIFICATEUR}
      {return new Yytoken(yytext());}


[^[:letter:]0-9]+|[:letter:]
      {}
