import java.util.Scanner;
/*This program asks user for three values to calculate a quadratic equation.
it will give output for every real value.*/
public class Ex2b{
	public static void main(String[] args){
		System.out.println("This program solves a quadratic equation.");
		System.out.print("Enter the coefficients a b c: ");
		Scanner inputScan = new Scanner(System.in);
//Epsilon is a variable representing a very small number to compare to floating point numbers and avoid round off errors.
        final double EPSILON = 0.000001;
		double a = inputScan.nextDouble();
		double b = inputScan.nextDouble();
		double c = inputScan.nextDouble();
		double det = Math.sqrt(b*b-4*a*c);
		double detIn = (b*b)-(4*a*c)+0;
		double x1 = (-b+det)/(2*a)+0;
		double x2 = (-b-det)/(2*a)+0;
        double oneSol = (-c/b)+0;
        double oneSol2 = (-b)/(2*a)+0;
		System.out.format("Equation: %.2f*x^2 + %.2f*x + %.2f = 0%n", a, b, c);
/*the calculator is devided to two major conditions => when a=0 (then there's no qurdratic equation), and when a!=0 (then we solve
a vieta formula, comparing the determinant to the three options of math trichotomy*/ 
		if (a==0){
			if(b==0){
				if(c==0){
					System.out.println("All real numbers are solutions.");
				}
				else{
					System.out.println("There are no real solutions.");
				}
			}
			else{
				System.out.format("There is one real solution: %.2f.%n", oneSol);
			}
		}
		else{
			if(Math.abs(detIn) <= EPSILON){
				System.out.format("There is one real solution: %.2f.%n", oneSol2);
			}
			if(detIn<0){
				System.out.println("There are no real solutions.");
			}
			if(detIn>0){
				if(x1<x2){
					System.out.format("There are two real solutions: %.2f, %.2f.%n", x1, x2);
				}
				else{
				    System.out.format("There are two real solutions: %.2f, %.2f.%n", x2, x1);	
                }		
            }
        }					
	}
}
