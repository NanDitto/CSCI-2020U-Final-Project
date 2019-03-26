package mainApp;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.util.Duration;
import javafx.util.Pair;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;

//Citation: Baimagambetov, Almas (Published 2017)
//[Source code] : small snippets from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/civ6menu/Civ6MenuApp.java


public class MainMenu extends Application {

	public Stage window2;
	public String temp;
	public String file;

	public void settemp(String temp, String file) {
		this.temp = temp;
		this.file = file;
	}

	// sets the width and height of the primary Stage
	private int Width = 900;
	private int Height = 590;

	// Create a pane, a vbox for the menu, and a line
	private VBox menu = new VBox(5);
	private Pane pane = new Pane();
	private Line line;

	@Override
	public void start(Stage primaryStage) {

		window2 = primaryStage;

		addBackground();
		addTitle();

		double lineX = Width / 2 - 100;
		double lineY = Height / 4;

		addLine(lineX - 6, lineY - 5);
		addMenuItems(lineX, lineY - 22);
		animateMenuItems();

		Scene scene = new Scene(pane);
		window2.setTitle("Arcade Menu");
		window2.setScene(scene);
		window2.show();

	}
	
	//creates and adds the menu items
	private void addMenuItems(double x, double y) {
		menu.setTranslateX(x);
		menu.setTranslateY(y);

		for (Pair<String, Runnable> data : menuData) {
			
			MenuItems item = new MenuItems(data.getKey());
			item.setOnAction(data.getValue());
			item.run();
			Rectangle clip = new Rectangle(250, 40); //animated rectangle
			item.setTranslateX(-250);
			item.setClip(clip); //rectangle is binded to menu item
			clip.translateXProperty().bind(item.translateXProperty().negate());
			menu.getChildren().add(item);
		}

		pane.getChildren().add(menu);
	}

	// create menu item names and their actions once clicked
	private List<Pair<String, Runnable>> menuData = Arrays.asList(
	
			new Pair<String, Runnable>("Play", () -> { //Directs to arcade games screen
				Scene3 hi = new Scene3();
				hi.settemp(temp, file);
				hi.start(window2);
				window2.setWidth(900);
				window2.setHeight(920);
			}),

			new Pair<String, Runnable>("Instructions", () -> { //directs to instructions page(how to play)
				Instructions instructions = new Instructions();
				Stage stage = new Stage();
				try {
					instructions.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}), new Pair<String, Runnable>("Scores", () -> { //directs to scores screen(shows progress)
				Scores scores = new Scores();
				Stage stage = new Stage();
				try {
					scores.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}), new Pair<String, Runnable>("Credits", () -> { //shows credits (us)
				Credits credits = new Credits();
				Stage stage = new Stage();
				try {
					credits.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}), new Pair<String, Runnable>("Exit to Desktop", Platform::exit));

	public void changeScreenButton(ActionEvent event) throws IOException {
		Parent creditsParent = FXMLLoader.load(getClass().getResource("sample.FXML"));
		Scene creditsScene = new Scene(creditsParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

	}
	
	// Begins animating the menu objects
	private void animateMenuItems() {
		// Animate the side bar
		ScaleTransition sideBar = new ScaleTransition(Duration.seconds(1), line);
		sideBar.setToY(1.15);

		// after line has finished animating, animate the menu items in order
		sideBar.setOnFinished((ActionEvent e) -> {
			for (int i = 0; i < menu.getChildren().size(); i++) {
				Node n = menu.getChildren().get(i);
				TranslateTransition items = new TranslateTransition(Duration.seconds(1 + i * 0.1), n);
				items.setToX(0);
				items.setOnFinished(e1 -> n.setClip(null));
				items.play();
			}
		});

		sideBar.play();
	}

	
		// creates and adds the title
	private void addTitle() {
		Title title = new Title("WELCOME TO OUR ARCADE");
		title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
		title.setTranslateY(Height / 11);
		pane.getChildren().add(title);
	}


	// adds the animated line for the menu items
	private void addLine(double x, double y) {
		line = new Line(x, y, x, y + 220);
		line.setEffect(new DropShadow(3, Color.BLACK));
		line.setStroke(Color.color(1, 1, 1, 0.7));
		line.setScaleY(0);
		line.setStrokeWidth(3);
		pane.getChildren().add(line);
	}
	
		
	// creates and adds the background
	private void addBackground() {
		ImageView imageView = new ImageView(new Image("temp.jpg"));
		imageView.setFitHeight(Height);
		imageView.setFitWidth(Width);
		pane.getChildren().add(imageView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
