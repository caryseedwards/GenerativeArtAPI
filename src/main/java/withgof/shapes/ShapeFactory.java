package withgof.shapes;

import withgof.parameters.ShapeParameters;

public class ShapeFactory {
    public Shape createShape(int x, int y, double radius, ShapeParameters parameters) {
        Shape newShape = switch (parameters.getShapeType()) {
            case "circle" -> createCircle(x, y, radius);
            case "square" -> createSquare(x, y, radius);
            case "triangle" -> createTriangle(x, y, radius);
            case "hexagon" -> createHexagon(x, y, radius);
            default -> throw new IllegalArgumentException("Invalid shape type: " + parameters.getShapeType());
        };
        newShape.setParameters(parameters);
        return newShape;
    }
    private Circle createCircle(int x, int y, double radius){
        return new Circle(x, y, radius);
    }
    private Square createSquare(int x, int y, double radius){
        return new Square(x, y, radius);
    }
    private Triangle createTriangle(int x, int y, double radius){
        return new Triangle(x, y, radius);
    }
    private Hexagon createHexagon(int x, int y, double radius){
        return new Hexagon(x, y, radius);
    }
}