/**
* A class that implements the well-known Game Of Life, invented in 1968 by John Conway. 
* The universe of the Game of Life is a two-dimensional orthogonal grid of square cells, each of which is in one of two possible states: alive or dead. 
* Every cell interacts with its eight neighbors, which are the cells that are horizontally, vertically, or diagonally adjacent. 
* A generation of the game is a state of the world: which cells are alive, and which cells are dead. 
* The game advances from the initial generation to the next generation and so on. 
* In the process of calculating generation n+1 from generation n, 
* the following transitions occur (all 4 rules are applied simultaneously 
* to every cell in the current generation - births and deaths occur simultaneously):
* 
* 1. Any cell that is alive and has fewer than two live neighbors in generation n will die of under-population, and will thus be dead in generation n+1.
* 2. Any cell that is alive and has two or three live neighbors in generation n will stay alive in generation n+1.
* 3. Any cell that is alive and has more than three live neighbors in generation n will die of overcrowding, and will thus be dead in generation n+1.
* 4. Any cell that is dead and has exactly three live neighbors in generation n will be revived by reproduction, and will thus be alive in generation n+1.
*/

public class GameOfLife {
	private final boolean DEAD = false;
	private final boolean ALIVE = true;
	private static boolean[][] cells;
	private static boolean[][] cellsClone;
	private static boolean[][] biggerArray;
	private static int height1;
	private static int width1;
	private static boolean isCyclic1;
	private static String str;
		
	/**
	* Constructs an empty world with the given size.
	* @param height the height (number of rows) of the world. Assumed to be positive.
	* @param width the width (number of columns) of the world. Assumed to be positive.
	* @param isCyclic whether the world is cyclic.
	*/
	public GameOfLife(int height, int width, boolean isCyclic) {
		height1 = height;
		width1 = width;
		isCyclic1 = isCyclic;
		cells = new boolean[height1][width1];
	}
		
    /**
	* Constructs a cyclic empty world with the given size.
    * @param height the height (number of rows) of the world. Assumed to be positive.
	* @param width the width (number of columns) of the world. Assumed to be positive.
	*/
	public GameOfLife(int height, int width) {
	    this(height, width, true);
		cells = new boolean[height1][width1];
	}
		
	/**
	* Constructs a world populated according to a String description.
	* @param height the height (number of rows) of the world.
	* @param width the width (number of columns) of the world.
	* @param content a String description of the world. In this String, a minus sign ('-') represents a dead cell, and a plus sign ('+') represents a live cell. For 
	* example, a 3x5 world with live cells at locations (2, 0) and (2, 2) is represented by:
	* 
	*   -----
	*   -----
	*   +-+--
	*   
	* Note that there is a newline character (i.e., '\n') at the end of each line, including the last line. It is assumed that the content string matches the size 
	* parameters.
	* @param isCyclic whether the world is cyclic.
	*/
	public GameOfLife(int height, int width, java.lang.String content, boolean isCyclic) {
		height1 = height;
		width1 = width;
	    str=content.replaceAll("\n", "");
		cells = new boolean[height][width];
	    int index = 0;
	    for (int i = 0 ; i < height1 ; i++) {
	    	for (int j = 0; j < width1; j++) {
	            if (str.charAt(index) == '+') {
	                cells[i][j] = ALIVE;
	            }
	            else {
	            	cells[i][j] = DEAD;
	            }   
	            index++;
	        }
	    }
	    isCyclic1 = isCyclic;
	}
	
	/**
	* Populates a creature in row y, column x (i.e. makes this cell ALIVE). If the parameters coordinates are outside of the world borders, 
	* no action is performed.
	* @param y the row number.
	* @param x the column number.
	*/
	public void addCreature(int y, int x) {
		if (((y>=0) && (y<height1)) && ((x>=0) && (x<width1))) {
	        cells[y][x]=ALIVE;
		}
	}
		
	/**
	* Removes a creature from the cell in row y, column x (i.e. makes this cell dead). If the parameters coordinates are outside of the world borders, 
	* no action is performed.
	* @param y the row number.
	* @param x the column number.
	*/
	public void removeCreature(int y, int x) {
	    if (((y>=0) && (y<height1)) && ((x>=0) && (x<width1))){
	        cells[y][x] = DEAD;
        }
	}
		
	/**
	* Returns a two-dimensional boolean array representing the current state of the world. Any future changes to the world are not reflected on this array,
	*  and any future changes to this array are not reflected in the world.
	* @return a two-dimensional array in which cell [i][j] specifies whether the cell in the i'th row and the j'th column is alive.
	*/
	public boolean[][] getWorld() {
		boolean[][] cellsClone = new boolean [height1][width1];
	    for (int i = 0 ; i < height1 ; i++) {
	        for(int j = 0 ; j < width1; j++ ) {
	            cellsClone[i][j]=cells[i][j];
	        }
	    }
	        return cellsClone;
	 }
		
