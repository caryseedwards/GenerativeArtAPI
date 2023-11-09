package patterns;

import parameters.SierpinskiShapeParameters;
import shapes.*;

import javax.swing.*;
import java.awt.*;

public class SierpinskiShape extends JPanel {
    private final SierpinskiShapeParameters params;

    public SierpinskiShape(SierpinskiShapeParameters params) {
        this.params = params;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        switch (params.shapeType) {
            case "triangle":
                drawSierpinski(g2d, new Triangle(params.centreX, params.centreY, params.polygonSize), params.depth);
                break;
            case "circle":
                drawGasket(g2d, new Circle(params.centreX, params.centreY, params.polygonSize), params.depth);
                break;
            case "square":
                drawCarpet(g2d, new Square(params.centreX, params.centreY, params.polygonSize), params.depth);
                break;
            case "hexagon":
                drawHexagon(g2d, new Hexagon(params.centreX, params.centreY, params.polygonSize), params.depth);
                break;
        }
    }
    public void drawSierpinski(Graphics2D g, Triangle triangle, int depth) {
        if (depth == 0) {
            triangle.draw(g, params.shapeLineColour, params.shapeLineWidth, params.shapeFillColour, null);
            return;
        }

        double newRadius = triangle.radius / 2;

        int midX1 = (triangle.x1 + triangle.x2) / 2;
        int midY1 = (triangle.y1 + triangle.y2) / 2;
        int midX3 = (triangle.x1 + triangle.x3) / 2;
        int midY3 = (triangle.y1 + triangle.y3) / 2;

        drawSierpinski(g, new Triangle(triangle.x1, triangle.y1 - (int) newRadius, newRadius), depth - 1);
        drawSierpinski(g, new Triangle(midX1, midY1 - (int) newRadius, newRadius), depth - 1);
        drawSierpinski(g, new Triangle(midX3, midY3 - (int) newRadius, newRadius), depth - 1);
    }
    private void drawGasket(Graphics2D g, Circle circle, int depth) {
        if (depth <= 0) return;

        circle.draw(g, params.shapeLineColour, params.shapeLineWidth, params.shapeFillColour, "");

        int newRadius = (int) circle.radius / 2;
        int dx = (int) (newRadius * Math.cos(Math.PI / 6));
        int dy = (int) (newRadius * Math.sin(Math.PI / 6));

        drawGasket(g, new Circle(circle.centerX, circle.centerY - (int) newRadius, newRadius), depth - 1);
        drawGasket(g, new Circle(circle.centerX - dx, circle.centerY + dy, newRadius), depth - 1);
        drawGasket(g, new Circle(circle.centerX + dx, circle.centerY + dy, newRadius), depth - 1);
    }

    public void drawHexagon(Graphics2D g, Hexagon hexagon, int depth) {
        if (depth == 0) {
            hexagon.draw(g, params.shapeLineColour, params.shapeLineWidth, params.shapeFillColour, "");
            return;
        }

        double newRadius = hexagon.radius / 3;

        for (int i = 0; i < 6; i++) {
            int newX = hexagon.centerX + (int) (newRadius * 2 * Math.cos(i * Math.PI / 3));
            int newY = hexagon.centerY + (int) (newRadius * 2 * Math.sin(i * Math.PI / 3));
            drawHexagon(g, new Hexagon(newX, newY, newRadius), depth - 1);
        }

        drawHexagon(g, new Hexagon(hexagon.centerX, hexagon.centerY, newRadius), depth - 1);
    }
    public void drawCarpet(Graphics2D g, Square square, int depth) {
        if (depth == 0) {
            square.draw(g, params.shapeLineColour, params.shapeLineWidth, params.shapeFillColour,null);
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
                drawCarpet(g, newSquare, depth - 1);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Shape");
        SierpinskiShapeParameters params = new SierpinskiShapeParameters();
        params.setCentreX(400);
        params.setCentreY(400);
        params.setPolygonSize(300);
        params.setDepth(3);
        params.setShapeType("hexagon");
        params.setShapeLineColour(Color.BLACK);
        params.setShapeFillColour(Color.WHITE);
        params.setShapeLineWidth(1);

        SierpinskiShape sierpinskiShape = new SierpinskiShape(params);
        frame.add(sierpinskiShape);
        frame.setSize(params.centreX*2, params.centreY*2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
