package unit_test.version2.shapes;

import org.junit.Before;
import org.junit.Test;
import version2.shapes.Circle;
import version2.shapes.Hexagon;

import java.awt.*;

import static org.junit.Assert.*;

public class HexagonTest {
    private Hexagon hexagon;

    @Before
    public void setUp() {
        hexagon = new Hexagon(10, 10, 5);
    }

    @Test
    public void testHexagonInitialization() {
        assertEquals("Center X should be initialized correctly", 10, hexagon.getCenterX());
        assertEquals("Center Y should be initialized correctly", 10, hexagon.getCenterY());
        assertEquals("Radius should be initialized correctly", 5, hexagon.radius, 0.0);
    }

    @Test
    public void testIsInsideTrue() {
        Circle circle = new Circle(10, 10, 2);
        assertTrue("Circle should be inside hexagon", hexagon.isInside(circle));
    }

    @Test
    public void testIsInsideFalse() {
        Circle circle = new Circle(20, 20, 3);
        assertFalse("Circle should not be inside hexagon", hexagon.isInside(circle));
    }

    @Test
    public void testRandomPositionInside() {
        boolean isInside = true;
        for (int i = 0; i < 100; i++) {
            Point point = hexagon.randomPositionInside();
            if (!hexagon.isPointInside(point.x, point.y)) {
                isInside = false;
                break;
            }
        }
        assertTrue("Randomly generated point should be inside hexagon", isInside);
    }


    @Test
    public void testSetPosition() {
        hexagon.setPosition(15, 15);
        assertEquals("Center X should be updated", 15, hexagon.getCenterX());
        assertEquals("Center Y should be updated", 15, hexagon.getCenterY());
    }

    @Test
    public void testSetScale() {
        hexagon.setScale(10);
        assertEquals("Radius should be updated", 10, hexagon.radius, 0.0);
    }

    }
