package mainApp;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuItems extends Pane implements Runnable {

	private String name; //name of menu item
	private Text text; 	 //text of menu item
	private Effect highlight = new DropShadow(7, Color.WHITE);
	private Effect blur = new BoxBlur(0, 0, 0);

	public MenuItems(String name) {
		this.name = name;
	}

	public void run() {
		
		// creates a rectangular button type background for each menu item
		Polygon menuTextBackground = new Polygon(0, 0, 200, 0, 200, 15, 200, 30, 0, 30);

		// sets the color properties of the item background
		menuTextBackground.setStroke(Color.color(1, 1, 1, 0.75));
		menuTextBackground.setEffect(new GaussianBlur(5));

		// sets color properties when a menu item is pressed
		menuTextBackground.fillProperty().bind(Bindings.when(pressedProperty()).then(Color.color(0, 0, 0, 0.7))
				.otherwise(Color.color(0, 0, 0, 0.3)));

		text = new Text(name);
		text.setTranslateX(10); //centers the text inside the rectangular background
		text.setTranslateY(20);

		Font font = Font.loadFont(getClass().getResourceAsStream("/GamePlayed.otf"), 20);
		text.setFont(font);
		text.setFill(Color.BLACK);

		//sets a glow effect on text when mouse is hovering over it
		text.effectProperty().bind(Bindings.when(hoverProperty()).then(highlight).otherwise(blur));

		getChildren().addAll(menuTextBackground, text);
	}

	public void run(Runnable action) {
		action.run();
	}

	public void setOnAction(Runnable action) {
		setOnMouseClicked(e -> run(action));
	}
}