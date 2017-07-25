/**
 *  Presents the density of prime numbers, and compares it to the approximation function. 
 */
public class PrimesDensityVisualizer {
    private static Plotter plotter = new Plotter();
	/**
	 * Used for testing the exercise.
     * 
	 * @param plotter the new plotter to use.
	 */
	public static void setPlotter(Plotter plotter){
		PrimesDensityVisualizer.plotter = plotter;
	}
	
	/**
	 * Receives exactly one argument, a number n.
	 *  The function pi and its approximation will be plotted for all natural numbers in the (inclusive) range [2,n]. 
     * 
	 * @param args the command-line arguments of the program. Expected to contain a single number.
	 */
    //main method checks for illegal values such as number smaller than 2 and decimal numbers.
	public static void main(String[] args){
		int number = Integer.parseInt(args[0]);
		if(number>1){
		drawPrimesCountingFunction(number);
		}
		else{
			if(args[0].contains(".")){
				System.out.println("Usage: PrimesDensityVisualizer x, where x is a natural number greater than 1.");
				return;	
			}
			else{
				System.out.println("Usage: PrimesDensityVisualizer x, where x is a natural number greater than 1.");
				return;
			}
		}
	}
	

	/**
	 * Returns the approximate number of primes which are smaller than or equal to a given number. 
     * The formula used for the approximation is f(x)=x/ln(x).
     * 
	 * @param number the number used for the calculation.
	 * @return the value of the approximation function, 
	 * number/ln(number). In case number is less than 2, the function returns 0.
	 */
	public static double approximateNumOfPrimesBelow(int number){
		if (number>1){
			double approximation = (number)/(Math.log(number));
			return approximation;
		}
		else {
			return 0;
		}
	}
	/**
	 * Returns the number of primes which are smaller than or equal to a given number. 
	 * In the exercise description this function is called pi(x). 
     *
	 * @param number the number used for the calculation.
	 * @return the number of primes which are smaller than or equal to the given number.
	 */
	public static int numberOfPrimesBelow(int number){
	    PrimesEnumerator primesEn = new PrimesEnumerator(number);
		int numOfPrimes = 0;
		for(int i=2; i<=number; i++){
			if(primesEn.hasNext()){
                primesEn.next();
				++numOfPrimes;
			}	
		}
		return numOfPrimes;
	}
	public static void drawPrimesCountingFunction(int number){
		plotter.openWindow();
		plotter.startCurve(java.awt.Color.red);
		for(int k=2; k<=number;k++){
			plotter.addToCurve(new Point(k, numberOfPrimesBelow(k)));	
		}
		plotter.endCurve();
		plotter.startCurve(java.awt.Color.green);
		for(int k=2; k<=number;k++){
			plotter.addToCurve(new Point(k, approximateNumOfPrimesBelow(k)));
		}
		plotter.endCurve();
		
	}
}
