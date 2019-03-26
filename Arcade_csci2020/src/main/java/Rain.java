package mainApp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import java.util.Random;
import java.io.IOException;

public class Rain extends Application {

	private final int WIDTH = 700;
	private final int HEIGHT = 800;
	private final int MAX = 20;
	private final int SPEED = 5;
	// Array of the alphabet to see what the user enters
	private final String[] ALPHABET = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	// Concurrent score of the user
	private int score = 0;
	private Text Score1 = new Text(40, 30, "0");

	BorderPane pane = new BorderPane();
	Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
	Text prompt = new Text(150, HEIGHT / 2, "");
	Random rand = new Random();
	TextField text = new TextField();
	Text[] letters = new Text[MAX];
	public String temp;
	public String file;

	public void settemp(String temp, String file) {
		this.temp = temp;
		this.file = file;
	}

	public boolean WIN;

	@Override
	public void start(Stage primaryStage) {
		WIN = false;
		ImageView back = new ImageView("retro.png"); // main background image
		pane.getChildren().add(back);

		for (int i = 0; i < MAX; i++) {
			letters[i] = new Text();
		}

		primaryStage.setTitle("Alphabetic Rain");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		Score1.setFill(Color.BLACK);
		Score1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		prompt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		prompt.setFill(Color.BLACK);

		for (int i = 0; i < MAX; i++) {
			letters[i].setFont(Font.font("Times New Roman", 25));
		}
		spawnLetters();

		pane.getChildren().addAll(Score1);
		pane.getChildren().addAll(letters);
		pane.getChildren().addAll(prompt);
		// Timeline to update the letters traveling on the screen
		Timeline Tick = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent h) {
				for (int i = 0; i < MAX; i++) {
					letters[i].setY(letters[i].getY() + SPEED);
				}

				checkLoss();

				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

					public void handle(KeyEvent ke) {
						for (int i = 0; i < MAX; i++) {
							if (letters[i].getText().equals(ke.getText().toLowerCase())) {
								respawnLetter(i);
								score += 1;
								break;
							}
						}
						if (ke.getCode() == KeyCode.ESCAPE) {
							Scene3 hi = new Scene3();
							try {
								hi.settemp(temp, file);
								hi.start(primaryStage);
								primaryStage.setWidth(900);
								primaryStage.setHeight(910);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}

				});

			}
		}));
		// Timeline that checks if the user losses, and stops the letters from
		// moving
		Timeline Tok = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent h) {
				for (int y = 0; y < 20; y++) {
					if (letters[y].getY() > HEIGHT) {
						pane.getChildren().removeAll(letters);
						Tick.stop();
					}
				}
			}
		}));

		Tick.setCycleCount(Timeline.INDEFINITE);
		Tok.setCycleCount(Timeline.INDEFINITE);
		Tick.play();
		Tok.play();

	}

	// Read a file and add the next level if they get past a ceratin score
	public void readAdd() {
		CSV savea = new CSV();
		try {
			savea.read(temp, file, "7");
			savea.save(file);
		} catch (IOException e) {
		}

	}

	// Check if the letter has reached the bottom, if it reaches the bottom the
	// game stops
	public void checkLoss() {
		for (int y = 0; y < 20; y++) {
			if (letters[y].getY() > HEIGHT - 20) {
				prompt.setText(" Your score: " + score + " (50 needed for level unlock)");
				if (score >= 50) {
					WIN = true;
				}
				pane.getChildren().remove(letters);

			} else {
				Score1.setText(Integer.toString(score));
			}
		}

	}

	// Function that generates random letter and x/y cordinates of the letter
	public void respawnLetter(int index) {
		int randLetter = rand.nextInt(25);
		int x = rand.nextInt(WIDTH);
		int y = rand.nextInt(30);
		y *= -1;
		letters[index].setX(x);
		letters[index].setY(y);
		letters[index].setText(ALPHABET[randLetter]);
	}

	// A function that brings 20 letters to the top of the stage/ window, but it
	// will be outside the top so it falls into the pane
	public void spawnLetters() {
		for (int i = 0; i < MAX; i++) {
			respawnLetter(i);
		}
	}

	public static void main(String[] args) {
		launch();
	}

}
