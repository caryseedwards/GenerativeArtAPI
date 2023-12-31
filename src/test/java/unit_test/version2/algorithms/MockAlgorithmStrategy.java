package unit_test.version2.algorithms;

import version2.algorithms.AlgorithmStrategy;

import java.awt.*;

class MockAlgorithmStrategy implements AlgorithmStrategy {
    private boolean executeCalled = false;
    private boolean drawCalled = false;
    private boolean validationOutcome = true;

    @Override
    public boolean validateParameters() {
        return validationOutcome;
    }

    @Override
    public void executeAlgorithm() {
        executeCalled = true;
    }

    @Override
    public void drawPattern(Graphics g) {
        drawCalled = true;
    }

    @Override
    public void saveImage(String filePath) {
    }

    public void setValidationOutcome(boolean outcome) {
        this.validationOutcome = outcome;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public boolean isDrawCalled() {
        return drawCalled;
    }
}

