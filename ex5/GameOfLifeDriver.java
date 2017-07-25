import il.ac.huji.cs.intro.IntroUtil;
import java.util.Scanner;
import il.ac.huji.cs.intro.GridWindow;
/**
 * Receives a string from the user and uses the GameOfLife class and GridWindow class 
 * to open a grid window with the values given by the user.
 */
public class GameOfLifeDriver {	
	private static boolean isCyclic;
	private static GameOfLife newGame;
	/**
	 * the main method of the driver, uses the constructor to start the game and other methods 
	 * to update the grid window to the next generation.
	 */
	public static void main(String[] args) {
		final int HEIGHT = Integer.parseInt(args[0]);
		final int WIDTH = Integer.parseInt(args[1]);
			if (args[3].contains("acyclic")) {
				isCyclic = false;
			}
			else{
				isCyclic = true;
			}
			Scanner sc = IntroUtil.newScannerFromFile(args[2]);
			String str= sc.next();
			String newStr="";
			for ( int i=0; i<str.length(); i++) {
				char c=str.charAt(i);
				if (c!='n' && c!='\\') {
					newStr+=c;
				}
			}
			newGame = new GameOfLife(HEIGHT, WIDTH, newStr, isCyclic);
		GridWindow newWindow = new GridWindow(HEIGHT, WIDTH, "new window");
		while(true) {
			newWindow.update(newGame.getWorld());
			IntroUtil.sleep(0.4);
			newGame.nextGeneration();	
		}
	}
}
