import il.ac.huji.cs.intro.ex8.Sequence;

/**
 * Enables creation of sequences that correspond to the contents of strings.
 */
public class SequenceFactory extends java.lang.Object {
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char POWER = '^';
	private static final char MUL = '*';
	private static final char DIV = '/';
	private static final char OPENER = '(';	
	private static final char CLOSER = ')';
	private static final String PLUS_STR = "+";
	private static final String MIN_STR = "-";	
	private static final String POW_STR = "^";
	private static final String MUL_STR = "*";
	private static final String DIV_STR = "/";
	private static final String OPENER_STR = "(";
	private static final String FIB = "fib()";
	private static final String RATIO = "ratio";
	private static final String SUM = "sum";
	private static final String IDENTITY = "n";	
	private static final int MINUS_ONE = -1;
	
	/**
	 * empty constructor
	 */
	public SequenceFactory() {}
	/**
	 * Returns a sequence that corresponds to the given expression, as specified in the exercise description. 
	 * Whitespace characters in the input string are ignored.
	 * @param stringExpression a string expression. Assumed to contain a legal expression string.
	 * @return a sequence equivalent to the string given. Returns null if the given string is null.
	 */
	//this method receives the input string, checks whether or not it's null (returns null accordingly)
	//then, removes all whitespaces from the string and returns the seqFactory method.
	public static Sequence sequenceFromString(java.lang.String stringExpression) {
		if (stringExpression == null) {
			return null;
		}
		String string = stringExpression.replaceAll("\\s", "");;
		return seqFactory(string);
	}

	//this method returns sequence according to the string given by the user.
	private static Sequence seqFactory(String str) {
		String string = str;
		//The logic that we will use to parse expressions into sequences is as follows, 
		//given some input string that represents an expression (note that the parsing is done recursively, 
		//following the recursive definition given earlier):
		
		//Priority is given using the following rules (in this order), recursively:
		//Any operators inside parenthesized parts are ignored until later recursive calls.
		
		//first check whether there are + or - operators in the string
		if (string.contains(PLUS_STR) || string.contains(MIN_STR)) {
			//look for the operators and make sure they aren't inside parentheses, return the index of that operator.
			int indexOfPlus = parentheses(string, PLUS_STR);
			int indexOfMinus = parentheses(string, MIN_STR);
			//if the +'s index is bigger than the -'s, return addition sequence, else go to checkMinus. 
			if (indexOfPlus>indexOfMinus) {
					return new AdditionSequence(seqFactory(split(string, indexOfPlus)[0]), 
							seqFactory(split(string, indexOfPlus)[1]));
			}
			else {
				if (indexOfMinus != MINUS_ONE) {
					return checkMinus(indexOfMinus, string);
				}
			}
		}
		//check whether the string contains *, and return a multiplication sequence
		if (string.contains(MUL_STR)) {		
			int index = parentheses(string, MUL_STR);
			if (index != MINUS_ONE) {
				return new MultiplicationSequence(seqFactory(split(string, index)[0]), 
						seqFactory(split(string, index)[1]));
			}			
		}
		//check whether the string contains /, and return a division sequence		
		if (string.contains(DIV_STR)) {
			int index = parentheses(string, DIV_STR);
			if (index != MINUS_ONE) {
				return new DivisionSequence(seqFactory(split(string, index)[0]), 
						seqFactory(split(string, index)[1]));
			}			
		}
		//check whether the string contains ^, and return a power sequence
		if (string.contains(POW_STR)) {
			int index = parentheses(string, POW_STR);
			if (index != MINUS_ONE) {
				return new PowerSequence(seqFactory(split(string, index)[0]), 
						seqFactory(split(string, index)[1]));
			}			
		}

		//base cases for ending recursion
		//if the expression is in parentheses return the expression without them.
		if (string.startsWith(OPENER_STR)) {
			String substring = string.substring(1, (string.length()-1));
			return seqFactory(substring);
		}
		//if the expression is "n", return an identity sequence.
		if (string.startsWith(IDENTITY)) {
			return new IdentitySequence();
		}
		//if the expression is "fib()" return a fibonacci sequence.
		if (string.startsWith(FIB)) {
			return new FibonacciSequence();
		}
		//if the expression is inside "ratio()" then call recursion inside the ratio sequence.
		if (string.startsWith(RATIO)) {
			String expr = string.substring(6, string.length()-1);
			return new ConsecutiveRatioSequence(seqFactory(expr));
		}
		//if the expression is inside "sum()" then call recursion inside the sum sequence.
		if (string.startsWith(SUM)) {
			String expr2 = string.substring(4, string.length()-1);
			return new CumulativeSumSequence(seqFactory(expr2));
		}
		//else, if the string got here then it has to be a double expression. 
		//change the string to double and return constant sequence.
		return new ConstantSequence(Double.parseDouble(string));
	}
	
