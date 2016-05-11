import java.lang.Math;

public class calculation {
	
	private String operator = "";
	private double firstNum = 0.0;
	private double secondNum = 0.0;
	private double memNum = 0.0;
	private String memOp = "";

	public calculation () {}

	public void setFirstNum (double firstNum) {
		this.firstNum = firstNum;
	}

	public void setSecondNum (double secondNum) {
		this.secondNum = secondNum;
	}

	public void setOperator (String operator) {
		this.operator = operator;
	}

	public boolean checkifNull () {
		if (Double.isNaN(this.firstNum)) {
			return true;
		}
		return false;
	}

	public void erase(String test) {
		this.memNum = this.secondNum;
		this.memOp = this.operator;
		this.operator = null;
		this.firstNum = Double.NaN;
		this.secondNum = Double.NaN;
		if (test.equals("all")) {
			this.memNum = Double.NaN;
			this.memOp = null;
		}
	}

	public String calculate () {
		if (this.operator == null) {
			this.operator = this.memOp;
			this.secondNum = this.memNum;
		}
		if (this.operator.equals("sum")) {
			return Double.toString(this.firstNum+this.secondNum);
		} else if (this.operator.equals("sub")) {
			return Double.toString(this.firstNum-this.secondNum);
		} else if (this.operator.equals("div")) {
			if (this.secondNum == 0) {
				erase("");
				return "ERR: Divide by 0";
			} else {
				return Double.toString(this.firstNum/this.secondNum);
			}
		} else if (this.operator.equals("multi")) {
			return Double.toString(this.firstNum*this.secondNum);
		}
		return "ERR";
	}

}