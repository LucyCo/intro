/**
 * Solves the Mystery problem without recursion.
 */
public class NonRecursiveMystery {
	/**
	 * Calculates a given number's all possible dividers (not including itself), and returns their sum. 
	 * if the number is smaller or equals to 1, the method returns 0.
	 * @param n the given number
	 * @return Given number's all possible dividers' (not including itself) sum. if n<2 the calculator returns 0.
	 */
	public static int mysteryComputation(int n) {
		if (n<2) {
			return 0;
		}
		else {
			int c = 0;
			for(int i = n-1; i>0; i--) {
				if (i==0) {
					return 0;
				}
				if (n%i==0) {
					c += i;
				}
			}
			return c;
		}
	}
}

