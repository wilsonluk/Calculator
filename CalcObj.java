public class CalcObj {

    public void CalcObj (String input) {
        
    }

    public String interpret (String expression) {
        boolean errorTrip = false;
        double op1 = 0;
        double op2 = 0;
        expression = expression.replace("~", "temp");
        expression = expression.replace("-", "~");
        expression = expression.replace("temp", "-");
        if (expression.indexOf("sin(") != -1) {
            int index = expression.indexOf("sin(");
            int end = findNeutralLevel(index, expression.length(), expression);
            String originalExpression = expression.substring(index + 3, end+1);
            int end2 = findNeutralLevel(0, originalExpression.length(), originalExpression);
            String number = interpret(originalExpression.substring(1, end2));
            String solved = calculate(Double.parseDouble(number), 0, "sin");
            expression = expression.replace("sin" + originalExpression, solved);
            interpret(expression);
        } else if (expression.indexOf("cos(") != -1) {
            int index = expression.indexOf("cos(");
            int end = findNeutralLevel(index, expression.length(), expression);
            String originalExpression = expression.substring(index + 3, end+1);
            int end2 = findNeutralLevel(0, originalExpression.length(), originalExpression);
            String number = interpret(originalExpression.substring(1, end2));
            String solved = calculate(Double.parseDouble(number), 0, "cos");
            expression = expression.replace("cos" + originalExpression, solved);
            interpret(expression);
        } else if (expression.indexOf("tan(") != -1) {
            int index = expression.indexOf("tan(");
            int end = findNeutralLevel(index, expression.length(), expression);
            String originalExpression = expression.substring(index + 3, end+1);
            int end2 = findNeutralLevel(0, originalExpression.length(), originalExpression);
            String number = interpret(originalExpression.substring(1, end2));            
            if(Double.parseDouble(number) != Math.PI/2) {
                System.out.println(number);
                System.out.println("Hi");
                String solved = calculate(Double.parseDouble(number), 0, "tan");
                expression = expression.replace("tan" + originalExpression, solved);
                interpret(expression);
            } else {
                expression = "ERR: TAN";
                errorTrip = true;
            }
        } else if (expression.indexOf("(") != -1) {
            int start = 0;
            int end = expression.indexOf(")");
            boolean trip = true;
            for (int i = end; i > 0 && trip; i--) {
                if (expression.charAt(i) == '(') {
                    trip = false;
                    start = i;
                }
            }
            expression = expression.replace("~", "temp");
            expression = expression.replace("-", "~");
            expression = expression.replace("temp", "-");
            expression = expression = expression.replace(expression.substring(start, end + 1), interpret(expression.substring(start + 1, end)));
        } else {
            while(!isDouble(expression)) {
                boolean oooTrip = false;
                String operation = "";
                int test = 0;
                if (expression.indexOf("*") != -1) {
                    test = expression.indexOf("*");
                    operation = "*";
                    oooTrip = true;
                }
                if (expression.indexOf("/") != -1) {
                    if (expression.indexOf("/") < test || test == 0) {
                        test = expression.indexOf("/");
                        operation = "/";
                    }
                    oooTrip = true;
                }
                if (expression.indexOf("~") != -1 && !oooTrip) {
                    test = expression.indexOf("~");
                    operation = "~";
                } else if (expression.indexOf("+") != -1 && !oooTrip) {
                    test = expression.indexOf("+");
                    operation = "+";
                }
                int i = 2;
                while (test + i <= expression.length() && isDouble(expression.substring(test + 1, test + i))) {
                    boolean tripForNegative = false;
                    String testString = expression.substring(test + 1, test + i);
                    if(testString.charAt(0) == '-') {
                        testString = testString.replace("-", "");
                        tripForNegative = true;
                        if(testString.equals("")) {
                            testString = "0";
                        }
                    }
                    op2 = Double.parseDouble(testString);
                    if (tripForNegative) {
                        op2 = op2*-1;
                    }
                    i++;
                }
                int j = 1;
                while (test - j >= 0 && isDouble(expression.substring(test - j, test))) {
                    boolean tripForNegative = false;
                    String testString = expression.substring(test - j, test);
                    if(testString.charAt(0) == '-') {
                        testString = testString.replace("-", "");
                        tripForNegative = true;
                        if(testString.equals("")) {
                            testString = "0";
                        }
                    }
                    op1 = Double.parseDouble(testString);
                    if (tripForNegative) {
                        op1 = op1*-1;
                    }
                    j++;
                }                
                if(op2 == 0 && operation.equals("/")) {
                    return "ERR: Divide By 0";
                } else {
                    String result = calculate(op1, op2, operation);
                    expression = expression = expression.replace(expression.substring(test - j + 1, test) + operation + expression.substring(test + 1, test + i - 1), result);
                }
                
            }
        }if (!errorTrip) {
            while (!isDouble(expression)) {
                expression = interpret(expression);
            }
        }
        expression = expression.replace("-", "~");
        return expression;
    }

    public static int findNeutralLevel (int start, int end, String text) {
        int level = 0;
        int index = 0;
        for (int i = start; i < end; i++) {
            if (text.charAt(i) == '(') {
                level++;
            } else if (text.charAt(i) == ')') {
                level--;
            }
            if (level == 0) {
                index = i;
            }
        }
        return index;
    }

    public static boolean isDouble( String str ) {
        if (str.indexOf("+") != -1) {
            return false;
        }
        if(str.equals("-")) {
            return true;
        }
        try{
            Double.parseDouble( str );
            return true;
        }
        catch( Exception e ){
            return false;
        }
    }

    public static String calculate (double op1, double op2, String temp) {
        if (temp.equals("*")) {
            return Double.toString(op1*op2);
        } else if (temp.equals("+")) {
            return Double.toString(op1+op2);
        } else if (temp.equals("~")) {
            return Double.toString(op1-op2);
        } else if (temp.equals("/")) {
            return Double.toString(op1/op2);
        } else if (temp.equals("sin")) {
            Double tempDouble = Math.sin(op1);
            return tempDouble.toString();
        } else if (temp.equals("cos")) {
            Double tempDouble = Math.cos(op1);
            return tempDouble.toString();
        } else if (temp.equals("tan")) {
            Double tempDouble = Math.tan(op1);
            return tempDouble.toString();
        }
        return "";
    }
}