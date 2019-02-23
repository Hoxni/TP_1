package sample.shape1d;

import javafx.geometry.Point2D;
import sample.Utils;

import java.util.ArrayList;
import java.util.List;

public class Line extends Ray{
    public Line(Point2D center){
        super(center);
    }

    @Override
    public void setThePoint(Point2D point){
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

            theCenter = intersect.get(0);
            thePoint = intersect.get(intersect.size() - 1);
        } else {
            thePoint = point;
        }
    }
}