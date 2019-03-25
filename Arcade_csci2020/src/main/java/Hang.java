package mainApp;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Hang extends Application {

	private String cwd = System.getProperty("user.dir");
	private ImageView back = new ImageView("test.gif"); // main background image

	private ArrayList<String> storeWordList = new ArrayList<String>();
	private String currentWord = new String();
	private TextField Guess = new TextField();
	private TextField LettersGuessed = new TextField();
	private PauseTransition delay = new PauseTransition(Duration.seconds(1));
	private Text prompt1 = new Text(235, 175, "");
	private Text prompt2 = new Text(100, 175, "");
	private Text prompt3 = new Text(300, 175, "");
	private Text prompt4 = new Text(140, 175, "");
	private Label guessesRemaining;
	private int left;
	private ArrayList<Shape> body;
	private ObservableList<Node> children;
	private Random rand = new Random();
	private String guessedLetters = "";
	private Scanner input;
	private Text[] text;
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
		back.setFitHeight(400);
		back.setFitWidth(700);
		// adjusting and setting the scene to display everything correctly
		// also adjusting text sizes and color and positions
		Pane pane = new Pane();
		Guess.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		LettersGuessed.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		Guess.setLayoutX(485);
		Guess.setLayoutY(15);
		LettersGuessed.setLayoutX(485);
		LettersGuessed.setLayoutY(60);
		children = pane.getChildren();
		pane.getChildren().add(back);
		back.toBack();
		// draw the hang
		hang();
		// initialize the body
		body();
		getWord();
		Guess.setOnAction(e -> play());
		left = 6;
		Label e = new Label("Guesses Remaining:");
		e.setStyle("-fx-font-weight: bold");
		e.setTextFill(Color.web("#FF0000"));
		e.setLayoutX(320);
		e.setLayoutY(103);
		e.setFont(new Font(15));
		pane.getChildren().addAll(Guess, e);
		Label l = new Label("Letters Guessed:");
		l.setStyle("-fx-font-weight: bold");
		l.setTextFill(Color.web("#FF0000"));
		l.setLayoutX(320);
		l.setLayoutY(62);
		l.setFont(new Font(15));
		pane.getChildren().addAll(LettersGuessed, l);
		LettersGuessed.setEditable(false);
		Label g = new Label("Enter a letter: ");
		g.setStyle("-fx-font-weight: bold");
		g.setTextFill(Color.web("#FF0000"));
		g.setLayoutX(330);
		g.setLayoutY(16);
		g.setFont(new Font(15));
		guessesRemaining = new Label(String.valueOf(left));
		guessesRemaining.setStyle("-fx-font-weight: bold");
		guessesRemaining.setTextFill(Color.web("#FF0000"));
		guessesRemaining.setLayoutX(500);
		guessesRemaining.setLayoutY(100);
		guessesRemaining.setFont(new Font(25));
		pane.getChildren().addAll(guessesRemaining, g);
		BorderPane bPane = new BorderPane();
		bPane.setRight(pane);
		prompt1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		prompt1.setFill(Color.RED);
		prompt2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		prompt2.setFill(Color.RED);
		prompt3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		prompt3.setFill(Color.RED);
		prompt4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
		prompt4.setFill(Color.RED);
		pane.getChildren().addAll(prompt1, prompt2, prompt3, prompt4);
		Scene scene = new Scene(bPane, 700, 400, Color.BLACK);
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		scene.setOnKeyPressed(h -> {
			switch (h.getCode()) {
			case ESCAPE:
				if (WIN == true) {
					readAdd();
				}
				Scene3 hi = new Scene3();
				try {
					hi.settemp(temp, file);
					hi.start(primaryStage);
					hi.curentLevel();
					primaryStage.setWidth(900);
					primaryStage.setHeight(910);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			default:
				break;
			}
		});
	}

	public void readAdd() {
		CSV savea = new CSV();
		try {
			savea.read(temp, file, "5");
			savea.save(file);
		} catch (IOException e) {
		}

	}

	// function that draws the structure that the man hangs from
	private void hang() {

		Line top = new Line(25, 25, 200, 25);
		top.setStroke(Color.BLACK);
		top.setStrokeWidth(3);
		children.add(top);

		Line side = new Line(25, 25, 25, 300);
		side.setStroke(Color.BLACK);
		side.setStrokeWidth(3);
		children.add(side);

		Line base = new Line(200, 300, 25, 300);
		base.setStroke(Color.BLACK);
		base.setStrokeWidth(3);
		children.add(base);

		Line rope = new Line(200, 25, 200, 75);
		rope.setStroke(Color.BLACK);
		rope.setStrokeWidth(3);
		children.add(rope);
	}

<<<<<<< HEAD
	//function that draws the man's body parts
=======
	// function that draws the man's body parts
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
	private void body() {

		body = new ArrayList<Shape>();

		Ellipse head = new Ellipse(200, 112, 35, 35);
		head.setStroke(Color.BLACK);
		head.setFill(Color.WHITE);
		head.setStrokeWidth(5);
		head.setVisible(false);
		children.add(head);
		body.add(head);

		Line stomach = new Line(200, 200, 200, 150);
		stomach.setStroke(Color.BLACK);
		stomach.setStrokeWidth(5);
		stomach.setVisible(false);
		children.add(stomach);
		body.add(stomach);

		Line leftArm = new Line(150, 225, 200, 175);
		leftArm.setStroke(Color.BLACK);
		leftArm.setStrokeWidth(5);
		leftArm.setVisible(false);
		children.add(leftArm);
		body.add(leftArm);

		Line rightArm = new Line(250, 225, 200, 175);
		rightArm.setStroke(Color.BLACK);
		rightArm.setStrokeWidth(5);
		rightArm.setVisible(false);
		children.add(rightArm);
		body.add(rightArm);

		Line leftLeg = new Line(200, 200, 175, 275);
		leftLeg.setStroke(Color.BLACK);
		leftLeg.setStrokeWidth(5);
		leftLeg.setVisible(false);
		children.add(leftLeg);
		body.add(leftLeg);

		Line rightLeg = new Line(200, 200, 225, 275);
		rightLeg.setStroke(Color.BLACK);
		rightLeg.setStrokeWidth(5);
		rightLeg.setVisible(false);
		children.add(rightLeg);
		body.add(rightLeg);
	}

<<<<<<< HEAD
	//function that gets random words from a text file and sets that word as the current word to be guessed
=======
	// function that gets random words from a text file and sets that word as
	// the current word to be guessed
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
	private void getWord() {

		File file = new File(cwd + "/src/main/resources/translate.txt");
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
<<<<<<< HEAD
		}

        while (input.hasNext()) {
          currentWord  = input.next();
          storeWordList.add(currentWord);
        }
        input.close();

        int index = rand.nextInt(storeWordList.size());
        currentWord = storeWordList.get(index);
    	underlines();

    }

	//function that creates the right amount of underlines according to the word
	private void underlines(){
			Line[] blanks = new Line[currentWord.length()];
			int xStart = 375;
			int lineLength = 25;
			int lineSpacing = 35;
			for (int i = 0; i < currentWord.length(); i++){
				int xcoord = xStart + (lineSpacing * i);
				blanks[i] = new Line(xcoord, 225, xcoord - lineLength, 225);
				blanks[i].setStroke(Color.BLACK);
				blanks[i].setStrokeWidth(3);
				children.add(blanks[i]);
=======
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
		}

		while (input.hasNext()) {
			currentWord = input.next();
			storeWordList.add(currentWord);
		}
		input.close();

		int index = rand.nextInt(storeWordList.size());
		currentWord = storeWordList.get(index);
		underlines();

	}

