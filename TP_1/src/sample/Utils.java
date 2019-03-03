package sample;

import javafx.geometry.Point2D;

public class Utils{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    public static final double OFFSET = 50;

    public static Point2D calcPoint(double x, double y, double o){
        return new Point2D(x, y - o);
    }
}
