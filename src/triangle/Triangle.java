package triangle;

import javafx.geometry.Point2D;

/**
 * Triangle object for use in DrawTriangle class.
 * Consists of three points and an iteration number.
 *
 * @author Jenelle Yonkman | A00930379
 * @version 1.0
 */
public class Triangle {
    
    /**
     * First point.
     */
    private Point2D a;
    
    /**
     * Second point.
     */
    private Point2D b;
    
    /**
     * Third point.
     */
    private Point2D c;
    
    /**
     * First midpoint.
     */
    private Point2D midPointA;
    
    /**
     * Second midpoint.
     */
    private Point2D midPointB;
    
    /**
     * Third midpoint.
     */
    private Point2D midPointC;
    
    /**
     * Iteration number.
     */
    private int iterationNumber;
    
    /**
     * Constructs an object of type triangle.
     * @param a first point
     * @param b second point
     * @param c third point
     * @param iterationNumber iteration index
     */
    public Triangle(Point2D a, Point2D b, Point2D c, int iterationNumber) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.midPointA = getMidPoint(a, b);
        this.midPointB = getMidPoint(b, c);
        this.midPointC = getMidPoint(a, c);
        this.iterationNumber = iterationNumber;
    }
    
    /**
     * Calculates midpoint.
     * @param first first point
     * @param second second point
     * @return midpoint
     */
    private Point2D getMidPoint(Point2D first, Point2D second) {
        return new Point2D((first.getX() + second.getX()) / 2, 
                (first.getY() + second.getY()) / 2);
    }
    
    /**
     * Returns the a for this Triangle.
     * @return a
     */
    public Point2D getA() {
        return a;
    }

    /**
     * Returns the b for this Triangle.
     * @return b
     */
    public Point2D getB() {
        return b;
    }

    /**
     * Returns the c for this Triangle.
     * @return c
     */
    public Point2D getC() {
        return c;
    }

    /**
     * Returns the midPointA for this Triangle.
     * @return midPointA
     */
    public Point2D getMidPointA() {
        return midPointA;
    }

    /**
     * Returns the midPointB for this Triangle.
     * @return midPointB
     */
    public Point2D getMidPointB() {
        return midPointB;
    }

    /**
     * Returns the midPointC for this Triangle.
     * @return midPointC
     */
    public Point2D getMidPointC() {
        return midPointC;
    }
    
    /**
     * Returns the a for this Triangle.
     * @return a
     */
    public int getIterationNumber() {
        return iterationNumber;
    }
    

}
