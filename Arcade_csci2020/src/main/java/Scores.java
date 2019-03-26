package mainApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/*
 A window to display all historic user scores
*/
public class Scores extends Application {

	private int Width = 900;
	private int Height = 590;
	String cwd = System.getProperty("user.dir");
	String ret = "";
	String currentFile = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene1 input = new Scene1();
		currentFile = input.currentFilename;
		Pane pane = new Pane();
		VBox box = new VBox(10);

		Title title = new Title("Scores");
		title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
		title.setTranslateY(Height / 11);

		pane.getChildren().add(title);

		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> levels = new ArrayList<String>();

		// ret = File.separator;
		File file = new File(currentFile);
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] details = line.split(",");
				for (int i = 0; i < details.length; i++) {
					if (i == 0) {
						user.add(details[0]);
					}
					if (i == (details.length - 1)) {
						levels.add(details[details.length - 1]);
					}
				}
			}
		}

		Text[] text = new Text[user.size()];

		for (int i = 0; i < user.size(); i++) {
			if (i == 0) {
				text[i] = new Text(user.get(i) + "   " + levels.get(i));
			} else {
				text[i] = new Text(user.get(i) + ":        " + levels.get(i));
			}
		}

		for (int i = 0; i < user.size(); i++) {
			text[i].setFill(Color.WHITE);
			Font myFont = Font.loadFont(getClass().getResourceAsStream("/HyperspaceBold.otf"), 20);
			text[i].setFont(myFont);
			box.getChildren().addAll(text[i]);
		}

		Button button = new Button("Back");
		button.setId("button");

		button.setOnAction(actionEvent -> primaryStage.close());
		button.setTranslateX(80);
		box.getChildren().addAll(button);

		box.setTranslateX(Width / 3 + 50);
		box.setTranslateY(Height / 5 - 20);

		pane.getChildren().addAll(box);

		pane.setStyle("-fx-background-color: black");
		primaryStage.setTitle("Scores");
		Scene scene = new Scene(pane, Width, Height);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("main.css");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
