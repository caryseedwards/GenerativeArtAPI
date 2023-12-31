package version1.algorithms;

import version1.parameters.CanvasParameters;
import version1.parameters.Parameters;
import version1.parameters.ShapeParameters;
import version1.parameters.SierpinskiShapeAlgorithmParameters;
import version1.shapes.Shape;
import version1.shapes.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * The implementation of the Sierpinski Shape algorithm
 * @author carysedwards
 */
public class SierpinskiShapeAlgorithm extends Algorithm {
    private SierpinskiShapeAlgorithmParameters params;
    private ShapeParameters sierpinskiShape;
    private ArrayList<Shape> shapesToDraw = new ArrayList<>();

    /**
     * Constructor to create the sierpinski shape algorithm
     * @param canvasParams - details of the canvas to draw upon
     * @param shapeParams - details of the shapes used within the algorithm
     * @param algorithmParams - details of the algorithm
     */
    public SierpinskiShapeAlgorithm(CanvasParameters canvasParams, ArrayList<ShapeParameters> shapeParams, Parameters algorithmParams) {
        super(canvasParams, shapeParams, algorithmParams);
        initialiseAlgorithm();
        executeAlgorithm();
    }

    /**
     * Initialises the algorithm with default values
     */
    @Override
    protected void initialiseAlgorithm() {
        this.params = (SierpinskiShapeAlgorithmParameters) getAlgorithmParams();
        this.sierpinskiShape = getShapeParameters().get(0);
        this.shapesToDraw = new ArrayList<>();
    }

    /**
     * Draws a triangle and adds it to the pattern
     * @param triangle - previous triangle drawn
     * @param depth - the recursive depth
     */
    private void addSierpinski(Triangle triangle, int depth) {
        if (depth == 0) {
            shapesToDraw.add(triangle);
            return;
        }

        double newRadius = triangle.radius / 2;

        int midX1 = (triangle.x1 + triangle.x2) / 2;
        int midY1 = (triangle.y1 + triangle.y2) / 2;
        int midX3 = (triangle.x1 + triangle.x3) / 2;
        int midY3 = (triangle.y1 + triangle.y3) / 2;

        addSierpinski(new Triangle(triangle.x1, triangle.y1 - (int) newRadius, newRadius), depth - 1);
        addSierpinski(new Triangle(midX1, midY1 - (int) newRadius, newRadius), depth - 1);
        addSierpinski(new Triangle(midX3, midY3 - (int) newRadius, newRadius), depth - 1);
    }

    /**
     * Draws a circle and adds it to the pattern
     * @param circle - previous circle drawn
     * @param depth - the recursive depth
     */
    private void addGasket(Circle circle, int depth) {
        if (depth <= 0) return;

        shapesToDraw.add(circle);

        int newRadius = (int) circle.radius / 2;
        int dx = (int) (newRadius * Math.cos(Math.PI / 6));
        int dy = (int) (newRadius * Math.sin(Math.PI / 6));

        addGasket(new Circle(circle.centerX, circle.centerY - newRadius, newRadius), depth - 1);
        addGasket(new Circle(circle.centerX - dx, circle.centerY + dy, newRadius), depth - 1);
        addGasket(new Circle(circle.centerX + dx, circle.centerY + dy, newRadius), depth - 1);
    }

    /**
     * Draws a hexagon and adds it to the pattern
     * @param hexagon - previous hexagon drawn
     * @param depth - the recursive depth
     */
    public void addHexagon(Hexagon hexagon, int depth) {
        if (depth == 0) {
            shapesToDraw.add(hexagon);
            return;
        }

        double newRadius = hexagon.radius / 3;

        for (int i = 0; i < 6; i++) {
            int newX = hexagon.centerX + (int) (newRadius * 2 * Math.cos(i * Math.PI / 3));
            int newY = hexagon.centerY + (int) (newRadius * 2 * Math.sin(i * Math.PI / 3));
            addHexagon(new Hexagon(newX, newY, newRadius), depth - 1);
        }

        addHexagon(new Hexagon(hexagon.centerX, hexagon.centerY, newRadius), depth - 1);
    }

    /**
     * Draws a square and adds it to the pattern
     * @param square - previous square drawn
     * @param depth - the recursive depth
     */
    private void addCarpet(Square square, int depth) {
        if (depth == 0) {
            shapesToDraw.add(square);
            return;
        }

        double newRadius = square.radius / 3;
        double offsetX = newRadius * 2;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (row == 1 && col == 1) continue;

                int newX = (int) (square.centerX + (col - 1) * offsetX);
                int newY = (int) (square.centerY + (row - 1) * offsetX);

                Square newSquare = new Square(newX, newY, newRadius);
                addCarpet(newSquare, depth - 1);
            }
        }
    }

    /**
     * Executes the algorithm based on the parameters already passed
     * The algorithm executes depends on the shape selected
     */
    @Override
    public void executeAlgorithm() {
        switch (getShapeParameters().get(0).getShapeType()) {
            case "triangle":
                addSierpinski(new Triangle(params.getCentreX(), params.getCentreY(), params.getPolygonSize()), params.getDepth());
                break;
            case "circle":
                addGasket(new Circle(params.getCentreX(), params.getCentreY(), params.getPolygonSize()), params.getDepth());
                break;
            case "square":
                addCarpet(new Square(params.getCentreX(), params.getCentreY(), params.getPolygonSize()), params.getDepth());
                break;
            case "hexagon":
                addHexagon(new Hexagon(params.getCentreX(), params.getCentreY(), params.getPolygonSize()), params.getDepth());
                break;
            default:
                throw new IllegalArgumentException("Invalid shape type: " + getShapeParameters().get(0).getShapeType());
        }
    }

    /**
     * Draws the algorithm to the graphics object based on the parameters already passed
     * @param g - The graphics object to draw to
     */
    @Override
    public void drawPattern(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getCanvasParameters().getBackgroundColour());
        g2d.fillRect(0, 0, getCanvasParameters().getWidth(), getCanvasParameters().getHeight());

        for (Shape shape : shapesToDraw) {
            shape.draw(g2d, sierpinskiShape.getLineColour(), sierpinskiShape.getLineWidth(), sierpinskiShape.getFillColour(), null);
        }
    }
}
