package mainApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Scene3 extends Application {
	private final int WIDTH = 900;
	private final int HEIGHT = 900;
	private final int STARTX = 125;
	private final int GAP = 250;
	private final int windowWIDTH = 160;
	private final int windowHEIGHT = 160;
	public int levelsUnlocked =3;
  int currentX= 122;
  int currentY = 140;
  ImageView [] locks = new ImageView[7];
  Pane pane = new Pane();
	//A USER_NAME thats passed between FILES
	public String USER_NAME = "INSERT USERS NAME HERE";
	public Text user = new Text(20,40,USER_NAME);
  String cwd = System.getProperty("user.dir");

	 @Override
  public void start(Stage stage) {
		System.out.println(levelsUnlocked);
    //TicTacToe, Rain, Jeopardy, BlackJack
    //President, Pong, SnakeGame
    user.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    user.setFill(Color.WHITE);
    user.setX(150);
    user.setY(80);
    Image image = new Image("file:"+ cwd + "/src/main/resources/background.gif");
    ImageView background = new ImageView(image);
    background.setFitWidth(WIDTH+10);
    background.setFitHeight(HEIGHT+20);
    Image light = new Image("file:"+ cwd + "/src/main/resources/light.png");
    ImageView lights = new ImageView(light);
    lights.setFitWidth(WIDTH+110);
    lights.setFitHeight(HEIGHT+110);
    lights.setX(-50);
    lights.setY(-130);
    pane.getChildren().addAll(background,lights);



    Image Lock = new Image("file:"+ cwd + "/src/main/resources/lock.png");
    Image back = new Image("file:"+ cwd + "/src/main/resources/left_arrow.gif");

    Image game1 = new Image("file:"+ cwd + "/src/main/resources/TicTacToe.png");
    Image game2 = new Image("file:"+ cwd + "/src/main/resources/BlackJack.png");
    Image game3 = new Image("file:"+ cwd + "/src/main/resources/Pong.png");
    Image game4 = new Image("file:"+ cwd + "/src/main/resources/President.png");
    Image game5 = new Image("file:"+ cwd + "/src/main/resources/SnakeGame.png");
    Image game6 = new Image("file:"+ cwd + "/src/main/resources/HangMan.png");
    Image game7 = new Image("file:"+ cwd + "/src/main/resources/Jeopardy.png");
    Image game8 = new Image("file:"+ cwd + "/src/main/resources/LetterFall.png");



    ImageView Back_Arrow = new ImageView(back);
    Back_Arrow.setX(30);
    Back_Arrow.setY(30);

    ImageView Level1 = new ImageView(game1);
    ImageView Level2 = new ImageView(game2);
    ImageView Level3 = new ImageView(game3);
    ImageView Level4 = new ImageView(game4);
    ImageView Level5 = new ImageView(game5);
    ImageView Level6 = new ImageView(game6);
    ImageView Level7 = new ImageView(game7);
    ImageView Level8 = new ImageView(game8);



    Text l1 = new Text(STARTX,340,"TicTacToe");
    l1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));


    Text l2 = new Text(STARTX + GAP + 8,340,"Black Jack");
    l2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l3 = new Text(STARTX + GAP*2 + 33,340,"Pong");
    l3.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l4 = new Text(STARTX+10,595,"President");
    l4.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l5 = new Text(STARTX + GAP,595,"Snake Game");
    l5.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l6 = new Text(STARTX + GAP*2+10,598,"Hang Man");
    l6.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l7 = new Text(STARTX+15,865,"Jeopardy");
    l7.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l8 = new Text(STARTX + GAP+10,865,"LetterFall");
    l8.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    l1.setFill(Color.WHITE);
    l2.setFill(Color.WHITE);
    l3.setFill(Color.WHITE);
    l4.setFill(Color.WHITE);
    l5.setFill(Color.WHITE);
    l6.setFill(Color.WHITE);
    l7.setFill(Color.WHITE);
    l8.setFill(Color.WHITE);


    Level1.setFitWidth(152);
    Level1.setFitHeight(167);
    Level1.setX(STARTX);
    Level1.setY(136);

    Level2.setFitWidth(152);
    Level2.setFitHeight(166);
    Level2.setX(STARTX + GAP);
    Level2.setY(136);

    Level3.setFitWidth(152);
    Level3.setFitHeight(167);
    Level3.setX(STARTX + GAP*2);
    Level3.setY(136);

    Level4.setFitWidth(152);
    Level4.setFitHeight(166);
    Level4.setX(STARTX);
    Level4.setY(396);

    Level5.setFitWidth(152);
    Level5.setFitHeight(167);
    Level5.setX(STARTX + GAP);
    Level5.setY(397);

    Level6.setFitWidth(152);
    Level6.setFitHeight(166);
    Level6.setX(STARTX + GAP*2);
    Level6.setY(400);

    Level7.setFitWidth(152);
    Level7.setFitHeight(167);
    Level7.setX(STARTX);
    Level7.setY(663);

    Level8.setFitWidth(152);
    Level8.setFitHeight(166);
    Level8.setX(STARTX + GAP);
    Level8.setY(663);
    //Event for each level being clicked

    	//Event for each level being clicked
    Back_Arrow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            MainMenu TTT = new MainMenu();
            try {
                TTT.start(stage);
								stage.setHeight(590);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
   });
     //TicTacToe
    Level1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TicTacToe TTT = new TicTacToe();
            TTT.start(stage);
            stage.setWidth(450);
            stage.setHeight(300);

        }
   });
     //BlackJack
    Level2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Blackjack hi = new Blackjack();
            hi.start(stage);

            stage.setWidth(470);
            stage.setHeight(470);


        }
   });
     //Pong
    Level3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Pong TTT = new Pong();
            stage.setHeight(600);
            stage.setWidth(1000);
            TTT.start(stage);

        }
   });
    //President
    Level4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Pres hi = new Pres();
            try {
                hi.start(stage);
                stage.setHeight(800);
                stage.setWidth(650);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
   });
     //Snake
    Level5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            SnakeGame TTT = new SnakeGame();
            try {
                TTT.start(stage);
                stage.setWidth(1200);
                stage.setHeight(800+20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
   });

    Level6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Hang hi = new Hang();
            hi.start(stage);
            stage.setWidth(700);
            stage.setHeight(400);

        }
   });


    Level7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Jeopardy TTT = new Jeopardy();
            try {
                TTT.start(stage);
                stage.setWidth(950);
                stage.setHeight(570);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
   });

    Level8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Rain hi = new Rain();
            hi.start(stage);
            stage.setWidth(700);
            stage.setHeight(800);
        }
   });


    //Add everything to the pane
    pane.getChildren().addAll(Back_Arrow,user,Level1,l1,Level2,l2,Level3,l3,Level4,l4,Level5,l5,Level6,l6,Level7,l7,Level8,l8);
  //Create a for loop to place locks on levels and after completion of levels unlock another level

    for(int i = levelsUnlocked; i < locks.length;i++)
    {
    	locks[i] = new ImageView(Lock);
      addLocks(i);
    }

    if(levelsUnlocked < 7){Level8.setDisable(true);}
		if(levelsUnlocked < 6){Level7.setDisable(true);}
		if(levelsUnlocked < 5){Level6.setDisable(true);}
		if(levelsUnlocked < 4){Level5.setDisable(true);}
    if(levelsUnlocked < 3){Level4.setDisable(true);}
		if(levelsUnlocked < 2){Level3.setDisable(true);}
		if(levelsUnlocked < 1){Level2.setDisable(true);}



    Scene scene = new Scene(pane,WIDTH,HEIGHT);
    stage.setResizable(false);
    stage.setTitle("Select A Game");
    stage.setScene(scene);
    stage.show();
  }

  public void addLocks(int number){
    if((number>=2) && (number <= 4)){
        locks[number].setX((STARTX-5)+GAP*(number-2));
        locks[number].setY(396);
    }
    if((number>=5) && (number <=6)){
        locks[number].setX((STARTX-5)+GAP*(number-5));
        locks[number].setY(663);
    }
    if((number>=0) && (number<=1)){
        locks[number].setX((STARTX-5)+GAP*(number+1));
        locks[number].setY(currentY);
    }
    locks[number].setFitWidth(windowWIDTH);
    locks[number].setFitHeight(windowHEIGHT);
    pane.getChildren().add(locks[number]);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
