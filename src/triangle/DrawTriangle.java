package triangle;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * <p>
 * On mouse drag, this program draws a Sierpinksi Gasket triangle with one point
 * at the cursor centered at the circle.</p>
 *
 * @author Jenelle Yonkman
 * @version 1.0
 */
public class DrawTriangle extends Application {
    
    
    /**Maximum iterations. Adjust for more iterations.*/
    private static final int MAX_ITERATIONS = 6;
    
    /**
     * Sides of a triangle.
     */
    private static final int THREE = 3;
    
    /**
     * Number of degrees for first rotation.
     */
    private static final int ONE_TWENTY_DEGREES = 120;
    
    /**
     * Number of degrees for second rotation.
     */
    private static final int TWO_FORTY_DEGREES = 240;
    
    /**
     * The center X of the triangle.
     */
    private double centerX;
    
    /**
     * The center Y of the triangle.
     */
    private double centerY;
    
    /** The contents of the application scene. */
    private Group root;
    
    /** circle to move to first mouse click location. */
    private Circle atCenter = new Circle(0, 0, THREE);
   
    /**
     * Displays an initially empty scene, waiting for 
     * the user to select a location with the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        root = new Group(atCenter);
        atCenter.setFill(Color.CYAN);

        final int appWidth = 800;
        final int appHeight = 500;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);
        
        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);

        primaryStage.setTitle("Sierpinski Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    /**
     * Adds a new line to the scene when the mouse button is pressed.
     * 
     * @param event
     *            invoked this method
     */
    public void processMousePress(MouseEvent event) {
        centerX = event.getX();
        centerY = event.getY();
        atCenter.setCenterX(centerX);
        atCenter.setCenterY(centerY);
        
    }
    
    /**
     * Draws a polygon from a triangle.
     * @param childTriangle Triangle to draw
     */
    public void drawChildTriangle(Triangle childTriangle) {
        Polygon childPolygon = new Polygon(childTriangle.getA().getX(), 
                childTriangle.getA().getY(), 
                childTriangle.getB().getX(), 
                childTriangle.getB().getY(), 
                childTriangle.getC().getX(), 
                childTriangle.getC().getY());
        childPolygon.setStroke(Color.WHITE);
        childPolygon.setFill(Color.BLACK);
        root.getChildren().add(childPolygon); 
    }
    
    /**
     * Recursively creates child triangles from a parent.
     * @param parentTriangle Parent triangle
     */
    public void createChildTriangle(Triangle parentTriangle) {
        if (parentTriangle.getIterationNumber() != MAX_ITERATIONS) {
            Triangle[] childTriangles = new Triangle[THREE];
            for (int i = 0; i < THREE; i++)  {
                if (i == 0) {
                    childTriangles[i] = new Triangle(parentTriangle.getA(), 
                            parentTriangle.getMidPointA(), 
                            parentTriangle.getMidPointC(), 
                            parentTriangle.getIterationNumber() + 1);
                    drawChildTriangle(childTriangles[i]);
                    createChildTriangle(childTriangles[i]);
                    
                } else if (i == 1) {
                    childTriangles[i] = new Triangle(parentTriangle.getC(), 
                            parentTriangle.getMidPointB(), 
                            parentTriangle.getMidPointC(), 
                            parentTriangle.getIterationNumber() + 1);
                    drawChildTriangle(childTriangles[i]);
                    createChildTriangle(childTriangles[i]);
                } else if (i == 2) {
                    childTriangles[i] = new Triangle(parentTriangle.getB(), 
                            parentTriangle.getMidPointB(), 
                            parentTriangle.getMidPointA(), 
                            parentTriangle.getIterationNumber() + 1);
                    drawChildTriangle(childTriangles[i]);
                    createChildTriangle(childTriangles[i]);
                }
            }
        }
    }
    

    /**
     * Draws triangles around centre point when mouse is dragged.
     * 
     * @param event
     *            invoked this method
     */
    public void processMouseDrag(MouseEvent event) {
        root.getChildren().clear();


        Point2D a = new Point2D(event.getX(), event.getY());
        Rotate oneTwenty = new Rotate(ONE_TWENTY_DEGREES, centerX, centerY);
        Point2D b = oneTwenty.transform(a);
        Rotate twoForty = new Rotate(TWO_FORTY_DEGREES, centerX, centerY);
        Point2D c = twoForty.transform(a);
        
        Triangle firstTriangle = new Triangle(a, b, c, 0);
      
        Polygon firstPolygon = new Polygon(firstTriangle.getA().getX(), 
                firstTriangle.getA().getY(), 
                firstTriangle.getB().getX(), 
                firstTriangle.getB().getY(), 
                firstTriangle.getC().getX(), 
                firstTriangle.getC().getY());
        

        firstPolygon.setStroke(Color.BLUE);
        firstPolygon.setFill(Color.TRANSPARENT);
        root.getChildren().add(firstPolygon);
        createChildTriangle(firstTriangle);


    }


    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

