import java.util.Scanner;
//this program asks for the user for his age, then calculates the target heart rate during exercise
public class Ex2a{
	public static void main(String[] args){
		Scanner newScan = new Scanner(System.in);
		System.out.println("This program calculates your target heart rate while exercising.");
		System.out.print("Enter your age: ");		
		double age = newScan.nextDouble();
		double min = (220-age)*0.65;
		double max = (220-age)*0.85;
		System.out.println("Your estimated target heart rate zone is " + 
				            Math.round(min) + " - " + Math.round(max) + 
                           " beats per minute.");
	}
}


