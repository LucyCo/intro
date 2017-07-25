import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

/**
 * Allows plotting of curves.
 * 
 * An example usage of the class to display a simple curve is as follows:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * Plotter p = new Plotter();
 * plotter.openWindow();
 * p.startCurve(java.awt.Color.red);
 * p.addToCurve(new Point(0, 1));
 * p.addToCurve(new Point(1, 0));
 * p.addToCurve(new Point(2, 2));
 * p.endCurve();
 * </pre>
 * 
 * </blockquote>
 * </p>
 * 
 * @author intro2cs team
 */
public class Plotter {

    /**
     * Error messages.
     */
    private static final String ERROR_COLOR_NULL = "Color must not be null.";
    private static final String ERROR_CURVE_ALREADY_EXISTS = 
            "Cannot start a new curve before ending the previous.";
    private static final String ERROR_CURVE_NOT_STARTED =
            "A curve must be started before calling this function.";
    private static final String ERROR_POINT_IS_NULL = "Point must not be null.";
    private static final String ERROR_BAD_POINT_X_VAL =
            "New point can't have a lower X value than previous ones.";
    private static final String ERROR_WINDOW_ALREADY_OPEN = 
            "Can't open more than one window.";

    /** 
     * Axis labels. 
     **/
    private static final String AXIS_LABEL_X = "X axis";
    private static final String AXIS_LABEL_Y = "Y axis";
    
    /**
     * Color for the background lines.
     */
    private static final Color BACKGROUND_LINES_COLOR = new Color(0.9f, 0.9f,
            0.9f);
    
    /**
     * Variables used for the plotting state machine. These represent the curve
     * currently being added.
     */
    private Color currentColor;
    private List<Point> currentCurve;

    /**
     * The JFrame instance to use as window.
     */
    private JFrame jFrame;

    /**
     * The PlotPanel on which we draw.
     */
    private PlotPanel plotPanel;

    /**
     * Maximal X,Y coordinates in the curves.
     */
    private double maxX;
    private double maxY;

    /**
     * Holds the curves to draw.
     */
    protected List<List<Point>> curves;

    /**
     * Holds the colors of the curves.
     */
    protected List<Color> curvesColor;

    /**
     * Constructs a new Plotter object.
     */
    public Plotter() {
        curves = new ArrayList<List<Point>>();
        curvesColor = new ArrayList<Color>();
        currentColor = null;
        currentCurve = null;

        // Initialize maxX and maxY::
        maxX = 0;
        maxY = 0;
        
    }

    /**
     * Opens a window to display plots in. Must be called once.
     */
    public void openWindow() {
        if(jFrame != null) {
            throw new RuntimeException(ERROR_WINDOW_ALREADY_OPEN);
        }
        // Initialize the window and plot panel:
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plotPanel = new PlotPanel();
        jFrame.add(plotPanel);
        jFrame.setSize(400, 400);
        jFrame.setLocation(200, 200);
        jFrame.setVisible(true);
    }
    
    /**
     * Starts plotting a new curve with the given color. Points are added to
     * this curve using the {@link #addToCurve(Point) addToCurve()} function, 
     * sequentially.
     * This function may only be called when another curve is not already in the
     * process of being plotted: it may be called directly after the creation of
     * this Plotter instance, and any additional call to this function must be
     * preceded by a call to {@link #endCurve() endCurve()}.
     * 
     * @param color
     *            the color to use for the new curve.
     */
    public void startCurve(Color color) {
        if (color == null) {
            throw new NullPointerException(ERROR_COLOR_NULL);
        }
        if (currentCurve != null) {
            throw new RuntimeException(ERROR_CURVE_ALREADY_EXISTS);
        }
        currentCurve = new ArrayList<Point>();
        currentColor = color;
    }

