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

public class Credits extends Application {

    private int Width = 900;
    private int Height = 590;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        VBox box = new VBox(5);

        MenuTitle title = new MenuTitle("Credits");
        title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Height / 11);

        pane.getChildren().add(title);

        Text text = new Text("Hanan Amer");
        text.setX(Width/2 - text.getLayoutBounds().getWidth() - 24);
        text.setY(100);
        text.setFill(Color.WHITE);
        text.setFont(Font.loadFont(MenuDriver.class.getResource("/src/main/resources/HyperspaceBold.otf").toExternalForm(), 30));

        Text text1 = new Text("Nandor Gallo");
        text1.setX(Width/2 - text1.getLayoutBounds().getWidth()- 24);
        text1.setY(200);
        text1.setFill(Color.WHITE);
        text1.setFont(Font.loadFont(MenuDriver.class.getResource("/src/main/resources/HyperspaceBold.otf").toExternalForm(), 30));

        Text text2 = new Text("Joseph Fanous");
        text2.setX(Width/2 - text2.getLayoutBounds().getWidth()- 24);
        text2.setY(300);
        text2.setFill(Color.WHITE);
        text2.setFont(Font.loadFont(MenuDriver.class.getResource("/src/main/resources/HyperspaceBold.otf").toExternalForm(), 30));

        Text text3 = new Text("Garry Masaun");
        text3.setX(Width/2 - text3.getLayoutBounds().getWidth()- 24);
        text3.setY(400);
        text3.setFill(Color.WHITE);
        text3.setFont(Font.loadFont(MenuDriver.class.getResource("/src/main/resources/HyperspaceBold.otf").toExternalForm(), 30));

        Button button = new Button("Back");

        button.setTranslateX(Width/2 - 30);
        button.setTranslateY(500);

        button.setOnAction(actionEvent -> primaryStage.close());

        //box.getChildren().addAll(label, label1, label2, label3, label4, button);
        pane.getChildren().addAll(text, text1, text2, text3, button);

        pane.setStyle("-fx-background-color: black");
        primaryStage.setTitle("Credits");
        primaryStage.setScene(new Scene(pane, Width, Height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

