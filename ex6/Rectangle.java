/**
 * A rectangle in the x/y plane, with sides parallel to the axes. 
 * Important: y coordinates increase in the down direction and decrease in the up direction.
 */
public class Rectangle {
	private double width;
	private double height;
	private Point topLeft;
	private Point bottomRight;
	
	//constructors
	/**
	 * Constructs a new Rectangle with the given top-left and bottom-right corners.
	 * @param topLeft the top-left corner of this Rectangle.
	 * @param bottomRight the bottom-right corner of this Rectangle.
	 */
	public Rectangle(Point topLeft, Point bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.width = Math.abs((topLeft.getX()) - (bottomRight.getX()));
		this.height = Math.abs( (bottomRight.getY()) - (topLeft.getY()));
	}
	
	/**
	 * Constructs a new Rectangle with the given dimensions the top-left corner.
	 * @param topLeft the top left corner of this Rectangle.
	 * @param width the width of this Rectangle.
	 * @param height the height of this Rectangle.
	 */
	public Rectangle(Point topLeft, double width, double height) {
		this.topLeft = topLeft;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Constructs a copy of the given Rectangle.
	 * @param rectangle the Rectangle to copy.
	 */
	public Rectangle(Rectangle rectangle) {
		this.topLeft = rectangle.getTopLeft();	
		this.width = rectangle.getWidth();
		this.height = rectangle.getHeight();	
	}

	//methods
	/**
	 * Returns the four corners of this Rectangle.
	 * @return an array of the corners of this rectangle. The first point in this array is the top-left corner of this Rectangle, 
	 * and following it are the other corners in clockwise order.
	 */
	public Point[] getCorners() {
		Point[] corners = new Point[4];
		Point widthX = new Point(width, 0);
		Point heightY = new Point(0, height);
		Point widthHeightXY = new Point(width, height);
		Point topL = topLeft;
		Point topR = (topLeft.plus(widthX));
		Point bottomR = (topLeft.plus(widthHeightXY));
		Point bottomL = (topLeft.plus(heightY));
		corners[0] = topL;
		corners[1] = topR;
		corners[2] = bottomR;
		corners[3] = bottomL;
		return corners;	
	}
	
	/**
	 * Gets the height of this Rectangle.
	 * @return the height of this Rectangle.
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Returns the top-left point of this Rectangle.
	 * @return the top-left point of this Rectangle.
	 */
	public Point getTopLeft() {
		return topLeft;
	}
	/**
	 * Returns the width of this Rectangle.
	 * @return the width of this Rectangle.
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * Determines whether a point is inside this Rectangle (including on the edges).
	 * @param p the Point to check.
	 * @return whether the p is inside this Rectangle (including on the edges).
	 */
	public boolean isPointInside(Point p) {
		boolean isPointInside = ((topLeft.getY()+height)>=p.getY() && p.getY()>=topLeft.getY() && 
								(topLeft.getX()+width)>=p.getX() && p.getX()>=topLeft.getX());
		if(isPointInside) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the height of this Rectangle, keeping the top side in place.
	 * @param height the new height for this Rectangle.
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Sets the top-left corner of this Rectangle.
	 * @param topLeft the new top-left corner for this Rectangle.
	 */
	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}
	
	/**
	 * Sets the width of this Rectangle, keeping the left side in place.
	 * @param width the new width for this Rectangle.
	 */
	public void setWidth(double width) {
		this.width = width;
	}
}