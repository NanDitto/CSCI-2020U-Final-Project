import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Scence3 extends Application {
	private final int WIDTH = 590;
	private final int HEIGHT = 900;
	public String USER_NAME = "Nandor";
	public Text user = new Text(20,40,USER_NAME);
  @Override
  public void start(Stage stage) {

    Pane pane = new Pane();

    //TicTacToe, Rain, Jeopardy, BlackJack
    //President, Pong, SnakeGame
    Image image = new Image("file:/Users/Nandor Gallo/workspace/Scene3/src/Background3.png");

    ImageView background = new ImageView(image);
    background.setFitWidth(WIDTH+10);
    background.setFitHeight(HEIGHT+20);
    pane.getChildren().add(background);





    Image game1 = new Image("file:/Users/Nandor Gallo/workspace/Scene3/src/TicTacToe.png");
    Image game2 = new Image("file:/Users/Nandor Gallo/workspace/Scene3/src/BlackJack.png");

    ImageView Level1 = new ImageView(game1);
    ImageView Level2 = new ImageView(game2);

    //Add Textboxes
    Text l1 = new Text(35,340,"TicTacToe");
    l1.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));

  //Add Textboxes
    Text l2 = new Text(260,340,"BLACK \n JACK");
    l2.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));

    user.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));

    Level1.setFitWidth(152);
    Level1.setFitHeight(167);
    Level1.setX(25);
    Level1.setY(136);

    Level2.setFitWidth(152);
    Level2.setFitHeight(166);
    Level2.setX(234);
    Level2.setY(136);










    pane.getChildren().addAll(user,Level1,l1,Level2,l2);




    Scene scene = new Scene(pane,WIDTH,HEIGHT);
    stage.setResizable(false);
    stage.setTitle("Select A Game");
    stage.setScene(scene);
    stage.show();

    TicTacToe hi = new TicTacToe();
    //hi.start(stage);

  }

  public static void main(String[] args) {
    launch(args);
  }
}
