package main.java;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.lang.System;

public class client extends Application {
	// IO streams
	DataOutputStream toServer = null;
	DataInputStream fromServer = null;
	public TextArea ta = new TextArea();
	Scene3 content = new Scene3();
	Socket socket;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public void action(String levels) {
		new Thread(() -> {
			try {
				Socket socket = new Socket("localhost", 4444);
				fromServer = new DataInputStream(socket.getInputStream());
				toServer = new DataOutputStream(socket.getOutputStream());
				toServer.writeUTF(levels);
				toServer.flush();

				String fromPlayer = fromServer.readUTF();

				// Display to the text area
				Platform.runLater(() -> {
					ta.appendText("You are on " + levels + "\n");
					ta.appendText("Player is on" + fromPlayer + '\n');
				});
			} catch (IOException ex) {
				ta.appendText(ex.toString() + '\n');
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}