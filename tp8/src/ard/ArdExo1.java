package ard;

import java.io.Reader;

public class ArdExo1 extends Ard {


    public ArdExo1(Reader in) {
        super(in);
    }


    @Override
    protected void axiom() throws SyntaxException, ParserException {
        S();
    }

    private void S() throws SyntaxException, ParserException {
        switch (current) {
            case 'a':
            case 'b':
            case 'c':
                E();
                R();
                S();
                break;
            case '(':
                E();
                R();
                S();
                break;
            case ')':
                break;
            case END_MARKER:
                break;
            default:
                // erreur
                throw new SyntaxException(ErrorType.NO_RULE, current);
        }
    }

    private void R() throws SyntaxException, ParserException {
        switch (current) {
            case 'a':
            case 'b':
            case 'c':
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                C();
                break;
            case '(':
                break;
            case ')':
                break;
            case END_MARKER:
                break;
            default:
                // erreur
                throw new SyntaxException(ErrorType.NO_RULE, current);
        }
    }

    private void C() throws SyntaxException, ParserException {
        switch (current) {
            case '0':
                eat('0');
                break;
            case '1':
                eat('1');
                break;
            case '2':
                eat('2');
                break;
            case '3':
                eat('3');
                break;
            case '4':
                eat('4');
                break;
            case '5':
                eat('5');
                break;
            case '6':
                eat('6');
                break;
            case '7':
                eat('7');
                break;
            case '8':
                eat('8');
                break;
            case '9':
                eat('9');
                break;
            default:
                // erreur
                throw new SyntaxException(ErrorType.NO_RULE, current);
        }
    }

    private void E() throws SyntaxException, ParserException {
        switch (current) {
            case 'a':
            case 'b':
            case 'c':
                L();
                break;
            case '(':
                eat('(');
                S();
                eat(')');
                break;
        }
    }

    private void L() throws SyntaxException, ParserException {
        switch (current) {
            case 'a':
                eat('a');
                break;
            case 'b':
                eat('b');
                break;
            case 'c':
                eat('c');
                break;
            default:
                // erreur
                throw new SyntaxException(ErrorType.NO_RULE, current);
        }
    }
}
