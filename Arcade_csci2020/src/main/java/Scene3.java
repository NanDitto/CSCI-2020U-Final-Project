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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.shape.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class Scene3 extends Application {
	private final int WIDTH = 900;
    private final int HEIGHT = 900;
    private final int STARTX = 125;
    private final int GAP = 250;
    private final int windowWIDTH = 160;
    private final int windowHEIGHT = 160;
    private final int borderWIDTH = 170;
    private final int borderHEIGHT = 185;
    public int levelsUnlocked =8;
    public String ret = "";
    static String cwd = System.getProperty("user.dir"); // used to read the user current directory
    public String userName;
    public int type;

    int currentX= 122;
    int currentY = 140;
    ImageView [] locks = new ImageView[7];
    Pane pane = new Pane();
    
    public String temp;
    public String file;
    public void settemp(String temp,String file){
        this.temp = temp;
        this.file = file;
    }
    serverSocket server;
    client clt;
    Stage secondStage = new Stage();
    Stage sStage = new Stage();
     //A USER_NAME thats passed between FILES
    public boolean check=false;

     @Override
    public void start(Stage stage) {

    String USER_NAME = "Welcome: " + temp;
    Text user = new Text(10,30,USER_NAME);
    /*
    try{
       curentLevel();
    }catch (IOException e){}
    */
    ret = File.separator;
        //Adds User Name to top right
    user.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
    user.setFill(Color.WHITE);
    user.setX(WIDTH - 250);
    user.setY(80);
        //Background image
    Image image = new Image("file:"+ cwd + "/src/main/resources/background.gif");
    ImageView background = new ImageView(image);
    background.setFitWidth(WIDTH+30);
    background.setFitHeight(HEIGHT+30);
        //Lighting image

    Image light = new Image("file:"+ cwd + "/src/main/resources/light.png");
    ImageView lights = new ImageView(light);
    lights.setFitWidth(WIDTH+110);
    lights.setFitHeight(HEIGHT+110);
    lights.setX(-50);
    lights.setY(-130);



    pane.getChildren().addAll(background,lights);

    //Hovering border of each window
    Rectangle window_Outline = new Rectangle(borderWIDTH,borderHEIGHT);
    window_Outline.setFill(Color.YELLOW);

        //All Images to make Stage look nice
    Image light_border = new Image("file:"+ cwd + "/src/main/resources/lights.gif");
    Image spaceShip = new Image("file:"+ cwd + "/src/main/resources/ship.gif");
    Image Lock = new Image("file:"+ cwd + "/src/main/resources/lock.png");
    Image back = new Image("file:"+ cwd + "/src/main/resources/left_arrow.gif");
    Image Planets = new Image("file:"+ cwd + "/src/main/resources/planets.png");

    Image game1 = new Image("file:"+ cwd + "/src/main/resources/TicTacToe.png");
    Image game2 = new Image("file:"+ cwd + "/src/main/resources/BlackJack.png");
    Image game3 = new Image("file:"+ cwd + "/src/main/resources/Pong.png"); 
    Image game4 = new Image("file:"+ cwd + "/src/main/resources/SnakeGame.png");
    Image game5 = new Image("file:"+ cwd + "/src/main/resources/HangMan.png");
    Image game6 = new Image("file:"+ cwd + "/src/main/resources/Jeopardy.png");
    Image game7 = new Image("file:"+ cwd + "/src/main/resources/LetterFall.png");
    Image game8 = new Image("file:"+ cwd + "/src/main/resources/President.png");

    ImageView planet_background = new ImageView(Planets);
    pane.getChildren().add(planet_background);
    //planet_background.setFitWidth(300);
    //planet_background.setFitHeight(200);

    ImageView ss = new ImageView(spaceShip);
    pane.getChildren().add(ss);

    ImageView border = new ImageView(light_border);
    pane.getChildren().add(border);
    border.setFitWidth(WIDTH);
    border.setFitHeight(HEIGHT);

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
    Button join = new Button("Join");
    join.setId("button");
    Button host = new Button("Host");
    host.setId("button");


    if(levelsUnlocked == 8){
        join.setDisable(false);
        host.setDisable(false);
    }else{
        join.setDisable(true);
        host.setDisable(true);
    }

    Text l1 = new Text(STARTX,340,"TicTacToe");
    l1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));


    Text l2 = new Text(STARTX + GAP + 8,340,"Black Jack");
    l2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l3 = new Text(STARTX + GAP*2 + 33,340,"Pong");
    l3.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l4 = new Text(STARTX+10,595,"Snake Game");
    l4.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l5 = new Text(STARTX + GAP,595,"Hang Man");
    l5.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l6 = new Text(STARTX + GAP*2+10,598,"Jeopardy");
    l6.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l7 = new Text(STARTX+15,865,"LetterFall");
    l7.setFont(Font.font("Times New Roman",FontWeight.BOLD, 25));

    Text l8 = new Text(STARTX + GAP-40,865,"President(2 Plys)");
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

