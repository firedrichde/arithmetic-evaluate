import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Queue;

public class ShuntingYardTest {
    private String expression;
    private String expectedRPN;
    private int expectedValue;

    @Before
    public void setUp() throws Exception {
        expression = "(2+3)*4-(10 -2)";
        expectedRPN = "2 3 + 4 ";
        expectedValue = 12 ;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseCompoundExpression() {
        List<Token> tokenList = ShuntingYard.parseCompoundExpression(expression);
        String tokenString = ShuntingYard.IterableToString(tokenList);
        System.out.println(tokenString);
        Queue<Token> rpn = ShuntingYard.shuntingYard(tokenList);
        String rpnString = ShuntingYard.IterableToString(rpn);
        Assert.assertEquals(expectedRPN, rpnString);
    }

    @Test
    public void eval() {
        List<Token> tokenList = ShuntingYard.parseCompoundExpression(expression);
        String tokenString = ShuntingYard.IterableToString(tokenList);
        System.out.println(tokenString);
        Queue<Token> rpn = ShuntingYard.shuntingYard(tokenList);
        String rpnString = ShuntingYard.IterableToString(rpn);
        System.out.println(rpnString);
        int actualValue = ShuntingYard.eval(rpn);
        Assert.assertEquals(expectedValue,actualValue);
    }
}