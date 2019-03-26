package mainApp;

import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Title extends Pane {
	private Text text;

	//puts title into a char array
	public Title(String name) {
		String Name = "";
		for (char c : name.toCharArray()) { Name += c; }

		//set title properties
		text = new Text(Name);
		Font font = Font.loadFont(getClass().getResourceAsStream("/atarifull.ttf"), 36); 
		text.setFont(font);
		text.setFill(Color.WHITE);
		text.setEffect(new DropShadow(25, Color.BLACK));

		getChildren().addAll(text);
	}

	//gets the width of the title text
	public double getTitleWidth() {
		return text.getLayoutBounds().getWidth();
	}
}
