import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * A finite sequence of numbers.
 */
public class FiniteSequence implements Sequence {
	double [] sourceArray;
	int n;
	
	/**
	 * Constructs a sequence whose elements are the contents of the given array. 
	 * Any subsequent changes to the contents of the given array must not affect the contents of the sequence.
	 * @param sourceArray an array whose elements will be stored as a new sequence. In case the array is null, 
	 * the sequence will be empty.
	 */
	public FiniteSequence(double[] sourceArray) {
		if (sourceArray != null) {
			this.sourceArray = sourceArray.clone();
		}
		this.n = 0;
	}
	
	/**
	 * Returns a string representation of this sequence. This method returns the string "finiteSequence(s0,s1,...,sn)", 
	 * where the elements of the finite sequence are s0,...,sn. If the sequence is empty, 
	 * no elements are displayed in the parentheses. 
	 * As an example, the output may be "finiteSequence(1.0,15.2,7.33)" for a non-empty sequence, or "finiteSequence()" 
	 * for an empty sequence.
	 * @return a string representation of the sequence as defined above.
	 */
	@Override
	public String toString() {
		if (sourceArray == null) {
			return "finiteSequence()";
		}
		if (sourceArray.length==0) {
			return "finiteSequence()";			
		}
		String str = "";
		for (int i = 0; i < (sourceArray.length-1); i++) {
			String number = Double.toString(sourceArray[i]);
			str = str + number + ",";
		}
		String number = Double.toString(sourceArray[sourceArray.length-1]);
		str = str + number;
		return "finiteSequence("+str+")";
	}
	/**
	 * Checks whether or not there are more numbers in the sequence to return. 
	 * The next() method may be called if and only if hasNext() returns true.
	 * @return true if more numbers remain in the sequence, false otherwise.
	 */	
	@Override
	public boolean hasNext() {
		if (sourceArray==null) return false;
		if (sourceArray.length>=(n+1)) {
			return true;
		}
		return false;
	}
	/**
	 * Returns the next number in the sequence. This function may only 
	 * be called if hasNext() returns true.
	 * @return the next number in the sequence.
	 */
	@Override
	public double next() {
		++n;
		return sourceArray[n-1];
	}
}
