package mainApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Instructions extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox box = new VBox(10);
        Label label = new Label("How to play:");
        Label label1 = new Label("TicTacToe:");
        Label label2 = new Label("Blackjack:");
        Label label3 = new Label("Snake:");
        Label label4 = new Label("Jeopardy:");
        Label label5 = new Label("President:");
        Label label6 = new Label("Rain");
        Label label7 = new Label("Pong:");
        Label label8 = new Label("Hangman:");

        Button button = new Button("Back");
        button.setOnAction(actionEvent -> primaryStage.close());

        box.getChildren().addAll(label, label1, label2, label3, label4, label5, label6, label7, label8, button);

        primaryStage.setTitle("Instructions");
        primaryStage.setScene(new Scene(box, 300, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
