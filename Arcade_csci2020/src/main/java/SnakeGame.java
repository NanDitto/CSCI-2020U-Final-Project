package mainApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.IOException;

public class SnakeGame extends Application {
	// Images
	String cwd = System.getProperty("user.dir");
	private final String background_Image = "file:" + cwd + "/src/main/resources/GrassBackground.png";
	// Board Size
	private final int HEIGHT = 800;
	private final int WIDTH = 1200;
	private Pane pane = new Pane();
	private int SCORE = 0;
	boolean start = true;
	boolean dead = true;
	// Size of each square in the board
	private final int LENGTH = 20;
	// Food for head
	Rectangle food = new Rectangle(LENGTH, LENGTH);
	// Messages
	Text scoreTxt = new Text("0"); // SHOW SCORE WHEN GAME IS OVER
	Text prompts = new Text(35, 250, "PRESS SPACE TO START!");
	// head Properties
	boolean RIGHT = true;
	boolean LEFT = false;
	boolean UP = false;
	boolean DOWN = false;
	public String temp;
	public String file;

	public void settemp(String temp, String file) {
		this.temp = temp;
		this.file = file;
	}

	boolean WIN;
	private ArrayList<double[]> prevTailPos = new ArrayList<double[]>();

	private Rectangle head = new Rectangle(WIDTH / 2, HEIGHT / 2, LENGTH, LENGTH);

	private ArrayList<Rectangle> tails = new ArrayList<Rectangle>();

	private int dots = 0;
	private final int SPEED = 20;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws InterruptedException {
		WIN = false;
		primaryStage.setResizable(false);
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.BLACK);
		ImageView background = new ImageView(new Image(background_Image));
		background.setFitHeight(HEIGHT + 10);
		background.setFitWidth(WIDTH + 10);

		primaryStage.setTitle("Snake Game"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		prompts.setFill(Color.WHITE);
		prompts.setFont(Font.font("Times New Roman", FontWeight.BOLD, 80));
		pane.getChildren().addAll(background, prompts);
		head.setFill(Color.CADETBLUE);
		tails.add(head);

		Timeline Update = new Timeline(new KeyFrame(Duration.millis(80), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				checkDirection();
				changeDirection();
				checkCollect();
				if (checkDead()) {
					if (dead) {
						prompts.setText("Game Over\nScore: " + SCORE + "\nPress Esc To Return");
						prompts.setX((WIDTH / 2) - 200);
						prompts.setY(HEIGHT / 2);
						pane.getChildren().add(prompts);
					}

				}

			}
		}));

		Timeline Downdate = new Timeline(new KeyFrame(Duration.millis(80), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (checkDead()) {
					if (dead) {
						pane.getChildren().removeAll(tails);
						pane.getChildren().removeAll(head);
						pane.getChildren().removeAll(food);
						dead = false;
					}
				}

			}
		}));

		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case DOWN:
				if (UP)
					break;
				DOWN = true;
				UP = false;
				LEFT = false;
				RIGHT = false;
				break;
			case UP:
				if (DOWN)
					break;
				UP = true;
				DOWN = false;
				LEFT = false;
				RIGHT = false;
				break;
			case LEFT:
				if (RIGHT)
					break;
				LEFT = true;
				DOWN = false;
				UP = false;
				RIGHT = false;
				break;
			case RIGHT:
				if (LEFT)
					break;
				RIGHT = true;
				DOWN = false;
				UP = false;
				LEFT = false;
				break;
			case ESCAPE:
				Scene3 hi = new Scene3();
				try {
					hi.settemp(temp, file);
					hi.start(primaryStage);
					primaryStage.setWidth(900);
					primaryStage.setHeight(910);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			case SPACE:
				if (start) {
					GenerateFruit();
					pane.getChildren().add(food);
					pane.getChildren().add(head);
					pane.getChildren().remove(prompts);
					Update.setCycleCount(Timeline.INDEFINITE);
					Update.play();
					Downdate.setCycleCount(Timeline.INDEFINITE);
					Downdate.play();
					start = false;
				}
				break;
			default:
				break;
			}
		});
	}

	public void readAdd() {
		CSV savea = new CSV();
		try {
			savea.read(temp, file, "4");
			savea.save(file);
		} catch (IOException e) {
		}

	}

	private boolean checkDead() {
		// Dies if touches tail or wall
		// Bottom wall and top wall
		if (head.getY() + LENGTH > HEIGHT || head.getY() < 0) {
			return true;
		}
		// Left Wall and Right Wall
		if (head.getX() < 0 || head.getX() + LENGTH > WIDTH) {
			return true;
		}

		for (int i = 1; i < dots; i++) {
			if (head.getX() == tails.get(i).getX() && head.getY() == tails.get(i).getY()) {
				return true;
			}
		}
		return false;
	}

	protected void changeDirection() {
		// Previous Position of Previous tail // head
		for (int i = 0; i < dots; i++) {
			tails.get(i + 1).setX(prevTailPos.get(i)[0]);
			tails.get(i + 1).setY(prevTailPos.get(i)[1]);
		}
		// Changes direction of Solo head
		if (UP) {
			head.setY(head.getY() - SPEED);
		} else if (DOWN) {
			head.setY(head.getY() + SPEED);
		} else if (LEFT) {
			head.setX(head.getX() - SPEED);

		} else if (RIGHT) {
			head.setX(head.getX() + SPEED);
		}
	}

	private void checkDirection() {

		for (int i = 0; i < dots; i++) {
			prevTailPos.get(i)[0] = tails.get(i).getX();
			prevTailPos.get(i)[1] = tails.get(i).getY();
		}

	}

	private void GenerateFruit() {
		// Move the food somewhere random in the pane
		Random num = new Random();
		int x = num.nextInt((WIDTH / LENGTH) - 1) + 1; // Random Integer 1-59
		int y = num.nextInt((HEIGHT / LENGTH) - 1) + 1; // Random Integer 1-39
		food.setX(x * LENGTH);
		food.setY(y * LENGTH);
		food.setFill(Color.BROWN);
	}

	private void extendTail() {
		prevTailPos.add(new double[2]);
		Rectangle tail = new Rectangle(LENGTH, LENGTH);
		tail.setFill(Color.WHITE);
		tails.add(tail);
		tail.setX(head.getX());
		tail.setY(head.getY());
		pane.getChildren().add(tail);
	}

	private void checkCollect() {
		// Check if the head, touches the food
		if (head.getX() == food.getX() && head.getY() == food.getY()) {
			SCORE += 100;
			if (SCORE >= 3000) {
				WIN = true;
			}
			dots++;
			extendTail();
			GenerateFruit();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
