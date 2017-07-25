import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * A sequence whose elements are a constant number. For example, if the constant is 4, 
 * then the sequence would be 4, 4, 4, ...
 */
public class ConstantSequence implements Sequence {
	private final double D;
	
	/**
	 * Constructs a new constant sequence whose elements are all the given number.
	 * @param d the number to return as the sequence elements.
	 */
	public ConstantSequence(double d) {
	this.D = d;	
	}
	/**
	 * Returns a string representation of this sequence. Let c be the constant returned 
	 * by this sequence. This method returns the string "c", as returned by Double.toString()
	 * @return a string representation of the sequence as defined above.
	 */
	@Override
	public String toString() {
		return Double.toString(D);
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
	 * Returns the next number in the sequence. This function may only 
	 * be called if hasNext() returns true.
	 * @return the next number in the sequence.
	 */
	@Override
	public double next() {
		return D;
	}
}
