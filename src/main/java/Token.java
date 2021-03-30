public abstract class Token {
    private int mTokenType;
    public Token(int tokenType) {
        mTokenType = tokenType;
    }

    public int getTokenType() {
        return mTokenType;
    }


    abstract boolean isOperator();

    abstract String getExpression();

    abstract OperatorEnum getOperatorType();

    @Override
    public String toString() {
        return getExpression();
    }
}
