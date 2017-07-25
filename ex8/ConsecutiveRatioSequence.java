import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * Given another sequence S=(s0,s1,...,sn), this sequence has for its i'th element a 
 * value of (s(i+1)/s(i)). The first element has a value of s1/s0, and thus this 
 * sequence has one less element than the source sequence.
 * In case the the source sequence has an element s(i) == 0, the i'th element in this 
 * sequence is defined as 0, to avoid illegal division.
 * For example, given a source sequence S=(1,2,3,0,7,-14), the elements of the ratio 
 * sequence will be (2,1.5,0,0,-2).
 */
public class ConsecutiveRatioSequence implements Sequence {
	private Sequence sourceSequence;
	private double next;
	private double next2;
	
	/**
	 * Constructs a new consecutive ratio sequence over the given source.
	 * @param sourceSequence a source sequence whose elements will be used. 
	 * If the source sequence is null, the resulting sequence is empty. 
	 * It is assumed that the source sequence is not accessed by any other code; i.e. 
	 * the newly constructed sequence should hold a reference to this source Sequence instance.
	 */
	public ConsecutiveRatioSequence(Sequence sourceSequence) {
		this.sourceSequence = sourceSequence;
		if (sourceSequence!=null && sourceSequence.hasNext()==true) {
			this.next = sourceSequence.next();
		}
	}
	/**
	 * Returns a string representation of this sequence. Let S be the result of 
	 * invoking the source sequence's toString method, or "null" if the source sequence 
	 * is null. This method returns the string "ratio(S)".
	 * @return a string representation of the sequence as defined above.
	 */
	@Override
	public String toString() {
		if (sourceSequence == null) {
			return "null";
		}
		String s = sourceSequence.toString();
		return "ratio("+s+")";
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
		this.next2 = sourceSequence.next();
		if (next == 0) {
			next = next2;
			return 0;
		}
		double ret = (next2/next);
		next = next2;
		if (ret == 0) {	
			return 0;
		}
		return ret;
	}
}
