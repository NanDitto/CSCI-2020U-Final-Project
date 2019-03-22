import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

public class Hang extends Application {

    private ArrayList<String> storeWordList = new ArrayList<String>();
    private String currentWord = new String();
	private TextField Guess = new TextField();
	private TextField LettersGuessed = new TextField();
	private PauseTransition delay = new PauseTransition(Duration.seconds(1));
	private Text prompt1 = new Text(300,175, "");
	private Text prompt2 = new Text(100,175, "");
	private Text prompt3 = new Text(300,175, "");
	private Text prompt4 = new Text(140,175, "");
	private Label guessesRemaining;
	private int left;
	private ArrayList<Shape> body;
	private ObservableList<Node> children;
	private Random rand = new Random();
	private String guessedLetters = "";
	private Scanner input;
	private Text[] text;



	
	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();
		children = pane.getChildren();
		// draw the hang
		hang();
		// initialize the body
		body();
		getWord();
		Guess.setOnAction(e -> play());
		left = 6;
		GridPane gridPane = new GridPane(); // Create UI
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Enter a letter:"), 0, 0);
		gridPane.add(Guess, 1, 0);
		gridPane.add(new Label("Letters Guessed:"), 0, 1);
		gridPane.add(LettersGuessed, 1, 1);
		LettersGuessed.setEditable(false);
		gridPane.add(new Label("Guesses Remaining: "), 0, 2);
		guessesRemaining = new Label(String.valueOf(left));
		gridPane.add(guessesRemaining, 1, 2);
		BorderPane bPane = new BorderPane();
		bPane.setRight(gridPane);
		bPane.setCenter(pane);
		prompt1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		prompt1.setFill(Color.RED);
		prompt2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 20));
		prompt2.setFill(Color.RED);
		prompt3.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		prompt3.setFill(Color.RED);
		prompt4.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		prompt4.setFill(Color.RED);
		pane.getChildren().addAll(prompt1,prompt2,prompt3,prompt4);
		Scene scene = new Scene(bPane, 700, 400, Color.BLACK);
		primaryStage.setTitle("Show Hangman");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
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
	
	private void getWord() {

        File file = new File("d:/Users/Joseph/Desktop/translate.txt");
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} 
     
        while (input.hasNext()) {
          currentWord  = input.next();
          storeWordList.add(currentWord);
        }
        input.close();
        
        int index = rand.nextInt(storeWordList.size());
        currentWord = storeWordList.get(index);
    	System.out.println(currentWord);
    	underlines();

    }
	
	private void underlines(){
			Line[] blanks = new Line[currentWord.length()];
			int xStart = 375;
			int lineLength = 25;
			int lineSpacing = 35;
			for (int i = 0; i < currentWord.length(); i++){
				// Calculate the starting point of the line segment
				int xcoord = xStart + (lineSpacing * i);
				// create the line
				blanks[i] = new Line(xcoord, 225, xcoord - lineLength, 225);
				blanks[i].setStroke(Color.BLACK);
				blanks[i].setStrokeWidth(3);
				children.add(blanks[i]);
		}
			text = initText();
	}
	
	private Text[] initText() {
		Text[] text = new Text[currentWord.length()];
		int xStartw = 355;
		int lineSpacingw = 35;
		for (int i = 0; i < text.length; i++) {
			// Calculate the starting point of the line segment
			int xcoordw = xStartw + (lineSpacingw * i);
			text[i] = new Text(currentWord.substring(i, i + 1));
			text[i].setFont(new Font(30));
			text[i].setX(xcoordw);
			text[i].setY(220);
			text[i].setVisible(false);
			children.add(text[i]);
		}
		return text;
	}
	
	private void play(){
		// Get the guessed letter
		String guess = Guess.getText();
		if(guess.length() == 0){
			prompt3.setText("Please enter a letter.");
			delay.setOnFinished( event -> prompt3.setVisible(false));
			delay.play();
			return;
			
		}

		if (guess.length() > 1) { // if more than one letter, take only the	first
			guess = guess.substring(0, 1);
		}
		guess = guess.toLowerCase();
		Guess.setText("");
		// Check if the letter has already been guessed

		if (guessedLetters.length() > 0) {
			if (guessedLetters.indexOf(guess) > -1) {
				prompt4.setText("Letter has already been guessed. Try again.");
				delay.setOnFinished( event -> prompt4.setVisible(false));
				delay.play();
				return;
			} else {
				guessedLetters+=guess;
			}
		} else {
			guessedLetters+=guess;
		}
		LettersGuessed.setText(guessedLetters);
		System.out.println(guessedLetters);		
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
			prompt1.setText("You have 0 tries left, you lose.");
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
			prompt2.setText("Congratulations, you have successfully guessed the correct letters.");
			Guess.setEditable(false);
		}
	}
	
   public static void main(String[] args) {
       launch();
   } 
   
}		