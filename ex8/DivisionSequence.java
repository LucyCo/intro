import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * Given two sequences S=(s0,s1,...,sn) and T=(t0,t1,...,tm), this sequence has for its 
 * i'th element a value of (si/ti). The number of elements this sequence has is exactly 
 * min{n,m} where n,m are the lengths of the two input sequences.
 * To avoid division by zero, in case ti == 0, the i'th element is defined as 0.
 * For example, given two sequences S=(1,2,3), T=(7,4), the elements of the division 
 * sequence will be (1/7,1/2).
 */
public class DivisionSequence implements Sequence {
	private Sequence seq1;
	private Sequence seq2;	
	private double next;
	private double next2;
	/**
	 * Constructs a new division sequence. If any of the source sequences is null, the 
	 * resulting sequence is empty. It is assumed that the given source sequence 
	 * parameters are not accessed by any other code; i.e. the newly constructed 
	 * sequence should hold a reference to these source Sequence instances.
	 * @param seq1 the sequence whose elements will the numerators.
	 * @param seq2 the sequence whose elements will be the denominators.
	 */
	public DivisionSequence(Sequence seq1, Sequence seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		this.next = 0;
		this.next2 = 0;
	}
	/**
	 * Returns a string representation of this sequence. Let S1, S2 be the results of 
	 * invoking the two source sequences' toString methods, or "null" if the corresponding 
	 * source sequence is null. This method returns the string "division(S1,S2)".
	 * @return a string representation of the sequence as defined above.
	 */
	@Override
	public String toString() {
		if (seq1 == null || seq2 == null) {
			return "null";
		}
		String s1 = seq1.toString();
		String s2 = seq2.toString();
		return "division("+s1+","+s2+")";
	}
	/**
	 * Checks whether or not there are more numbers in the sequence to return. 
	 * The next() method may be called if and only if hasNext() returns true.
	 * @return true if more numbers remain in the sequence, false otherwise.
	 */		
	@Override
	public boolean hasNext() {
		if (seq1 == null || seq2 == null) {
			return false;
		}
		boolean hasNext = seq1.hasNext() && seq2.hasNext();
		return hasNext;
	}
	/**
	 * Returns the next number in the sequence. This function may only 
	 * be called if hasNext() returns true.
	 * @return the next number in the sequence.
	 */
	@Override
	public double next() {
		this.next = seq1.next();
		this.next2 = seq2.next();			
		if (next2 == 0) {
			return 0;
		}
		return next/next2;
	}
}