import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Calc2 extends Application {

	private static final double WINDOW_WIDTH = 500 ; //sets the deafult window width
    private static final double WINDOW_HEIGHT = 300 ; //sets the default window height
    public static String displayContents = ("0");
    public static double num = 0.0;
    public static calculation calc = new calculation();

	TextField display;
	Button zero, one, two, three, four, five, six, seven, eight, nine;
	Button multiply, divide, add, subtract;
    Button sin, cos, tan;
	Button decimal, equals, dummy, reset, clearScreen;
    Button adjustFont;
    ChoiceBox<Integer> choiceBox = new ChoiceBox<>();

    int fontSize = 20;

	public void start(Stage primaryStage) { //initializes the GUI
		
		display = new TextField();
		String str = new String("SansSerif");		
		display.setAlignment(Pos.CENTER_RIGHT);
		display.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        choiceBox.getItems().addAll(10, 20, 30, 40, 50, 60, 70);
        choiceBox.setValue(20);        
        choiceBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);        

		zero = new Button("0");
		zero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		one = new Button("1");
		one.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		two = new Button("2");
		two.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		three = new Button("3");
		three.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		four = new Button("4");
		four.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		five = new Button("5");
		five.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		six = new Button("6");
		six.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		seven = new Button("7");
		seven.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		eight = new Button("8");
		eight.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		nine = new Button("9");
		nine.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		multiply = new Button(" *");
		multiply.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		divide = new Button(" /");
		divide.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		add = new Button("+");
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		subtract = new Button("- ");
		subtract.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		decimal = new Button(". ");
		decimal.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		equals = new Button("=");
		equals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		dummy = new Button("?");
		dummy.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		reset = new Button("C");
		reset.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		clearScreen = new Button("CE");
		clearScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        adjustFont = new Button("Font Size");
        adjustFont.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		grid.add(display, 0, 0, 5, 1);        
		grid.add(seven, 0, 1);
		grid.add(eight, 1, 1);
		grid.add(nine, 2, 1);
		grid.add(divide, 3, 1);       
		grid.add(four, 0, 2);
		grid.add(five, 1, 2);
		grid.add(six, 2, 2);
		grid.add(multiply, 3, 2);
		grid.add(one, 0, 3);
		grid.add(two, 1, 3);
		grid.add(three, 2, 3);
		grid.add(subtract, 3, 3);
		grid.add(zero, 0, 4);
		grid.add(decimal, 1, 4);
		grid.add(dummy, 2, 4);
		grid.add(add, 3, 4);
		grid.add(equals, 4, 3, 1, 2);
		grid.add(reset, 4, 1);
		grid.add(clearScreen, 4, 2);
        grid.add(choiceBox, 5, 1, 1, 1);
        grid.add(adjustFont, 5, 2);

		attachCode();

		for (int colIndex = 0; colIndex < 6; colIndex++){
			ColumnConstraints column = new ColumnConstraints();
    		column.setHgrow(Priority.ALWAYS);
    		column.setFillWidth(true);
    		grid.getColumnConstraints().add(column);
    	}

    	for (int rowIndex = 0; rowIndex < 5; rowIndex++){
			RowConstraints row = new RowConstraints();
    		row.setVgrow(Priority.ALWAYS);
    		row.setFillHeight(true);
    		grid.getRowConstraints().add(row);
    	}


    	grid.setPrefSize(WINDOW_WIDTH-10, WINDOW_HEIGHT-10);
    	grid.setMaxSize(Region.USE_COMPUTED_SIZE-10, Region.USE_COMPUTED_SIZE-10);

		Scene calcScene = new Scene (grid, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setTitle("Calc V0.1");
		primaryStage.setScene(calcScene);
		primaryStage.show();
		display.setText(displayContents);
	}

	public void attachCode() {
		zero.setOnAction(e -> btncode(e));
        one.setOnAction(e -> btncode(e));
        two.setOnAction(e -> btncode(e));
        three.setOnAction(e -> btncode(e));
        four.setOnAction(e -> btncode(e));
        five.setOnAction(e -> btncode(e));
        six.setOnAction(e -> btncode(e));
        seven.setOnAction(e -> btncode(e));
        eight.setOnAction(e -> btncode(e));
        nine.setOnAction(e -> btncode(e));
        add.setOnAction(e -> btncode(e));
        subtract.setOnAction(e -> btncode(e));
        multiply.setOnAction(e -> btncode(e));
        divide.setOnAction(e -> btncode(e));
        decimal.setOnAction(e -> btncode(e));
        equals.setOnAction(e -> btncode(e));
        dummy.setOnAction(e -> btncode(e));
        clearScreen.setOnAction(e -> btncode(e));
        reset.setOnAction(e -> btncode(e));
        adjustFont.setOnAction(e -> btncode(e));          
    }

    public void btncode(ActionEvent e) {
    	if (e.getSource() == zero) {
    		if (!(displayContents.equals("0"))){
    			displayContents += "0";
    			display.setText(displayContents);
    		}
    	}
    	if (e.getSource() == one) {
    		addToDisplay("1");
    	} else if (e.getSource() == two) {
    		addToDisplay("2");
    	} else if (e.getSource() == three) {
    		addToDisplay("3");
    	} else if (e.getSource() == four) {
    		addToDisplay("4");
    	} else if (e.getSource() == five) {
    		addToDisplay("5");
    	} else if (e.getSource() == six) {
    		addToDisplay("6");
    	} else if (e.getSource() == seven) {
    		addToDisplay("7");
    	} else if (e.getSource() == eight) {
    		addToDisplay("8");
    	} else if (e.getSource() == nine) {
    		addToDisplay("9");
    	} else if (e.getSource() == decimal) {
            if (displayContents.indexOf(".") == -1) {
    		    displayContents += ".";
                display.setText(displayContents);
            }
    	} else if (e.getSource() == clearScreen) {
    		clearDisplay();
    	} else if (e.getSource() == reset) {
            clearDisplay();
            calc.erase("all");
        } else if (e.getSource() == add) {
    		operateNums("sum");
    	} else if (e.getSource() == subtract) {
    		operateNums("sub");
    	} else if (e.getSource() == multiply) {
    		operateNums("multi");
    	} else if (e.getSource() == divide) {
    		operateNums("div");
    	} else if (e.getSource() == equals) {
    		calculate();
    	} else if(e.getSource() == adjustFont) {
            getChoice();
        }

    }

    public void getChoice(){
        fontSize = choiceBox.getValue();
        Font font1 = new Font(fontSize);
        display.setFont(font1);
    }

    public void operateNums (String operation) {
        num = Double.parseDouble(displayContents);
        clearDisplay();
        calc.setFirstNum(num);
        calc.setOperator(operation);
    }

    public void addToDisplay (String num) {
    	if ((displayContents.equals("0"))){
    			displayContents = num;
    	} else {
    			displayContents += num;
    	}
    	display.setText(displayContents);
    }

    public void clearDisplay () {
    	displayContents = "0";
    	display.setText(displayContents);
    }

    public void addNums() {
    	num = Double.parseDouble(displayContents);
    	clearDisplay();
    	calc.setFirstNum(num);
    	calc.setOperator("sum");
    }

    public void calculate () {
        if (calc.checkifNull()) {
            calc.setFirstNum(Double.parseDouble(displayContents));
        } else {
    	   num = Double.parseDouble(displayContents);
    	   calc.setSecondNum(num);
        }
    	printResult(calc.calculate());
    	calc.erase("");
    }

    public void printResult (String result) {
    	if (result.indexOf(".0") == (result.length()-2)) {
    		result = result.substring(0, result.length()-2);
    	}
    	displayContents = result;
    	display.setText(displayContents);
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}