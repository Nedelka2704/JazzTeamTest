package Project;

import java.util.LinkedList;

public class PolishNotation {

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' ;
    }

    static int priority(char с) {
        switch (с) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    static void operation(LinkedList<Integer> st, char op) {
        int r = st.removeLast();
        int l = st.removeLast();
        switch (op) {
            case '+':
                st.addLast(l + r);
                break;
            case '-':
                st.addLast(l - r);
                break;
            case '*':
                st.addLast(l * r);
                break;
            case '/':
                st.addLast(l / r);
                break;
        }
    }
    public static String toPolishNotation(String s) {
        String res = "";
        String result = "";
        LinkedList<String> polish = new LinkedList<>();
        LinkedList<Character> symbols = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                symbols.add(c);
                continue;
            }

            if (c == ')') {
                while (symbols.getLast() != '(')
                    polish.addLast(symbols.removeLast() + " ");
                symbols.removeLast();
                continue;
            }

            if (isOperator(c)) {
                while (!symbols.isEmpty() && priority(symbols.getLast()) >= priority(c)) {
                    polish.addLast(symbols.removeLast() + " ");
                }
                symbols.addLast(c);
                continue;
            }

            if ( Character.isDigit(c)) {
               res += c ;
               i++;
                while (i != s.length() && Character.isDigit(s.charAt(i))) {
                    res += s.charAt(i) ;
                    i++;
                }
                i--;

            }
            polish.addLast(res + " ");
            res = "";
        }
        while (!symbols.isEmpty()) {
            polish.addLast(symbols.removeLast() + "");
        }

        for (String nums: polish) {
            result += nums;
        }
        //result = result.replace("  ", " ");
        return result;
    }

    public static double getAnswer(String s) {
        String res = "";
        LinkedList<Integer> digits = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {

            if (isOperator(s.charAt(i))) {
                operation(digits,s.charAt(i));
                continue;
            }

            if (s.charAt(i) == ' ' && res.isEmpty()) {
                continue;
            }

            if ( s.charAt(i) != ' ') {
                res += s.charAt(i);
                i++;
                while (Character.isDigit(s.charAt(i))) {
                    res += s.charAt(i);
                    i++;
                } i--;
            }
            digits.addLast(Integer.parseInt(res));
            res = "";
        }

        double a = digits.get(0);
        return a;
    }

    public static void main(String[] args) {
        String polsk = toPolishNotation("(44+6)*2/(7+3)");
        System.out.println(polsk);
        double a = getAnswer(polsk);
        System.out.println(a);
    }
}