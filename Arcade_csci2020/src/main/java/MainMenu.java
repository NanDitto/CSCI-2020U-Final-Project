package mainApp;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends Application {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 590;
    public Stage window2;
    public String temp;
    public String file;
    public void settemp(String temp,String file){
        this.temp = temp;
        this.file = file;
    }
    //sets the width and height of the primary Stage
    private int Width = 900;
    private int Height = 590;

    //Create a pane, a vbox for the menu, and a line
    private Pane root = new Pane();
    private VBox menu = new VBox(5);
    private Line line;
    @Override
    public void start(Stage primaryStage) {
        window2 = primaryStage;
        Scene scene = new Scene(createContent());
        window2.setTitle("Arcade Menu");
        window2.setScene(scene);
        window2.show();

    }

    //create menu item names and their actions once clicked
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {
                Scene3 hi = new Scene3();
                hi.settemp(temp,file);
                hi.start(window2);
                window2.setWidth(900);
                window2.setHeight(920);
            }),

            new Pair<String, Runnable>("Instructions", () -> {
                Instructions instructions = new Instructions();
                Stage stage = new Stage();
                try {
                    instructions.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),
            new Pair<String, Runnable>("Scores", () -> {
                Scores scores = new Scores();
                Stage stage = new Stage();
                try {
                    scores.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),
            new Pair<String, Runnable>("Credits", () -> {
                Credits credits = new Credits();
                Stage stage = new Stage();
                try {
                    credits.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    public void changeScreenButton(ActionEvent event) throws IOException {
        Parent creditsParent = FXMLLoader.load(getClass().getResource("sample.FXML"));
        Scene creditsScene = new Scene (creditsParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    }

    //Sets the background, adds the title,
    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = Width / 2 - 100;
        double lineY = Height / 4;

        addLine(lineX - 6, lineY -5);
        addMenuItems(lineX , lineY-22);
        animateMenuItems();
        return root;
    }

    //creates and adds the background
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("temp.jpg"));
        imageView.setFitWidth(Width);
        imageView.setFitHeight(Height);
        root.getChildren().addAll(imageView);
    }

    //creates and adds the title
    private void addTitle() {
        Title title = new Title("WELCOME TO OUR ARCADE");
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
    private void animateMenuItems() {
        //Animate the side bar
        ScaleTransition sideBar = new ScaleTransition(Duration.seconds(1), line);
        sideBar.setToY(1.15);

        //after line has finished animating, animate the menu items in order
        sideBar.setOnFinished((ActionEvent e) -> {
            for (int i = 0; i < menu.getChildren().size(); i++) {
                Node n = menu.getChildren().get(i);
                TranslateTransition items = new TranslateTransition(Duration.seconds(1 + i * 0.1), n);
                items.setToX(0);
                items.setOnFinished(e2 -> n.setClip(null));
                items.play();
            }
        });

        sideBar.play();
    }

    //adds the menu items
    private void addMenuItems(double x, double y) {
        menu.setTranslateX(x);
        menu.setTranslateY(y);

        for (Pair<String, Runnable> data : menuData) {
            MenuItems item = new MenuItems(data.getKey());
            item.setOnAction(data.getValue());
            item.run();

            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menu.getChildren().addAll(item);
        }

        root.getChildren().add(menu);
    }


    public static void main(String[] args) {
        launch(args);
    }

}