	//this method finds the given operator and returns its index only if it's not inside parentheses
	private static int parentheses(String stringExpression, String operator) {
		String str = stringExpression;
		while (str.contains(operator)){
			int indexOfSign = str.lastIndexOf(operator);
			String substr = str.substring(0, indexOfSign);
			int opener = repeatedSign(substr, OPENER);
			int closer = repeatedSign(substr, CLOSER);
			if (opener != closer){
				str = str.substring(0, indexOfSign);
			}
			else 
				return indexOfSign;
		}
		return MINUS_ONE;
	}


    /* this method checks where the minus is in the expression, if the minus is at the beginning
	 * of the statement then return a multiplication sequence with the constant sequnece of -1 and
	 * call recursion on the rest of the expression.
	 * else, find out which operator is before the minus and return sequences accordingly.
	 */
	
	private static Sequence checkMinus(int index, String string) {
		if (index == 0) {
			String substr = string.substring(index+1, string.length());
			return new MultiplicationSequence(new ConstantSequence(MINUS_ONE), seqFactory(substr));				
		}
		else {								
			if (string.charAt(index-1) == PLUS) {
				return new AdditionSequence(seqFactory(splitTwoOperators(string, index)[0]), 
						new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
								seqFactory(splitTwoOperators(string, index)[1])));					
			}					
			if (string.charAt(index-1) == MUL) {
				return new MultiplicationSequence(seqFactory(splitTwoOperators(string, index)[0]), 
						new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
								seqFactory(splitTwoOperators(string, index)[1])));					
			}
			if (string.charAt(index-1) == DIV) {
				return new DivisionSequence(seqFactory(splitTwoOperators(string, index)[0]), 
						new MultiplicationSequence(new ConstantSequence(MINUS_ONE),
								seqFactory(splitTwoOperators(string, index)[1])));	
			}
			if (string.charAt(index-1) == MINUS) {
				String substr = string.substring(index+1, string.length());
				return new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
						new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
								seqFactory(substr)));					
			}	
			if (string.charAt(index-1) == POWER) {
				String substrMinus = string.substring(0, index);
				int indexMinus = parentheses(substrMinus, MIN_STR);
				if (indexMinus == 0) {
					substrMinus = string.substring(1, string.length());
					return new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
							seqFactory(substrMinus));
				}
				return new PowerSequence(seqFactory(splitTwoOperators(string, index)[0]), 
						new MultiplicationSequence (new ConstantSequence(MINUS_ONE),
								seqFactory(splitTwoOperators(string, index)[1])));	
			}				
			return new AdditionSequence(seqFactory(split(string, index)[0]),
					new MultiplicationSequence(new ConstantSequence(MINUS_ONE), 
							seqFactory(split(string, index)[1])));
		}
	}
	
	//this method splits a given string to two substring and returns a two celled array. according to the given index.
	private static String[] split(String string, int index) {
		String[] stringArray = new String[2];
		stringArray[0] = string.substring(0, index);
		stringArray[1] = string.substring(index+1, string.length());
		return stringArray;
	}
	//this method splits a given string to two substring from the given index and the operator before it.
	private static String[] splitTwoOperators(String string, int index) {
		String[] stringArray = new String[2];
		stringArray[0] = string.substring(0, index-1);
		stringArray[1] = string.substring(index+1, string.length());
		return stringArray;
	}

	//this method returns how many times the given sign is found in the given string.
	private static int repeatedSign(String str, char sign) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == sign) {
				++count;
			}
		}
		return count;
	}
}
