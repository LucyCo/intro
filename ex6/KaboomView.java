import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A Kaboom! game GUI.
 */
public class KaboomView extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(0, 127, 0);			
    private Mediator mediator;
	
    /**
     * Constructs a KaboomView communicating with the {@link Game} logic
     * through the given {@link Mediator}.
     * 
     * @param m the Mediator object through which all the communication to the {@link Game} is performed.
     * @param width the width of the GUI, in pixels.
     * @param height the height of the GUI, in pixels.
     */
    public KaboomView(Mediator m, int width, int height) {
        mediator = m;
        m.setMouseLocation(
            new Point(MouseInfo.getPointerInfo().getLocation().getX(),
            MouseInfo.getPointerInfo().getLocation().getY()));
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension((int)width, (int)height));
    }
	
    /**
     * Starts the game timer.
     * After this is called, {@link Mediator.timerTick} is invoked about 60
     * times per second on the {@link Mediator} provided at construction time.
     */
    public void startGame() {
        // Add a listener for mouse motion.
        // Each time the mouse is moved, the handleMouseMove method
        // will be called.
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }});

        // Create the animation timer.
        // It will fire an event about 60 times per second.
        // Every time a timer event fires, the handleTimerEvent method
        // will be called.
        Timer timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTimerEvent();
            }});
            
        // Let the game begin.
        timer.start();
    }

    // Once startGame is called, this method is called by the timer about 60
    // times per second.
    protected void handleTimerEvent() {
        // You will probably not need to change this method.
    	mediator.timerTick();
    	repaint();  // Triggers a call to paintComponent.
    }
	
    // Once startGame is called, this method is called whenever the mouse is
    // moved.
    protected void handleMouseMove(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_MOVED) {        	
            mediator.setMouseLocation(new Point(e.getX(),e.getY()));
        }
    }

    // Called whenever the graphics have to be redrawn.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background
        mediator.drawGame(g);
    }    
}
