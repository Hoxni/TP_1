package sample;

import javafx.geometry.Point2D;

public class Utils{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    public static final double OFFSET = 50;

    public enum ShapeType{
        SEGMENT,
        RAY,
        LINE,
        POLYGON,
        RECT,
        SYMMETRIC_POLYGON,
        ELLIPSE,
        CIRCLE
    }

    public static Point2D calcPoint(double x, double y, double o){
        return new Point2D(x, y - o);
    }
}
