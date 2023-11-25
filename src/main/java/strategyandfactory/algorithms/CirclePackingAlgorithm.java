package strategyandfactory.algorithms;

import strategyandfactory.parameters.CanvasParameters;
import strategyandfactory.parameters.CirclePackingAlgorithmParameters;
import strategyandfactory.parameters.ShapeParameters;
import strategyandfactory.shapes.Circle;
import strategyandfactory.shapes.Shape;
import strategyandfactory.shapes.ShapeFactory;

import java.awt.*;
import java.util.ArrayList;

/**
 * The implementation of the Circle Packing algorithm
 * Will fill a specified shapes with circles as an animation
 * @author carysedwards
 */
public class CirclePackingAlgorithm implements AlgorithmStrategy {
    private final ShapeFactory shapeFactory;
    private final CanvasParameters canvasParameters;
    private final ShapeParameters boundaryParameters;
    private final ShapeParameters circleParameters;
    private final CirclePackingAlgorithmParameters algorithmParameters;
    private final ArrayList<Circle> circles = new ArrayList<>();
    private Shape boundaryShape;

    /**
     * Constructor to create the circle packing algorithm
     * @param canvasParameters - details of the canvas to draw upon
     * @param shapeParameters - details of the shapes used within the algorithm
     * @param algorithmParameters - details of the algorithm
     */
    public CirclePackingAlgorithm(CanvasParameters canvasParameters, ArrayList<ShapeParameters> shapeParameters, CirclePackingAlgorithmParameters algorithmParameters) {
        this.canvasParameters = canvasParameters;
        this.boundaryParameters = shapeParameters.get(0);
        this.circleParameters = shapeParameters.get(1);
        this.algorithmParameters = algorithmParameters;
        shapeFactory = new ShapeFactory();
        setBoundaryShape(boundaryParameters.getShapeType());
    }

    /**
     * Validates the parameters passed to ensure the algorithm is safe to execute
     * @return True if the parameters used are valid
     */
    @Override
    public boolean validateParameters() {
        return canvasParameters.validateParameters() && boundaryParameters.validateParameters() && circleParameters.validateParameters() && algorithmParameters.validateParameters();
    }

    /**
     * Executes the algorithm based on the parameters already passed
     */
    @Override
    public void executeAlgorithm() {
        if (validateParameters()) {
            addCircles();
        }
    }

    /**
     * Draws the algorithm to the graphics object based on the parameters already passed
     * @param g - The graphics object to draw to
     */
    @Override
    public void drawPattern(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(canvasParameters.getBackgroundColour());
        g2d.fillRect(0, 0, canvasParameters.getWidth(), canvasParameters.getHeight());

        boundaryShape.draw(g2d, boundaryParameters.getLineColour(), boundaryParameters.getLineWidth(), boundaryParameters.getFillColour(), "solid");
        for (Circle circle : circles) {
            circle.draw(g2d, circleParameters.getLineColour(), circleParameters.getLineWidth(), circleParameters.getFillColour(), "solid");
        }
    }

    /**
     * The implementation of the logic of the algorithm
     * Will fill a bounded shapes with circles
     */
    public void addCircles() {
        for (int i = 0; i < algorithmParameters.maxAttempts; i++) {
            Point randomPosition = boundaryShape.randomPositionInside();
            int randomRadius = algorithmParameters.minRadius + (int) (Math.random() * (algorithmParameters.maxRadius - algorithmParameters.minRadius));
            Circle newCircle = new Circle(randomPosition.x, randomPosition.y, randomRadius);

            boolean overlaps = circles.stream().anyMatch(newCircle::overlaps);
            if (!overlaps) {
                boolean isInside = boundaryShape.isInside(newCircle);
                if (isInside) {
                    circles.add(newCircle);
                }
            }
        }
    }

    /**
     * Gets the algorithm parameters specified for the algorithm
     * @return algorithmParameters
     */
    public CirclePackingAlgorithmParameters getAlgorithmParameters() {
        return this.algorithmParameters;
    }

    /**
     * Gets the boundary shape for the algorithm
     * @return boundaryShape
     */
    public Shape getBoundaryShape() {
        return boundaryShape;
    }

    /**
     * Sets the boundary shape for the algorithm
     * @param type the type of shape to use
     */
    public void setBoundaryShape(String type) {
        boundaryParameters.setShapeType(type);
        boundaryShape = shapeFactory.createShape(algorithmParameters.getCentreX(), algorithmParameters.getCentreY(), algorithmParameters.getPolygonSize(), boundaryParameters);
    }

    /**
     * Gets the circles creates to be drawn onto the canvas for the algorithm
     * @return circles
     */
    public ArrayList<Circle> getCircles() {
        return circles;
    }
}
