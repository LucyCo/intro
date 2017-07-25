import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import il.ac.huji.cs.intro.junit.runners.IntroJUnit4ClassRunner;

/**
 * Tests the rectangle class.
 */
@RunWith(IntroJUnit4ClassRunner.class)
public class RectangleTester {

    private static final double EPSILON=0.000001;
    private final double X = -2;
    private final double Y = 4;
    private final Point TOPLEFT = new Point(-2, 4);
    private final Point BOTTOMRIGHT = new Point();
    private final Rectangle RECTANGLE = new Rectangle(TOPLEFT, BOTTOMRIGHT);
    private final double widthCalculate =  Math.abs(RECTANGLE.getTopLeft().getX()-BOTTOMRIGHT.getX());
    private final double heightCalculate =  Math.abs(BOTTOMRIGHT.getY()-RECTANGLE.getTopLeft().getY());
    
    /**
     * Tests two of three constructors of the rectangle class.
     */
    @Test
    public void testConstructor1And3() {
        Rectangle checkRec = new Rectangle(RECTANGLE);
        assertEquals(widthCalculate, checkRec.getWidth(), EPSILON); 
        assertEquals(heightCalculate, checkRec.getHeight(), EPSILON);
        
        Rectangle checkRec1 = new Rectangle(TOPLEFT, BOTTOMRIGHT);
        assertEquals(widthCalculate, checkRec1.getWidth(), EPSILON);
        assertEquals(heightCalculate, checkRec1.getHeight(), EPSILON);
    }

    /**
     * Tests the second constructor of the rectangle class.
     */
    @Test 
    public void testConstructor2() {
        Rectangle checkRec1 = new Rectangle(TOPLEFT, widthCalculate, heightCalculate);
    	assertEquals(X, checkRec1.getTopLeft().getX(), EPSILON);
    	assertEquals(Y, checkRec1.getTopLeft().getY(), EPSILON);
    	assertEquals(widthCalculate, checkRec1.getWidth(), EPSILON);
    	assertEquals(heightCalculate, checkRec1.getHeight(), EPSILON);
    }
    
    /**
     * Tests get corners of the rectangle.
     */
    @Test
    public void testGetCorners() {
    	assertEquals(-2, RECTANGLE.getCorners()[0].getX() , EPSILON);
    	assertEquals(4, RECTANGLE.getCorners()[0].getY() , EPSILON);
    	assertEquals(0, RECTANGLE.getCorners()[1].getX() , EPSILON);
       	assertEquals(4, RECTANGLE.getCorners()[1].getY() , EPSILON);
    	assertEquals(0, RECTANGLE.getCorners()[2].getX() , EPSILON);
       	assertEquals(8, RECTANGLE.getCorners()[2].getY() , EPSILON);
    	assertEquals(-2, RECTANGLE.getCorners()[3].getX() , EPSILON);
    	assertEquals(8, RECTANGLE.getCorners()[3].getY() , EPSILON);
    }
    
    /**
     * Tests get height of the rectangle.
     */
    @Test
    public void testGetHeight() {
    	assertEquals(heightCalculate, RECTANGLE.getHeight(), EPSILON);
    }

    /**
     * Tests get top left point of the rectangle.
     */
    @Test
    public void testGetTopLeft() {
    	assertEquals(TOPLEFT.getX(), RECTANGLE.getTopLeft().getX(), EPSILON);
    	assertEquals(TOPLEFT.getY(), RECTANGLE.getTopLeft().getY(), EPSILON);

    }
    
    /**
     * Tests get width of the rectangle.
     */
    @Test
    public void testGetWidth() {
     	assertEquals(widthCalculate, RECTANGLE.getWidth(), EPSILON);
    }
	private final Point point1 = new Point(-1, 7);//true
	private final Point point2 = new Point(-1, -1);//false
	private final Point point3 = new Point(-1, 9);//false
    private final double widthHeight = 0;
    private final double widthHeight1 = 5;
 
    /**
     * Tests the is point inside method.
     */
    @Test
    public void testIsPointInside() {
    	assertTrue(RECTANGLE.isPointInside(point1));
       	assertFalse(RECTANGLE.isPointInside(point2));
       	assertFalse(RECTANGLE.isPointInside(point3));
    }
    
    /**
     * Tests set top left point.
     */
    @Test
    public void testSetTopLeft() {
    	RECTANGLE.setTopLeft(point1);
    	assertEquals(point1.getX(), RECTANGLE.getTopLeft().getX(), EPSILON);
    	assertEquals(point1.getY(), RECTANGLE.getTopLeft().getY(), EPSILON);
    }
    
    /**
     * Tests set height of the rectangle.
     */
    @Test
    public void testSetHeight() {
    	RECTANGLE.setHeight(widthHeight);
    	assertEquals(widthHeight, RECTANGLE.getHeight(), EPSILON);
    	RECTANGLE.setHeight(widthHeight1);
    	assertEquals(widthHeight1, RECTANGLE.getHeight(), EPSILON);
    }
    
    /**
     * Tests set width of the rectangle.
     */
    @Test
    public void testSetWidth() {
    	RECTANGLE.setWidth(widthHeight);
    	assertEquals(widthHeight, RECTANGLE.getWidth(), EPSILON);
    	RECTANGLE.setWidth(widthHeight1);
    	assertEquals(widthHeight1, RECTANGLE.getWidth(), EPSILON);
    }
}
