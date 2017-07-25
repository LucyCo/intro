import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import il.ac.huji.cs.intro.junit.runners.IntroJUnit4ClassRunner;

/**
 * Tests the circle class.
 */
@RunWith(IntroJUnit4ClassRunner.class)
public class CircleTester {

    private static final double EPSILON=0.000001;

    private static int failedTests=0;
    final double RADIUS = 5;   
    final static double X = 3;
    final static double Y = 4;
    final Point POINT = new Point(X, Y);
    Circle CIRCLE = new Circle(POINT, RADIUS);
	
    /**
 	 * Tests the constructors of the class.
     */
    @Test
    public void testConstructors() {
        Circle checkCircle = new Circle(CIRCLE);
        assertEquals(RADIUS, checkCircle.getRadius(), EPSILON); 
        assertEquals(X, checkCircle.getCenter().getX(), EPSILON);
        assertEquals(Y, checkCircle.getCenter().getY(), EPSILON);        
    }
    
    /**
     * Tests get center of circle.
     */
    @Test 
    public void testGetCenter() {
    	assertEquals(X, CIRCLE.getCenter().getX(), EPSILON);
    	assertEquals(Y, CIRCLE.getCenter().getY(), EPSILON);
    }
    
    /**
     * Tests get radius of circle.
     */
    @Test
    public void testGetRadius() {
    	assertEquals(RADIUS, CIRCLE.getRadius(), EPSILON);
    }
    private final Point point1 = new Point(-5, 3);//false
    private final Point point2 = new Point(-1, 0);//false
    private final Point point3 = new Point(3, 4);//true
    private final Point point4 = new Point(5.5, 4);//true
    private final Point point5 = new Point(8, 4);//true
    private final double rad1 = 0;
    private final double rad2 = -5;
    
    /**
     * Tests if the given points are inside of circle.
     */
    @Test
    public void testIsPointInside() {
    	assertFalse(CIRCLE.isPointInside(point1));
    	assertFalse(CIRCLE.isPointInside(point2));
    	assertTrue(CIRCLE.isPointInside(point3));
    	assertTrue(CIRCLE.isPointInside(point4));
    	assertTrue(CIRCLE.isPointInside(point5));
    }

    /**
     * Tests set center of circle.
     */
    @Test
    public void testSetCenter() {
    	CIRCLE.setCenter(point1);
    	assertEquals(-5, CIRCLE.getCenter().getX(), EPSILON);
    	assertEquals(3, CIRCLE.getCenter().getY(), EPSILON);
    	CIRCLE.setCenter(point3);
    	assertEquals(3, CIRCLE.getCenter().getX(), EPSILON);
    	assertEquals(4, CIRCLE.getCenter().getY(), EPSILON);
    }
    
    /**
     * Tests set radius of circle.
     */
    @Test
    public void testSetRadius() {
    	CIRCLE.setRadius(rad1);
    	assertEquals(rad1, CIRCLE.getRadius(), EPSILON);
    	CIRCLE.setRadius(rad2);
    	assertEquals(rad2, CIRCLE.getRadius(), EPSILON);
    }
}
