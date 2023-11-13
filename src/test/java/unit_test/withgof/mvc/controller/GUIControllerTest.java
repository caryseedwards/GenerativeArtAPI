package unit_test.withgof.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import withgof.gui.controller.ArtworkGUIController;
import withgof.gui.controller.CirclePackingController;
import withgof.gui.controller.RecursiveShapeController;
import withgof.gui.controller.SierpinskiController;
import withgof.gui.model.ParametersModel;
import withgof.gui.view.ArtworkGUIView;

import static org.junit.Assert.*;

public class GUIControllerTest {

    private ArtworkGUIController controller;
    private ArtworkGUIView view;
    private ParametersModel model;

    @Before
    public void setUp() {
        view = new ArtworkGUIView();
        model = new ParametersModel();
        controller = new ArtworkGUIController(view, model);
    }

    @Test
    public void testUpdateAlgorithmPanelVisibility() {
        // Test for "Recursive Shape"
        controller.updateAlgorithmPanelVisibility("Recursive Shape");
        assertTrue(view.getRecursivePanel().isVisible());
        assertFalse(view.getCirclePackingPanel().isVisible());
        assertFalse(view.getSierpinskiPanel().isVisible());

        // Test for "Circle Packing"
        controller.updateAlgorithmPanelVisibility("Circle Packing");
        assertFalse(view.getRecursivePanel().isVisible());
        assertTrue(view.getCirclePackingPanel().isVisible());
        assertFalse(view.getSierpinskiPanel().isVisible());

        // Test for "Sierpinski Shape"
        controller.updateAlgorithmPanelVisibility("Sierpinski Shape");
        assertFalse(view.getRecursivePanel().isVisible());
        assertFalse(view.getCirclePackingPanel().isVisible());
        assertTrue(view.getSierpinskiPanel().isVisible());

        // Test for an invalid algorithm (edge case)
        controller.updateAlgorithmPanelVisibility("Invalid Algorithm");
        assertFalse(view.getRecursivePanel().isVisible());
        assertFalse(view.getCirclePackingPanel().isVisible());
        assertFalse(view.getSierpinskiPanel().isVisible());
    }
    @Test
    public void testControllerSettersAndGetters() {
        // Test for RecursiveShapeController
        RecursiveShapeController rsc = new RecursiveShapeController(model, view.getRecursivePanelView());
        controller.setRecursiveController(rsc);
        assertEquals(rsc, controller.getRecursiveController());

        // Test for CirclePackingController
        CirclePackingController cpc = new CirclePackingController(model, view.getCirclePackingPanelView());
        controller.setPackingController(cpc);
        assertEquals(cpc, controller.getPackingController());

        // Test for SierpinskiController
        SierpinskiController sc = new SierpinskiController(model, view.getSierpinskiPanelView());
        controller.setSierpinskiPanelController(sc);
        assertEquals(sc, controller.getSierpinskiPanelController());
    }




}