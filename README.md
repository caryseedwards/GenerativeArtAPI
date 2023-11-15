# Artwork Generator

## About
Generates artwork
todo...

## General
Can be run with GUI mode or headless 

todo...

### Build
`mvn clean install`

### Run
Without GoF GUI: 
With GoF GUI: 


### Maven targets
PMD metrics: `mvn clean verify` generates in `target/site/pmd.html`
https://github.com/pmd/pmd/tree/master/pmd-java/src/main/resources/rulesets/java

Jacoco coverage: `mvn clean verify`

### Other commands
Checkstyle (Google) `java -jar checkstyle-*-all.jar -c /google_checks.xml src/ -o report.txt`


## Generate Artwork API Usage 

### Example 1: With GoF strategy pattern 
```java
public static void main(String[] args) {
    AlgorithmStrategy strategy = new ...ShapeStrategy();
    AlgorithmContext context = new AlgorithmContext();
    JFrame frame = new JFrame();
    context.setStrategy(strategy);
    context.executeAlgorithm();
    frame.add(context);
    frame.setSize(1000, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}

public static AlgorithmStrategy recursiveShapeStrategy() {
    CanvasParameters canvas = new CanvasParameters(800, 800, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("triangle", 1, Color.BLACK, Color.BLACK));
    shapes.add(new ShapeParameters("square", 1, Color.BLACK, Color.YELLOW));
    RecursiveShapeAlgorithmParameters algorithm = new RecursiveShapeAlgorithmParameters(250, 250, 100, 4, 6);
    return new RecursiveShapeAlgorithm(canvas, shapes, algorithm);
}

public static AlgorithmStrategy circlePackingStrategy() {
    CanvasParameters canvas = new CanvasParameters(800, 800, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("hexagon", 1, Color.BLACK, Color.WHITE));
    shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.WHITE));
    CirclePackingAlgorithmParameters algorithm = new CirclePackingAlgorithmParameters(250, 250, 200, 5, 50, 100, 1);
    return new CirclePackingAlgorithm(canvas, shapes, algorithm);
}

public static AlgorithmStrategy sierpinskiShapeStrategy() {
    CanvasParameters canvas = new CanvasParameters(1200, 1200, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("circle", 0.1f, Color.BLACK, Color.WHITE));
    SierpinskiShapeAlgorithmParameters algorithm = new SierpinskiShapeAlgorithmParameters(400, 400, 100, 4);
    return new SierpinskiShapeAlgorithm(canvas, shapes, algorithm);
}
```

### Example 2: Sierpinski without GoF: 
```java
public static void main(String[] args) {
    JFrame frame = new JFrame("Sierpinski Shape");
    CanvasParameters canvas = new CanvasParameters(800, 800, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("triangle", 0.1f, Color.BLACK, Color.WHITE));
    SierpinskiShapeAlgorithmParameters algorithm = new SierpinskiShapeAlgorithmParameters(400, 1200, 400, 5);
    SierpinskiShapeAlgorithm sierpinskiShape = new SierpinskiShapeAlgorithm(canvas, shapes, algorithm);
    frame.add(sierpinskiShape);
    frame.setSize(canvas.getWidth(), canvas.getHeight());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}
```

### Example 3: CirclePacking without GoF:
```java
public static void main(String[] args) {
    JFrame frame = new JFrame("Circle Packing in Shapes");
    CanvasParameters canvas = new CanvasParameters(500, 500, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.WHITE));
    shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.WHITE));
    CirclePackingAlgorithmParameters algorithm = new CirclePackingAlgorithmParameters(250, 250, 200, 5, 50, 100, 1);
    CirclePackingAlgorithm packing = new CirclePackingAlgorithm(canvas, shapes, algorithm);
    packing.executeAlgorithm();
    frame.add(packing);
    frame.setSize(canvas.getWidth(), canvas.getHeight());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    Timer timer = new Timer(packing.params.animationSpeed, e -> {
        packing.addCircles();
        packing.repaint();
    });
    timer.start();
}
```

### Example 4: Recursive without GoF:
```java
public static void main(String[] args) {
    JFrame frame = new JFrame("Recursive Pattern");
    CanvasParameters canvas = new CanvasParameters(500, 500, Color.WHITE);
    ArrayList<ShapeParameters> shapes = new ArrayList<>();
    shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.BLACK));
    shapes.add(new ShapeParameters("circle", 1, Color.BLACK, Color.YELLOW));
    RecursiveShapeAlgorithmParameters algorithm = new RecursiveShapeAlgorithmParameters(250, 250, 100, 4, 6);
    RecursiveShapeAlgorithm pattern = new RecursiveShapeAlgorithm(canvas, shapes, algorithm);
    pattern.executeAlgorithm();
    frame.add(pattern);
    frame.setSize(canvas.getHeight(), canvas.getWidth());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}
```
