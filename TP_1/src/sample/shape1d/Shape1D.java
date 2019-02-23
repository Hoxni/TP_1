package sample.shape1d;

import javafx.geometry.Point2D;
import sample.Shape;

abstract public class Shape1D extends Shape{
    protected Point2D thePoint;

    public Shape1D(Point2D center){
        super(center);
    }

    public void setThePoint(Point2D thePoint){
        this.thePoint = thePoint;
    }
}
