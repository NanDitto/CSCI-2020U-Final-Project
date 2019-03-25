package mainApp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import java.io.IOException;
public class Pong extends Application{
	//IF PLAYER BEATS COMPUTER PASS LEVEL
	public boolean pass = false;
	// Board Layout
	String cwd = System.getProperty("user.dir");
	private final String background_Image = "file:" + cwd + "/src/main/resources/GrassBackground.png";
	private final String log_image = "file:" + cwd + "/src/main/resources/Log.png";
	private final String ball_image = "file:" + cwd + "/src/main/resources/ball1.png";

	public final static int TIME = 60;
	public int time = TIME;
	public final static int HEIGHT = 600;
	public final static int WIDTH = 1000;
	public final Pane Board = new Pane();
	public final Scene scene = new Scene(Board, WIDTH, HEIGHT, Color.BLACK);
	Text prompt = new Text(35, 250, "Press Space to Start!");
	Text timer = new Text(WIDTH/2 , 30,Integer.toString(TIME));
	//Determine Winner
	Text Score1 = new Text(40, 30,"0");
	Text Score2 = new Text(WIDTH -50, 30,"0");
	public int PlayerScore = 0;
	public int ComputerScore = 0;
	/*Ball Properties:
	 * X and Y Speed
	 * Size of the ball
	 * is a circle
	 * */
	float xspeed = 0;
	float yspeed = -4;
	public final int SIZE = 15;
	public boolean WIN;

