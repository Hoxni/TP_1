package sample.shape1d;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Segment extends Shape1D{
    public Segment(Point2D center){
        super(center);
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
        gc.strokeLine(theCenter.getX(), theCenter.getY(), thePoint.getX(), thePoint.getY());
    }
}