    /**
     * Adds a point to the currently plotted curve. The curve will contain a
     * linear interpolation between this point and the previous point that was
     * added (if such a previous point exists). Any call to this function must
     * be done while a curve is being plotted, i.e. a sequence of calls to this
     * function must be made between a call to 
     * {@link #startCurve(Color) startCurve()} and a call to
     * {@link #endCurve() endCurve()}.
     * 
     * The new point added must have an X coordinate that is greater or equal to
     * those of points previously added to the curve.
     * 
     * @param p
     *            the new point to add to the curve.
     */
    public void addToCurve(Point p) {
        if (currentCurve == null) {
            throw new RuntimeException(ERROR_CURVE_NOT_STARTED);
        }
        if (p == null) {
            throw new RuntimeException(ERROR_POINT_IS_NULL);
        }
        if (!currentCurve.isEmpty()
                && p.getX() < currentCurve.get(currentCurve.size() - 1)
                        .getX()) {
            throw new RuntimeException(ERROR_BAD_POINT_X_VAL);
        }

        currentCurve.add(p);
    }

    /**
     * Ends the curve currently being plotted and adds it to the plot window,
     * including all points added to it. This function must be called after a
     * call to {@link #startCurve(Color) startCurve()} which has been followed 
     * by a sequence of calls to {@link #addToCurve(Point) addToCurve()}.
     */
    public void endCurve() {
        if (currentCurve == null) {
            throw new RuntimeException(ERROR_CURVE_NOT_STARTED);
        }
        if (currentCurve.size() > 0) {
            addCurve(currentCurve, currentColor);
        }
        currentColor = null;
        currentCurve = null;
    }

    /**
     * Adds the curve with the given color.
     * 
     * @param curve
     *            the curve to add.
     * @param color
     *            the color the curve will be drawn in.
     */
    private void addCurve(List<Point> curve, Color color) {
        if (curve == null || color == null || curve.size() <= 0) {
            return;
        }
        curves.add(curve);
        curvesColor.add(color);

        // Update the maxX and maxY values:
        Iterator<Point> it = curve.iterator();
        while (it.hasNext()) {
            Point p = it.next();
            double x = p.getX();
            double y = p.getY();
            maxX = (x > maxX) ? x : maxX;
            maxY = (y > maxY) ? y : maxY;
        }

        
        if(plotPanel != null) {
            //In testing mode, there is no plotPanel.
            plotPanel.repaint();
        }
    }

    private class PlotPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        /**
         * The scale factors used for rendering.
         */
        private double xScale;
        private double yScale;

        /**
         * Padding between the axes and the borders.
         */
        private static final int PADDING = 20;

        public PlotPanel() {
            // Enable tooltips:
            ToolTipManager ttManager = ToolTipManager.sharedInstance();
            ttManager.registerComponent(this);
            ttManager.setInitialDelay(0);
            ttManager.setReshowDelay(0);
            enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            requestFocus();
        }

        /**
         * Display tooltips upon mouse motion.
         */
        @Override
        protected void processMouseMotionEvent(MouseEvent e) {
            super.processMouseMotionEvent(e);
            if (e.getID() == MouseEvent.MOUSE_MOVED) {
                int displayX = (int) Math.floor(xFromScreenCoord(e.getPoint()
                        .getX()));
                // int displayY =
                // (int)Math.floor(yFromScreenCoord(e.getPoint().getY()));

                if (displayX > maxX) {
                    displayX = (int) maxX;
                } else if (displayX < 1) {
                    displayX = 1;
                }
                String tooltip = "x = " + displayX;
                // tooltip += ", y = " + displayY;
                setToolTipText(tooltip);
            }
        }

        private void adjustScale() {
            xScale = (double) (plotPanel.getWidth() - 2 * PADDING) / maxX;
            yScale = (double) (plotPanel.getHeight() - 2 * PADDING) / maxY;
        }

        /**
         * Draws the x and y axes.
         * 
         * @param g
         *            the Graphics2D object used for drawing.
         */
        private void drawAxes(Graphics2D g) {
            int width = getWidth();
            int height = getHeight();

            // Draw the fine number lines:
            g.setColor(BACKGROUND_LINES_COLOR);
            for (int x = 1; x <= maxX; x++) {
                g.draw(new Line2D.Double(xToScreenCoord(x), PADDING,
                        xToScreenCoord(x), height - PADDING));
            }
            for (int y = 1; y <= maxY; y++) {
                g.draw(new Line2D.Double(PADDING, yToScreenCoord(y), width
                        - PADDING, yToScreenCoord(y)));
            }

            // Draw the main axis lines:
            g.setColor(Color.black);
            g.draw(new Line2D.Double(PADDING, PADDING, PADDING, height
                    - PADDING));
            g.draw(new Line2D.Double(PADDING, height - PADDING,
                    width - PADDING, height - PADDING));
        }

