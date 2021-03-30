public class ConstantToken extends Token{
    private String mExpression;

    public ConstantToken(String expression) {
        super(TokenType.constant);
        mExpression = expression;
    }

    @Override
    OperatorEnum getOperatorType() {
        throw new UnsupportedOperationException("this is a constant token "+mExpression);
    }

    @Override
    boolean isOperator() {
        return false;
    }

    @Override
    String getExpression() {
        return mExpression;
    }
}