	/**
	* Returns the world's width (number of columns).
	* @return the width (number of columns) of the world.
	*/
	public int getWidth() {
		return width1;
	}
		
	/**
	* Returns the world's height (number of rows).
	* @return the height (number of rows) of the world.
	*/
	public int getHeight() {
		return height1;
	}
		
	/**
	* Advances the world to the next generation. The next generation is computed according to the following rules:
	* 
    * 1. Any cell that is alive and has fewer than two live neighbors in generation n will die of under-population, and will thus be dead in generation n+1.
	* 2. Any cell that is alive and has two or three live neighbors in generation n will stay alive in generation n+1.
	* 3. Any cell that is alive and has more than three live neighbors in generation n will die of overcrowding, and will thus be dead in generation n+1.
	* 4. Any cell that is dead and has exactly three live neighbors in generation n will be revived by reproduction, and will thus be alive in generation n+1.
	*/
	public void nextGeneration() {
		cellsClone = new boolean[cells.length][];
		for (int i = 0; i < cells.length; i++) {
			cellsClone[i] = cells[i].clone();
		}
		if (isCyclic1) {
			biggerArray();
		}
		for (int row = 0 ; row < cells.length ; row++) {
			for (int col = 0 ; col < cells[0].length ; col++) {
				int neighborsAlive = neighborsAlive(row, col, cellsClone);
				if (neighborsAlive > 3 || neighborsAlive < 2) {
					removeCreature(row, col);
				}
				if (neighborsAlive == 3) {
					addCreature(row, col);
				}
			}
		}
	}
	
    /* creates a clone of the original world, adding external cells that surrounds the cells of the original
	 * world duplicates the boolean values of the neighbours of each cells in the state of a cyclic world*/
	private boolean[][] biggerArray() {
		biggerArray = new boolean[height1+2][width1+2];
		//duplicates the inner cells;
		for (int i = 1 ; i < cellsClone.length+1; i++) {
			for (int j = 1 ; j < cellsClone[0].length+1; j++) {
				biggerArray[i][j] = cellsClone[i-1][j-1];
			}
		}
		//duplicates the external cells; 
		for (int j = 1 ; j < cellsClone[0].length+1 ; j++) {
			biggerArray[0][j]=cellsClone[height1-1][j-1];
		}
		for (int j = 1 ; j < cellsClone[0].length+1 ; j++) {
			biggerArray[height1+1][j]=cellsClone[0][j-1];
		}
		for (int i = 1 ; i < cellsClone.length+1 ; i++) {
			biggerArray[i][0]=cellsClone[i-1][width1-1];
		}
		for (int i = 1 ; i < cellsClone.length+1 ; i++) {
			biggerArray[i][width1+1]=cellsClone[i-1][0];
		}
		biggerArray[0][0]=cellsClone[height1-1][width1-1];
		biggerArray[height1+1][0]=cellsClone[0][width1-1];
		biggerArray[0][width1+1]=cellsClone[height1-1][0];
		biggerArray[height1+1][width1+1]=cellsClone[0][0];
		return biggerArray;
	}
	
	/* calculates the number of living cells around each given cell, seperates between two cases:
	* a cyclic world and an acyclic world.*/		
	private int neighborsAlive(int row, int col, boolean[][] cellsClone) {
		int neighborsAlive = 0;
		//calculates neighbours in a cyclic world.
		if (isCyclic1) {
			for (int i = row-1 ; i <= row+1 ; i++) {
				for (int j = col-1 ; j <= col+1 ; j++) {
					if ((i!=row) | (j!=col)) {
						if (biggerArray[i+1][j+1] == ALIVE) {
							++neighborsAlive;
						}
					}
				}
			}
		}
		//calculates neighbours is an acyclic world.
		else{
			for (int i = row-1 ; i <= row+1 ; i++) {
				for (int j = col-1 ; j <= col+1 ; j++) {
					if (i >= 0 && j >= 0 && i <= cells.length-1 && j <= cells[0].length-1) {
						if ((i!=row) | (j!=col)) {
							if (cellsClone[i][j] == ALIVE) {
								++neighborsAlive;
							}
						}
					}
				}
			}
		}	
		return neighborsAlive;
	}
	
	/**
	 * Returns a String representation of the world.
	 * 
	 * @returns a String description of the world. In this String, a minus sign ('-') represents a dead cell,
	 *  and a plus sign ('+') represents a live cell. For example, a 3x5 world with live cells at locations (2, 0) and (2, 2) is represented by:
     * -----
     * -----
     * +-+--
     *
	 *	Note that there is a newline character (i.e., '\n') at the end of each line, including the last line. It is assumed that the content string matches the size parameters.  	
	 */
	public java.lang.String toString() {
		String str = "";
		for (int i = 0 ; i < height1 ; i++) {
			for (int j = 0 ; j < width1 ; j++) {
				if (cells[i][j] == ALIVE) {
					str= str + "+";
				}
				else {
					str= str + "-";
				}
			}
			str = str + "\n";
		}
		return str;
	}
}