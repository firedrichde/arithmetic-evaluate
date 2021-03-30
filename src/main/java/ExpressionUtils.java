import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpressionUtils {
    public static char sZero = '0';
    public static char sLeftBracket = '(';
    public static char sRightBracket = ')';
    public static char sBlankChar = ' ';
    private static Map<Character,OperatorEnum> sOperatorMap = new HashMap<>();
    private static Set<Character> sOperatorSet = new HashSet<>();
    static {
        OperatorEnum[] operatorEnums = OperatorEnum.values();
        for (OperatorEnum operatorEnum : operatorEnums) {
            sOperatorMap.put(operatorEnum.getOperator(), operatorEnum);
            sOperatorSet.add(operatorEnum.getOperator());
        }
    }
    public static OperatorEnum getOperatorEnum(char operator) {
        OperatorEnum operatorEnum = sOperatorMap.get(operator);
        if (operatorEnum == null) {
            throw new IllegalArgumentException("illegal operator");
        }else {
            return operatorEnum;
        }
    }

    public static boolean isOperator(char operator) {
        return sOperatorSet.contains(operator);
    }

    public static int parseInt(char ch) {
        int value = ch -sZero;
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("not a digit");
        }else {
            return value;
        }
    }

    public static boolean isLeftBracket(char letter) {
        return letter == sLeftBracket;
    }

    public static boolean isRightBracket(char letter) {
        return letter == sRightBracket;
    }

    public static boolean isDigit(char letter) {
        int value = letter - sZero;
        return value >=0 && value <=9;
    }

    /**
     * find the boundary index of the specified integer in the expression from the given start index
     * example:
     *   expression 16*(2+3), from index 0 the integer is 16, so the boundary index is 1
     * @param expression the given expression
     * @param startIndex the given start index
     * @return the boundary index of the integer
     */
    public static int findIntegerBoundary(String expression, int startIndex) {
        int boundary = startIndex;
        for (int i = startIndex; i < expression.length(); i++) {
            char letter = expression.charAt(i);
            if (!isDigit(letter)) {
                break;
            } else {
                boundary = i;
            }
        }
        return boundary;
    }

    public static String expressionAdd(String leftOperandExpression, String rightOperandExpression) {
        int leftNumber = Integer.parseInt(leftOperandExpression);
        int rightNumber = Integer.parseInt(rightOperandExpression);
        return String.valueOf(leftNumber+rightNumber);
    }

    public static String expressionSub(String leftOperandExpression, String rightOperandExpression) {
        int leftNumber = Integer.parseInt(leftOperandExpression);
        int rightNumber = Integer.parseInt(rightOperandExpression);
        return String.valueOf(leftNumber - rightNumber);
    }
    public static String expressionMul(String leftOperandExpression, String rightOperandExpression) {
        int leftNumber = Integer.parseInt(leftOperandExpression);
        int rightNumber = Integer.parseInt(rightOperandExpression);
        return String.valueOf(leftNumber * rightNumber);
    }

    public static String expressionDiv(String leftOperandExpression, String rightOperandExpression) {
        int leftNumber = Integer.parseInt(leftOperandExpression);
        int rightNumber = Integer.parseInt(rightOperandExpression);
        int resultNumber = leftNumber / rightNumber;
        return String.valueOf(resultNumber);
    }

}
