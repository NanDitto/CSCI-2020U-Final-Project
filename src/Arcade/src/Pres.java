

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
import javafx.scene.layout.Pane;
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
import javafx.scene.text.Text;
import java.util.Arrays; 
import java.util.stream.IntStream; 
import javafx.animation.FadeTransition;
import java.util.Scanner;
import javafx.scene.shape.Rectangle;
import java.util.Collections;


public class Pres extends Application {
	String source = "President/res/Card/"; 
	private VBox pane = new VBox(); // playerone
	private HBox cards = new HBox();
	private HBox buttons = new HBox();

	// playertwo
	private HBox cardstwo = new HBox();
	private HBox playertwo = new HBox();
	private HBox passandText =  new HBox();
	private HBox passandText2 =  new HBox();

	private StackPane stack = new StackPane();
  	private ArrayList<String> number  = new ArrayList<String>();

	private ArrayList<ImageView> user  = new ArrayList<ImageView>();
	private ArrayList<ImageView> usertwo  = new ArrayList<ImageView>();
	private Button butto[] = new Button[12];

	private Button playertwobutto[] = new Button[12];

	private ArrayList<String> userran = new ArrayList<String>();
	private ArrayList<String> usertworan = new ArrayList<String>();
	
	private Button passButton[] = new Button[2];
	
  	private Random rand = new Random();
  	private int count = 0,countt=0;

  	public void start(Stage primaryStage) throws Exception{
  	
  	passButton[0] = new Button("PASS");
  	passButton[1] = new Button("PASS");
  	passButton[0].setId("pass");
  	passButton[1].setId("pass");
  	Label player1 = new Label("Player 1");
  	player1.setId("player");
  	passandText.getChildren().addAll(passButton[0],player1);
  	passandText.setSpacing(500);
  	pane.getChildren().add(passandText);

  	Label player2 = new Label("Player 2");
  	player2.setId("player");
  	passandText2.getChildren().addAll(passButton[1],player2);
  	passandText2.setSpacing(500);



    for(int i=3;i<=16;i++){
      	number.add(i+"a");
      	number.add(i+"b");
      	number.add(i+"c");
      	number.add(i+"d");
    }
    ImageView first = new ImageView(source+"3a.png");
    stack.getChildren().add(first);
    Random rand = new Random();





	ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i=1; i<=54; i++) {
        list.add(new Integer(i));
    }
    Collections.shuffle(list);
    for (int i=0; i<=22; i++) {
        list.get(i);
    }

    for(int i=0;i<=11;i++){
      //int n = rand.nextInt(number.size());
      user(number.get(list.get(i)),user);
      userran.add(number.get(list.get(i)));
      number.remove(list.get(i));
      list.remove(i);
    }
    for(int i=0;i<=11;i++){
      //n = rand.nextInt(number.size());
      usertwos(number.get(list.get(i)), usertwo);
      usertworan.add(number.get(list.get(i)));
      number.remove(list.get(i));
      list.remove(i);
    }
    
