package mainApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
  A window that displays credits to the user
*/
public class Credits extends Application {

    private int Width = 900;
    private int Height = 590;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        VBox box = new VBox(60);

        Title title = new Title("Credits");
        title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Height / 11);

        pane.getChildren().add(title);

        Text[] text = {new Text("Hanan Amer"), new Text("Nandor Gallo"), new Text("Joseph Fanous"),
                new Text("Garry Masaun")};

        for(int i=0; i<4; i++){
            text[i].setFill(Color.WHITE);
            Font myFont = Font.loadFont(getClass().getResourceAsStream("/HyperspaceBold.otf"), 30);
            text[i].setFont(myFont);
            box.getChildren().addAll(text[i]);
        }
        Button button = new Button("Back");

        button.setOnAction(actionEvent -> primaryStage.close());
        button.setTranslateX(80);
        box.getChildren().addAll(button);


        box.setTranslateX(Width/3 +30);
        box.setTranslateY(Height/5);

        pane.getChildren().addAll(box);

        pane.setStyle("-fx-background-color: black");
        primaryStage.setTitle("Credits");
        primaryStage.setScene(new Scene(pane, Width, Height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
