import java.util.Scanner;
//this program asks the user for a string. then, it offers 7 actions to execute on the given string.
public class Ex2c{
	public static void main(String[] args){
		System.out.println("Please enter a sentence:");
		Scanner newScan = new Scanner(System.in);
		String newString = newScan.nextLine();
        System.out.println("1) Number of characters");
        System.out.println("2) Remove vowels");
        System.out.println("3) Replace a string");
        System.out.println("4) First and last word");
        System.out.println("5) Remove following characters");
        System.out.println("6) Middle characters");
        System.out.println("7) Convert to upper/lower case");
        System.out.println("Choose option to execute:");
        int length = newString.length();
        int x = newScan.nextInt();
/*user chooses an option to execute on the given string. after performing the action on the string, the program closes.
if the user chooses any number that isn't 1-7 the program prints out error and closes.*/
        switch(x){
//the following will print out the length of the string.
            case 1: 
            System.out.println("The result is: " + length);
            break;
//the following will print the string without vowels.
            case 2:
            System.out.println("The result is: " + newString.replaceAll("[aeiou]", ""));
            break;
/*the following will ask user for a substring to replace from the original string and a new substring to replace with. then print the
string with the new substring instead of the original one.*/
            case 3:
            System.out.println("String to replace:");
            String replace = newScan.next();
            System.out.println("New string:");
            String replaceWith = newScan.next();
            System.out.println("The result is: " + newString.replaceAll(replace, replaceWith));
            break;
//the following will first check that the given string has atleast one space. if not it will print out an empty string.
            case 4:
            if(newString.contains(" ")){
                int firstSpace = newString.indexOf(" ");
                String firstWord = newString.substring(0, (firstSpace));
                int lastSpace = newString.lastIndexOf(" ");
                String lastWord = newString.substring((lastSpace+1), (length));
                String firstLast = newString.replaceAll(firstWord, "");
                if(firstWord.equals(lastWord)){
                    System.out.println("The result is: " + firstLast.trim());
                }
                else{
                    System.out.println("The result is: " + newString);
                }
            }
            else{
                System.out.println("The result is: ");
            }        
            break;
//the following will first check that the original string contains the new input string. if not it will print the original string.
            case 5:
            System.out.println("Enter a string:");
            String string5 = newScan.next();
            if(newString.contains(string5)){
                int lengthOfString = string5.length();
                int index1 = newString.indexOf(string5);
                String endOfSen = newString.substring((index1+lengthOfString), length);
                int lengthEndOfSen = endOfSen.length();
                if(string5.matches(".*[A-Z].*")){
                    System.out.println("The result is: " + newString);
                }
                else{
                    if(lengthEndOfSen>=lengthOfString){
                        String lettersToRplce = newString.substring((index1+lengthOfString), (index1+(2*lengthOfString)));
                        System.out.println("The result is: " + newString.replaceFirst(lettersToRplce, ""));
                    }
                    else{
                        System.out.println("The result is: " + newString.replaceFirst(endOfSen, ""));
                    }
                }
            }
            else{
                System.out.println("The result is: " + newString);
            }
            break;
/*the following wil print the two middle characters i the number of characters without spaces is even.
else, it will print out the three middle letters.*/
            case 6:
            String noSpaces = newString.replaceAll(" ", "");
            int numOfLetters = noSpaces.length();
            if(numOfLetters==1||numOfLetters==0){
                System.out.println("The result is: " + noSpaces);
            }
            else{
                if(numOfLetters%2==0){
                    int midFirst = (numOfLetters/2);
                    String midEven = noSpaces.substring((midFirst-1), (midFirst+1));
                    System.out.println("The result is: " + midEven);
                }          
                else{
                    int midScnd = (((numOfLetters+1)/2)-1);
                    String midOdd = noSpaces.substring((midScnd-1), (midScnd+2));
                    System.out.println("The result is: " + midOdd);           
                }
            }
            break;
            case 7:
/*the following searches for capital letters in the string. if the boolean returns false it will print out the string with 
capitals, if it returns true it will print the string without capitals.*/ 
            if(newString.matches(".*[A-Z].*")){               
                System.out.println("The result is: " + newString.toLowerCase());
            }
            else{
                System.out.println("The result is: " + newString.toUpperCase());
            }
            break;
            default:
//if the uses chooses value that isn't 1-7.
            System.out.println("The result is: Error!");
            break;            
        }			
	}
}
