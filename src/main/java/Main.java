import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField num1Field;
    private TextField num2Field;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Basic Calculator");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        num1Field = new TextField();
        num1Field.setPromptText("Enter first number");
        GridPane.setConstraints(num1Field, 0, 0);

        num2Field = new TextField();
        num2Field.setPromptText("Enter second number");
        GridPane.setConstraints(num2Field, 0, 1);

        resultLabel = new Label("Result: ");
        GridPane.setConstraints(resultLabel, 0, 2);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> performOperation('+'));
        GridPane.setConstraints(addButton, 1, 0);

        Button subtractButton = new Button("Subtract");
        subtractButton.setOnAction(e -> performOperation('-'));
        GridPane.setConstraints(subtractButton, 2, 0);

        Button multiplyButton = new Button("Multiply");
        multiplyButton.setOnAction(e -> performOperation('*'));
        GridPane.setConstraints(multiplyButton, 1, 1);

        Button divideButton = new Button("Divide");
        divideButton.setOnAction(e -> performOperation('/'));
        GridPane.setConstraints(divideButton, 2, 1);

        Button gcdButton = new Button("GCD");
        gcdButton.setOnAction(e -> performOperation('g'));
        GridPane.setConstraints(gcdButton, 1, 2);

        Button lcmButton = new Button("LCM");
        lcmButton.setOnAction(e -> performOperation('l'));
        GridPane.setConstraints(lcmButton, 2, 2);

        grid.getChildren().addAll(num1Field, num2Field, resultLabel, addButton, subtractButton, multiplyButton, divideButton, gcdButton, lcmButton);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

private void performOperation(char operator) {
        String num1Text = num1Field.getText();
        String num2Text = num2Field.getText();

        if (isValidNumber(num1Text) && isValidNumber(num2Text)) {
            int num1 = Integer.parseInt(num1Text);
            int num2 = Integer.parseInt(num2Text);
            double result = 0.0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = (double) num1 / num2;
                    } else {
                        resultLabel.setText("Result: Error (Division by zero)");
                        return;
                    }
                    break;
                case 'g': // GCD
                    result = gcd(num1, num2);
                    break;
                case 'l': // LCM
                    result = lcm(num1, num2);
                    break;
                default:
                    resultLabel.setText("Result: Invalid Operation");
                    return;
            }

            resultLabel.setText("Result: " + String.format("%.2f", result));
        } else {
            resultLabel.setText("Result: Invalid Input");
        }
    }

    
}
