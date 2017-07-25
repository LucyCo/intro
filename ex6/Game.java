import java.util.ArrayList;
import java.util.Random;

/**
 * A complete, yet abstract, representation of a Kaboom! game state, including the position of the bucket, 
 * the position/direction of the bomber, the position(s) of the bombs, etc.
 */
public class Game {
	private static GameParams params;
	private ArrayList<Circle> bombs;
	private Rectangle bomber;
	private Circle bomb;
	private Rectangle bucket;
	private int score;
	private int count;
	private int step;
	private int random;
	private Point initialBomber;
	private Point initialBucket;
	private Random randomDirection;
	private boolean gameOver;
	private boolean direction; //left is false, right is true.
	private Point bomberCenter;
	
	/**
	 * Constructs a new Game governed by the given parameters. The entire settings of the game (e.g. sizes of objects) are specified by the given GameParams object. 
	 * The score is reset to 0, the positions of the bomber and of the bucket are initialized according to the game parameters, 
	 * and the starting direction of the bomber is set to the left. A bomb is dropped by the bomber at the instance the game begins.
	 * @param params the parameters governing this Game.
	 */
	public Game(GameParams params) {
		//new random class for the direction change
		randomDirection = new Random();
		this.params = params;
		//sets score to 0
		this.score = 0;
		//sets number of bombs to 0
		//starting coordinate of bomber
		this.initialBomber = new Point(params.getBomberInitialTopLeftX(), params.getBomberTopLeftY());
		//starting coordinate of bucket
		this.initialBucket = new Point(params.getBucketInitialTopLeftX(), params.getBucketTopLeftY());
		//create bomber
		this.bomber = new Rectangle(initialBomber, params.getBomberWidth(), params.getBomberHeight());
		//create bucket
		this.bucket = new Rectangle(initialBucket, params.getBucketWidth(), params.getBucketHeight());
		//create an empty arraylist for bombs
		this.bombs = new ArrayList<Circle>();
		//calculates the center of the bomber.
		this.bomberCenter = new Point((this.bomber.getTopLeft().getX()+(this.bomber.getWidth())/2), 
					this.bomber.getTopLeft().getY()+(this.bomber.getHeight())/2);
		//adds a new bomb object to arraylist creates circle in the center of the bomber
		this.newBomb();
		this.random = 0;
		//resets the random method for direction change
		this.random();
		this.count = 0;
		this.step = 0;
		//sets starting direction to left;
		this.direction = true;
	}

	//creates a new bomb and adds the bomb to the arraylist.
	private void newBomb() {
		this.bomberCenter = new Point((this.bomber.getTopLeft().getX()+(this.bomber.getWidth())/2), 
				params.getBombsInitialY());
		this.bomb = new Circle(this.bomberCenter, params.getBombsRadius());
		this.bombs.add(bomb);
	}

	/**
	 * Returns the geometry of one of the bombs in this Game.
	 * @param i the index of the requested bomb. Assumed to be ≥ 0 and < the value returned by numberOfBombs. 
	 * While the order of bombs is not guaranteed, it is guaranted that between calls to step, each bomb is consistently indexed by exactly one index. 
	 * (Thus, iterating over all indices will return all bombs, each one exactly once.)
	 * @return the geometry of the bomb indexed as i in the current game step.
	 */
	public Circle getBomb(int i) {
		return new Circle((Circle)bombs.get(i));
	}
	
	/**
	 * Returns the current geometry of the bomber in this Game.
	 * @return the current geometry of the bomber in this Game.
	 */
	public Rectangle getBomber() {
		Rectangle bomberCopy = new Rectangle(this.bomber);
		return bomberCopy;
	}
	
	/**
	 * Returns the current geometry of the bucket in this Game.
	 * @return the current geometry of the bucket in this Game.
	 */
	public Rectangle getBucket() {
		Rectangle bucketCopy = new Rectangle(this.bucket);
		return bucketCopy;
	}
	
	/**
	 * Return the score of this Game.
	 * @return the score of this Game.
	 */
	public int getScore() {
		return this.score*params.getPointsPerBomb();
	}
	
