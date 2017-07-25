/**
 * A container for all the parameters of a Kaboom! game.
 *
 * @author Intro2cs team
 */
public class GameParams {

    private double gameWidth;
    private double gameHeight;
	
    private double bomberWidth;
    private double bomberHeight;
    private double bomberTopLeftY;	
    private double bomberInitialTopLeftX;
    private double bomberSpeed;
	
    private double bucketWidth;	
    private double bucketHeight;
    private double bucketInitialTopLeftX;
    private double bucketTopLeftY;
    private double bucketSpeed;
	
    private double bombsRadius;
    private double bombsSpeed;
    private int pointsPerBomb;
    private double bombsInitialY;
    
    private int bombsFrequency;
    private int minStepsTillDirectionChange;
    private int maxStepsTillDirectionChange;  


    /**
     * Returns the bucket's speed.
     *
     * @return the bucket's speed.
     */
    public double getBucketSpeed() {
        return bucketSpeed;
    }

    /**
     * Sets the bucket's speed.
     *
     * @param bucketSpeed the new speed for the bucket.
     */
    public void setBucketSpeed(double bucketSpeed) {
        this.bucketSpeed = bucketSpeed;
    }

    /**
     * Returns the gameplay field's height.
     *
     * @return the gameplay field's height.
     */
    public double getGameHeight() {
        return gameHeight;
    }

    /**
     * Sets the gameplay field's height.
     *
     * @param gameHeight the new height for the gameplay field.
     */
    public void setGameHeight(double gameHeight) {
        this.gameHeight = gameHeight;
    }

    /**
     * Returns the bomber's width.
     *
     * @return the bomber's width.
     */
    public double getBomberWidth() {
        return bomberWidth;
    }

    /**
     * Sets the bomber's width.
     *
     * @param bomberWidth the new width for the bomber.
     */
    public void setBomberWidth(double bomberWidth) {
        this.bomberWidth = bomberWidth;
    }

    /**
     * Returns the gameplay field's width.
     *
     * @return the gameplay field's width.
     */
    public double getGameWidth() {
        return gameWidth;
    }

    /**
     * Sets the gameplay field's width.
     *
     * @param gameWidth the new width for the gameplay field.
     */
    public void setGameWidth(double gameWidth) {
        this.gameWidth = gameWidth;
    }

    /**
     * Returns the bomber's height.
     *
     * @return the bomber's height.
     */
    public double getBomberHeight() {
        return bomberHeight;
    }

    /**
     * Sets the bomber's height.
     *
     * @param bomberHeight the new height for the bomber.
     */
    public void setBomberHeight(double bomberHeight) {
        this.bomberHeight = bomberHeight;
    }

    /**
     * Returns the bomber's top-left y coordinate.
     *
     * @return the bomber's top-left y coordinate.
     */
    public double getBomberTopLeftY() {
        return bomberTopLeftY;
    }

    /**
     * Sets the bomber's top-left y coordinate.
     *
     * @param bomberTopLeftY the new top-left y coordinate for the bomber.
     */
    public void setBomberTopLeftY(double bomberTopLeftY) {
        this.bomberTopLeftY = bomberTopLeftY;
    }

    /**
     * Returns the bomber's initial top-left x coordinate.
     *
     * @return the bomber's initial top-left x coordinate.
     */
    public double getBomberInitialTopLeftX() {
        return bomberInitialTopLeftX;
    }

    /**
     * Sets the bomber's initial top-left x coordinate.
     *
     * @param bomberInitialTopLeftX the initial top-left x coordinate for the bomber.
     */
    public void setBomberInitialTopLeftX(double bomberInitialTopLeftX) {
        this.bomberInitialTopLeftX = bomberInitialTopLeftX;
    }

    /**
     * Returns the bomber's speed.
     *
     * @return the bomber's speed.
     */
    public double getBomberSpeed() {
        return bomberSpeed;
    }

    /**
     * Sets the bomber's speed.
     *
     * @param bomberSpeed the new speed for the bomber.
     */
    public void setBomberSpeed(double bomberSpeed) {
        this.bomberSpeed = bomberSpeed;
    }

    /**
     * Returns the bucket's width.
     *
     * @return the bucket's width.
     */
    public double getBucketWidth() {
        return bucketWidth;
    }

    /**
     * Sets the bucket's width.
     *
     * @param bucketWidth the new width for the bucket.
     */
    public void setBucketWidth(double bucketWidth) {
        this.bucketWidth = bucketWidth;
    }

    /**
     * Returns the bucket's height.
     *
     * @return the bucket's height.
     */
    public double getBucketHeight() {
        return bucketHeight;
    }

    /**
     * Sets the bucket's height.
     *
     * @param bucketHeight the new height for the bucket.
     */
    public void setBucketHeight(double bucketHeight) {
        this.bucketHeight = bucketHeight;
    }

