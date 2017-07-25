/**
 * Tests primality and generates prime numbers in a specified range.
 */
public class PrimesEnumerator {
	private int lowerBound;
	private int upperBound;
	private int number;
	/**
	 * Constructs a new PrimesEnumerator with a given upper bound, and a lower bound value of 2. For a more detailed definition, 
	 * please see the definition of the constructor {@link PrimesEnumerator(int, int)}.
     * 
	 * @param upperBound the upper bound for the calculation of primes.
	 */
	public PrimesEnumerator(int upperBound) {
		this.lowerBound = 2;
		this.upperBound = upperBound;
		number = lowerBound;
	}
	
	/**
	 * Creates a new PrimesEnumerator with a lower bound. The enumerator will return all prime numbers in the range from the given lower bound 
	 * (inclusive) and up to the given upper bound (inclusive), upon calls to hasNext() and next(). For example, a PrimesEnumerator with 
	 * lowerBound=6, upperBound=13 will return, upon calls to hasNext() and next(), the following numbers, in ascending order: 7, 11, 13.
     * 
	 * @param lowerBound the lower bound for the calculation of primes.
	 * @param upperBound the upper bound for the calculation of primes.
	 */
	public PrimesEnumerator(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		number = lowerBound;
	}

	
	/**
	 * Checks if a given number is prime.
     * 
	 * @param number the number to check.
	 * @return true if the number is prime, false otherwise.
	 */
    /*isPrime method returns true if the number is 2, then checks wether number is even, if it is, isPrime returns false, then the for loop 
    begins examining each odd number from 3 to the squareroot of the upperBound (number), wether or not number can be divided by
    a value other than 1 or itself. if the number isn't prime, isPrime returns false, if the number is prime, the loop ends 
    and returns true. (unless the input number is smaller than 2, then isPrime returns false.*/
	public static boolean isPrime(int number) {
		if(number==2){
			return true;
		}
		if(number%2==0){
			return false;
		}
		for(int i=3; i<=Math.sqrt(number); i=i+2) {
			if(number%i==0){
				return false;
			}
			else{
				continue;
			}
		}
		if(number<2){
			return false;
		}
		else{
		return true;
		}
	}
	/**
	 * Checks whether or not more primes in the range remain. 
	 * The next() method may be called iff hasNext() returns true.
     * 
	 * @return true if more primes remain in the range, false otherwise.
	 */
	public boolean hasNext() {
		for(int i=number; i<=upperBound; i++) {
			if(isPrime(i)){
				number=i;
				return true;
			}
			else{
				continue;
			}
		}
		return false;		
	}
	/**
	 * Returns the next prime in the range. This function may only be called if hasNext() returns true.
     * 
	 * @return the next prime in the range.
	 */
	public int next() {
		++number;
		return number-1;
	}
}
