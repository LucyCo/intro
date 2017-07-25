import java.util.ArrayList;

/**
 * This class represents a puzzle piece: a shape made of some number of equal-sized square cells, 
 * where each square shares an edge with one or more other squares. 
 * It is initialized using a two-dimensional boolean array that represents the piece.
 */
public class PuzzlePiece {
	boolean[][] initialConformation;
	boolean[][] conformationChange;
	ArrayList<boolean[][]> allConformations;
	
	/* puzzlePiece constructor:
	 * 1. receives the initialConformation.
	 * 2. creates an array list and adds the initial conformation.
	 * 3. copies the initial conformation to a new 2d boolean array.
	 * 4. tries to find the next conformation by rotating it and checking if the rotated conformation 
	 * already exists in the array of conformations. if not, adds the conformation to the array list.
	 * 5. after rotating 3 times, flip the initial conformation and rotate again.
	 * there can be from 1 conformation to 8 conformation per piece.
	 */
	/**
	 * Constructor for a puzzle piece.
	 * @param initialConformation a two-dimensional boolean array that represents the initial (default) 
	 * conformation for this piece.
	 */
	public PuzzlePiece(boolean[][] initialConformation) {
		this.initialConformation = initialConformation;
		this.allConformations = new ArrayList<boolean[][]>();
		allConformations.add(initialConformation);
		
		boolean[][] rotatedConformation = initialConformation;
		for (int k = 0; k<7; k++) {
			boolean isFound = false;
			rotatedConformation = conformationsRotate(rotatedConformation);
			if (k==3) {
				rotatedConformation = verticalConformation();
			}			
			for (int i = 0; i<allConformations.size(); i++) {
				if (isEqual(rotatedConformation, allConformations.get(i))) {
					isFound = true;
				}
			}
			if (!isFound) {
				allConformations.add(rotatedConformation);	
			}
		}
	}
	
	/**
	 * Returns a representation of the piece in one of its conformations. 
	 * The legal values for the parameter are from 0 to numberOfConformations-1.
	 * @param conformation the number of conformation.
	 * @return a two-dimensional boolean array that represents the requested conformation, 
	 * null if the number of conformation isn't legal.
	 */
	public boolean[][] getConformation(int conformation) {
		return allConformations.get(conformation);
	}

	/**
	 * @return the number of different conformations the piece has (>= 1).
	 */
	public int getNumberOfConformations() {
		return (allConformations.size());
	}

	//this method checks whether one 2d boolean array is equal to the other. returns true if it is.
	private boolean isEqual(boolean[][] one, boolean[][] two) {
		if (one.length != two.length){
			return false;
		}
		for (int i = 0; i<one.length; i++) {
			for (int j = 0; j<one[0].length ; j++) {
				if (one[i][j] != two[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//this method receives a 2d boolean array and rotates it by 90 degrees. returns the rotated array.
	private boolean[][] conformationsRotate(boolean[][] conformation) {	
		boolean[][] rotatedConformation = new boolean[conformation[0].length][conformation.length];
		for(int y = 0; y<conformation.length; y++) {
			for(int x = 0; x<conformation[0].length; x++) {
				rotatedConformation[x][conformation.length-1-y] = conformation[y][x];
			}
		}
		
		return rotatedConformation;
	}

	//this method receives a 2d boolean array and flips it vertically. returns the flipped array.
	private boolean[][] verticalConformation() {
		boolean[][] conformations = new boolean[initialConformation.length][initialConformation[0].length]; 
		for(int y = 0; y<initialConformation.length; y++) {//3
			for(int x = 0; x<initialConformation[0].length; x++) {
				conformations[y][x] = initialConformation[initialConformation.length-1-y][x];
			}
		}
		return conformations;
	}
}
