package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static sample.Utils.*;

public class App extends Application{

    @Override
    public void start(Stage primaryStage){
        final BorderPane root = new BorderPane();
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.setCenter(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);

        final VBox vBox = new VBox();
        vBox.getChildren().addAll(Initializer.createMenu(gc), Initializer.createButtons());
        root.setTop(vBox);

        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, Initializer.createHandler(gc));
        primaryStage.setTitle("PT");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
