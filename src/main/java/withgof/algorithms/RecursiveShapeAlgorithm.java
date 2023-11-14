package withgof.algorithms;

import withgof.parameters.CanvasParameters;
import withgof.parameters.RecursiveShapeAlgorithmParameters;
import withgof.parameters.ShapeParameters;
import withgof.shapes.Shape;
import withgof.shapes.ShapeFactory;

import java.awt.*;
import java.util.ArrayList;

public class RecursiveShapeAlgorithm implements AlgorithmStrategy {
    private final ShapeFactory shapeFactory;
    private final ArrayList<Shape> shapesToDraw;
    private final CanvasParameters canvasParameters;
    private final ShapeParameters largeShapeParameters;
    private final ShapeParameters smallShapeParameters;
    private final RecursiveShapeAlgorithmParameters algorithmParameters;

    public RecursiveShapeAlgorithm(CanvasParameters canvasParameters, ArrayList<ShapeParameters> shapeParameters, RecursiveShapeAlgorithmParameters algorithmParameters) {
        this.canvasParameters = canvasParameters;
        this.algorithmParameters = algorithmParameters;
        this.largeShapeParameters = shapeParameters.get(0);
        this.smallShapeParameters = shapeParameters.get(1);
        this.shapesToDraw = new ArrayList<>();
        this.shapeFactory = new ShapeFactory();
    }

    @Override
    public boolean validateParameters() {
        return canvasParameters.validateParameters() && largeShapeParameters.validateParameters() && smallShapeParameters.validateParameters() && algorithmParameters.validateParameters();
    }

    @Override
    public void executeAlgorithm() {
        if (validateParameters()) {
            addPattern(algorithmParameters.getCenterX(), algorithmParameters.getCenterY(), algorithmParameters.getInitialSize(), algorithmParameters.getDepth());
        }
    }

    @Override
    public void drawPattern(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(canvasParameters.getBackgroundColour());
        g2d.fillRect(0, 0, canvasParameters.getWidth(), canvasParameters.getHeight());
        for (Shape shape : shapesToDraw) {
            shape.draw(g2d, shape.getParameters().getLineColour(), shape.getParameters().getLineWidth(),
                    shape.getParameters().getFillColour(), "solid");
        }
    }

    public void addPattern(int x, int y, int size, int depth) {
        if (depth == 0) return;
        Shape newLargeShape = shapeFactory.createShape(x, y, size, largeShapeParameters);
        shapesToDraw.add(newLargeShape);
        double angleStep = Math.PI * 2 / algorithmParameters.getNumShapes();
        int smallerSize = (int) (size * Math.sin(angleStep / 2));

        for (int i = 0; i < algorithmParameters.getNumShapes(); i++) {
            double angle = i * angleStep;
            int newX = (int) (x + size * Math.cos(angle));
            int newY = (int) (y + size * Math.sin(angle));

            Shape newSmallShape = shapeFactory.createShape(newX, newY, smallerSize, smallShapeParameters);
            shapesToDraw.add(newSmallShape);
            addPattern(newX, newY, smallerSize, depth - 1);
        }
    }

    public ArrayList<Shape> getShapesToDraw() {
        return shapesToDraw;
    }

    public ShapeParameters getLargeShapeParameters() {
        return largeShapeParameters;
    }

    public ShapeParameters getSmallShapeParameters() {
        return smallShapeParameters;
    }
}
