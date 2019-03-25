package mainApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Instructions extends Application {

	//sets width and height of scene
	private int Width = 900;
	private int Height = 590;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox box = new VBox(10);

		Pane pane = new Pane();

		//add scene title
		Title title = new Title("Instructions");
		title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
		title.setTranslateY(Height / 11);

		pane.getChildren().add(title);

		//add scene subtext
		Text[] text = { new Text("TicTacToe:"), new Text("Blackjack:"), new Text("Snake:"), new Text("Jeopardy:"),
				new Text("President:"), new Text("Rain"), new Text("Pong:"), new Text("Hangman:") };

		//set text style
		for (int i = 0; i < 8; i++) {
			text[i].setFill(Color.WHITE);
			Font myFont = Font.loadFont(getClass().getResourceAsStream("/HyperspaceBold.otf"), 15);
			text[i].setFont(myFont);
			box.getChildren().addAll(text[i]);
		}
		
		//button that takes you back to the menu
		Button button = new Button("Back");
		button.setId("button");
		button.setOnAction(actionEvent -> primaryStage.close());
		box.getChildren().addAll(button);
		
		box.setTranslateX(Width / 4 + 20);
		box.setTranslateY(Height / 4);

		pane.getChildren().addAll(box);
		pane.setStyle("-fx-background-color: black");

		//add scene to stage
		primaryStage.setTitle("Instructions");
		Scene scene = new Scene(pane, Width, Height);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("main.css");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}