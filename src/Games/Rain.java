import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;

public class Rain extends Application{

	private final int WIDTH = 700;
	private final int HEIGHT = 800;
	private final int MAX = 20;
	private final int SPEED = 5;
	private final String[] ALPHABET =  {"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	private int score = 0;
	private Text Score1 = new Text(40, 30,"0");
	
	BorderPane pane = new BorderPane();  
    Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
	Text prompt = new Text(150, HEIGHT/2, "");
	Random rand = new Random();
	TextField text = new TextField();
	Text[] letters = new Text[MAX];

	@Override
    public void start(Stage primaryStage) {  

        for(int i =0;i<MAX;i++){
        	letters[i] = new Text();
        }

        
        primaryStage.setTitle("Alphabetic Rain");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
		Score1.setFill(Color.BLACK);
		Score1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		prompt.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		prompt.setFill(Color.BLACK);
		
		for(int i = 0; i < MAX; i++){
			letters[i].setFont(Font.font("Times New Roman", 25));
		}
    	spawnLetters();
    	
    	pane.getChildren().addAll(Score1);
    	pane.getChildren().addAll(letters);
		pane.getChildren().addAll(prompt);
    	

    	
    	Timeline Tick = new Timeline(new KeyFrame(Duration.millis(100), 
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent h) {
				for(int i = 0;i<MAX;i++){
					letters[i].setY(letters[i].getY() + SPEED);
				}
				
				checkLoss();
				
		        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		            public void handle(KeyEvent ke) {
		            	for(int i =0;i<MAX;i++){
		            		if(letters[i].getText().equals(ke.getText().toLowerCase())) {
		            			respawnLetter(i);
		            			score += 1;
		            			break;
		            		}
		            	}
		            }
		        });
				
			}
		}));
    	
    	Timeline Tok = new Timeline(new KeyFrame(Duration.millis(1), 
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent h) {
				for(int y = 0; y<20; y++){
					if(letters[y].getY() > HEIGHT){
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
	
	public void checkLoss() {
		// TODO IF letters.getY() == HEIGHT of pane **BOTTOM OF WINDOW
		for(int y = 0; y<20; y++){
			if(letters[y].getY() > HEIGHT-20){
				prompt.setText("You have achieved a score of: " + score);
				pane.getChildren().remove(letters);

			}
			else{
				Score1.setText(Integer.toString(score));
			}
		}
		
	}
	public void respawnLetter(int index){
		int randLetter = rand.nextInt(25);
		int  x = rand.nextInt(WIDTH);
		int y = rand.nextInt(30);
		y*=-1;
		letters[index].setX(x);
		letters[index].setY(y);
		letters[index].setText(ALPHABET[randLetter]);
	}
	
	public void spawnLetters(){
		for(int i = 0; i<MAX; i++){
			respawnLetter(i);
		}
		
	}
		
	
	   public static void main(String[] args) {
	       launch();
	   }  
	   
}