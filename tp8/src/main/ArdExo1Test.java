package main;

import ard.ParserException;
import ard.SyntaxException;
import org.junit.Test;

import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArdExo1Test {

    @Test
    public void testExpandSimple() throws SyntaxException, ParserException {
        String input = "ba2";
        String expected = "baa";

        String result = getParseResult(input);
        assertThat(result, is(expected));
    }


    @Test
    public void testExpandModerate() throws SyntaxException, ParserException {
        String input = "(ba)2";
        String expected = "baba";

        String result = getParseResult(input);
        assertThat(result, is(expected));
    }


    @Test
    public void testExpandDifficult() throws SyntaxException, ParserException {
        String input = "(a(bc)2)3(ba)2";
        String expected = "abcbcabcbcabcbcbaba";

        String result = getParseResult(input);
        assertThat(result, is(expected));
    }

    private String getParseResult(String input) throws SyntaxException, ParserException {
        ArdExo1 ardExo1 = new ArdExo1(new StringReader(input));
        ardExo1.parse();
        return ardExo1.getExpandedExpression();
    }

}
