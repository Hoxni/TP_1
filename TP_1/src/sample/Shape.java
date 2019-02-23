package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

abstract public class Shape{
    protected Point2D theCenter;

    public Shape(Point2D center){
        theCenter = center;
    }

    public void setLocation(Point2D center){
        theCenter = center;
    }

    public Point2D getTheCenter(){
        return theCenter;
    }

    public abstract void move(Point2D target);

    public abstract void draw(GraphicsContext gc);
}
