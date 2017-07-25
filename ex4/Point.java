/**
 * Represents a two dimentional point.
 */
public class Point {
	private double newX;
	private double newY;
	/**
	 * Creates a new point with X and Y coordinates (0,0).
	 */
	public Point() {
		newX=0;
        newY=0;
	}
	/**
	 * Creates a new Point subject, with the given X and Y coordinates.
     * 
	 * @param x the X coordinate of the new point.
	 * @param y the Y coordinate of the new point.
	 */
	public Point(double x, double y) {
		newX=x;
		newY=y;
	}
	/**
	 * Returns the X coordinate of this point.
     * 
	 * @return the X coordinate of this point.
	 */
	public double getX() {
		return newX;
	}
	/**
	 * Returns the Y coordinate of this point.
     * 
	 * @return the Y coordinate of this point.
	 */
	public double getY() {
		return newY;
	}
	/**
	 * Returns a new point which is the result of multiplying this point by the given multiplier.
     * 
	 * @param multiplier the number to multiply by.
	 * @return a new point which is the result of multiplying this point by the given multiplier.
	 */
	public Point multiply(double multiplier){
		double multipliedX = newX*multiplier;
		double multipliedY = newY*multiplier;
		Point mul = new Point(multipliedX, multipliedY);
		return mul; 
	}
	/**
	 * Returns a new point which is the result of adding this point and the point given as parameter.
     * 
	 * @param other the other point to perform the operation with.
	 * @return a new point, whose coordinates are the addition of this and the other point's coordinates.
	 * In case the other point is null this method will return null.
	 */
	public Point plus(Point other){
		if (other==null){
			return null;
		}
        else{
		double plusX = (this.newX+other.getX());
		double plusY = (this.newY+other.getY());
		Point plus = new Point(plusX, plusY);
		return plus;
        } 
	}	
}


