package sample;

import javafx.geometry.Point2D;

public class Utils{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    public static final int SEGMENT = 0;
    public static final int RAY = 1;
    public static final int LINE = 2;

    public static final int POLYGON = 3;
    public static final int RECT = 4;
    public static final int SYMMETRIC_POLYGON = 5;

    public static final int ELLIPSE = 6;
    public static final int CIRCLE = 7;

    public static Point2D calcPoint(double x, double y, double o){
        return new Point2D(x, y - o);
    }
}
