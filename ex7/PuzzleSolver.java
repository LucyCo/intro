/**
 * This class solves a Fill-the-Board puzzle, given a board and a list of pieces, 
 * using the recursive backtracking approach.
 */
public class PuzzleSolver {
	private static PuzzleData puzzle;
	private static PuzzleGUI puzzleGUI;
	private static boolean[] usedPieces;
	private static int topLeftX;
	private static int count;
	
	/*	constructor:
	 *  1. copy the given puzzledata object.
	 *  2. update the value of the count to 0.
	 */
	/**
	 * Constructor for a solver of the Fill-the-Board puzzle.
	 * @param data an object containing the board and the list of puzzle pieces to use.
	 */
	public PuzzleSolver(PuzzleData data) {
		puzzle = data;
		count = 0;
		usedPieces = new boolean[puzzle.getPieces().length];
	}
	
	/*	solve;
	 *  1. copy the given PuzzleGUI object.
	 *  2. find the next empty cell in the grid
	 *  3. return play method
	 */	
	/**
	 * Solves the puzzle. 
	 * The solution uses recursive backtracking, as described in more detail in the definition of the exercise, 
	 * while displaying the board using the provided GUI, as the solution progresses. 
	 * More specifically, as each puzzle piece is placed on the board, 
	 * the corresponding cells on the board are painted. 
	 * @param gui the GUI object, needed for graphically displaying the board.
	 * @return if the board is solvable.
	 */
	public boolean solve(PuzzleGUI gui) {
		puzzleGUI = gui;
		int[] nextEmptyPlace = (nextAvailablePlace());
		return play(nextEmptyPlace[0], nextEmptyPlace[1]);
	}
	
	/* 	play; receives next available place's x and y coordinates.
	 *  1. loop for pieces, loop for conformations and find the top left true cell of the current conformation.
	 *  2. check whether the current conformation of this piece can be placed on the next available place in the grid: 
	 *  when trying to place the top left true cell of the piece on the "next available place": 
	 *  check whether the piece fits the dimensions of the board array. 
	 *  make sure that for every true cell in the piece there's a cell in the board with the value of 0.
	 *  3. if the current conformation fits, use the gui to draw it on the grid, and change the value of the cells 
	 *  from 0 to the number of the piece.
	 *  4. make a 1d boolean array and change the value numbered as the piece to true if the piece was placed.
	 *  5. keep placing pieces until there are no more pieces available for placement, 
	 *  (if used all the pieces then play returns true) if there are still pieces left that cannot be drawn on the grid, 
	 *  remove the last piece drawn and try to place the same piece with the next conformation, 
	 *  if the next conformations don't match, move to the next piece. etc..
	 *  6. if play isn't able to find a solution return false.
	 */
	private boolean play(int nextEmptyCellX, int nextEmptyCellY) { 
		if (!usedPieces(usedPieces)) {
			++count;
			String str = "Number of recursion calls :" + count;
			puzzleGUI.setStatusMessage(str);
			for (int piece = 0; piece < puzzle.getPieces().length; piece++) {
				if (!usedPieces[piece]) {
					for (int conf = 0; conf < puzzle.getPiece(piece).getNumberOfConformations(); conf++) {
						topLeftX = findTopLeft(puzzle.getPiece(piece).getConformation(conf));
						if (placeConformation(puzzle.getPiece(piece).getConformation(conf))) {
							usedPieces[piece] = true;//mark that this piece has been used
							drawPiece(piece, conf);
							int[] nextEmptyPlace = (nextAvailablePlace());
							if (play(nextEmptyPlace[0], nextEmptyPlace[1])) {
								return true;
							}
							removePiece(piece+1);
							usedPieces[piece] = false;
							}
						}
					}
				}
			return false;
		}
		return true;
	}

	//returns false if there are still pieces left that weren't used.
	private boolean usedPieces(boolean[] usedPieces) {
		for (int i = 0; i< usedPieces.length; i++) {
			if (!usedPieces[i]) {
				return false;
			}
		}
		return true;
	}
	//draws the piece on the board
	private void drawPiece(int i, int j) {
		int[] nextEmptyPlace = (nextAvailablePlace());
		int addX = nextEmptyPlace[0]- topLeftX;
		int addY = nextEmptyPlace[1];
		//begin drawing piece
		puzzleGUI.startPiece(i+1); 
		for (int row = 0; row < puzzle.getPiece(i).getConformation(j).length; row++) {
			for (int col = 0; col < puzzle.getPiece(i).getConformation(j)[0].length; col++) {
				if (puzzle.getPiece(i).getConformation(j)[row][col]) {
					//change the value for the cells of this piece to the number of the piece
					puzzle.setSquare((col+addX), (row+addY), i+1);
					//create the piece with a given color each cell at a time
					puzzleGUI.colorSquare(col+addX, row+addY);
				}
			}
		}
		puzzleGUI.endPiece();//stop drawing piece	 
	}
	//removes a piece from the board, changes the color and value of each cell with the 
	//color i to 0.
	private void removePiece(int i) {
		puzzleGUI.startPiece(0);
		for (int row = 0; row < puzzle.getBoardHeight(); row++) {
			for (int col = 0; col < puzzle.getBoardWidth(); col++) {
				if (puzzle.getBoard()[row][col] == i) {
					puzzle.setSquare((col), (row), 0);
					puzzleGUI.colorSquare(col, row);
				}
			}
		}
		puzzleGUI.endPiece(); 
	}
	
	//finds the top left true cell in the piece's array.
	//returns the x value of this cell.
	//if such a cell was not found return 0.
	private static int findTopLeft(boolean[][] conformation) {
		for (int col = 0; col < conformation[0].length; col++) {
			if (conformation[0][col]) {
				return col;
			}
		}
		return 0;
	}
	
	//finds the next available place (top left cell with the value of 0) in the board.
	//returns an integer array with the coordinates.
	private int[] nextAvailablePlace() {
		int[] nextAvailablePlace = new int[2];
		for (int y = 0; y < puzzle.getBoardHeight(); y++) {
			for (int x = 0; x < puzzle.getBoardWidth(); x++) {
				if (puzzle.getSquare(x, y) == 0) {
					nextAvailablePlace[0] = x;
					nextAvailablePlace[1] = y;
					return nextAvailablePlace;
				}
			}
		}
		return nextAvailablePlace;
	}
	//receives a conformation and returns true if it can be placed on the grid.
	private boolean placeConformation(boolean[][] conformation) {
		int[] nextEmptyPlace = (nextAvailablePlace());
		if (topLeftX > nextEmptyPlace[0] || //checks whether the shape is out of the boards' bounds
			conformation[0].length - topLeftX > puzzle.getBoardWidth() - nextEmptyPlace[0] || 
			conformation.length > puzzle.getBoardHeight() - nextEmptyPlace[1]) {
			return false;
		}
		//tries to place the top left cell in piece on the next empty cell of the board and checks that for every "true"
		//cell in the piece's array there's an empty cell on the board.
		else {
			int addX = nextEmptyPlace[0]- topLeftX;
			int addY = nextEmptyPlace[1];
			for (int row = 0; row < conformation.length; row++) {
				for (int col = 0; col < conformation[0].length; col++) {
					if (conformation[row][col]) {
						if (puzzle.getSquare(col+addX, row+addY) != 0) {
							return false;
						}
					}
				}
			}
			return true;	
		}
	}
}