    button(butto,butto.length,buttons);
    button(playertwobutto,playertwobutto.length,playertwo);
    pane.getChildren().add(cards);
    pane.getChildren().add(buttons);
    pane.getChildren().add(stack);
    pane.setSpacing(60);
    pane.getChildren().add(playertwo);
    pane.getChildren().add(cardstwo);
    pane.getChildren().add(passandText2);
    //pane.setSpacing(40);
    cards.setAlignment(Pos.CENTER);
    cardstwo.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(10, 5, 10, 5));

   	for(int i=0;i<butto.length;i++){
   		if(i<=1){
   			passButton[i].setOnAction(this::handleButtonAction);
   		}
    	butto[i].setOnAction(this::handleButtonAction);
	}

	for(int i=0;i<playertwobutto.length;i++){
    	playertwobutto[i].setOnAction(this::handleButtonAction);
	}
	for(int i=0;i<usertwo.size();i++){
     		usertwo.get(i).setVisible(false);
	}

      Scene scene = new Scene(pane,650,800);
      pane.setId("scene");
      scene.getStylesheets().add("style.css");  
      primaryStage.setTitle("President"); // Set the stage title
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage
 	}

 	public static void main(String[] args) {
    	launch(args);
  }

  
  	public void user(String card, ArrayList<ImageView> lists){
  		String end = card + ".png";
  		String finalo = source + end; // adding the entension with the destination
  		lists.add(new ImageView(finalo));
  		lists.get(count).setFitHeight(100);
		lists.get(count).setFitWidth(80);
  		cards.setSpacing(-40);
  		cards.getChildren().add(lists.get(count));
  		count = count + 1;
  	}
  	public void usertwos(String card, ArrayList<ImageView> lists){
  		String end = card + ".png";
  		String finalo = source + end; // adding the entension with the destination
  		lists.add(new ImageView(finalo));
  		lists.get(countt).setFitHeight(100);
		lists.get(countt).setFitWidth(80);
  		cardstwo.setSpacing(-40);
  		cardstwo.getChildren().add(lists.get(countt));
  		countt = countt + 1;
  	}
  	
  	

  	public void button(Button[] butt,int len, HBox name){
  		for(int i=0;i<len;i++){
  			butt[i] = new Button(Integer.toString(i+1));
  			butt[i].setId("Button");
  			name.setSpacing(5);
  			name.getChildren().add(butt[i]);
  		}
  	}
  	public void handleButtonAction(ActionEvent event){
  		if(event.getSource() == passButton[0]){
  			for(int i=0;i<usertwo.size();i++){
        		usertwo.get(i).setVisible(true);
        	}
        	for(int i=0;i<user.size();i++){
        		user.get(i).setVisible(false);
        	}
  		}
        for(int p=0; p<butto.length; p++){
		 	if(event.getSource() == butto[p]){
		 		if (userran.get(p).equals("16a") || userran.get(p).equals("16b") ){
        			ImageView xx = new ImageView(source+userran.get(p)+".png");
 					stack.getChildren().add(xx);
        			fadeAndDisable(butto[p]);
        			fadeAndDisableCards(user.get(p));

		 		}else{
		 			for(int i=0;i<usertwo.size();i++){
        				usertwo.get(i).setVisible(true);
        			}
		 			ImageView xx = new ImageView(source+userran.get(p)+".png");
 					stack.getChildren().add(xx);
        			fadeAndDisable(butto[p]);
        			fadeAndDisableCards(user.get(p));
        			for(int i=0;i<user.size();i++){
        				user.get(i).setVisible(false);
        			}
		 		}
			}
		}
		if(event.getSource() == passButton[1]){
  			for(int i=0;i<user.size();i++){
	        	user.get(i).setVisible(true);
	        }
        	for(int i=0;i<usertwo.size();i++){
	        	usertwo.get(i).setVisible(false);
	        }
  		}
		for(int p=0; p<playertwobutto.length; p++){
		 	if(event.getSource() == playertwobutto[p]){
		 		if (usertworan.get(p).equals("16a") || usertworan.get(p).equals("16b") ){
		 			ImageView xx = new ImageView(source+usertworan.get(p)+".png");
	 				stack.getChildren().add(xx);
	        		fadeAndDisable(playertwobutto[p]);
	        		fadeAndDisableCards(usertwo.get(p));

		 		}else{
			 		for(int i=0;i<user.size();i++){
	        			user.get(i).setVisible(true);
	        		}
	        		ImageView xx = new ImageView(source+usertworan.get(p)+".png");
	 				stack.getChildren().add(xx);
	        		fadeAndDisable(playertwobutto[p]);
	        		fadeAndDisableCards(usertwo.get(p));
	        		for(int i=0;i<usertwo.size();i++){
	        			usertwo.get(i).setVisible(false);
	        		}
				}
			}
		}

	}
  	
  	private void fadeAndDisable(Button x){
 		FadeTransition trans = new FadeTransition(Duration.seconds(1), x);
        trans.setFromValue(0.0);
        trans.setToValue(.20);
        trans.setCycleCount(1);
        trans.play();
        x.setDisable(true);	
        
 	}
 	private void fadeAndDisableCards(ImageView x){
 		FadeTransition trans = new FadeTransition(Duration.seconds(1), x);
        trans.setFromValue(0.0);
        trans.setToValue(.00);
        trans.setCycleCount(1);
        trans.play();
        
 	}
 }