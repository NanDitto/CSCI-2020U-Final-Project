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

    private String name;
    private Text text;
    private Effect shadow = new DropShadow(5, Color.WHITE);
    private Effect blur = new BoxBlur(0, 0, 0);

    //creates a rectangular button type background for each menu item
    public MenuItems(String name) {
        this.name = name;
    }

    public void run(){
        Polygon menuTextBackground = new Polygon(
            0, 0,
            200, 0,
            200, 15,
            200, 30,
            0, 30
        );

        //sets the color properties of the item background
        menuTextBackground.setStroke(Color.color(1, 1, 1, 0.75));
        menuTextBackground.setEffect(new GaussianBlur(5));

        //sets color properties when a menu item is pressed
        menuTextBackground.fillProperty().bind(
                Bindings.when(pressedProperty())
                    .then(Color.color(0, 0, 0, 0.75))
                            .otherwise(Color.color(0, 0, 0, 0.25))
        );

        text = new Text(name);
        text.setTranslateX(10);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(MenuDriver.class.getResource("res/GamePlayed.otf").toExternalForm(), 20));
        text.setFill(Color.BLACK);

        text.effectProperty().bind(
                Bindings.when(hoverProperty()).then(shadow).otherwise(blur)
        );

        getChildren().addAll(menuTextBackground, text);
    }

    public void run(Runnable action){
        action.run();
    }

    public void setOnAction(Runnable action) {
          setOnMouseClicked(e -> run(action));
    }
}
