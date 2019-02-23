package sample.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Rect extends Shape2D{
    private double w;
    private double h;
    public Rect(Point2D center){
        super(center);
    }

    public void setPoint(Point2D point){
        w = Math.abs(point.getX() - theCenter.getX());
        h = Math.abs(point.getY() - theCenter.getY());
        theCenter = theCenter.midpoint(point);
        theCenter = theCenter.subtract(w/2, h/2);
    }

    @Override
    public void move(Point2D target){
        theCenter = target;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.fillRect(theCenter.getX(), theCenter.getY(), w, h);
        gc.strokeRect(theCenter.getX(), theCenter.getY(), w, h);
    }
}
