package mainApp;
import javafx.event.ActionEvent;
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
import javax.swing.JOptionPane.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javafx.scene.control.Dialog.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Scene1 extends Application {
	public Stage CurrentWindow;
	VBox buttons = new VBox(10);
	StackPane pane = new StackPane(); // main pane
	Button login = new Button("Login"); // buttons for functionaliy
	Button register = new Button("Register");
	Button imports = new Button("Import File");
	static String cwd = System.getProperty("user.dir"); // used to read the user current directory
	static String ret = "";
	static String chck ="";

	public String currentFilename = (cwd + ret + "src" + ret + "main" + ret + "java" + ret +  "progress.csv"); // intial file name

	public void start(Stage primaryStage) {
		System.out.print(currentFilename);
		CurrentWindow = primaryStage;
		/*
		Media media = new Media(finalsDes + "music.mp3");
       	MediaPlayer player = new MediaPlayer(media); // used for playig music at the background
       	player.play();
       	*/
       	ImageView home = new ImageView("q.gif"); // main background image
		pane.getChildren().add(home);
		login.setId("button");
		register.setId("button");
		imports.setId("button");

		buttons.getChildren().addAll(login,register,imports); // adding all the objects to the pane
		pane.getChildren().addAll(buttons);
		buttons.setAlignment(Pos.CENTER);

		fadeTran(1,0.8,home,2); // fade transiton of picture
        pathTran(100,20,950,600,login); // path transition of the buttons
        pathTran(100,20,-950,600,register);
        pathTran(100,20,-450,600,imports);

        login.setOnAction(this::handleButtonAction);
        register.setOnAction(this::handleButtonAction);
        imports.setOnAction(this::handleButtonAction);

        Scene scene = new Scene(pane,900,540);
        scene.getStylesheets().add("main.css");  // adding styles using css
        CurrentWindow.setResizable(false);
		CurrentWindow.setTitle("Welcome!"); // Set the stage title
		CurrentWindow.setScene(scene); // Place the scene in the stage
		CurrentWindow.show(); // Display the stage
	}
	public static void main(String[] args) {
		ret = File.separator; // to get file seperator according to the os
    	launch(args);
  	}
  	public void pathTran(int x, int y,int moveX, int moveY,Button butto){
  		Path path = new Path();
	    //Moving to the starting point
	    MoveTo moveTo = new MoveTo(moveX, moveY);
      	//Creating 1st line
      	LineTo line1 = new LineTo(x, y);
      	//Adding all the elements to the path
      	path.getElements().addAll(moveTo, line1);
      	//Creating the path transition
     	PathTransition pathTransition = new PathTransition();
      	//Setting the duration of the transition
      	pathTransition.setDuration(Duration.seconds(2));
      	//Setting the node for the transition
      	pathTransition.setNode(butto);
      	//Setting the path for the transition
      	pathTransition.setPath(path);
      	//Setting the cycle count for the transition
      	pathTransition.setCycleCount(1);
      	//Setting auto reverse value to true
      	pathTransition.setAutoReverse(false);
      	//Playing the animation
      	pathTransition.play();
  	}
  	public void fadeTran(double from, double to, ImageView objs, int time){

		FadeTransition ft = new FadeTransition(Duration.seconds(time), objs);
        ft.setFromValue(from); // go from the number
        ft.setToValue(to); // go to the number
        ft.setCycleCount(1); // do the fade just once
        ft.play();
  	}
  	public void handleButtonAction(ActionEvent event){
  		if(event.getSource() == login){
  			try {
  				File f = new File(currentFilename);
	            Scanner sc = new Scanner(f);
	            JLabel label_login = new JLabel("Username:"); // username textfield
				JTextField login = new JTextField();

				JLabel label_password = new JLabel("Password:"); // password textfield
				JPasswordField password = new JPasswordField();
				Object[] array = { label_login,  login, label_password, password };
				int res = JOptionPane.showConfirmDialog(null, array, "Login",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE); // Dialog with all the information

				if (res == JOptionPane.OK_OPTION) { // do the following when ok is pressed
					boolean opt = false;
		            while(sc.hasNextLine()){ // read the file until its not the end
		                String line = sc.nextLine();
		                String[] details = line.split(",");
		                String temp1 = login.getText().trim();
		                String temp2 = new String(password.getPassword());
						if(details[0].equals(temp1) && details[1].equals(temp2)){ // if the username and password are same, then continue
							ImageView image = new ImageView("cm.gif");
						    pane.getChildren().add(image);
						    fadeTran(1,0,image,5);
						    opt = true;
						    
						    MenuDriver hi = new MenuDriver();
						    hi.start(CurrentWindow);
						   	
						}
					}
					if(opt == false){
						ImageView image = new ImageView("x.gif"); // if information is incorrect, then try again
					    pane.getChildren().add(image);
					    fadeTran(1,0,image,2);
					   	image.setDisable(true);
					}
				}
			} catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        if(event.getSource() == register){
        	try{
        		BufferedWriter out = new BufferedWriter(new FileWriter(currentFilename,true)); // reading the file

	        	JLabel label_login = new JLabel("Username:");
				JTextField login = new JTextField();

				JLabel label_password = new JLabel("Password:");
				JPasswordField password = new JPasswordField();

				JLabel email_login = new JLabel("Email:");
				JTextField email = new JTextField();

				JLabel age_login = new JLabel("Age:");
				JTextField age = new JTextField();

				Object[] array = { label_login,  login, label_password, password,email_login, email,age_login,age};
				int res = JOptionPane.showConfirmDialog(null, array, "Register",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				String temp1,temp2,temp3,temp4;
				temp1 = login.getText().trim();
	            temp2 = new String(password.getPassword());
	            temp3 = email.getText().trim();
	            temp4 = age.getText().trim();
				if (res == JOptionPane.OK_OPTION) {
					out.write(temp1+","+temp2+","+temp3+","+temp4); // appeding the information collected from the user into the file
					out.newLine();
				}
				out.close();
        	}catch (IOException e){
        		e.printStackTrace();
        	}

        }
        if(event.getSource() == imports){
        	FileChooser fileChooser = new FileChooser(); // new filechoser
	        fileChooser.setInitialDirectory(new File("."));
	        currentFilename = fileChooser.showOpenDialog(CurrentWindow).getAbsolutePath(); // overwriting filename to the user designated file destination
        }
	}
}
