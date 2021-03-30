import java.util.Scanner;

public class Launder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String expressionLine = scanner.nextLine().strip();
            Brackets brackets = new Brackets(expressionLine);
            try {
                brackets.valid();
            }catch (Brackets.BracketMatchException e){
                System.err.println(e.getMessage());
                continue;
            }
            int result = ShuntingYard.eval(expressionLine);
            System.out.println(expressionLine+" = "+result);
        }
    }
}
