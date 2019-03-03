package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sample.shape1d.Line;
import sample.shape1d.Ray;
import sample.shape1d.Segment;
import sample.shape2d.*;

import static sample.Utils.*;
import static sample.Utils.ShapeType.*;

public class Initializer{
    private static Shape shape;
    private static ShapeType mode = LINE;
    private static int clickCount = 0;
    private static final Dialog<Integer> numOfAnglesDialog = createNumberOfAnglesDialog();

    public static HBox createButtons(){
        final Button line = new Button("Line");
        line.setOnAction(event -> {
            mode = LINE;
            clickCount = 0;
        });
        final Button ray = new Button("Ray");
        ray.setOnAction(event -> {
            mode = RAY;
            clickCount = 0;
        });
        final Button segment = new Button("Segment");
        segment.setOnAction(event -> {
            mode = SEGMENT;
            clickCount = 0;
        });

        final Button circle = new Button("Circle");
        circle.setOnAction(event -> {
            mode = CIRCLE;
            clickCount = 0;
        });
        final Button ellipse = new Button("Ellipse");
        ellipse.setOnAction(event -> {
            mode = ELLIPSE;
            clickCount = 0;
        });

        final Button polygon = new Button("Polygon");
        polygon.setOnAction(event -> {
            mode = POLYGON;
            clickCount = 0;
        });

        final Button rect = new Button("Rect");
        rect.setOnAction(event -> {
            mode = RECT;
            clickCount = 0;
        });

        final Button symmetricPolygon = new Button("Symmetric Polygon");
        symmetricPolygon.setOnAction(event -> {
            mode = SYMMETRIC_POLYGON;
            clickCount = 0;
            numOfAnglesDialog.showAndWait();
        });

        final HBox hBox = new HBox();
        hBox.getChildren().addAll(line, ray, segment, circle, ellipse, polygon, rect, symmetricPolygon);

        return hBox;
    }

    public static HBox createMenu(final GraphicsContext gc){
        final MenuBar menuBar = new MenuBar();
        final Menu color = new Menu("Color");
        final MenuItem lineColor = new MenuItem("Line color...");
        final MenuItem fillColor = new MenuItem("Fill color...");
        menuBar.getMenus().add(color);
        color.getItems().addAll(lineColor, fillColor);

        final Menu clear = new Menu("Function");
        final MenuItem clearRect = new MenuItem("Clear");
        clear.getItems().add(clearRect);
        menuBar.getMenus().add(clear);
        clearRect.setOnAction(event -> {
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    Color buff = (Color) gc.getStroke();
                    gc.strokeRect(0, 0, WIDTH, HEIGHT);
                    gc.setStroke(buff);
                }
        );

        final Dialog<Color> colorPickerDialog = createColorPickerDialog();

        lineColor.setOnAction(event -> {
            colorPickerDialog.setTitle("Line color");
            colorPickerDialog.showAndWait();
            if(colorPickerDialog.getResult() != null){
                gc.setStroke(colorPickerDialog.getResult());
            } else {
                gc.setStroke(Color.BLACK);
            }
        });

        fillColor.setOnAction(event -> {
            colorPickerDialog.setTitle("Fill color");
            colorPickerDialog.showAndWait();
            if(colorPickerDialog.getResult() != null){
                gc.setFill(colorPickerDialog.getResult());
            } else {
                gc.setFill(Color.WHITE);
            }
        });

        final HBox hBox = new HBox();
        hBox.getChildren().add(menuBar);
        return hBox;
    }

    private static Dialog<Integer> createNumberOfAnglesDialog(){
        final Dialog<Integer> numOfAnglesDialog = new Dialog<>();
        numOfAnglesDialog.setTitle("Number of angles");

        final ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        numOfAnglesDialog.getDialogPane().getButtonTypes().add(okButton);

        final Node okButtonNode = numOfAnglesDialog.getDialogPane().lookupButton(okButton);
        okButtonNode.setDisable(true);

        final TextField numOfAngles = new TextField("Enter num of angles");
        numOfAnglesDialog.getDialogPane().setContent(numOfAngles);
        numOfAngles.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.matches("[3-9]|(\\d\\d+)")){
                okButtonNode.setDisable(false);
            } else {
                okButtonNode.setDisable(true);
            }
        });

        numOfAnglesDialog.setResultConverter(buttonType -> {
            if(buttonType == okButton){
                return Integer.parseInt(numOfAngles.getText());
            }
            return null;
        });

        return numOfAnglesDialog;
    }

    private static Dialog<Color> createColorPickerDialog(){
        final Dialog<Color> colorPickerDialog = new Dialog<>();
        colorPickerDialog.setTitle("Color");

        final ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        colorPickerDialog.getDialogPane().getButtonTypes().add(okButton);

        final ColorPicker colorPicker = new ColorPicker();
        colorPickerDialog.getDialogPane().setContent(colorPicker);
        //colorPicker.setOnAction(event -> gc.setFill(colorPicker.getValue()));

        colorPickerDialog.setResultConverter(buttonType -> {
            if(buttonType == okButton){
                return colorPicker.getValue();
            }
            return null;
        });

        return colorPickerDialog;
    }

    public static EventHandler<MouseEvent> createHandler(final GraphicsContext gc){
        return event -> {
            switch(mode){
                case SEGMENT:
                    if(clickCount == 0){
                        shape = new Segment(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Segment) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case RAY:
                    if(clickCount == 0){
                        shape = new Ray(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Ray) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case LINE:
                    if(clickCount == 0){
                        shape = new Line(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Line) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case CIRCLE:
                    if(clickCount == 0){
                        shape = new Circle(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Circle) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case ELLIPSE:
                    if(clickCount == 0){
                        shape = new Ellipse(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Ellipse) shape).setThePoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case POLYGON:
                    if(shape instanceof Polygon && ((Polygon) shape).isFinished()) shape = null;
                    if(clickCount == 0 && !(shape instanceof Polygon)){
                        shape = new Polygon(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                        clickCount = -1;
                    } else {
                        ((Polygon) shape).addPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    if(event.getClickCount() == 2){
                        ((Polygon) shape).setFinished();
                    }
                    clickCount += 2;
                    break;

                case RECT:
                    if(clickCount == 0){
                        shape = new Rect(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    } else {
                        ((Rect) shape).setPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                    }
                    clickCount++;
                    break;

                case SYMMETRIC_POLYGON:
                    if(clickCount == 0){
                        if(numOfAnglesDialog.getResult() == null) break;
                        shape = new SymmetricPolygon(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET), numOfAnglesDialog.getResult());
                    } else {
                        SymmetricPolygon s = (SymmetricPolygon) shape;
                        s.addPoint(calcPoint(
                                event.getSceneX(), event.getSceneY(), OFFSET));
                        s.setFinished();
                    }
                    clickCount++;
                    break;
            }

            if(clickCount > 1){
                shape.draw(gc);
                clickCount = 0;
            }
        };
    }
}