	/*Paddle Properties:
	 * Y Speed
	 * Size of Paddle
	 * is a Rectangle
	 * Has a length and width
	 * Only moves with a key press
	 * */
	public final float Bot_Y = 3;
	public final float DELTA_Y = 10;
	public final float LENGTH = 100;
	public final float W = 20;
	public String temp;
  	public String file;
  	public void settemp(String temp,String file){
        this.temp = temp;
        this.file = file;
  	}
	@Override
	@FXML
	public void start(Stage stage) {
		WIN = false;
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		//Timer Text
		timer.setFill(Color.WHITE);
		timer.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));

		//Prompt Text
		prompt.setFont(Font.font("Times New Roman",FontWeight.BOLD, 80));
		prompt.setFill(Color.WHITE);
		//Ball
		Circle ball = new Circle(SIZE, Color.WHITE);
		ball.relocate(WIDTH/2, HEIGHT/2);
		//Paddles
		Rectangle Paddle1 = new Rectangle(10, HEIGHT/2, W, LENGTH);
		Paddle1.setFill(Color.WHITE);
		Rectangle Paddle2 = new Rectangle(WIDTH - 30, HEIGHT/2, W, LENGTH);
		Paddle2.setFill(Color.WHITE);
		Image img = new Image(log_image);
		Image ballimg = new Image(ball_image);
		ImageView background = new ImageView(background_Image);

		ball.setFill(new ImagePattern(ballimg));
		Paddle1.setFill(new ImagePattern(img));
		Paddle2.setFill(new ImagePattern(img));
		background.setFitHeight(HEIGHT + 10);
		background.setFitWidth(WIDTH + 10);

		Board.getChildren().addAll(background,Paddle1,Paddle2,timer,prompt);
		//Ball movement and paddle interactions
		Timeline Movement = new Timeline(new KeyFrame(Duration.millis(20),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				//move the ball
				ball.setLayoutX(ball.getLayoutX() + xspeed);
				ball.setLayoutY(ball.getLayoutY() + yspeed);
				//Computer Movements
				if(ball.getLayoutY() >= Paddle2.getY()){
					if(Paddle2.getY() + LENGTH + DELTA_Y >= HEIGHT){
		        		Paddle2.setY(HEIGHT - LENGTH);
		        	}else{
		        		Paddle2.setY(Paddle2.getY() + Bot_Y);
		        	}
				}else if(ball.getLayoutY() <= Paddle2.getY()){
					if(Paddle1.getY() - DELTA_Y <= 0){
						Paddle2.setY(0);
		        	}else{
		        		Paddle2.setY(Paddle2.getY() - Bot_Y);
		        	}
				}
				//If the ball touches left paddle
				if(ball.getLayoutX() <= Paddle1.getX() + W && ((ball.getLayoutY() >= Paddle1.getY())
						&& (ball.getLayoutY() <= Paddle1.getY() + LENGTH))){
					xspeed -= 3;
					xspeed = -xspeed;
				}
				//If the ball touches right paddle
				if(ball.getLayoutX() >= Paddle2.getX() - W && ((ball.getLayoutY() >= Paddle2.getY())
						&& (ball.getLayoutY() <= Paddle2.getY() + LENGTH)  )){
					xspeed += 3;
					xspeed *= -1;
				}
				//If the ball reaches the left Player 2 gets a Point and reset ball
				//If the ball reaches the right Player 1 gets a point and reset ball
				if(ball.getLayoutX() <= (0 + ball.getRadius())){
					ComputerScore++;
					reset(ball);
				}else if(ball.getLayoutX() >= (WIDTH - ball.getRadius()))
				{
					PlayerScore++;
					reset(ball);
				}
				//If the ball reaches the bottom or top border change the direction
				if((ball.getLayoutY() >= (HEIGHT - ball.getRadius())) || (ball.getLayoutY() <= (0 + ball.getRadius()))){
					yspeed = -yspeed;
				}
			}
		}));
		Timeline Tick = new Timeline(new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent h) {
				time -=1;
				timer.setText(Integer.toString(time));

				if(time == 0){
					Board.getChildren().remove(ball);
					Movement.stop();
					DetermineWinner();
				}
			}
		}));
		//Controls for paddle and if space or esc is pressed
		scene.setOnKeyPressed(e -> {
		      switch (e.getCode()) {
		        case DOWN:
		        	if(Paddle1.getY() + LENGTH + DELTA_Y >= HEIGHT){
		        		Paddle1.setY(HEIGHT - LENGTH);
		        	}else{
		        		Paddle1.setY(Paddle1.getY() + DELTA_Y);
		        	}break;
		        case UP:
		        	if(Paddle1.getY() - DELTA_Y <= 0){
		        		Paddle1.setY(0);
		        	}else{
		        		Paddle1.setY(Paddle1.getY() - DELTA_Y);
		        	}break;
		        case SPACE:
	        	//When Space key is pressed Start The movement and Timer
	        	Board.getChildren().remove(prompt);
		    		Score1.setFill(Color.WHITE);
		    		Score2.setFill(Color.WHITE);
		    		Score1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
		    		Score2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
	        	Board.getChildren().addAll(ball,Score1,Score2);
	        	reset(ball);
		        Movement.setCycleCount(Timeline.INDEFINITE);
	        	Movement.play();
		      	Tick.setCycleCount(TIME);
	        	Tick.play();
	        	break;
                case ESCAPE:
                    if(WIN == true){
                        readAdd();
                    }
                    Scene3 hi = new Scene3();
                try {
									//If escape is pressed
                    hi.settemp(temp,file);
                    hi.start(stage);
                    hi.curentLevel();
                    stage.setWidth(900);
                    stage.setHeight(910);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
		        default:
		        	break;
		      }
		    });
	}

	 public void readAdd(){
        CSV savea = new CSV();
        try{
            savea.read(temp, file,"3");
            savea.save(file);
        }catch (IOException e){}

    }
		//Function that adds the ball back to the middle with a random x speed, and updates scores
	public void reset(Circle c){
		Random rand = new Random();
    	float n = rand.nextInt(9) + 1;
    	xspeed = n;
    	if(n > 5)
    	xspeed -= 10;
		c.relocate(WIDTH/2, HEIGHT/2);
		Score1.setText(Integer.toString(PlayerScore));
		Score2.setText(Integer.toString(ComputerScore));
	}
	//Function to see whos score is higher, higher score determines winner
	public void DetermineWinner(){
		prompt.setX(250);
		if(PlayerScore>ComputerScore){
			prompt.setText("You Win!");
			Board.getChildren().add(prompt);
			pass = true;
			WIN = true;
		}else if(ComputerScore > PlayerScore){
			prompt.setText("Computer Wins!");
			Board.getChildren().add(prompt);
		}else{
			prompt.setText("TIE");
			prompt.setX(450);
			Board.getChildren().add(prompt);
		}
	}
	public static void main(String[] args) {
		launch();
	}
}
