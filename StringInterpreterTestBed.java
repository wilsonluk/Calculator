import java.util.*;

public class StringInterpreterTestBed {

	public static double op1 = 0;
	public static double op2 = 0;
	public static boolean triper = true;

	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		String inputOp = input.next();
		System.out.println(interpret(inputOp));
	}

	public static String interpret (String expression) {
		expression = expression.replace("~", "temp");
		expression = expression.replace("-", "~");
		expression = expression.replace("temp", "-");
		if (expression.indexOf("(") != -1) {
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
				System.out.println(expression);
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
					System.out.println(testString);
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
				String result = calculate(op1, op2, operation);
				expression = expression = expression.replace(expression.substring(test - j + 1, test) + operation + expression.substring(test + 1, test + i - 1), result);
			}
		}
		while (!isDouble(expression)) {
			expression = interpret(expression);
		}
		return expression;
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
		}
		return "";
	}
}