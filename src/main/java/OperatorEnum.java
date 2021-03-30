public enum OperatorEnum {
    ADD('+', 1, Associativity.left),
    SUB('-', 1, Associativity.left),
    MUL('*', 5, Associativity.left),
    DIV('/', 5, Associativity.left)
    ;
    private char mOperator;
    private int mPrecedence;
    private int mAssociativity;

    OperatorEnum(char operator, int precedence, int associativity) {
        mOperator = operator;
        mPrecedence = precedence;
        mAssociativity = associativity;
    }

    public char getOperator() {
        return mOperator;
    }

    public int getPrecedence() {
        return mPrecedence;
    }

    public int getAssociativity() {
        return mAssociativity;
    }
}
