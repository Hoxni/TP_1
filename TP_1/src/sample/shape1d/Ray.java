package sample.shape1d;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sample.Utils;

import java.util.ArrayList;
import java.util.List;

public class Ray extends Segment{

    private static final double EPS = 0.0001;

    public Ray(Point2D center){
        super(center);
    }

    private boolean isEqual(double d1, double d2){
        return Math.abs(d1 - d2) < EPS;
    }

    protected Point2D getLineIntersection(Point2D l1p1, Point2D l1p2, Point2D l2p1, Point2D l2p2){
        double A1 = l1p2.getY() - l1p1.getY();
        double B1 = l1p1.getX() - l1p2.getX();
        double C1 = A1 * l1p1.getX() + B1 * l1p1.getY();

        double A2 = l2p2.getY() - l2p1.getY();
        double B2 = l2p1.getX() - l2p2.getX();
        double C2 = A2 * l2p1.getX() + B2 * l2p1.getY();

        //lines are parallel
        double det = A1 * B2 - A2 * B1;
        if(isEqual(det, 0)){
            return null; //parallel lines
        } else {
            double x = (B2 * C1 - B1 * C2) / det;
            double y = (A1 * C2 - A2 * C1) / det;

            boolean online = ((Math.min(l2p1.getX(), l2p2.getX()) < x || isEqual(Math.min(l2p1.getX(), l2p2.getX()), x))
                    && (Math.max(l2p1.getX(), l2p2.getX()) > x || isEqual(Math.max(l2p1.getX(), l2p2.getX()), x))
                    && (Math.min(l2p1.getY(), l2p2.getY()) < y || isEqual(Math.min(l2p1.getY(), l2p2.getY()), y))
                    && (Math.max(l2p1.getY(), l2p2.getY()) > y || isEqual(Math.max(l2p1.getY(), l2p2.getY()), y))
            );

            if(online)
                return new Point2D(x, y);
        }
        return null; //intersection is at out of at least one segment.
    }

    @Override
    public void setThePoint(Point2D point){
        super.setThePoint(point);
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D(0, 0));
        points.add(new Point2D(0, Utils.HEIGHT));
        points.add(new Point2D(Utils.WIDTH, Utils.HEIGHT));
        points.add(new Point2D(Utils.WIDTH, 0));
        List<Point2D> intersect = new ArrayList<>();

        if(theCenter.distance(point) != 0){
            for(int i = 0; i < points.size(); i++){
                Point2D res = getLineIntersection(theCenter, point, points.get(i), points.get((i + 1) % points.size()));
                if(res != null) intersect.add(res);
            }

            Point2D point1 = intersect.get(0);
            if(theCenter.distance(point1) > thePoint.distance(point1)){
                thePoint = point1;
            } else {
                thePoint = intersect.get(intersect.size() - 1);
            }
        } else {
            thePoint = point;
        }
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.strokeLine(theCenter.getX(), theCenter.getY(), thePoint.getX(), thePoint.getY());
    }
}