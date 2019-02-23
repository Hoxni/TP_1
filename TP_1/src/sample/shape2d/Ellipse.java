package sample.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Shape2D{
    protected Point2D thePoint;

    public Ellipse(Point2D center){
        super(center);
    }

    public void setThePoint(Point2D point){
        double w = Math.abs(point.getX() - theCenter.getX());
        double h = Math.abs(point.getY() - theCenter.getY());
        theCenter = theCenter.midpoint(point);
        theCenter = theCenter.subtract(w/2, h/2);
        thePoint = theCenter.add(w, h);
    }

    @Override
    public void move(Point2D target){
        double dx = target.getX() - theCenter.getX();
        double dy = target.getY() - theCenter.getY();
        theCenter.add(dx, dy);
        thePoint.add(dx, dy);
    }

    @Override
    public void draw(GraphicsContext gc){
        double w = thePoint.getX() - theCenter.getX();
        double h = thePoint.getY() - theCenter.getY();
        gc.fillOval(theCenter.getX(), theCenter.getY(), w, h);
        gc.strokeOval(theCenter.getX(), theCenter.getY(), w, h);
    }
}