;
    join.setLayoutX(STARTX + GAP*2);
    join.setLayoutY(710);

    host.setLayoutX(STARTX + GAP*2);
    host.setLayoutY(770);




    host.setOnAction(actionEvent -> {
        server = new serverSocket();
        server.start(secondStage);
        server.action(temp);
        //server.action(String.valueOf(levelsUnlocked));
        join.setDisable(true);
        type = 1;
    });
    join.setOnAction(actionEvent -> {
        
        clt = new client();
        clt.start(sStage);
        clt.action(temp);
        //clt.action(String.valueOf(levelsUnlocked));
        host.setDisable(true);
        type = 2;
        
    });
    //Event for each level being clicked

        //Event for each level being clicked
    Back_Arrow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            MainMenu TTT = new MainMenu();
            try {
                TTT.start(stage);
                TTT.settemp(temp,file);
                stage.setHeight(590);
                stage.setWidth(910);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
   });
     //TicTacToe
         Level1.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level1.toFront();
         window_Outline.setX(STARTX-10);
         window_Outline.setY(126);
        });
    Level1.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
    Level1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TicTacToe TTT = new TicTacToe();

            TTT.settemp(temp,file);
            TTT.start(stage);
            stage.setWidth(450);
            stage.setHeight(300+20);

        }
   });
     Level2.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level2.toFront();
         window_Outline.setX((STARTX-10)+GAP);
         window_Outline.setY(126);
        });
    Level2.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
     //BlackJack
    Level2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Blackjack hi = new Blackjack();
            hi.settemp(temp,file);
            hi.start(stage);
            stage.setWidth(470);
            stage.setHeight(470+20);
        }
   });
     Level3.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level3.toFront();
         window_Outline.setX(STARTX-10 + GAP*2);
         window_Outline.setY(126);
        });
    Level3.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
     //Pong
    Level3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Pong TTT = new Pong();
            TTT.settemp(temp,file);
            TTT.start(stage);
            stage.setHeight(600+20);
            stage.setWidth(1000);

        }
   });
     Level4.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level4.toFront();
         window_Outline.setX(STARTX-10);
         window_Outline.setY(386);
        });
    Level4.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
    //President
    Level4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            SnakeGame hi = new SnakeGame();
            try {
                hi.settemp(temp,file);
                hi.start(stage);
                stage.setHeight(800+20);
                stage.setWidth(1200);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
   });
     Level5.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level5.toFront();
         window_Outline.setX(STARTX-10 + GAP);
         window_Outline.setY(386);
        });
    Level5.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
     //Snake
    Level5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Hang TTT = new Hang();
            try {
                TTT.settemp(temp,file);
                TTT.start(stage);
                stage.setWidth(700);
                stage.setHeight(400+20);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
   });
     Level6.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level6.toFront();
         window_Outline.setX(STARTX-10 + GAP*2);
         window_Outline.setY(386);
        });
    Level6.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });

    Level6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Jeopardy hi = new Jeopardy();
            try{
                hi.settemp(temp,file);
                hi.start(stage);
                stage.setWidth(950);
                stage.setHeight(570+20);
            }catch (Exception e){
                 e.printStackTrace();
            }

        }
   });
     Level7.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level7.toFront();
         window_Outline.setX(STARTX-10);
         window_Outline.setY(653);
        });
    Level7.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });

    Level7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Rain TTT = new Rain();
            try {
                TTT.settemp(temp,file);
                TTT.start(stage);
                stage.setWidth(700);
                stage.setHeight(800+20);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
   });
     Level8.setOnMouseEntered(e -> {
         pane.getChildren().add(window_Outline);
         Level8.toFront();
         window_Outline.setX(STARTX-10 + GAP);
         window_Outline.setY(653);
        });
    Level8.setOnMouseExited(e -> {
        pane.getChildren().remove(window_Outline);
        });
    Level8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Pres hi = new Pres();
            try{
                hi.settemp(temp,file);
                hi.start(stage);
                stage.setWidth(650);
                stage.setHeight(800+20);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
   });
    //Add everything to the pane
    pane.getChildren().addAll(Back_Arrow,user,Level1,l1,Level2,l2,Level3,l3,Level5,l5,Level6,l6,Level7,l7,Level8,l8,Level4,l4,join,host);
  //Create a for loop to place locks on levels and after completion of levels unlock another level

    for(int i = levelsUnlocked; i < locks.length;i++)
    {
        locks[i] = new ImageView(Lock);
        addLocks(i);
        FadeEffect(2, locks[i], 0,1);
    }


    if(levelsUnlocked < 7){Level8.setDisable(true);}
        if(levelsUnlocked < 6){Level7.setDisable(true);}
        if(levelsUnlocked < 5){Level6.setDisable(true);}
        if(levelsUnlocked < 4){Level5.setDisable(true);}
    if(levelsUnlocked < 3){Level4.setDisable(true);}
        if(levelsUnlocked < 2){Level3.setDisable(true);}
        if(levelsUnlocked < 1){Level2.setDisable(true);}



    Scene scene = new Scene(pane,WIDTH,HEIGHT);
    scene.getStylesheets().add("main.css");  
    stage.setResizable(false);
    stage.setTitle("Select A Game");
    stage.setScene(scene);
    stage.show();
  }
  public void FadeEffect(int time, ImageView img, int from, int to){
        FadeTransition ft = new FadeTransition(Duration.seconds(time), img);
        ft.setFromValue(from); // go from the number
        ft.setToValue(to); // go to the number
        ft.setCycleCount(1); // do the fade just once
        ft.play();
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
  public void curentLevel() throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader( new FileReader(file));
            String strLine = "";
            while((strLine = br.readLine()) != null){
                String[] details = strLine.split(",");
                if(details[0].equals(temp)){
                    levelsUnlocked = Integer.parseInt(details[details.length-1]);
                }
            }
        }catch (IOException e){}
  }
  public static void main(String[] args) {
    launch(args);
  }
}