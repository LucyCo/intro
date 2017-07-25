/**
 * Class whose instances represent circles in the x/y plane. Circles can be moved by setting a new center point, 
 * and resized by setting a new radius.
 */
public class Circle {
	private double radius;
	private Point center;
	
	//constructor for a copy of a given circle
	/**
	 * Constructs a copy of the given Circle.
	 * @param circle the Circle to copy.
	 */
	public Circle(Circle circle) {
		this.center = circle.getCenter();	
		this.radius = circle.getRadius();
	}
	//constructor for a new circle
	/**
	 * Constructs a new Circle with the given center and radius.
	 * @param center the center Point of this Circle.
	 * @param radius the radius of this Circle.
	 */
	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	//returns the current center point of the circle
	/**
	 * Returns the current center Point of this Circle.
	 * @return the center Point of this Circle.
	 */
	public Point getCenter() {
		return center;
	}
	//returns the radius of the circle
	/**
	 * Returns the radius of this Circle.
	 * @return the radius of this Circle.
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * Determines whether the given point is inside this Circle (including on the perimeter).
	 * @param p the Point to check.
	 * @return whether p is inside this circle (including on the perimeter).
	 */
	public boolean isPointInside (Point p) {
		boolean isPointInside = (Math.pow((p.getX())-(getCenter().getX()), 2) + 
				Math.pow((p.getY())-(getCenter().getY()), 2)) <= Math.pow(getRadius(), 2); 
		if(isPointInside) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Set the center Point of this Circle.
	 * @param center the center for this Circle.
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	/**
	 * Sets the radius of this Circle.
	 * @param radius the new radius for this Circle.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}	
}
