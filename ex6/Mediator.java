import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A mediator between a Game logic and a KaboomView) GUI, responsible for translating timer ticks and mouse movements to Game messages, 
 * and for graphically drawing the Game. The API does not guarantee the exact details of how the game is drawn.
 *  (E.g. it does not guarantee the colors.)
 *
 */
public class Mediator {
	private Game newGame;
	private Graphics g;
	private Point mouseLocation;
	private final int SCOREPOSX = 50;
	private final int SCOREPOSY = 50;
	private final String STRING = "Score : ";
	private final int SCORESIZE = 20;
	private final int GAMEOVERPOSX = 130;
	private final int GAMEOVERPOSY = 250;
	private final int GAMEOVERSIZE = 70;
	private final int ADDTORIGHT = 10;
	private final int ADDTOLEFT = -10;
	
	/**
	 * Constructor a Mediator object encapsulating a given Game object.
	 * @param game the Game object to encapsulate.
	 */
	public Mediator(Game game) {
		this.newGame = game;
	}

	/**
	 * Paints the entire content of the encapsulated Game, including the bucket, bomber, bombs, 
	 * the current score and the game over message if relevant. Called by the KaboomView whenever the game graphics are to be refreshed.
	 * @param g the Graphics object to draw on.
	 */
	public void drawGame(Graphics g) {
		this.g = g;
		//bomber
		int bomberHeight = (int)newGame.getBomber().getHeight();
		int bomberWidth = (int)newGame.getBomber().getWidth();
		int bomberInitialX = (int)newGame.getBomber().getTopLeft().getX();
		int bomberInitialY = (int)newGame.getBomber().getTopLeft().getY();
		g.setColor(Color.BLUE);
		g.fillRect(bomberInitialX, bomberInitialY, bomberWidth, bomberHeight);
		//bucket
		int bucketHeight = (int)newGame.getBucket().getHeight();
		int bucketWidth = (int)newGame.getBucket().getWidth();
		int bucketInitialX = (int)newGame.getBucket().getTopLeft().getX();
		int bucketInitialY = (int)newGame.getBucket().getTopLeft().getY();		
		g.setColor(Color.gray);
		g.fillRect(bucketInitialX, bucketInitialY, bucketWidth, bucketHeight);
		//bomb
		for (int i=0; i < newGame.numberOfBombs(); i++) {
			int bombRadius = (int)newGame.getBomb(i).getRadius();
			int bombInitialX = (int)newGame.getBomb(i).getCenter().getX();
			int bombInitialY = (int)newGame.getBomb(i).getCenter().getY();
			g.setColor(Color.BLACK);
			g.fillOval(bombInitialX-bombRadius, bombInitialY-bombRadius, bombRadius, bombRadius);
		}
		//score
		String score = String.valueOf(newGame.getScore());
		g.setColor(Color.WHITE);
		g.setFont(new Font(null,Font.PLAIN,SCORESIZE));
		g.drawString(STRING + score, SCOREPOSX, SCOREPOSY);
		//gameover
		if(newGame.isOver()) {
			String gameOver = new String("Game Over");
			g.setColor(Color.RED);
			g.setFont(new Font(null,Font.PLAIN,GAMEOVERSIZE));
			g.drawString(gameOver, GAMEOVERPOSX, GAMEOVERPOSY);
		}
	}
	/**
	 * Registers a mouse movement. Called by the KaboomView whenever the mouse is moved.
	 * @param newLocation the new location of the cursor.
	 */
	public void setMouseLocation(Point newLocation) {
		this.mouseLocation = newLocation;
	}
	
	/**
	 * Updates the bucket location (if necessary) and calls Game.step on the encapsulates Game. 
	 * Called by the KaboomView about 60 times per second.
	 */
	public void timerTick() {
		newGame.step();
		if (newGame.isOver() == false) {
			if (this.mouseLocation.getX() > ADDTORIGHT + newGame.getBucket().getTopLeft().getX()) {
				newGame.moveBucketRight();
			}
			if (this.mouseLocation.getX() < ADDTOLEFT + newGame.getBucket().getTopLeft().getX()) {
				newGame.moveBucketLeft();
			}
		}
	}
}