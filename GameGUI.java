import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This program is the view model for a rock paper scissors game
 * March 5, 2023
 *
 * @author Jesse Atkinson
 */
public class GameGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Rock Paper Scissors");
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        Game game = new Game();

        // Create combo box for selecting number of rounds
        ComboBox<Integer> reqPointsComboBox = new ComboBox<>();
        reqPointsComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Create UI elements
        Label playerLabel = new Label("YOU");
        Label computerLabel = new Label("CPU");
        Button rockButton = new Button("ROCK");
        Button paperButton = new Button("PAPER");
        Button scissorsButton = new Button("SCISSORS");
        Button restartButton = new Button("RESTART");
        Label resultLabel = new Label("");
        Label playerScoreLabel = new Label("0");
        Label computerScoreLabel = new Label("0");
        Label reqPointsLabel = new Label("# TO WIN");

        // Position UI elements
        reqPointsLabel.setLayoutX(100);
        reqPointsLabel.setLayoutY(75);
        reqPointsComboBox.setLayoutX(100);
        reqPointsComboBox.setLayoutY(100);

        playerLabel.setLayoutX(275);
        playerLabel.setLayoutY(75);
        playerScoreLabel.setLayoutX(285);
        playerScoreLabel.setLayoutY(105);

        computerLabel.setLayoutX(375);
        computerLabel.setLayoutY(75);
        computerScoreLabel.setLayoutX(385);
        computerScoreLabel.setLayoutY(105);

        restartButton.setLayoutX(500);
        restartButton.setLayoutY(100);

        rockButton.setLayoutX(180);
        rockButton.setLayoutY(225);
        paperButton.setLayoutX(290);
        paperButton.setLayoutY(225);
        scissorsButton.setLayoutX(400);
        scissorsButton.setLayoutY(225);

        resultLabel.setLayoutX(100);
        resultLabel.setLayoutY(150);

        // Styles for GUI components
        reqPointsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");
        reqPointsComboBox.setStyle("-fx-font-size: 16px;");
        reqPointsComboBox.setPrefSize(100, 50);
        playerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");
        playerScoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");
        computerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");
        computerScoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");
        rockButton.setStyle("-fx-text-fill: purple;");
        rockButton.setPrefSize(100, 50);
        paperButton.setStyle("-fx-text-fill: purple;");
        paperButton.setPrefSize(100, 50);
        scissorsButton.setStyle("-fx-text-fill: purple;");
        scissorsButton.setPrefSize(100, 50);
        restartButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        restartButton.setPrefSize(100, 50);
        resultLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: blue");

        // Add event handlers to buttons and combo box
        reqPointsComboBox.setOnAction(e -> {
            int reqPoints = reqPointsComboBox.getValue();
            game.setReqPoints(reqPoints);
        });
        rockButton.setOnAction(e -> {
            game.setPlayerMove("rock");
            game.generateComputerMove();
            resultLabel.setText(game.getResult());
            playerScoreLabel.setText(game.getPlayerScore() + "");
            computerScoreLabel.setText(game.getComputerScore() + "");
        });
        paperButton.setOnAction(e -> {
            game.setPlayerMove("paper");
            game.generateComputerMove();
            resultLabel.setText(game.getResult());
            playerScoreLabel.setText(game.getPlayerScore() + "");
            computerScoreLabel.setText(game.getComputerScore() + "");
        });
        scissorsButton.setOnAction(e -> {
            game.setPlayerMove("scissors");
            game.generateComputerMove();
            resultLabel.setText(game.getResult());
            playerScoreLabel.setText(game.getPlayerScore() + "");
            computerScoreLabel.setText(game.getComputerScore() + "");
        });
        restartButton.setOnAction(e -> {
            game.reset();
            playerScoreLabel.setText("0");
            computerScoreLabel.setText("0");
            resultLabel.setText("");
            reqPointsComboBox.valueProperty().set(0);
            // Re-enable the buttons
            rockButton.setDisable(false);
            paperButton.setDisable(false);
            scissorsButton.setDisable(false);
        });

        // Listeners for checking the scores and disabling the buttons when someone wins
        ChangeListener<String> scoreChangeListener = (observable, oldValue, newValue) -> {
            int playerScore = Integer.parseInt(playerScoreLabel.getText());
            int computerScore = Integer.parseInt(computerScoreLabel.getText());
            int reqPoints = game.getReqPoints();
            if (playerScore == reqPoints || computerScore == reqPoints) {
                rockButton.setDisable(true);
                paperButton.setDisable(true);
                scissorsButton.setDisable(true);
            }
        };
        playerScoreLabel.textProperty().addListener(scoreChangeListener);
        computerScoreLabel.textProperty().addListener(scoreChangeListener);

        // Add UI elements to root pane
        root.getChildren().addAll(restartButton, reqPointsLabel, reqPointsComboBox, playerScoreLabel, computerScoreLabel, playerLabel, computerLabel, rockButton, paperButton, scissorsButton, resultLabel);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
