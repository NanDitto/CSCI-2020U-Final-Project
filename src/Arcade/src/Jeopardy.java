

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
import javafx.scene.layout.GridPane;
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
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.lang.Object.*;
import java.util.*; 
import javafx.scene.control.TextInputDialog;
import java.awt.Dialog;
import javafx.scene.text.Text;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.util.Iterator.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Jeopardy extends Application {
	Stage Window;
	GridPane numbers = new GridPane();
	int one=4, two=4,three=4,four=4,five=4;
	int score,mainCounter=0;
	VBox pane = new VBox();
	HBox catego = new HBox();
	HBox scoring = new HBox();
	private Button[][] numButtons = new Button[6][6];
	ArrayList<String> tokens = new ArrayList<String>(); 
	ArrayList<String> questions[] = new ArrayList[21];
	ArrayList<String> answers[] = new ArrayList[21];
	Label scoreboard = new Label();
	private int firstCat,secondCat,temp,thirdCat,fourthCat,fifthCat;
	Random rand = new Random();

	public void start(Stage primaryStage) throws Exception {
		Window = primaryStage;
		layout();
		showLines("questionAnswers.txt");
		categoryAcclines();
		for(int i=1;i<numButtons.length;i++){
			for(int j=1;j<numButtons.length;j++){
				numButtons[i][j].setOnAction(this::handleButtonAction);
			}
		}
		numbers.setHgap(5); //horizontal gap in pixels => that's what you are asking for
		numbers.setVgap(5); //vertical gap in pixels
		numbers.setPadding(new Insets(10, 5, 10, 5)); //margins around the whole grid (top/right/bottom/left)
	    
		catego.setSpacing(5);
		catego.setPadding(new Insets(10, 5, 10, 5));
	    pane.getChildren().add(catego);                                         
		pane.getChildren().add(numbers);
		pane.getChildren().add(scoring);
		Label player = new Label("Points: ");
		scoring.getChildren().addAll(player,scoreboard);
		scoreboard.setText("0");
		scoreboard.setId("scoreboard");
		player.setId("scoreboard");
		pane.setSpacing(10);
		scoring.setSpacing(5);
		scoring.setPadding(new Insets(10, 5, 10, 5));
		scoring.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane,950,500);
		scene.getStylesheets().add("Jeopardy/style.css");  
	 	Window.setTitle("Jeopardy!"); // Set the stage title
	  	Window.setScene(scene); // Place the scene in the stage
	  	Window.show(); // Display the stage


	}
   public static void main(String[] args) {
    	launch(args);
  }
  public void layout(){
  	for(int i = 1; i<= 5; i++){
          for( int p=1; p<= 5; p++){
            if(i == 1){
               numButtons[i][p] = new Button("100");
               numbers.add(numButtons[i][p], p,i); 
            } 
            if(i == 2){
               numButtons[i][p] = new Button("200");
               numbers.add(numButtons[i][p], p,i); 
            } 
            if(i == 3){
               numButtons[i][p] = new Button("300");
               numbers.add(numButtons[i][p], p,i); 
            } 
            if(i == 4){
               numButtons[i][p] = new Button("400");
               numbers.add(numButtons[i][p], p,i); 
            }  
            if(i == 5){
               numButtons[i][p] = new Button("500");
               numbers.add(numButtons[i][p], p,i); 
            }
            numButtons[i][p].setId("buttons");
            }

         }

 	 }
	  public  void showLines(String fileName) {
		 try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            int j=0;
            int t=0;
            for (int i = 0; i < 21; i++) { 
            		questions[i] = new ArrayList<String>(); 
            		answers[i] = new ArrayList<String>(); 
            } 
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(",");
                tokens.add(details[0]);
         		for(int i=1;i<details.length;i++){
         			if(i<=5){	
         				questions[j].add(details[i]);
         			}
         			if(i>=6){
         				answers[t].add(details[i]);
         			}
         		}

         		j = j +1;
         		t = t+1;

            }

        } catch (FileNotFoundException e) {         
            e.printStackTrace();
        }
       }


	public void categoryAcclines(){
		Button[] catButton = new Button[5];

		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=11; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            list.get(i);
        }

		firstCat = list.get(0);
		catButton[0] = new Button(tokens.get(list.get(0)));
		

		secondCat = list.get(1);
		catButton[1] = new Button(tokens.get(list.get(1)));
		

		thirdCat = list.get(2);
		catButton[2] = new Button(tokens.get(list.get(2)));
		

		fourthCat = list.get(3);
		catButton[3] = new Button(tokens.get(list.get(3)));
		
		
		fifthCat = list.get(4);
		catButton[4] = new Button(tokens.get(list.get(4)));
		
		for(int i = 0; i<catButton.length; i++){
			numbers.add(catButton[i],i+1,0);
			catButton[i].setId("categories");
		}
 	}
 	private void handleButtonAction(ActionEvent event){
 		mainCounter = mainCounter + 1;
 		for(int i = 1; i<= 5; i++){
          for( int p=1; p<= 5; p++){
          	if(i==1 && p>=1){
		 		if(event.getSource() == numButtons[i][p]){
		 			if(p==1){
		 				action(numButtons[i][p], firstCat,one, 100);
		 				one = one -1;
		 			}
		 			if(p==2){
		 				action(numButtons[i][p], secondCat,two,100);
		 				two = two -1;
		 			}
		 			if(p==3){
		 				action(numButtons[i][p], thirdCat,three,100);
		 				three = three -1;
		 			}
		 			if(p==4){
		 				action(numButtons[i][p], fourthCat,four,100);
		 				four = four -1;
		 			}
		 			if(p==5){
		 				action(numButtons[i][p], fifthCat,five,100);
		 				five = five -1;
		 			}
		 		}	
			}
			if(i==2 && p>=1){
		 		if(event.getSource() == numButtons[i][p]){
		 			if(p==1){
		 				action(numButtons[i][p], firstCat,one, 200);
		 				one = one -1;
		 			}
		 			if(p==2){
		 				action(numButtons[i][p], secondCat,two,200);
		 				two = two -1;
		 			}
		 			if(p==3){
		 				action(numButtons[i][p], thirdCat,three,200);
		 				three = three -1;
		 			}
		 			if(p==4){
		 				action(numButtons[i][p], fourthCat,four,200);
		 				four = four -1;
		 			}
		 			if(p==5){
		 				action(numButtons[i][p], fifthCat,five,200);
		 				five = five -1;
		 			}
		 		}	
			}
			if(i==3 && p>=1){
		 		if(event.getSource() == numButtons[i][p]){
		 			if(p==1){
		 				action(numButtons[i][p], firstCat,one, 300);
		 				one = one -1;
		 			}
		 			if(p==2){
		 				action(numButtons[i][p], secondCat,two,300);
		 				two = two -1;
		 			}
		 			if(p==3){
		 				action(numButtons[i][p], thirdCat,three,300);
		 				three = three -1;
		 			}
		 			if(p==4){
		 				action(numButtons[i][p], fourthCat,four,300);
		 				four = four -1;
		 			}
		 			if(p==5){
		 				action(numButtons[i][p], fifthCat,five,300);
		 				five = five -1;
		 			}
		 		}	
			}
			if(i==4 && p>=1){
		 		if(event.getSource() == numButtons[i][p]){
		 			if(p==1){
		 				action(numButtons[i][p], firstCat,one, 400);
		 				one = one -1;
		 			}
		 			if(p==2){
		 				action(numButtons[i][p], secondCat,two,400);
		 				two = two -1;
		 			}
		 			if(p==3){
		 				action(numButtons[i][p], thirdCat,three,400);
		 				three = three -1;
		 			}
		 			if(p==4){
		 				action(numButtons[i][p], fourthCat,four,400);
		 				four = four -1;
		 			}
		 			if(p==5){
		 				action(numButtons[i][p], fifthCat,five,400);
		 				five = five -1;
		 			}
		 		}	
			}
			if(i==5 && p>=1){
		 		if(event.getSource() == numButtons[i][p]){
		 			if(p==1){
		 				action(numButtons[i][p], firstCat,one, 500);
		 				one = one -1;
		 			}
		 			if(p==2){
		 				action(numButtons[i][p], secondCat,two,500);
		 				two = two -1;
		 			}
		 			if(p==3){
		 				action(numButtons[i][p], thirdCat,three,500);
		 				three = three -1;
		 			}
		 			if(p==4){
		 				action(numButtons[i][p], fourthCat,four,500);
		 				four = four -1;
		 			}
		 			if(p==5){
		 				action(numButtons[i][p], fifthCat,five,500);
		 				five = five -1;
		 				}
		 			}	
				}
			}

		}
		if(mainCounter == 25){
				JOptionPane.showMessageDialog(null, "You won! You money will be tranfered to you in 1 decade.", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
				try{
					Thread.sleep(2000);
				}catch(Exception e){}
				System.exit(0);
		}


 	}
 	private void action(Button x, int number, int rando,int points){
 		Random rand = new Random();

 		if(rando == 0){
 			temp = 0;
 		}
 		if(rando>=1){
 			temp = rand.nextInt(rando);
 		}
 
		TextInputDialog dialog = new TextInputDialog(); // new output display
		dialog.setTitle("Question");
		dialog.setHeaderText(questions[number].get(temp)); 
		Optional<String> result = dialog.showAndWait(); // maiking a new diaglog 
		String entered = "";
		if (result.isPresent()) { // taking the input and storing it 
		    entered = result.get();
		}
		if (entered.toUpperCase().contains(answers[number].get(temp).toUpperCase())){ // checking userinput and answer
			score = score + points;
		}
		update(score); // updating score 

		answers[number].remove(temp);
		questions[number].remove(temp); // removing elements from arrays to avoid duplication 
		fadeAndDisable(x); // fading the button and disabling it
		
 	}
 	private void fadeAndDisable(Button x){
 		FadeTransition trans = new FadeTransition(Duration.seconds(1), x);
        trans.setFromValue(1.0);
        trans.setToValue(.20);
        trans.setCycleCount(1);
        trans.play();
        x.setDisable(true);		
        
 	}
 	private void update(int score){
 		String conv = Integer.toString(score);
 		scoreboard.setText(conv);
 	}

}
