package unit_test.algorithms;

import algorithms.CirclePackingAlgorithm;
import org.junit.Test;
import parameters.CanvasParameters;
import parameters.CirclePackingAlgorithmParameters;
import parameters.ShapeParameters;
import shapes.Circle;
import shapes.Hexagon;
import shapes.Square;
import shapes.Triangle;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CirclePackingAlgorithmTest {
    private CirclePackingAlgorithm createTestInstance() {
        CanvasParameters canvas = new CanvasParameters(500, 500, Color.WHITE);
        ArrayList<ShapeParameters> shapes = new ArrayList<>();
        shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.WHITE));
        shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.WHITE));
        CirclePackingAlgorithmParameters algorithm = new CirclePackingAlgorithmParameters(250, 250, 200, 5, 50, 100, 1);

        return new CirclePackingAlgorithm(canvas, shapes, algorithm);
    }
    @Test
    public void testInitializationAndBoundaryShape() {
        CirclePackingAlgorithm packing = createTestInstance();
        assertNotNull("Boundary shape should be initialized", packing.getBoundaryShape());
    }
    @Test
    public void testCirclePackingConstructor() {
        CirclePackingAlgorithm packing = createTestInstance();
        assertNotNull(packing.getBoundaryShape());
        assertTrue(packing.getCircles().isEmpty());
    }

    @Test
    public void testSetBoundaryCircle() {
        CirclePackingAlgorithm packing = createTestInstance();
        assertTrue(packing.getBoundaryShape() instanceof Circle);
    }
    @Test
    public void testSetBoundarySquare() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.setBoundaryShape("square");
        assertTrue(packing.getBoundaryShape() instanceof Square);
    }
    @Test
    public void testRepetitiveSetBoundaryCalls() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.setBoundaryShape("triangle");
        assertTrue(packing.getBoundaryShape() instanceof Triangle);

        packing.setBoundaryShape("hexagon");
        assertTrue(packing.getBoundaryShape() instanceof Hexagon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBoundaryType() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.setBoundaryShape("invalidType");
    }

    @Test
    public void testAddCircle() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.addCircle();
        assertFalse("Circles list should not be empty after adding circles", packing.getCircles().isEmpty());
        assertTrue("All circles should be inside the boundary",
                packing.getCircles().stream().allMatch(circle -> packing.getBoundaryShape().isInside(circle)));
    }
    @Test
    public void testCircleCountAfterMultipleAdditions() {
        CirclePackingAlgorithm packing = createTestInstance();
        int initialCount = packing.getCircles().size();
        packing.addCircle(); // Call multiple times as needed
        assertTrue(packing.getCircles().size() > initialCount);
    }
    @Test
    public void testParameterModification() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.getAlgorithmParameters().setMinRadius(10);
        packing.getAlgorithmParameters().setMaxRadius(20);
        packing.addCircle();
        for (Circle circle : packing.getCircles()) {
            assertTrue(circle.getRadius() >= 10 && circle.getRadius() <= 20);
        }
    }

    @Test
    public void testNoOverlappingCircles() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.addCircle();
        ArrayList<Circle> circles = packing.getCircles();
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                assertFalse("Circles should not overlap", circles.get(i).overlaps(circles.get(j)));
            }
        }
    }

    @Test
    public void testCircleRadiusConstraints() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.addCircle();
        for (Circle circle : packing.getCircles()) {
            double radius = circle.getRadius();
            assertTrue("Circle radius should be within constraints",
                    radius >= packing.params.getMinRadius() && radius <= packing.params.getMaxRadius());
        }
    }
    @Test
    public void testBoundaryAndCircleIntegration() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.setBoundaryShape("hexagon");
        packing.executeAlgorithm();

        assertTrue(packing.getBoundaryShape() instanceof Hexagon);
        assertFalse(packing.getCircles().isEmpty());
        assertTrue(packing.getCircles().stream().allMatch(circle ->
                packing.getBoundaryShape().isInside(circle)));
    }
    @Test
    public void testCirclePlacement() {
        CirclePackingAlgorithm packing = createTestInstance();
        packing.addCircle();
        Circle lastAddedCircle = packing.getCircles().get(packing.getCircles().size() - 1);
        assertTrue(packing.getBoundaryShape().isInside(lastAddedCircle));
    }

}
