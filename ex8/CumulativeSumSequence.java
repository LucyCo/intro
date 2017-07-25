import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * Given another sequence S=(s0,s1,...,sn), this sequence has for its i'th element 
 * a value of (s0+s1+...+si).
 * For example, given a sequence S=(1,2,3), the elements of the cumulative sum 
 * sequence will be (1,3,6).
 */
public class CumulativeSumSequence implements Sequence {
	private Sequence sourceSequence;
	private double next;
	
	/**
	 * Constructs a new cumulative sum sequence over the given source.
	 * @param sourceSequence  a source sequence whose elements will be summed. If the source 
	 * sequence is null, the resulting sequence is empty. It is assumed that the source 
	 * sequence is not accessed by any other code; i.e. the newly constructed sequence should 
	 * hold a reference to this source Sequence instance.
	 */
	public CumulativeSumSequence(Sequence sourceSequence) {
		this.sourceSequence = sourceSequence;
		if (sourceSequence!=null && sourceSequence.hasNext()==true) {
			this.next = 0;
		}
	}
	/**
	 * Returns a string representation of this sequence. Let S be the result of invoking 
	 * the source sequence's toString method, or "null" if the source sequence is null. 
	 * This method returns the string "cumulativeSum(S)".
	 * @return a string representation of the sequence as defined above.
	 */
	@Override
	public String toString() {
		if (sourceSequence == null) {
			return "null";
		}
		String s = sourceSequence.toString();
		return "cumulativeSum("+s+")";
	}
	/**
	 * Checks whether or not there are more numbers in the sequence to return. 
	 * The next() method may be called if and only if hasNext() returns true.
	 * @return true if more numbers remain in the sequence, false otherwise.
	 */		
	@Override
	public boolean hasNext() {
		if (sourceSequence == null) {
			return false;
		}
		return sourceSequence.hasNext();
	}
	/**
	 * Returns the next number in the sequence. This function may only 
	 * be called if hasNext() returns true.
	 * @return the next number in the sequence.
	 */
	@Override
	public double next() {
		this.next += sourceSequence.next();			
		return next;
	}
}
