import java.lang.*;
import java.util.Scanner;

public class CalcObj {

    public static boolean isRadians = true;
    public static int counter = 0;

    public void CalcObj (String input) {
        
    }

    public void setIsRadians(boolean isRadians){
        this.isRadians = isRadians;
    }

    public int getCounter(){
        return counter;
    }

    //Code for each of the operations
    public String interpret (String expression, boolean tripConvert) {
        double op1 = 0;
        double op2 = 0;
        expression = expression.replace("~", "temp");
        expression = expression.replace("-", "~");
        expression = expression.replace("temp", "-");
        if (expression.indexOf("e") != -1) {
            Double naturalLog = new Double(Math.E);
            expression = expression.replace("e", naturalLog.toString());
            interpret(expression, true);
        } else if (expression.indexOf("^") != -1) {
            int index = expression.indexOf("^");
            int indexOfPower = findLevel(true, index, expression.length(), expression, -1);
            String stringOfPower = expression.substring(index + 1, indexOfPower);
            Double power = Double.parseDouble(stringOfPower);
            int indexOfBase = 0;
            for (int i = index; i >= 0; i--) {
                if (expression.charAt(i) == '(') {
                    indexOfBase = i;
                    break;
                }
            }
            String stringOfBase = expression.substring(indexOfBase + 1, index);
            Double base = Double.parseDouble(stringOfBase);
            expression = expression.replace("(" + stringOfBase + "^" + stringOfPower + ")", calculate(base, power, "^"));
            interpret(expression, true);
        } else if (expression.indexOf("log(") != -1) {
            interpret(replacement(expression, "log", 3, 1), true);
        } else if (expression.indexOf("ln(") != -1) {
            interpret(replacement(expression, "ln", 2, 1), true);
        } else if (expression.indexOf("sqrt(") != -1) {
            int index = expression.indexOf("sqrt(");
            int end = findLevel(false, index, expression.length(), expression, 0);
            String originalExpression = expression.substring(index + 4, end+1);
            int end2 = findLevel(false, 0, originalExpression.length(), originalExpression, 0);
            String number = interpret(originalExpression.substring(1, end2), false);   
            String solved = calculate(Double.parseDouble(number), 0, "sqrt");
            expression = expression.replace("sqrt" + originalExpression, solved);
            interpret(expression, true);
        } else if (expression.indexOf("sin(") != -1) {
            expression = trigReplacement(expression, "sin");
            interpret(expression, true);
        } else if (expression.indexOf("cos(") != -1) {
            expression = trigReplacement(expression, "sin");
            interpret(expression, true);
        } else if (expression.indexOf("tan(") != -1) {
            int index = expression.indexOf("tan(");
            int end = findLevel(false, index, expression.length(), expression, 0);
            String originalExpression = expression.substring(index + 3, end+1);
            int end2 = findLevel(false, 0, originalExpression.length(), originalExpression, 0);
            String number = interpret(originalExpression.substring(1, end2), false);            
            if (Double.parseDouble(number) != Math.PI/2) {                
                String solved = "";
                if (isRadians) {
                    solved = calculate(Double.parseDouble(number), 0, "tan");
                }
                else {
                    solved = calculate(Math.toRadians(Double.parseDouble(number)), 0, "tan");
                }
                expression = expression.replace("tan" + originalExpression, solved);
                interpret(expression, true);
            } else {
                expression = "ERR: TAN";
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
            expression = expression = expression.replace(expression.substring(start, end + 1), interpret(expression.substring(start + 1, end), true));
        } else {
            boolean infiniteLoop = false;
            while((!isDouble(expression)) && !(infiniteLoop)) {
                counter++;                
                if(counter > 100){
                    infiniteLoop = true;                       
                }         
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
        }
        while (!isDouble(expression)) {
            if(counter > 100){
                break;
            }                
            expression = interpret(expression, true);    
        }
        return expression;
    }

    //Generic code for the other functions
    public String replacement(String expression, String operator, int startPad, int endPad) {
        int index = expression.indexOf(operator+ "(");
        int end = findLevel(false, index, expression.length(), expression, 0);
        String originalExpression = expression.substring(index + startPad, end+endPad);
        int end2 = findLevel(false, 0, originalExpression.length(), originalExpression, 0);
        System.out.println(originalExpression.substring(1, end2));
        String number = interpret(originalExpression.substring(1, end2), false);   
        return expression.replace(operator + originalExpression, calculate(Double.parseDouble(number), 0, operator));
    }

    //Generic code for the trig functions
    public String trigReplacement(String expression, String operator) {
        int index = expression.indexOf(operator + "(");
        int end = findLevel(false, index, expression.length(), expression, 0);
        String originalExpression = expression.substring(index + 3, end+1);
        int end2 = findLevel(false, 0, originalExpression.length(), originalExpression, 0);
        String number = interpret(originalExpression.substring(1, end2), false);                        
        String solved = "";
        if(isRadians){
            solved = calculate(Double.parseDouble(number), 0, operator);
        }
        else{
            solved = calculate(Math.toRadians(Double.parseDouble(number)), 0, operator);
        }
        expression = expression.replace(operator + originalExpression, solved);
        return expression.replace("-", "~");
    }

    //Finds the index of a string where the parenthesis level is the passed integer
    public static int findLevel (boolean trip2, int start, int end, String text, int levelSet) {
        int level = 0;
        int index = 0;
        boolean trip = false;
        for (int i = start; i < end; i++) {
            if (text.charAt(i) == '(') {
                level++;
                trip = true;
            } else if (text.charAt(i) == ')') {
                level--;
            }
            if (level == levelSet && trip) {
                index = i;
                break;
            }
            if (trip2) {
                trip = true;
                trip2 = false;
            }
            
        }
        return index;
    }

    //Checks whether a string is a double or not
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

    //Runs the calcuation for each of the operations
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
        } else if (temp.equals("log")) {
            Double tempDouble = Math.log10(op1);
            return tempDouble.toString();
        } else if (temp.equals("ln")) {
            Double tempDouble = Math.log(op1);
            return tempDouble.toString();
        } else if (temp.equals("sqrt")) {
            Double tempDouble = Math.sqrt(op1);
            return tempDouble.toString();
        } else if (temp.equals("^")) {
            Double tempDouble = Math.pow(op1, op2);
            return tempDouble.toString();
        }
        return "";
    }

    //Code only for testing
    public static void main (String[] args) {
        System.out.println("Enter an operation:");
        Scanner input = new Scanner (System.in);
        CalcObj newCalc = new CalcObj();
        System.out.println(newCalc.interpret(input.next(), true));
    }
}