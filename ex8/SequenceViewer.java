import il.ac.huji.cs.intro.IntroUtil;
import java.awt.Color;
import java.util.Scanner;
import il.ac.huji.cs.intro.ex8.SequencePlotter;

/**
 * Displays sequences in a window.
 */
public class SequenceViewer {
	private static final String LIMIT_POINT = "LIMIT_POINT";
	private static final String COMMA = ",";
	/**
	 * Runs the main program.
	 * An input file path is given as a single argument to this function. 
	 * Each line in the file is either a whitespace line (and is ignored), or a line of the format: 
	 * SEQUENCE, [color], [style], [numberOfPoints], [sequence expression]
	 * or a line of the format: 
	 * LIMIT_POINT, [double] For example: 
	 * SEQUENCE, black, CONTINUOUS_LINEAR_INTERPOLATION, 100, n^2 
	 * 
	 * Or: 
	 * LIMIT_POINT, 3.14159265359 
	 * In each of the line's components, whitespace is ignored. 
	 * A sequence line is defined as follows:
	 * Colors may be one of: black, blue, red, green. 
	 * Style may be one of: CONTINUOUS_LINEAR_INTERPOLATION, DOT_PLOT.
	 * Number of points determines how many points are plotted. 
	 * The sequence expression is used to display a sequence.
	 * A limit point line is defined as follows:
	 * A single number (double-precision) is given, and this number should be added to the 
	 * plotter window using its limit point functionality.
	 * The program will open a plotting window and display all the specified graphs in it, 
	 * with their specified parameters. Limit points are added as specified.
	 * In case the number of arguments is different than 1, display an error message: 
	 * "Usage: SequenceViewer filePath, where filePath is a path to an input file.". 
	 * Otherwise, we assume the argument is a legal accessible path to an input file.
	 * @param args assumed to contain a path to an input file, which contains at least one 
	 * line describing a sequence to plot.
	 */
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: SequenceViewer filePath, where filePath is a path to an input file.");
			return;
		}
		else {
			//scans the string from the txt file
			Scanner scan = IntroUtil.newScannerFromFile(args[0]);
			String str;
			//creates a new plotter
			SequencePlotter plotter = SequencePlotter.getPlotter();
			plotter.openWindow();
			//continue as long as there are more lines in the txt file
			while (scan.hasNext()) {
				str = scan.nextLine();
				//remove all white spaces
				str = str.replaceAll("\\s", "");
				//make sure that the sting isn't empty
				if (str.length() > 0) {
					//split the string according to the "," sign
					String[] stringArray = str.split(COMMA);
					//if the string is of limit point
					if (stringArray[0].equals(LIMIT_POINT)) {
						plotter.addLimitPoint(Double.parseDouble(stringArray[1]));
					}
					//if the string represents sequence
					else {
						plotter.plotSequence(SequenceFactory.sequenceFromString(stringArray[4]), 
								getColor(stringArray[1]), 
								SequencePlotter.CURVE_DISPLAY_STYLE.valueOf(stringArray[2]), 
								Integer.parseInt(stringArray[3]));
					} 
				}
			}
		}
	}
	private static Color getColor(String color) {
		switch (color) {
		case "black":
			return Color.black;
		case "red":
			return Color.red;
		case "green":
			return Color.green;
		case "blue":
			return Color.blue;
		}
		return null;
	}
}
