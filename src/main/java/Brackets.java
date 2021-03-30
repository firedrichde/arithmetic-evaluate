import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Brackets {
    private String mExpression;
    private int mSize;
    private Map<Integer, Integer> mMatcher;

    public Brackets(String expression) {
        mExpression = expression;
        mMatcher = new HashMap<>();
        mSize = 0;
    }

    public void valid() throws BracketMatchException {
        parseBracketPairs();
    }

    private void parseBracketPairs() throws BracketMatchException {
        Stack<Integer> bracketsStack = new Stack<>();
        for (int i = 0; i < mExpression.length(); i++) {
            char letter = mExpression.charAt(i);
            if (letter == ExpressionUtils.sLeftBracket) {
                bracketsStack.push(i);
            } else if (letter == ExpressionUtils.sRightBracket) {
                if (bracketsStack.isEmpty()) {
                    throw new BracketMatchException(
                            "right brackets in expression is not well match, the extra bracket index is " + i);
                }
                Integer matchIndex = bracketsStack.pop();
                mMatcher.put(matchIndex, i);
                mSize++;
            }
        }
        if (!bracketsStack.isEmpty()) {
            int extraIndex = bracketsStack.peek();
            throw new BracketMatchException("" +
                    "left brackets in expression is not well match, the extra bracket index is " + extraIndex);
        }
    }


    public Integer getRightBracket(int left) {
        return mMatcher.get(left);
    }

    public int getSize() {
        return mSize;
    }

    public class BracketMatchException extends Exception {
        public BracketMatchException() {
        }

        public BracketMatchException(String message) {
            super(message);
        }
    }
}
