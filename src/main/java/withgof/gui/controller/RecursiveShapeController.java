package withgof.gui.controller;

import withgof.gui.model.ParametersModel;
import withgof.gui.view.panel.RecursivePanelView;
import withgof.gui.view.utilities;
import withgof.validate.Validate;

import java.awt.*;

public class RecursiveShapeController {
    private final RecursivePanelView view;
    ParametersModel model;

    public RecursiveShapeController(ParametersModel model, RecursivePanelView view) {
        this.view = view;
        this.model = model;
        initController();
    }

    public void initController() {
        model.getCanvasParams().setWidth(view.getCanvasWidth());
        model.getCanvasParams().setHeight(view.getCanvasHeight());
        view.getStartXTextField().addTextListener(e -> updateStartX());
        view.getStartYTextField().addTextListener(e -> updateStartY());
        view.getRecursiveDepthTextField().addTextListener(e -> updateRecursiveDepth());
        view.getInitialRadiusTextField().addTextListener(e -> updateInitialRadius());
        view.getNumShapeTextField().addTextListener(e -> updateNumShapes());
        view.getLargeShapeType().addItemListener(e -> updateLargeShapeType());
        view.getLargeShapeColourButton().addActionListener(e -> updateLargeShapeFillColour());
        view.getLargeLineColourButton().addActionListener(e -> updateLargeShapeLineColour());
        view.getLargeLineWidthTextField().addTextListener(e -> updateLargeLineWidth());
        view.getSmallShapeType().addItemListener(e -> updateSmallShapeType());
        view.getSmallShapeColourButton().addActionListener(e -> updateSmallShapeFillColour());
        view.getSmallLineColourButton().addActionListener(e -> updateSmallShapeLineColour());
        view.getSmallLineWidthTextField().addTextListener(e -> updateSmallLineWidth());
    }

    public void updateStartX() {
        Integer startX = Validate.safelyParseInteger(view.getStartXTextField().getText());
        if (startX != null) {
            model.getRecursiveParams().setCenterX(startX);
        }
    }

    public void updateStartY() {
        Integer startY = Validate.safelyParseInteger(view.getStartYTextField().getText());
        if (startY != null) {
            model.getRecursiveParams().setCenterY(startY);
        }
    }

    public void updateRecursiveDepth() {
        Integer depth = Validate.safelyParseInteger(view.getRecursiveDepthTextField().getText());
        if (depth != null) {
            model.getRecursiveParams().setDepth(depth);
        }
    }

    public void updateInitialRadius() {
        Integer initialRadius = Validate.safelyParseInteger(view.getInitialRadiusTextField().getText());
        if (initialRadius != null) {
            model.getRecursiveParams().setInitialSize(initialRadius);
        }
    }

    public void updateNumShapes() {
        Integer numShapes = Validate.safelyParseInteger(view.getNumShapeTextField().getText());
        if (numShapes != null) {
            model.getRecursiveParams().setNumShapes(numShapes);
        }
    }

    public void updateLargeShapeType() {
        model.getShapesParams().get(0).setShapeType(view.getLargeShapeType().getSelectedItem().toLowerCase());
    }

    public void updateLargeShapeFillColour() {
        Color color = utilities.getColourFromButton(view.getLargeShapeColourButton());
        model.getShapesParams().get(0).setFillColour(color);
    }

    public void updateLargeShapeLineColour() {
        Color color = utilities.getColourFromButton(view.getLargeLineColourButton());
        model.getShapesParams().get(0).setLineColour(color);
    }

    public void updateLargeLineWidth() {
        Integer largeLineWidth = Validate.safelyParseInteger(view.getLargeLineWidthTextField().getText());
        if (largeLineWidth != null) {
            model.getShapesParams().get(0).setLineWidth(largeLineWidth);
        }
    }

    public void updateSmallShapeType() {
        model.getShapesParams().get(1).setShapeType(view.getSmallShapeType().getSelectedItem().toLowerCase());
    }

    public void updateSmallShapeFillColour() {
        Color color = utilities.getColourFromButton(view.getSmallShapeColourButton());
        model.getShapesParams().get(1).setFillColour(color);
    }

    public void updateSmallShapeLineColour() {
        Color color = utilities.getColourFromButton(view.getSmallLineColourButton());
        model.getShapesParams().get(1).setLineColour(color);
    }

    public void updateSmallLineWidth() {
        Integer smallLineWidth = Validate.safelyParseInteger(view.getSmallLineWidthTextField().getText());
        if (smallLineWidth != null) {
            model.getShapesParams().get(1).setLineWidth(smallLineWidth);
        }
    }
}
