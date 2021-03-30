public class BracketToken extends Token {
    private char mBracket;
    private boolean mIsLeftBracket;

    public BracketToken(char bracket) {
        super(TokenType.bracket);
        mBracket = bracket;
        if (bracket == ExpressionUtils.sLeftBracket) {
            mIsLeftBracket = true;
        } else if (bracket == ExpressionUtils.sRightBracket) {
            mIsLeftBracket = false;
        } else {
            throw new IllegalArgumentException("is not a bracket " + bracket);
        }
    }

    public boolean isLeftBracket() {
        return mIsLeftBracket;
    }

    @Override
    boolean isOperator() {
        return false;
    }

    @Override
    String getExpression() {
        return String.valueOf(mBracket);
    }

    @Override
    OperatorEnum getOperatorType() {
        throw new UnsupportedOperationException("this is a bracket");
    }
}
