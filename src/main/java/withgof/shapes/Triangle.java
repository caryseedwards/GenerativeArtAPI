package withgof.shapes;

import java.awt.*;

public class Triangle extends Shape {
    private int centerX;
    private int centerY;
    private double radius;
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;

    public Triangle(int x, int y, double radius) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        setVertices();
    }

    public void setVertices() {
        x1 = centerX;
        y1 = (int) (centerY - radius);
        x2 = (int) (centerX - radius * Math.cos(Math.toRadians(30)));
        y2 = (int) (centerY + radius * Math.sin(Math.toRadians(30)));
        x3 = (int) (centerX + radius * Math.cos(Math.toRadians(30)));
        y3 = (int) (centerY + radius * Math.sin(Math.toRadians(30)));
    }

    @Override
    public Point randomPositionInside() {
        double r1 = Math.random();
        double r2 = Math.random();
        if (r1 + r2 > 1) {
            r1 = 1 - r1;
            r2 = 1 - r2;
        }
        int x = (int) ((1 - r1 - r2) * x1 + r1 * x2 + r2 * x3);
        int y = (int) ((1 - r1 - r2) * y1 + r1 * y2 + r2 * y3);
        return new Point(x, y);
    }

    @Override
    public boolean isInside(Circle circle) {
        for (int angle = 0; angle < 360; angle += 5) {
            double rad = Math.toRadians(angle);
            int pointX = (int) (circle.getCenterX() + circle.getRadius() * Math.cos(rad));
            int pointY = (int) (circle.getCenterY() + circle.getRadius() * Math.sin(rad));
            if (!isPointInside(pointX, pointY)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPointInside(int x, int y) {
        Polygon triangle = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        return triangle.contains(x, y);
    }

    @Override
    public void draw(Graphics2D g2d, Color lineColor, float lineWidth, Color fillColor, String lineType) {
        g2d.setColor(fillColor);
        g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);

        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }

    @Override
    public void setPosition(int x, int y) {
        this.centerX = x;
        this.centerY = y;
        setVertices();
    }

    @Override
    public void setScale(double scale) {
        this.radius = scale;
        setVertices();
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getX3() {
        return x3;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getY3() {
        return y3;
    }
}
