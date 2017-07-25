import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * The Fibonacci sequence S=(s0,s1,s2,...) is defined as follows: s0 = 0, s1 = 1, 
 * and for all i>1, si = s(i-1)+s(i-2).
 * For example, the first few elements of the series are: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */
public class FibonacciSequence implements Sequence {
	int n;

	/**
	 * Constructs a new Fibonacci sequence.
	 */
	public FibonacciSequence() {
		this.n = 0;
	}
	
	/**
	 * Returns a string representation of this sequence. 
	 * This method returns the string "fib()".
	 */
	@Override
	public String toString() {
		return "fib()";
	}
	
	/**
	 * Checks whether or not there are more numbers in the sequence to return. 
	 * The next() method may be called if and only if hasNext() returns true.
	 * @return true if more numbers remain in the sequence, false otherwise.
	 */
	@Override
	public boolean hasNext() {
		return true;
	}
	
	/**
	 * Returns the next number in the sequence. 
	 * This function may only be called if hasNext() returns true.
	 * @return the next number in the sequence.
	 */
	@Override
	public double next() {
		++n;
		return fibonacci(n-1);
	}
	
	private double fibonacci(int position) {
		double fib1, fib2, fib3;
		fib1 = -1; fib2 = 1;	
		for (int i = 0; i <= position; i++) {
			fib3 = (fib1 + fib2);
			fib1 = fib2;
			fib2 = fib3;	
		}
		return fib2;
	}
}