	/**
	 * Checks whether this Game is over.
	 * @return whether this Game is over.
	 */
	public boolean isOver() {
		return gameOver;
	}
	
	/**
	 * Moves the bucket left according to the speed specified by the game parameters.
	 */
	public void moveBucketLeft() {
		Point moveBucketLeft = new Point(this.bucket.getTopLeft().getX()-params.getBucketSpeed(),
				this.bucket.getTopLeft().getY());
		this.bucket.setTopLeft(moveBucketLeft);
	}
	
	/**
	 * Moves the bucket right according to the speed specified by the game parameters.
	 */
	public void moveBucketRight() {
		Point moveBucketRight = new Point(this.bucket.getTopLeft().getX()+params.getBucketSpeed(),
				this.bucket.getTopLeft().getY());
		if (this.bucket.getTopLeft().getX()+this.bucket.getWidth()<params.getGameWidth()) {
			this.bucket.setTopLeft(moveBucketRight);
		}
	}
	
	/**
	 * Returns the current number of bombs in this Game.
	 * @return the current number of bombs in this Game.
	 */
	public int numberOfBombs() {
		return bombs.size();
	}
	
	//this method creates a random number of steps before direction change.
	private void random(){
		int rand = randomDirection.nextInt(params.getMaxStepsTillDirectionChange()-params.getMinStepsTillDirectionChange());
		rand += params.getMinStepsTillDirectionChange();
		this.random = rand;
		}
	//this method moves the bomber to the right or to the left
	private void moveBomberLeftOrRight(){
		if (direction){
			Point moveBomberLeft = new Point(this.bomber.getTopLeft().getX()-params.getBomberSpeed(),this.bomber.getTopLeft().getY());
			this.bomber.setTopLeft(moveBomberLeft);
		}
		else{
			Point moveBomberRight = new Point(this.bomber.getTopLeft().getX()+params.getBomberSpeed(),this.bomber.getTopLeft().getY());
			this.bomber.setTopLeft(moveBomberRight);	
			}
		}
		
	/**
	 * Advances this game by one step.
	 * Moves the bomber right or left (in a "random" manner). The bomber should be in constant motion, but does not move in a predictable pattern. 
	 * Once the bomber commences in a particular direction (left or right), it continues to move in that direction for a randomly-
	 * chosen number of game steps within the bounds specified by the game parameters. 
	 * If it reaches the edge of the gameplay field, it always changes direction.
	 * Moves all bombs down toward the bottom of the game play field.
	 * Checks whether the bucket has caught one or more bombs; a bomb is caught if its center is inside the bucket. 
	 * The score increases by the amount specified by the game parameters for every bomb caught.
	 * Checks whether any bomb has reached the bottom of the gameplay field (i.e. whether the bottom of any bomb has reached the bottom of the gameplay field), 
	 * in which case the game is over.
	 * Possibly dropping a bomb by the bomber, according to the game parameters.
	 */
	public void step() {
		++count;
		++step;
		if(!gameOver) {
			if (step > this.random || (this.bomber.getTopLeft().getX() + this.bomber.getWidth() + params.getBomberSpeed()) 
					> params.getGameWidth() || this.bomber.getTopLeft().getX() - params.getBomberSpeed() < 0){
				this.random();
				step = 0;
				if (direction) {
					direction = false;
				}
				else {
					direction = true;
				}
			}
			this.moveBomberLeftOrRight();
			if (count==params.getBombsFrequency()) {
				newBomb();
				this.count = 0;
			}
			for (int i=0; i<bombs.size(); i++) {
				Point moveBombs = new Point(bombs.get(i).getCenter().getX(), bombs.get(i).getCenter().getY()+params.getBombsSpeed());
				bombs.get(i).setCenter(moveBombs);
			}
			for (int i=0; i<bombs.size(); i++) {
				if (this.bucket.isPointInside(bombs.get(i).getCenter())) {
					bombs.remove(i);
					this.score++;
				}
			}
			for (int i=0; i<bombs.size(); i++) {
				if ((bombs.get(i).getCenter().getY())>=params.getGameHeight()) {
					gameOver=true;
				}
			}
		}
	}
}