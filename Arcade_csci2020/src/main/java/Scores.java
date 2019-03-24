package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scores extends Application {

    private int Width = 900;
    private int Height = 590;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        VBox box = new VBox(10);

        MenuTitle title = new MenuTitle("Scores");
        title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Height / 11);

        pane.getChildren().add(title);

        int dataSize = 8; //number of players currently registered
        int[] levels = new int[dataSize];
        String[] users = new String[dataSize];
        Text[] text = new Text[dataSize];

        for(int i = 0; i<dataSize;i++){
            users[i] = "user";
            levels[i] = i; //store levels in an array
        }

        for(int i = 0; i<dataSize;i++){
            text[i] = new Text(users[i] + ": " + levels[i]);
        }


        for(int i=0; i<dataSize; i++){
            text[i].setFill(Color.WHITE);
            text[i].setFont(Font.loadFont(MenuDriver.class.getResource("res/HyperspaceBold.otf").toExternalForm(), 15));
            box.getChildren().addAll(text[i]);
        }

        Button button = new Button("Back");

        button.setOnAction(actionEvent -> primaryStage.close());
        button.setTranslateX(80);
        box.getChildren().addAll(button);


        box.setTranslateX(Width/3 +50);
        box.setTranslateY(Height/5 - 20);

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