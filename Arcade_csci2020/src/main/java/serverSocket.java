package mainApp;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class serverSocket extends Application {
  public TextArea ta = new TextArea();
  public Socket socket;
  DataInputStream inputFromClient = null;
  DataOutputStream outputToClient = null;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }


  public void action(String levels){
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(4444);
        Platform.runLater(() ->
          ta.appendText("Server started at " + new Date() + '\n'));
  
        // Listen for a connection request
        Socket socket = serverSocket.accept();

  
        // Create data input and output streams
        inputFromClient = new DataInputStream(socket.getInputStream());
        outputToClient = new DataOutputStream(socket.getOutputStream());
        while (true) {
          // Receive radius from the client
          String incoming = inputFromClient.readUTF();
  
          // Send area back to the client
          outputToClient.writeUTF(levels);
  
          Platform.runLater(() -> {
            ta.appendText("Player is on: " + incoming + '\n');
            ta.appendText("You are on: " + levels + '\n'); 
          });
        }
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }).start();
   }
  public static void main(String[] args) {
    launch(args);
  }
}
