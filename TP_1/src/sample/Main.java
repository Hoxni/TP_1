package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.shape1d.Line;
import sample.shape1d.Ray;
import sample.shape1d.Segment;
import sample.shape2d.*;

import static sample.Utils.*;

public class Main extends Application{
    private int mode = 2;
    private int clickCount = 0;
    private double offset = 50;
    private GraphicsContext gc;
    private Shape shape;
    private Dialog<Integer> dialog;

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);

        root.setCenter(canvas);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(createMenu(), createButtons());
        root.setTop(vBox);

        createDialog();

        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, createHandler());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private HBox createMenu(){
        MenuBar menuBar = new MenuBar();
        Menu color = new Menu("Color");
        MenuItem lineColor = new MenuItem("Line color...");
        MenuItem fillColor = new MenuItem("Fill color...");
        menuBar.getMenus().add(color);
        color.getItems().addAll(lineColor, fillColor);

        lineColor.setOnAction(event -> {
            Dialog cl = new Dialog<>();
            cl.setTitle("Line color");
            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            cl.getDialogPane().getButtonTypes().add(ok);
            ColorPicker c = new ColorPicker();
            cl.getDialogPane().setContent(c);
            c.setOnAction(event1 -> gc.setStroke(c.getValue()));
            cl.show();
        });

        fillColor.setOnAction(event -> {
            Dialog cf = new Dialog<>();
            cf.setTitle("Fill color");
            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            cf.getDialogPane().getButtonTypes().add(ok);
            ColorPicker c = new ColorPicker();
            cf.getDialogPane().setContent(c);
            c.setOnAction(event1 -> gc.setFill(c.getValue()));
            cf.show();
        });

        HBox hBox = new HBox();
        hBox.getChildren().add(menuBar);
        return hBox;
    }

    private HBox createButtons(){
        Button line = new Button("Line");
        line.setOnAction(event -> {
            mode = LINE;
            clickCount = 0;
        });
        Button ray = new Button("Ray");
        ray.setOnAction(event -> {
            mode = RAY;
            clickCount = 0;
        });
        Button segment = new Button("Segment");
        segment.setOnAction(event -> {
            mode = SEGMENT;
            clickCount = 0;
        });

        Button circle = new Button("Circle");
        circle.setOnAction(event -> {
            mode = CIRCLE;
            clickCount = 0;
        });
        Button ellipse = new Button("Ellipse");
        ellipse.setOnAction(event -> {
            mode = ELLIPSE;
            clickCount = 0;
        });

        Button polygon = new Button("Polygon");
        polygon.setOnAction(event -> {
            mode = POLYGON;
            clickCount = 0;
        });
        Button rect = new Button("Rect");
        rect.setOnAction(event -> {
            mode = RECT;
            clickCount = 0;
        });
        Button symmetricPolygon = new Button("Symmetric Polygon");
        symmetricPolygon.setOnAction(event -> {
            mode = SYMMETRIC_POLYGON;
            clickCount = 0;
            dialog.showAndWait();
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(line, ray, segment, circle, ellipse, polygon, rect, symmetricPolygon);

        return hBox;
    }

    private void createDialog(){
        dialog = new Dialog<>();
        dialog.setTitle("Number of angles");
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(ok);
        Node node = dialog.getDialogPane().lookupButton(ok);
        node.setDisable(true);
        TextField num = new TextField("Num of angles");
        dialog.getDialogPane().setContent(num);
        num.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.matches("[3-9]|(\\d\\d+)")){
                node.setDisable(false);
            } else {
                node.setDisable(true);
            }
        });
        dialog.setResultConverter(b->{
            if(b == ok){
                return Integer.parseInt(num.getText());
            }
            return null;
        });
    }

    private EventHandler<MouseEvent> createHandler(){
        return event -> {
            switch(mode){
                case SEGMENT:
                    if(clickCount == 0){
                        shape = new Segment(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Segment) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case RAY:
                    if(clickCount == 0){
                        shape = new Ray(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Ray) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case LINE:
                    if(clickCount == 0){
                        shape = new Line(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Line) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case CIRCLE:
                    if(clickCount == 0){
                        shape = new Circle(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Circle) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case ELLIPSE:
                    if(clickCount == 0){
                        shape = new Ellipse(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Ellipse) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case POLYGON:
                    if(shape instanceof Polygon && ((Polygon) shape).isFinished()) shape = null;
                    if(clickCount == 0 && !(shape instanceof Polygon)){
                        shape = new Polygon(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                        clickCount = -1;
                    } else {
                        ((Polygon) shape).addPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    if(event.getClickCount() == 2){
                        ((Polygon) shape).setFinished();
                    }
                    clickCount += 2;
                    break;

                case RECT:
                    if(clickCount == 0){
                        shape = new Rect(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    } else {
                        ((Rect) shape).setPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                    }
                    clickCount++;
                    break;

                case SYMMETRIC_POLYGON:
                    if(clickCount == 0){
                        if(dialog.getResult() == null) break;
                        shape = new SymmetricPolygon(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset), dialog.getResult());
                    } else {
                        SymmetricPolygon s = (SymmetricPolygon) shape;
                        s.addPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), offset));
                        s.setFinished();
                    }
                    clickCount++;
                    break;
            }

            if(clickCount > 1) {
                shape.draw(gc);
                clickCount = 0;
            }
        };
    }

    public static void main(String[] args){
        launch(args);
    }
}
