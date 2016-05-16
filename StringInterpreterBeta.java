import java.util.*;

public class StringInterpreterBeta {

	int result = 0;

	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		String inputOp = input.next();
		parseString(inputOp);

	}

	public static void parseString (String input) {
		int test = input.indexOf(")");
		if (test != -1) {
			boolean trip = true; 
			for (int i = test; i >= 0 && trip; i--) {
				if (input.charAt(i) == ('(')) {
					trip = false;
					String expression = input.substring(i, test+1);
					interpret(expression);
					parseString(input.substring(0, i) + input.substring(test+1, input.length()));
				}
			}
		}
	}

	public static void interpret (String expression) {


	}

	public static boolean isDouble( String str ) {
	    try{
	        Double.parseDouble( str );
	        return true;
	    }
	    catch( Exception e ){
	        return false;
	    }
	}

	public static boolean checkIfBoth (String expression, int placeOfOperation) {
		return (isDouble(expression.substring(0, placeOfOperation)) && isDouble(expression.substring(placeOfOperation, expression.length())));
	}
}