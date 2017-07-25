/**
 * a class that receives a board (1d integer array) and uses a recursive method in order to 
 * tell if the board is crossable:
 * for example:
 * The mission starts with James Bond on the leftmost (first) part of the corridor, 
 * and his goal is to reach rightmost (last) part, occupied by a zero, denoting the villain. 
 * At each step, James Bond may move a distance indicated by the integer in the part of the 
 * corridor he is currently at. Bond may move either left or right along the corridor, but may 
 * not move past either end and may not change direction mid-way through a step.
 */
public class GetToTheZero {
	
	/**
	 * creates two 1d boolean arrays for backtracking the left and right steps. that were taken.
	 * the constructor receives an integer of the starting point and an integer array (the board).
	 * @param start the starting point
	 * @param board the given board
	 * @return returns the recursive getToZero method which calculates if the puzzle is solvable or not.
	 */
	public static boolean isSolvable(int start, int[] board) {
		boolean[] forward = new boolean[board.length];
		boolean[] back = new boolean[board.length];
		return getToZero (start, board, forward, back);
	}
	
	/* getToZero:
	 * 1. checks whether the value of the current cell is 0. if it is, the method returns true.
	 * 2. checks whether the current cell has already been tried twice, when trying to go left and when trying to go right.
	 * returns false if the current cell had been tried.
	 * 3. checks whether it's possible to step to the right or left. if it is possible, 
	 * change the value of the destination cell to true and call the method again, 
	 * this time with the current starting point.
	 * 4. if the puzzle is not solvable. return false.
	 */
	private static boolean getToZero (int current, int[] board, boolean[] forward, boolean[] back) {
		if (board[current] == 0) {
			return true;
		}
		if (forward[current] && back[current]) {
			return false;
		}
		if (!forward[current]) {
			forward[current] = true;
			int steps = current + board[current];
			if (steps < board.length) {
				if (getToZero (steps, board, forward, back)) {
					return true;
				}
			}
		}
		if (!back[current]) {
			back[current] = true;
			int steps = current - board[current];
			if (steps >= 0) {
				if (getToZero (steps, board, forward, back)) {
					return true;
				}
			}
		}
		return false;
	}
}