    /**
     * Return the bucket's initial top-left x coordinate.
     *
     * @return the bucket's initial top-left x coordinate.
     */
    public double getBucketInitialTopLeftX() {
        return bucketInitialTopLeftX;
    }

    /**
     * Sets the the bucket's initial top-left x coordinate.
     *
     * @param bucketInitialTopLeftX the new initial top-left x coordinate for the bucket.
     */
    public void setBucketInitialTopLeftX(double bucketInitialTopLeftX) {
        this.bucketInitialTopLeftX = bucketInitialTopLeftX;
    }

    /**
     * Return the bucket's top-left y coordinate.
     *
     * @return the bucket's top-left y coordinate.
     */
    public double getBucketTopLeftY() {
        return bucketTopLeftY;
    }

    /**
     * Sets the the bucket's initial top-left y coordinate.
     *
     * @param bucketTopLeftY the new top-left y coordinate for the bucket.
     */
    public void setBucketTopLeftY(double bucketTopLeftY) {
        this.bucketTopLeftY = bucketTopLeftY;
    }

    /**
     * Returns the bombs' radius.
     *
     * @return the bombs' radius.
     */
    public double getBombsRadius() {
        return bombsRadius;
    }

    /**
     * Sets the bombs' radius.
     *
     * @param bombsRadius the new radius for bombs.
     */
    public void setBombsRadius(double bombsRadius) {
        this.bombsRadius = bombsRadius;
    }

    /**
     * Returns the number of game steps between consecutive bomb drops.
     *
     * @return the number of game steps between consecutive bomb drops.
     */
    public int getBombsFrequency() {
        return bombsFrequency;
    }

    /**
     * Sets the number of game steps between consecutive bomb drops.
     *
     * @param bombsFrequency the new number of game steps between consecutive bomb drops.
     */
    public void setBombsFrequency(int bombsFrequency) {
        this.bombsFrequency = bombsFrequency;
    }

    /**
     * Returns the lower bound for the number of game steps between consecutive
     * bomber direction changes.
     * (The bomber must change direction whenever it reaches the end of the
     * gameplay field, regardless of this value.)
     *
     * @return the lower bound for the number of game steps between consecutive
     *     bomber direction changes.
     */
    public int getMinStepsTillDirectionChange() {
        return minStepsTillDirectionChange;
    }

    /**
     * Sets the lower bound for the number of game steps between consecutive bomber direction changes.
     *
     * @param minStepsTillDirectionChange the new lower bound for the number
     *     of game steps between consecutive bomber direction changes.
     */
    public void setMinStepsTillDirectionChange(int minStepsTillDirectionChange) {
        this.minStepsTillDirectionChange = minStepsTillDirectionChange;
    }

    /**
     * Returns the upper bound for the number of game steps between consecutive bomber direction changes.
     *
     * @return the upper bound for the number of game steps between consecutive bomber direction changes.
     */
    public int getMaxStepsTillDirectionChange() {
        return maxStepsTillDirectionChange;
    }

    /**
     * Sets the upper bound for the number of game steps between consecutive bomber direction changes.
     *
     * @param macStepsTillDirectionChange the new upper bound for the number
     *     of game steps between consecutive bomber direction changes.
     */
    public void setMaxStepsTillDirectionChange(int maxStepsTillDirectionChange) {
        this.maxStepsTillDirectionChange = maxStepsTillDirectionChange;
    }

    /**
     * Returns the number of points awarded for each bomb caught.
     *
     * @return the number of points awarded for each bomb caught.
     */
    public int getPointsPerBomb() {
        return pointsPerBomb;
    }

    /**
     * Sets the number of points awarded for each bomb caught.
     *
     * @param pointPerBomb the number of points awarded for each bomb caught.
     */
    public void setPointsPerBomb(int pointsPerBomb) {
        this.pointsPerBomb = pointsPerBomb;
    }

    /**
     * Returns the bombs' initial y coordinate.
     *
     * @return the bombs' initial y coordinate.
     */
    public double getBombsInitialY() {
        return bombsInitialY;
    }

    /**
     * Sets the bombs' initial y coordinate.
     *
     * @param bombsInitialY the new initial y coordinate for bombs.
     */
    public void setBombsInitialY(double bombsInitialY) {
        this.bombsInitialY = bombsInitialY;
    }

    /**
     * Returns the distance each bomb drops in each step.
     *
     * @return the distance each bomb drops in each step.
     */
    public double getBombsSpeed() {
        return bombsSpeed;
    }

    /**
     * Sets the distance each bomb drops in each step.
     *
     * @param bombsSpeed the distance each bomb drops in each step.
     */
    public void setBombsSpeed(double bombsSpeed) {
        this.bombsSpeed = bombsSpeed;
    }
}
