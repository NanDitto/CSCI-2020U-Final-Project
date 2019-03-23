package mainApp;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.List;

public class MenuDriver extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //sets the width and height of the primary Stage
    private int Width = 900;
    private int Height = 590;

    //Create a pane, a vbox for the menu, and a line
    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    //create menu item names and their actions once clicked
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {
                window2.setResizable(false);
                Scene3 hi = new Scene3();
                hi.start(window2);
                window2.setWidth(900);
                window2.setWidth(900);
            }),

            new Pair<String, Runnable>("Instructions", () -> {}),
            new Pair<String, Runnable>("Scores", () -> {}),
            new Pair<String, Runnable>("Credits", () -> {}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    //Sets the background, adds the title,
    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = Width / 2 - 100;
        double lineY = Height / 4;

        addLine(lineX - 5, lineY );
        addMenu(lineX , lineY);
        startAnimation();
        return root;
    }

    //creates and adds the background
    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/temp.jpg").toExternalForm()));
        imageView.setFitWidth(Width);
        imageView.setFitHeight(Height);
        root.getChildren().addAll(imageView);
    }

    //creates and adds the title
    private void addTitle() {
        MenuTitle title = new MenuTitle("WELCOME TO OUR ARCADE");
        title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Height / 11);

        root.getChildren().add(title);
    }

    //adds the animated line for the menu items
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 220);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    //Begins animating the menu objects
    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    //adds the menu items
    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItems item = new MenuItems(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
