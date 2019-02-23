package sample.shape2d;

import javafx.geometry.Point2D;

public class Circle extends Ellipse{
    public Circle(Point2D center){
        super(center);
    }

    @Override
    public void setThePoint(Point2D point){
        double radius = theCenter.distance(point);
        theCenter = theCenter.subtract(radius, radius);
        thePoint = new Point2D(theCenter.getX() + radius * 2, theCenter.getY() + radius * 2);
    }
}