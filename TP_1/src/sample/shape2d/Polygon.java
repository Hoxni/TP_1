package sample.shape2d;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Shape2D{
    protected List<Point2D> points;
    protected boolean isFinished = false;

    public Polygon(Point2D center){
        super(center);
        points = new ArrayList<>();
        points.add(center);
    }

    public boolean isFinished(){
        return isFinished;
    }

    private void fillPolygon(GraphicsContext gc){
        int size = points.size();
        double[] x = new double[size];
        double[] y = new double[size];
        for(int i = 0; i < size; i++){
            Point2D p = points.get(i);
            x[i] = p.getX();
            y[i] = p.getY();
        }
        gc.fillPolygon(x, y, size);
    }

    public void setFinished(){
        if(points.size() > 2) isFinished = true;
    }

    public void addPoint(Point2D point){
        if(!isFinished) points.add(point);
    }

    @Override
    public void move(Point2D target){
        double dx = target.getX() - theCenter.getX();
        double dy = target.getY() - theCenter.getY();
        theCenter.add(dx, dy);
        for(Point2D p : points){
            p.add(dx, dy);
        }
    }

    @Override
    public void draw(GraphicsContext gc){
        if(isFinished){
            fillPolygon(gc);
            gc.strokeLine(points.get(0).getX(), points.get(0).getY(),
                    points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
        }
        for(int i = 1; i < points.size(); i++){
            gc.strokeLine(points.get(i - 1).getX(), points.get(i - 1).getY(),
                    points.get(i).getX(), points.get(i).getY());
        }
    }
}