import java.util.*;

public class ShuntingYard {
    /**
     * convert infix notations to reverse polish notations
     *
     * @param infixNotations the given tokens in infix order
     * @return reverse polish notations, if brackets is not be matched well , throw IllegalArgumentException
     */
    public static Queue<Token> shuntingYard(List<Token> infixNotations) {
        Stack<Token> operatorTokenStack = new Stack<>();
        Queue<Token> reversePolishNotationQueue = new LinkedList<>();
        Token topOperatorToken;
        for (Token token :
                infixNotations) {
            //                    int currentTokenAssociativity = token.getOperatorType().getAssociativity();
            switch (token.getTokenType()) {
                case TokenType.constant -> reversePolishNotationQueue.add(token);
                case TokenType.operator -> {
                    int topOperatorPrecedence;
                    int topOperatorAssociativity;
                    int currentTokenPrecedence = token.getOperatorType().getPrecedence();
                    while (!operatorTokenStack.isEmpty() &&
                            (topOperatorToken = operatorTokenStack.peek()).isOperator()) {
//                        topOperatorToken = operatorTokenStack.peek();
                        topOperatorPrecedence = topOperatorToken.getOperatorType().getPrecedence();
                        topOperatorAssociativity = topOperatorToken.getOperatorType().getAssociativity();
                        if (topOperatorPrecedence > currentTokenPrecedence ||
                                (topOperatorPrecedence == currentTokenPrecedence &&
                                        topOperatorAssociativity == Associativity.left)) {
                            reversePolishNotationQueue.add(operatorTokenStack.pop());
                        } else {
                            break;
                        }
                    }
                    operatorTokenStack.push(token);
                }
                case TokenType.bracket -> {
                    BracketToken bracketToken = (BracketToken) token;
                    if (bracketToken.isLeftBracket()) {
                        operatorTokenStack.push(token);
                    } else {
                        while (!operatorTokenStack.isEmpty() &&
                                operatorTokenStack.peek().isOperator()) {
                            reversePolishNotationQueue.add(operatorTokenStack.pop());
                        }
                        if (operatorTokenStack.isEmpty()) {
                            throw new IllegalArgumentException("extra right bracket");
                        } else {
                            // pop the left bracket in match
                            operatorTokenStack.pop();
                        }
                    }
                }
            }
        }
        while (!operatorTokenStack.isEmpty()) {
            reversePolishNotationQueue.add(operatorTokenStack.pop());
        }
        return reversePolishNotationQueue;
    }

    public static String IterableToString(Iterable<Token> items) {
        StringBuilder builder = new StringBuilder();
        for (Token token :
                items) {
            builder.append(token.getExpression());
            builder.append(" ");
        }
        return builder.toString().strip();
    }

    /**
     * convert compound expression to tokens
     * example : convert "2 + 3 * 4" to {"2", "+", "3", "*", "4}
     *
     * @param compoundExpression the given compound expression
     * @return tokens
     */
    public static List<Token> parseCompoundExpression(String compoundExpression) {
        List<Token> tokenList = new ArrayList<>();
        String blank = " ";
        String[] expressions = compoundExpression.split(blank);
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            int j_start;
            for (int j = 0; j < expression.length(); j++) {
                char letter = expression.charAt(j);
                if (ExpressionUtils.isOperator(letter)) {
                    tokenList.add(new OperatorToken(letter));
                } else if (ExpressionUtils.isLeftBracket(letter) || ExpressionUtils.isRightBracket(letter)) {
                    tokenList.add(new BracketToken(letter));
                } else if (ExpressionUtils.isDigit(letter)) {
                    j_start = j;
                    int constantExpressionBoundary = ExpressionUtils.findIntegerBoundary(expression, j);
                    String constantExpression = expression.substring(j_start, constantExpressionBoundary + 1);
                    tokenList.add(new ConstantToken(constantExpression));
                    j = constantExpressionBoundary;
                } else {
                    throw new IllegalArgumentException("noKnow letter " + letter);
                }
            }
        }
        return tokenList;
    }

    public static int eval(Queue<Token> reversePolishNotations) {
        Stack<Token> stack = new Stack<>();
        for (Token token :
                reversePolishNotations) {
            switch (token.getTokenType()) {
                case TokenType.constant -> stack.push(token);
                case TokenType.operator -> {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("lost operand for operator " + token.getExpression());
                    }
                    Token rightOperandToken = stack.pop();
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("lost operand for operator " + token.getExpression());
                    }
                    Token leftOperandToken = stack.pop();
                    stack.push(operate(token, leftOperandToken, rightOperandToken));
                }
                default -> throw new IllegalArgumentException("reverse polish notations is illegal");
            }
        }
        Token resultToken = stack.pop();
        if (resultToken == null || resultToken.getTokenType() != TokenType.constant) {
            throw new IllegalArgumentException("eval is wrong " + resultToken.getExpression());
        } else {
            return Integer.parseInt(resultToken.getExpression());
        }
    }

    public static ConstantToken operate(Token operator, Token leftOperand, Token rightOperand) {
        String resultExpression;
        String leftOperandExpression = leftOperand.getExpression();
        String rightOperandExpression = rightOperand.getExpression();
        resultExpression = switch (operator.getOperatorType()) {
            case ADD -> ExpressionUtils.expressionAdd(leftOperandExpression, rightOperandExpression);
            case SUB -> ExpressionUtils.expressionSub(leftOperandExpression, rightOperandExpression);
            case MUL -> ExpressionUtils.expressionMul(leftOperandExpression, rightOperandExpression);
            case DIV -> ExpressionUtils.expressionDiv(leftOperandExpression, rightOperandExpression);
        };
        return new ConstantToken(resultExpression);
    }

    public static int eval(String compoundExpression) {
        List<Token> tokenList = parseCompoundExpression(compoundExpression);
        Queue<Token> reversePolishNotation =  shuntingYard(tokenList);
        return eval(reversePolishNotation);
    }
}
