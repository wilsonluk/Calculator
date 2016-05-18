import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Calc2 extends Application {

	private static final double WINDOW_WIDTH = 500 ; //sets the deafult window width
    private static final double WINDOW_HEIGHT = 300 ; //sets the default window height
    public static String displayContents = ("0");
    public static double num = 0.0;
    public static double op1 = 0;
    public static double op2 = 0;

	public static TextField display;
	public static Button zero, one, two, three, four, five, six, seven, eight, nine;
	public static Button multiply, divide, add, subtract;
    public static Button leftParen, rightParen;
    public static Button sin, cos, tan;
	public static Button decimal, equals, negate, reset, clearScreen;
    public static RadioMenuItem setDisplayFontTo20, setDisplayFontTo30, setDisplayFontTo40;
    public static RadioMenuItem setButtonFontTo10, setButtonFontTo15, setButtonFontTo20;

	public void start(Stage primaryStage) { //initializes the GUI
		
		display = new TextField();	
		display.setAlignment(Pos.CENTER_RIGHT);
		display.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        BorderPane border = new BorderPane();

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        border.setTop(menuBar);

        Menu fileMenu = new Menu("File");
        MenuItem about = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        fileMenu.getItems().addAll(about, new SeparatorMenuItem(), exit);

        Menu optionsMenu = new Menu("Options");

        Menu displayFontSize = new Menu("Display Font Size");
        ToggleGroup displayToggle = new ToggleGroup();
        setDisplayFontTo20 = new RadioMenuItem("20");
        setDisplayFontTo20.setToggleGroup(displayToggle);
        setDisplayFontTo20.setSelected(true);
        setDisplayFontTo30 = new RadioMenuItem("30");
        setDisplayFontTo30.setToggleGroup(displayToggle);
        setDisplayFontTo40 = new RadioMenuItem("40");
        setDisplayFontTo40.setToggleGroup(displayToggle);
        displayFontSize.getItems().addAll(setDisplayFontTo20, setDisplayFontTo30, setDisplayFontTo40);
        optionsMenu.getItems().add(displayFontSize);

        Menu buttonFontSize = new Menu("Button Font Size");
        ToggleGroup buttonToggle = new ToggleGroup();
        setButtonFontTo10 = new RadioMenuItem("10");
        setButtonFontTo10.setToggleGroup(buttonToggle);
        setButtonFontTo10.setSelected(true);
        setButtonFontTo15 = new RadioMenuItem("15");
        setButtonFontTo15.setToggleGroup(buttonToggle);
        setButtonFontTo20 = new RadioMenuItem("20");
        setButtonFontTo20.setToggleGroup(buttonToggle);
        buttonFontSize.getItems().addAll(setButtonFontTo10, setButtonFontTo15, setButtonFontTo20);
        optionsMenu.getItems().add(buttonFontSize);

        menuBar.getMenus().addAll(fileMenu, optionsMenu);

        exit.setOnAction(actionEvent -> Platform.exit());     

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

        leftParen = new Button("(");
        leftParen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        rightParen = new Button(")");
        rightParen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

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

		negate = new Button("(-)");
		negate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        sin = new Button ("sin");
        sin.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        cos = new Button ("cos");
        cos.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        tan = new Button ("tan");
        tan.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		reset = new Button("C");
		reset.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		clearScreen = new Button("CE");
		clearScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        displayFontCode(20);
        buttonFontCode(12);

        GridPane grid = new GridPane();

		grid.add(menuBar, 0, 0, 5, 1);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 10, 10));

        grid.add(display, 0, 1, 5, 1);        
		grid.add(leftParen, 0, 2);
        grid.add(rightParen, 1, 2);
        grid.add(sin, 2, 2);
        grid.add(cos, 3, 2);
        grid.add(tan, 4, 2);
        grid.add(seven, 0, 3);
		grid.add(eight, 1, 3);
		grid.add(nine, 2, 3);
		grid.add(divide, 3, 3);       
		grid.add(four, 0, 4);
		grid.add(five, 1, 4);
		grid.add(six, 2, 4);
		grid.add(multiply, 3, 4);
		grid.add(one, 0, 5);
		grid.add(two, 1, 5);
		grid.add(three, 2, 5);
		grid.add(subtract, 3, 5);
		grid.add(zero, 0, 6);
		grid.add(decimal, 1, 6);
		grid.add(negate, 2, 6);
		grid.add(add, 3, 6);
		grid.add(equals, 4, 5, 1, 2);
		grid.add(reset, 4, 3);
		grid.add(clearScreen, 4, 4);

		attachCode();

		for (int colIndex = 0; colIndex < 6; colIndex++){
			ColumnConstraints column = new ColumnConstraints();
    		column.setHgrow(Priority.ALWAYS);
    		column.setFillWidth(true);
    		grid.getColumnConstraints().add(column);
    	}

    	RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(10);
        RowConstraints row1 = new RowConstraints();
    	row1.setVgrow(Priority.ALWAYS);
    	row1.setFillHeight(true);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        row2.setFillHeight(true);
        RowConstraints row3 = new RowConstraints();
        row3.setVgrow(Priority.ALWAYS);
        row3.setFillHeight(true);
        RowConstraints row4 = new RowConstraints();
        row4.setVgrow(Priority.ALWAYS);
        row4.setFillHeight(true);
        RowConstraints row5 = new RowConstraints();
        row5.setVgrow(Priority.ALWAYS);
        row5.setFillHeight(true);
        RowConstraints row6 = new RowConstraints();
        row6.setVgrow(Priority.ALWAYS);
        row6.setFillHeight(true);
    	grid.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6);

    	grid.setPrefSize(WINDOW_WIDTH-10, WINDOW_HEIGHT-10);
    	grid.setMaxSize(Region.USE_COMPUTED_SIZE-10, Region.USE_COMPUTED_SIZE-10);

		Scene calcScene = new Scene (grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        final ListView<String> console = new ListView<String>(FXCollections.<String>observableArrayList());
        display.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                String s = ke.getCode() + "";
                if(s.equals("ENTER")){
                    displayContents = display.getText();
                    displayContents = displayContents.replaceAll("\\s+","");
                    calculate();
                }                
            }
        });
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
        leftParen.setOnAction(e -> btncode(e));
        rightParen.setOnAction(e -> btncode(e));
        sin.setOnAction(e -> btncode(e));
        cos.setOnAction(e -> btncode(e));
        tan.setOnAction(e -> btncode(e));
        add.setOnAction(e -> btncode(e));
        subtract.setOnAction(e -> btncode(e));
        multiply.setOnAction(e -> btncode(e));
        divide.setOnAction(e -> btncode(e));
        decimal.setOnAction(e -> btncode(e));
        equals.setOnAction(e -> btncode(e));
        negate.setOnAction(e -> btncode(e));
        clearScreen.setOnAction(e -> btncode(e));
        reset.setOnAction(e -> btncode(e));
        setDisplayFontTo20.setOnAction(actionEvent -> displayFontCode(20));
        setDisplayFontTo30.setOnAction(actionEvent -> displayFontCode(30));
        setDisplayFontTo40.setOnAction(actionEvent -> displayFontCode(40));
        setButtonFontTo10.setOnAction(actionEvent -> buttonFontCode(10));
        setButtonFontTo15.setOnAction(actionEvent -> buttonFontCode(15));
        setButtonFontTo20.setOnAction(actionEvent -> buttonFontCode(20));
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
    	} else if (e.getSource() == leftParen) {
            addToDisplay("(");
        } else if (e.getSource() == rightParen) {
            addToDisplay(")");
        } else if (e.getSource() == sin) {
            addToDisplay("sin(");
        } else if (e.getSource() == cos) {
            addToDisplay("cos(");
        } else if (e.getSource() == tan) {
            addToDisplay("tan(");
        } else if (e.getSource() == negate) {
            addToDisplay("~");
        } else if (e.getSource() == decimal) {
            if (displayContents.indexOf(".") == -1) {
    		    displayContents += ".";
                display.setText(displayContents);
            }
    	} else if (e.getSource() == clearScreen) {
    		clearDisplay();
    	} else if (e.getSource() == reset) {
            clearDisplay();
        } else if (e.getSource() == add) {
    		addToDisplay("+");
    	} else if (e.getSource() == subtract) {
    		addToDisplay("-");
    	} else if (e.getSource() == multiply) {
    		addToDisplay("*");
    	} else if (e.getSource() == divide) {
    		addToDisplay("/");
    	} else if (e.getSource() == equals) {
            displayContents = display.getText();
            displayContents = displayContents.replaceAll("\\s+","");
    		calculate();
    	}
    }

    public void displayFontCode(int num) {
        Font font1 = new Font(num);
        display.setFont(font1);            
    }

    public void buttonFontCode (int num) {
        Font font2 = new Font(num);
        zero.setFont(font2);
        one.setFont(font2);
        two.setFont(font2);
        three.setFont(font2);
        four.setFont(font2);
        five.setFont(font2);
        six.setFont(font2);
        seven.setFont(font2);
        eight.setFont(font2);
        nine.setFont(font2);
        multiply.setFont(font2);
        divide.setFont(font2);
        add.setFont(font2);
        subtract.setFont(font2);
        decimal.setFont(font2);
        equals.setFont(font2);
        clearScreen.setFont(font2);
        reset.setFont(font2);
        sin.setFont(font2);
        cos.setFont(font2);
        tan.setFont(font2);
    }

    public static String interpret (String expression) {
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
                    displayContents = "ERR: Divide By 0";
                    display.setText("ERR: Divide By 0");
                    break;
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

    public static void addToDisplay (String input) {
        if(displayContents.indexOf("ERR") == -1) {
        	if ((displayContents.equals("0") && !input.equals("-") && !input.equals("+") && !input.equals("*") && !input.equals("/"))){
        			displayContents = input;
        	} else {
        			displayContents += input;
        	}
        	display.setText(displayContents);
        }
    }

    public static void clearDisplay () {
    	displayContents = "0";
    	display.setText(displayContents);
    }

    public static void calculate () {
        if(matched(displayContents) && displayContents.indexOf("ERR") == -1) {
            printResult(interpret(displayContents));
        }
        
    }

    public static boolean matched (String toTheTest) {
        int level = 0;
        for (int i = 0; i < toTheTest.length(); i++) {
            if (toTheTest.charAt(i) == '(') {
                level++;
            } else if (toTheTest.charAt(i) == ')') {
                level--;
            }
            if (level < 0) {
                return false;
            }
        }
        if (level == 0) {
            return true;
        }
        return false;
    }

    public static void printResult (String result) {
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