        /**
         * Draws the label of the y axis.
         * 
         * @param g2
         *            the 2D graphic object used for drawing.
         * @param yLabel
         *            the y axes label.
         */
        private void drawYLabel(Graphics2D g2, String yLabel) {
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = g2.getFont().getLineMetrics(yLabel, frc);
            float stringHeight = lm.getHeight(); // The height of the string.
            // Calculate the initial y location.
            float charyLocation = PADDING
                    + ((getHeight() - 2 * PADDING) - yLabel.length()
                            * stringHeight) / 2 + lm.getAscent();
            // Draw each letter at its location.
            for (int i = 0; i < yLabel.length(); i++) {
                String letter = String.valueOf(yLabel.charAt(i));
                // The x location depends on the width of the character.
                float charWidth = (float) font.getStringBounds(letter, frc)
                        .getWidth();
                float charXLocation = (PADDING - charWidth) / 2;
                // Draw this character.
                g2.drawString(letter, charXLocation, charyLocation);
                charyLocation += stringHeight;
            }
        }

        /**
         * Draws the label of the x axis.
         * 
         * @param g2
         *            the 2D graphic object used for drawing.
         * @param xLabel
         *            the x axes label.
         */
        private void drawXLabel(Graphics2D g2, String xLabel) {
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = g2.getFont().getLineMetrics(xLabel, frc);
            float stringHeight = lm.getHeight();
            float stringWidth = (float) font.getStringBounds(xLabel, frc)
                    .getWidth();
            float stringXStart = (getWidth() - stringWidth) / 2;
            float stringYStart = getHeight() - PADDING
                    + (PADDING - stringHeight) / 2 + lm.getAscent();
            g2.drawString(xLabel, stringXStart, stringYStart);
        }

        /**
         * Called upon repaint.
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            adjustScale();

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            drawAxes(g2);

            // Draw labels.
            g2.setColor(Color.black);
            drawYLabel(g2, AXIS_LABEL_Y);
            drawXLabel(g2, AXIS_LABEL_X);

            drawAllCurves(g2);
        }

        /**
         * Convert from x to screen-x.
         */
        private double xToScreenCoord(double x) {
            double coord = PADDING + xScale * x;
            if (coord < 1)
                coord = 1;
            if (coord >= getWidth())
                coord = getWidth() - 1;
            return coord;
        }

        /**
         * Convert from y to screen-y.
         */
        private double yToScreenCoord(double y) {
            double coord = (getHeight() - PADDING - yScale * y);
            if (coord < 1)
                coord = 1;
            if (coord >= getHeight())
                coord = getHeight() - 1;
            return coord;
        }

        /**
         * Convert from screen-x to x.
         */
        private double xFromScreenCoord(double screenX) {
            return (xScale == 0) ? 0 : ((screenX - PADDING) / xScale);
        }

        /**
         * Convert from screen-y to y.
         */
        private double yFromScreenCoord(double screenY) {
            return (yScale == 0) ? 0
                    : ((getHeight() - screenY - PADDING) / yScale);
        }

        /**
         * Draws all the curves.
         */
        private void drawAllCurves(Graphics2D g) {
            for (int i = 0; i < curves.size(); i++) {
                drawCurve(g, curves.get(i), curvesColor.get(i));
            }
        }

        /**
         * Draws a single curve.
         */
        private void drawCurve(Graphics2D g, List<Point> curve, Color color) {
            g.setColor(color);
            Iterator<Point> iter = curve.iterator();
            Point p = iter.next();
            while (iter.hasNext()) {
                Point p1 = iter.next();
                double x1 = xToScreenCoord(p.getX());
                double y1 = yToScreenCoord(p.getY());
                double x2 = xToScreenCoord(p1.getX());
                double y2 = yToScreenCoord(p1.getY());
                g.draw(new Line2D.Double(x1, y1, x2, y2));
                p = p1;
            }
        }

    }

}