<<<<<<< HEAD
	//function that associates the underlines to a letter of the current word
=======
	// function that creates the right amount of underlines according to the
	// word
	private void underlines() {
		Line[] blanks = new Line[currentWord.length()];
		int xStart = 375;
		int lineLength = 25;
		int lineSpacing = 35;
		for (int i = 0; i < currentWord.length(); i++) {
			int xcoord = xStart + (lineSpacing * i);
			blanks[i] = new Line(xcoord, 225, xcoord - lineLength, 225);
			blanks[i].setStroke(Color.BLACK);
			blanks[i].setStrokeWidth(3);
			children.add(blanks[i]);
		}
		text = initText();
	}

	// function that associates the underlines to a letter of the current word
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
	private Text[] initText() {
		Text[] text = new Text[currentWord.length()];
		int xStartw = 355;
		int lineSpacingw = 35;
		for (int i = 0; i < text.length; i++) {
			int xcoordw = xStartw + (lineSpacingw * i);
			text[i] = new Text(currentWord.substring(i, i + 1));
			text[i].setFont(new Font(40));
			text[i].setX(xcoordw);
			text[i].setY(220);
			text[i].setVisible(false);
			text[i].setFill(Color.RED);
			children.add(text[i]);
		}
		return text;
	}

<<<<<<< HEAD

	//function that plays the game
	private void play(){
=======
	// function that plays the game
	private void play() {
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
		// Get the guessed letter
		String guess = Guess.getText();
		if (guess.length() == 0) {
			prompt3.setText("Please enter a letter.");
			delay.setOnFinished(event -> prompt3.setVisible(false));
			delay.play();
			return;

		}

		if (guess.length() > 1) { // if more than one letter, take only the
									// first
			guess = guess.substring(0, 1);
		}
		guess = guess.toLowerCase();
		Guess.setText("");
		// Check if the letter has already been guessed

		if (guessedLetters.length() > 0) {
			if (guessedLetters.indexOf(guess) > -1) {
				prompt4.setText("Letter has already been guessed. \n                 Try AGAIN.");
				delay.setOnFinished(event -> prompt4.setVisible(false));
				delay.play();
				return;
			} else {
				guessedLetters += guess;
			}
		} else {
			guessedLetters += guess;
		}
		LettersGuessed.setText(guessedLetters);
		// Check if the letter is in the word
		boolean good = false;
		for (int i = 0; i < currentWord.length(); i++) {
			if (guess.equalsIgnoreCase(currentWord.substring(i, i + 1))) {
				text[i].setVisible(true);
				good = true;
			}
		}
		// Check if letter was not in word
		if (!good) {
			body.get(6 - left).setVisible(true);
			left--;
			guessesRemaining.setText(String.valueOf(left));
		}
		if (left == 0) {
			prompt1.setText("    You LOST! The word was: " + currentWord);
			Guess.setEditable(false);

		}
		// Check if word is solved
		boolean solved = true;
		for (int i = 0; i < text.length; i++) {
			if (!text[i].isVisible()) {
				solved = false;
				break;
			}
		}
		if (solved) {
			prompt2.setText("             Congratulations, you WON");
			WIN = true;
			Guess.setEditable(false);
		}
	}
<<<<<<< HEAD
   public static void main(String[] args) {
       launch();
   }

}
=======

	public static void main(String[] args) {
		launch();
	}

}
>>>>>>> 92bbedf29cee1d5fb3f4176a18fdc745f7f88896
