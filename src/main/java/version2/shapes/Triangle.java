package version2.shapes;

import java.awt.*;

/**
 * Implements a Triangle Shape to use in the algorithms
 * @author carysedwards
 */
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

    /**
     * Consructor for the triangle
     * @param x - the starting x co-ordinate
     * @param y - the starting y co-ordinate
     * @param radius - the scaling factor for the triangle
     */
    public Triangle(int x, int y, double radius) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        setVertices();
    }

    /**
     * Conversion method to calculate the vertices based on the starting X and Y co-ordinates
     * and the scaling factor
     */
    public void setVertices() {
        x1 = centerX;
        y1 = (int) (centerY - radius);
        x2 = (int) (centerX - radius * Math.cos(Math.toRadians(30)));
        y2 = (int) (centerY + radius * Math.sin(Math.toRadians(30)));
        x3 = (int) (centerX + radius * Math.cos(Math.toRadians(30)));
        y3 = (int) (centerY + radius * Math.sin(Math.toRadians(30)));
    }

    /**
     * Calculates a random position inside the triangle
     * @return A new point containing co-ordinates for a random point in the triangle
     */
    @Override
    public Point randomPositionInside() {
        int minX = Math.min(x1, Math.min(x2, x3));
        int maxX = Math.max(x1, Math.max(x2, x3));
        int minY = Math.min(y1, Math.min(y2, y3));
        int maxY = Math.max(y1, Math.max(y2, y3));

        Polygon triangle = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);

        while (true) {
            int x = minX + (int) (Math.random() * (maxX - minX + 1));
            int y = minY + (int) (Math.random() * (maxY - minY + 1));

            if (triangle.contains(x, y)) {
                return new Point(x, y);
            }
        }
    }

    /**
     * Checks if the triangle is inside a given circle
     * @param circle The circle to check if the triangle is inside of
     * @return True if the triangle fits inside the triangle
     */
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

    /**
     * Helper method to check if a point is inside a triangle at x+y co-ordinates
     * @param x - the starting X co-ordinate
     * @param y - the starting Y co-ordinate
     * @return true if the point is inside a triangle
     */
    public boolean isPointInside(int x, int y) {
        Polygon triangle = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        return triangle.contains(x, y);
    }

    /**
     * Draws the triangle onto the given graphics object
     * @param g2d - the graphics object to drawn
     * @param lineColor - the line colour of the triangle
     * @param lineWidth - the line width of the triangle
     * @param fillColor - the fill colour of the triangle
     * @param lineType - the line type of the triangle
     */
    @Override
    public void draw(Graphics2D g2d, Color lineColor, float lineWidth, Color fillColor, String lineType) {
        g2d.setColor(fillColor);
        g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);

        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
    }

    /**
     * Sets the starting position of the triangle
     * @param x - starting x co-ordinate
     * @param y - starting y co-ordinate
     */
    @Override
    public void setPosition(int x, int y) {
        this.centerX = x;
        this.centerY = y;
        setVertices();
    }

    /**
     * Sets the scale for the triangle
     * @param scale - the scale factor
     */
    @Override
    public void setScale(double scale) {
        this.radius = scale;
        setVertices();
    }

    /**
     * Gets the center x co-ordinate
     * @return centerX
     */
    public int getCenterX() {
        return centerX;
    }

    /**
     * Gets the center y co-ordinate
     * @return centerY
     */
    public int getCenterY() {
        return centerY;
    }

    /**
     * Gets the radius scaling factor
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the x1 vertices
     * @return x1
     */
    public int getX1() {
        return x1;
    }

    /**
     * Gets the x2 vertices
     * @return x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * Gets the x3 vertices
     * @return x3
     */
    public int getX3() {
        return x3;
    }

    /**
     * Gets the y1 vertices
     * @return y1
     */
    public int getY1() {
        return y1;
    }

    /**
     * Gets the y2 vertices
     * @return y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * Gets the y3 vertices
     * @return y3
     */
    public int getY3() {
        return y3;
    }
}
