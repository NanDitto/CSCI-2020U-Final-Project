
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.PathElement;
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import javafx.scene.text.Font;
import javafx.scene.paint.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;


public class Blackjack extends Application {
	private VBox pane = new VBox();
	private HBox cardi = new HBox();
	private HBox computer = new HBox();
	private ArrayList<ImageView> cards  = new ArrayList<ImageView>();
	private ArrayList<String> nameOfcard  = new ArrayList<String>();
	public int firstRan;
	public int ButtonRan;
	public int points =0;
	public int computerpoints =0;
	 
	private TextField userPoints = new TextField();
	private TextField computerpoint = new TextField();


	Stage Window;
	Scene newScene;

	String source = "file:/Users/Nandor Gallo/workspace/Arcade/src/BlackJack/Cards/"; 
  	public void start(Stage primaryStage) {
  		Window = primaryStage;
  		System.getProperty("user.dir");
  		userPoints.setPrefSize(100,40);
  		computerpoint.setPrefSize(100,40);
		userPoints.setMaxWidth(70);
		computerpoint.setMaxWidth(70);

  		userPoints.setStyle("-fx-font-weight: bold");
  		computerpoint.setStyle("-fx-font-weight: bold");

  		userPoints.setFont(Font.font ("Verdana", 22));
  		computerpoint.setFont(Font.font ("Verdana", 22));

  		HBox player = new HBox();
  		HBox computerboard = new HBox();

  		Label pla = new Label("Player Score: ");
  		pla.setFont(Font.font ("Verdana", 22));

  		Label com = new Label("Computer Score: ");
  		com.setFont(Font.font ("Verdana", 22));

  		player.getChildren().addAll(pla, userPoints);
  		computerboard.getChildren().addAll(com, computerpoint);

  		player.setSpacing(10);
  		computerboard.setSpacing(10);

  		pane.getChildren().add(player);

  		Random rand = new Random();
  		generateCards();
      	firstRan = rand.nextInt(cards.size());
      	cardi.getChildren().add(cards.get(firstRan));
      	score(firstRan);
      	cards.remove(firstRan);
      	nameOfcard.remove(firstRan);
      	Button hit = new Button("Hit");
		hit.setPrefSize(100,40);
		HBox button = new HBox();
		button.setSpacing(10);
		button.setAlignment(Pos.CENTER);

      	Button stand = new Button("Stand");
      	stand.setPrefSize(100,40);
      	hit.setStyle("-fx-base: red;");
      	stand.setStyle("-fx-base: green;");
      	pane.getChildren().add(cardi);

      	button.getChildren().addAll(hit,stand);
      	pane.getChildren().add(button);
      	pane.setSpacing(30);
      	cardi.setSpacing(-20);

    	userPoints.setEditable(false);
		userPoints.setMouseTransparent(true);
		userPoints.setFocusTraversable(false);
		pane.setPadding(new Insets(10, 0, 0, 10));
    	pane.getChildren().add(computer);
    	pane.getChildren().add(computerboard);
    	computer.setSpacing(-20);

      	hit.setOnAction(new EventHandler<ActionEvent>() {
      		@Override // Override the handle method
      		public void handle(ActionEvent e) {
      			if (points > 21){
      				StackPane layout1 = new StackPane();
      				Label newSenelabe = new Label("Computer Won!");
					newSenelabe.setStyle("-fx-font-weight: bold");
      				layout1.getChildren().add(newSenelabe);
      				newSenelabe.setFont(Font.font ("Verdana", 22));
      				newScene = new Scene(layout1,200,200);
      				Window.setScene(newScene);
      			}
      			if(points == 21){
      				StackPane layout1 = new StackPane();
      				Label newSenelabe = new Label("YOU WON!");
					newSenelabe.setStyle("-fx-font-weight: bold");
      				layout1.getChildren().add(newSenelabe);
      				newSenelabe.setFont(Font.font ("Verdana", 22));
      				newScene = new Scene(layout1,200,200);
      				Window.setScene(newScene);      			}
      			if(points < 21){
      				ButtonRan = rand.nextInt(cards.size());
	      			cardi.getChildren().add(cards.get(ButtonRan));
	      			score(ButtonRan);
	    
	      			cards.remove(ButtonRan);
	      			nameOfcard.remove(ButtonRan);
      			}

      		}
    	});
	  
      	stand.setOnAction(new EventHandler<ActionEvent>() {
      		@Override // Override the handle method
      		public void handle(ActionEvent e) {
      			if (computerpoints <= 12){
      				for(int i=0;i<2;i++){
	      				ButtonRan = rand.nextInt(cards.size());
	      				computer.getChildren().add(cards.get(ButtonRan));
	      				computerscore(ButtonRan);
	      				cards.remove(ButtonRan);
	      				nameOfcard.remove(ButtonRan);
	      			}
      			}else{
      				ButtonRan = rand.nextInt(cards.size());
      				computer.getChildren().add(cards.get(ButtonRan));
      				computerscore(ButtonRan);
      				cards.remove(ButtonRan);
      				nameOfcard.remove(ButtonRan);
      			}
      			if(computerpoints > 21){
      				StackPane layout1 = new StackPane();
      				Label newSenelabe = new Label("YOU WON!");
					newSenelabe.setStyle("-fx-font-weight: bold");
      				layout1.getChildren().add(newSenelabe);
      				newSenelabe.setFont(Font.font ("Verdana", 22));
      				newScene = new Scene(layout1,200,200);
      				Window.setScene(newScene);
      			}
      			if(computerpoints == 21){
      				StackPane layout1 = new StackPane();
      				Label newSenelabe = new Label("Computer Won!");
					newSenelabe.setStyle("-fx-font-weight: bold");
      				layout1.getChildren().add(newSenelabe);
      				newSenelabe.setFont(Font.font ("Verdana", 22));
      				newScene = new Scene(layout1,200,200);
      				Window.setScene(newScene);
      			}


      		}
    	});
    	  // create a background fill 
            BackgroundFill background_fill = new BackgroundFill(Color.PINK,  
                                          CornerRadii.EMPTY, Insets.EMPTY); 
  
            // create Background 
            Background background = new Background(background_fill); 
  
            // set background 
            pane.setBackground(background); 




    	Scene scene = new Scene(pane,470,470);

     	Window.setTitle("Black Jack"); // Set the stage title
      	Window.setScene(scene); // Place the scene in the stage
      	Window.show(); // Display the stage
   }
   public static void main(String[] args) {
    	launch(args);
  }

