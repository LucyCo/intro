import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * A Kaboom! runner.
 */
public class Kaboom {

    // Game parameters for this runner. (Test parameters vary!)

    private static final double GAME_WIDTH = 640;  // Gameplay field width.
    private static final double GAME_HEIGHT = 480;  // Gameplay field height.
	
    private static final double BOMBER_WIDTH = 80;
    private static final double BOMBER_HEIGHT = 80;
    private static final double BOMBER_TOP_LEFT_Y = GAME_HEIGHT/20;	
    private static final double BOMBER_INITIAL_TOP_LEFT_X = (GAME_WIDTH-BOMBER_WIDTH)/2;
    private static final double BOMBER_SPEED = 5;
	
    private static final double BUCKET_WIDTH = 120;	
    private static final double BUCKET_HEIGHT = 50;
    private static final double BUCKET_INITIAL_TOP_LEFT_X = (GAME_WIDTH-BUCKET_WIDTH)/2;
    private static final double BUCKET_TOP_LEFT_Y = GAME_HEIGHT - BUCKET_HEIGHT - GAME_HEIGHT/20;
    private static final double BUCKET_SPEED = 8;
	
    private static final double BOMBS_RADIUS = 25;
	
    private static final int BOMBS_FREQUENCY = 120;
    private static final int MIN_STEPS_TILL_DIRECTION_CHANGE = 30;
    private static final int MAX_STEPS_TILL_DIRECTION_CHANGE = 150;

    private static final int POINTS_PER_BOMB = 10;

    private static final double BOMBS_INITIAL_Y = BOMBER_TOP_LEFT_Y+BOMBER_HEIGHT;

    private static final double BOMBS_SPEED = 1;

    /**
     * Main method - runs a Kaboom! game.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the game parameters.
                GameParams params = new GameParams();
                params.setGameHeight(GAME_HEIGHT);
                params.setGameWidth(GAME_WIDTH);
                
                params.setBomberHeight(BOMBER_HEIGHT);
                params.setBomberWidth(BOMBER_WIDTH);		
                params.setBomberSpeed(BOMBER_SPEED);
                params.setBomberInitialTopLeftX(BOMBER_INITIAL_TOP_LEFT_X);
                params.setBomberTopLeftY(BOMBER_TOP_LEFT_Y);
                
                params.setBombsRadius(BOMBS_RADIUS);
                params.setBombsInitialY(BOMBS_INITIAL_Y);
                params.setBombsSpeed(BOMBS_SPEED);
                
                params.setBucketHeight(BUCKET_HEIGHT);
                params.setBucketWidth(BUCKET_WIDTH);		
                params.setBucketInitialTopLeftX(BUCKET_INITIAL_TOP_LEFT_X);
                params.setBucketTopLeftY(BUCKET_TOP_LEFT_Y);
                params.setBucketSpeed(BUCKET_SPEED);		
                
                params.setMaxStepsTillDirectionChange(MAX_STEPS_TILL_DIRECTION_CHANGE);
                params.setMinStepsTillDirectionChange(MIN_STEPS_TILL_DIRECTION_CHANGE);
                params.setPointsPerBomb(POINTS_PER_BOMB);
                params.setBombsFrequency(BOMBS_FREQUENCY);

                // Create the game, mediator and view.
                Game game = new Game(params);
                Mediator mediator = new Mediator(game);
                KaboomView view = new KaboomView(mediator, (int)GAME_WIDTH, (int)GAME_HEIGHT);

                // Create a frame (top-level window) to enclose the KaboomView.
                JFrame frame = new JFrame();
                frame.setTitle("Kaboom!");
                frame.setContentPane(view);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();

                // Show the frame.
                frame.setVisible(true);

                // Let the game begin.
                view.startGame();
            }
        });
    }
}
