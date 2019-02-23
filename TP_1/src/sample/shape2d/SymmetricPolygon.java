package sample.shape2d;

import javafx.geometry.Point2D;
import java.util.ArrayList;

public class SymmetricPolygon extends Polygon{
    private int theAngles;

    public SymmetricPolygon(Point2D center, int angles){
        super(center);
        theAngles = angles;
        points = new ArrayList<>(theAngles);
    }

    @Override
    public void addPoint(Point2D point){
        super.addPoint(point);
        setFinished();
        calculatePoints();
    }

    private void calculatePoints(){
        double angle = Math.toRadians(360.0 / theAngles);
        Point2D thePoint = points.get(0);
        double radius = theCenter.distance(thePoint);
        double theta1 = Math.atan2(-1, 0);
        double theta2 = Math.atan2(theCenter.getY() - thePoint.getY(), theCenter.getX() - thePoint.getX());
        double theta = theta2 - theta1;
        thePoint = theCenter.add(0, radius);
        points.clear();

        for(int i = 0; i < theAngles; i++){
            double tempX = thePoint.getX() + radius * Math.sin(angle * i) - theCenter.getX();
            double tempY = thePoint.getY() - radius * (1 - Math.cos(angle * i)) - theCenter.getY();
            double rotatedX = tempX * Math.cos(theta) - tempY * Math.sin(theta);
            double rotatedY = tempX * Math.sin(theta) + tempY * Math.cos(theta);
            points.add(new Point2D(rotatedX + theCenter.getX(), rotatedY + theCenter.getY()));
        }
    }
}