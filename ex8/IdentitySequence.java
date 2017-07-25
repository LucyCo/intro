import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * A sequence whose elements are ascending numbers. For all n>=0, 
 * the n'th element of the sequence is n.
 */
public class IdentitySequence implements Sequence {
	double n;
	
	/**
	 * Constructs a new identity sequence.
	 */
	public IdentitySequence() {}
	
	/**
	 * Returns a string representation of this sequence. 
	 * This method returns the string "n".
	 * @return a string representation of the sequence as defined above.
	 */	
	@Override
	public String toString() {
		String n = "n";
		return n;	
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
		++n;
		return (n-1);
	}
}