    public void score(int number){
    	//System.out.print("Adsad");
    	if(nameOfcard.get(number).equals("3a") || nameOfcard.get(number).equals("3b")||nameOfcard.get(number).equals("3c")||nameOfcard.get(number).equals("3d")){
    		points = points +3;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("4a") || nameOfcard.get(number).equals("4b")||nameOfcard.get(number).equals("4c")||nameOfcard.get(number).equals("4d")){
    		points = points +4;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("5a") || nameOfcard.get(number).equals("5b")||nameOfcard.get(number).equals("5c")||nameOfcard.get(number).equals("5d")){
    		points = points +5;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}


    	if(nameOfcard.get(number).equals("6a") || nameOfcard.get(number).equals("6b")||nameOfcard.get(number).equals("6c")||nameOfcard.get(number).equals("6d")){
    		points = points +6;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("7a") || nameOfcard.get(number).equals("7b")||nameOfcard.get(number).equals("7c")||nameOfcard.get(number).equals("7d")){
    		points = points +7;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("8a") || nameOfcard.get(number).equals("8b")||nameOfcard.get(number).equals("8c")||nameOfcard.get(number).equals("8d")){
    		points = points +8;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("9a") || nameOfcard.get(number).equals("9b")||nameOfcard.get(number).equals("9c")||nameOfcard.get(number).equals("9d")){
    		points = points +9;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("10a") || nameOfcard.get(number).equals("10b")||nameOfcard.get(number).equals("10c")||nameOfcard.get(number).equals("10d")){
    		points = points +10;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}

    	if(nameOfcard.get(number).equals("11a") || nameOfcard.get(number).equals("11b")||nameOfcard.get(number).equals("11c")||nameOfcard.get(number).equals("11d")
    		||nameOfcard.get(number).equals("12a") || nameOfcard.get(number).equals("12b")||nameOfcard.get(number).equals("12c")||nameOfcard.get(number).equals("12d")
    		||nameOfcard.get(number).equals("13a") || nameOfcard.get(number).equals("13b")||nameOfcard.get(number).equals("13c")||nameOfcard.get(number).equals("13d")){
    		points = points +10;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}
    	if(nameOfcard.get(number).equals("15a") || nameOfcard.get(number).equals("15b")||nameOfcard.get(number).equals("15c")||nameOfcard.get(number).equals("15d")){
    		points = points +2;
    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}

    	if(nameOfcard.get(number).equals("14a") || nameOfcard.get(number).equals("14b")||nameOfcard.get(number).equals("14c")||nameOfcard.get(number).equals("14d")){
    		int input = JOptionPane.showOptionDialog(null, "You got an ace. How many points would you like? Press NO for 11 and YES for 1", "Ace ALERT!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	        if(input == JOptionPane.YES_OPTION)
	        {
	            points = points +1;
	        }
	        if(input == JOptionPane.NO_OPTION)
	        {
	            points = points +11;
	        }

    		String value = Integer.toString(points);
    		userPoints.setText(value);
    	}


    }

	public void generateCards(){
		for(int j=3;j<=15;j++){
			for(int i=1;i<=4;i++){
				if(i ==1){
					String name = j+"a";
					String end = j+"a" + ".png";
  					String finalo = source + end;
					cards.add(new ImageView (finalo));
					nameOfcard.add(name);
				}
				if(i ==2){
					String name = j+"b";
					String end = j+"b" + ".png";
  					String finalo = source + end;
					cards.add(new ImageView (finalo));
					nameOfcard.add(name);

				}
				if(i ==3){
					String name = j+"c";
					String end = j+"c" + ".png";
  					String finalo = source + end;
					cards.add(new ImageView (finalo));
					nameOfcard.add(name);
				}
				if(i ==4){
					String name = j+"d";
					String end = j+"d" + ".png";
  					String finalo = source + end;
					cards.add(new ImageView (finalo));
					nameOfcard.add(name);
				}
			}

		}
	}

 public void computerscore(int number){
    	//System.out.print("Adsad");
    	if(nameOfcard.get(number).equals("3a") || nameOfcard.get(number).equals("3b")||nameOfcard.get(number).equals("3c")||nameOfcard.get(number).equals("3d")){
    		computerpoints = computerpoints +3;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("4a") || nameOfcard.get(number).equals("4b")||nameOfcard.get(number).equals("4c")||nameOfcard.get(number).equals("4d")){
    		computerpoints = computerpoints +4;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("5a") || nameOfcard.get(number).equals("5b")||nameOfcard.get(number).equals("5c")||nameOfcard.get(number).equals("5d")){
    		computerpoints = computerpoints +5;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}


    	if(nameOfcard.get(number).equals("6a") || nameOfcard.get(number).equals("6b")||nameOfcard.get(number).equals("6c")||nameOfcard.get(number).equals("6d")){
    		computerpoints = computerpoints +6;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("7a") || nameOfcard.get(number).equals("7b")||nameOfcard.get(number).equals("7c")||nameOfcard.get(number).equals("7d")){
    		computerpoints = computerpoints +7;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("8a") || nameOfcard.get(number).equals("8b")||nameOfcard.get(number).equals("8c")||nameOfcard.get(number).equals("8d")){
    		computerpoints = computerpoints +8;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("9a") || nameOfcard.get(number).equals("9b")||nameOfcard.get(number).equals("9c")||nameOfcard.get(number).equals("9d")){
    		computerpoints = computerpoints +9;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("10a") || nameOfcard.get(number).equals("10b")||nameOfcard.get(number).equals("10c")||nameOfcard.get(number).equals("10d")){
    		computerpoints = computerpoints +10;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}

    	if(nameOfcard.get(number).equals("11a") || nameOfcard.get(number).equals("11b")||nameOfcard.get(number).equals("11c")||nameOfcard.get(number).equals("11d")
    		||nameOfcard.get(number).equals("12a") || nameOfcard.get(number).equals("12b")||nameOfcard.get(number).equals("12c")||nameOfcard.get(number).equals("12d")
    		||nameOfcard.get(number).equals("13a") || nameOfcard.get(number).equals("13b")||nameOfcard.get(number).equals("13c")||nameOfcard.get(number).equals("13d")){
    		computerpoints = computerpoints +10;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}
    	if(nameOfcard.get(number).equals("15a") || nameOfcard.get(number).equals("15b")||nameOfcard.get(number).equals("15c")||nameOfcard.get(number).equals("15d")){
    		computerpoints = computerpoints +2;
    		String value = Integer.toString(computerpoints);
    		computerpoint.setText(value);
    	}

    	if(nameOfcard.get(number).equals("14a") || nameOfcard.get(number).equals("14b")||nameOfcard.get(number).equals("14c")||nameOfcard.get(number).equals("14d")){
	        computerpoints = computerpoints +1;
    		String value = Integer.toString(points);
    		computerpoint.setText(value);
    	}


    }

}
