public class OperatorToken extends Token{
    private char mOperator;
    private OperatorEnum mOperatorEnum;

    public OperatorToken(char operator) {
        super(TokenType.operator);
        mOperator = operator;
        mOperatorEnum = ExpressionUtils.getOperatorEnum(operator);
    }

    @Override
    boolean isOperator() {
        return true;
    }

    @Override
    String getExpression() {
        return String.valueOf(mOperator);
    }

    @Override
    OperatorEnum getOperatorType() {
        return mOperatorEnum;
    }
}
