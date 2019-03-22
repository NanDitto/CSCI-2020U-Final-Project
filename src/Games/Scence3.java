package Scene3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Scence3 extends Application {
	private final int WIDTH = 590;
	private final int HEIGHT = 900;
	//A USER_NAME thats passed between FILES
	public String USER_NAME = "Nandor";
	public Text user = new Text(20,40,USER_NAME);
  @Override 
  public void start(Stage stage) {
    
    Pane pane = new Pane();
   
    //TicTacToe, Rain, Jeopardy, BlackJack
    //President, Pong, SnakeGame
    user.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    Image image = new Image("res/Background3.png");
    ImageView background = new ImageView(image);
    background.setFitWidth(WIDTH+10);
    background.setFitHeight(HEIGHT+20);
    Image light = new Image("res/lights.gif");
    ImageView lights = new ImageView(light);
    lights.setFitWidth(720);
    lights.setFitHeight(1160);
    lights.setX(-60);
    lights.setY(-130);
    pane.getChildren().addAll(background,lights);
    
    
    
    
    
    Image game1 = new Image("res/TicTacToe.png");
    Image game2 = new Image("res/BlackJack.png");
    Image game3 = new Image("res/Pong.png");
    Image game4 = new Image("res/President.png");
    Image game5 = new Image("res/SnakeGame.png");
    Image game6 = new Image("res/HangMan.png");
    Image game7 = new Image("res/Jeopardy.png");
    Image game8 = new Image("res/LetterFall.png");
    
    ImageView Level1 = new ImageView(game1);
    ImageView Level2 = new ImageView(game2);
    ImageView Level3 = new ImageView(game3);
    ImageView Level4 = new ImageView(game4);
    ImageView Level5 = new ImageView(game5);
    ImageView Level6 = new ImageView(game6);
    ImageView Level7 = new ImageView(game7);
    ImageView Level8 = new ImageView(game8);
    
    
   //Added Text Under each window
    Text l1 = new Text(35,340,"TicTacToe");
    l1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l2 = new Text(260,340,"BLACK \n JACK");
    l2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l3 = new Text(470,340,"PONG");
    l3.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l4 = new Text(40,595,"President");
    l4.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l5 = new Text(230,595,"Snake Game");
    l5.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l6 = new Text(440,598,"Hang Man");
    l6.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l7 = new Text(35,865,"Jeopardy");
    l7.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Text l8 = new Text(250,865,"LetterFall");
    l8.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    
    Level1.setFitWidth(152);
    Level1.setFitHeight(167);
    Level1.setX(25);
    Level1.setY(136);
    
    Level2.setFitWidth(152);
    Level2.setFitHeight(166);
    Level2.setX(234);
    Level2.setY(136);
    
    Level3.setFitWidth(152);
    Level3.setFitHeight(167);
    Level3.setX(437);
    Level3.setY(136);
    
    Level4.setFitWidth(152);
    Level4.setFitHeight(166);
    Level4.setX(29);
    Level4.setY(396);
    
    Level5.setFitWidth(152);
    Level5.setFitHeight(167);
    Level5.setX(234);
    Level5.setY(397);
    
    Level6.setFitWidth(152);
    Level6.setFitHeight(166);
    Level6.setX(434);
    Level6.setY(400);
    
    Level7.setFitWidth(152);
    Level7.setFitHeight(167);
    Level7.setX(27);
    Level7.setY(663);
    
    Level8.setFitWidth(152);
    Level8.setFitHeight(166);
    Level8.setX(234);
    Level8.setY(663);

    Level1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe TTT = new TicTacToe();
            TTT.start(stage);
            
        }
   });
    
    Level2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe hi = new TicTacToe();
            hi.start(stage);
            
        }
   });
    
    Level3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe TTT = new TicTacToe();
            TTT.start(stage);
            
        }
   });
    
    Level4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe hi = new TicTacToe();
            hi.start(stage);
            
        }
   });
    
    Level5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe TTT = new TicTacToe();
            TTT.start(stage);
            
        }
   });
    
    Level6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe hi = new TicTacToe();
            hi.start(stage);
            
        }
   });
    
    Level7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe TTT = new TicTacToe();
            TTT.start(stage);
            
        }
   });
 
    Level8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	TicTacToe hi = new TicTacToe();
            hi.start(stage);
            
        }
   });
    
    
    pane.getChildren().addAll(user,Level1,l1,Level2,l2,Level3,l3,Level4,l4,Level5,l5,Level6,l6,Level7,l7,Level8,l8);
    
    Scene scene = new Scene(pane,WIDTH,HEIGHT);
    stage.setResizable(false);
    stage.setTitle("Select A Game"); 
    stage.setScene(scene); 
    stage.show();   
  }
  public static void main(String[] args) {
    launch(args);
  }
}