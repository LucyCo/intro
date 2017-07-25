import il.ac.huji.cs.intro.mastermind.*;

public class Mastermind{
    public static void main(String[] args){

        boolean quit = true;
        boolean quit1 = true;
        //begins a new game with new game parameters.
        MastermindUI newGame = MastermindUIFactory.newMastermindUI();
        //this 'do while' loop breaks when user chooses to quit the game entirely.
        do{
            //user enters three valid values for 'length of code', 'number of possible values' & 'max guesses'. 
            //these while loops debug options of negative values.
            int lengthOfCode;
            lengthOfCode = newGame.askNumber("Please enter length:");
            while (lengthOfCode<1) {
                newGame.displayErrorMessage("Error! Value must be positive!");
                lengthOfCode = newGame.askNumber("Please enter length:");
            }         

            int numOfValues;
            numOfValues = newGame.askNumber("Please enter possible values:");
            while (numOfValues<1) {
                newGame.displayErrorMessage("Error! Value must be positive!");
                numOfValues = newGame.askNumber("Please enter possible values:");
            }         

            int numOfGuesses;
            numOfGuesses = newGame.askNumber("Please enter max guesses:");
            while (numOfGuesses<1) {
                newGame.displayErrorMessage("Error! Value must be positive!");
                numOfGuesses = newGame.askNumber("Please enter max guesses:");
            }
            //resetting the game with the new game options.           
            newGame.reset(lengthOfCode, numOfValues, numOfGuesses);
            int wins = 0;
            int guessesWhenWinning = 0;
            String askGuess = "Enter guess:";

            //starting new game with the same game options.
            //this infinite loop breaks when the user chooses to continue playing with new options.
            for (int numOfGames = 1;true;numOfGames++) {
                int bulls = 0;
                int cows = 0;
                Code compCode = CodeGenerator.newCode(lengthOfCode, numOfValues);
                //this infinite loop breaks when a user guesses the code or when the game reaches maximal guesses.
                for (int turn = 1;true;turn++) {
                    Code userCode = newGame.askGuess(askGuess, lengthOfCode);
                    //the following calculates the bulls.
                    for (int j=1; j<lengthOfCode+1; j++) {
                        int one = compCode.getValue(j);
                        int two = userCode.getValue(j);
                        if (two==one) {
                            ++bulls;
                        }
                    }
                    /*cow calculation system: comparing each possible value for the code, with the computer's code
                    and the users code, then comparing the number of times each value repeats in each code,
                    taking the lower amount and printing it in variable 'cows'*/
                    for (int j=0; j<numOfValues; j++) {
                        int x = 0;
                        int y = 0;
                        for (int m=1; m<lengthOfCode+1; m++) {
                            int slot = compCode.getValue(m);
                            if (j==slot) {
                                ++x;
                            }
                        }
                        for (int m=1; m<lengthOfCode+1; m++) {
                            int slot = userCode.getValue(m);
                            if (j==slot) {
                                ++y;
                            }
                        }
                        if (x==y) {
                            cows=(cows+x);
                        }
                        if (x>y) {
                            cows=(cows+y);
                        }
                        if (x<y) {
                            cows=(cows+x);
                        }
                    }
                    //after calculating bulls and cows separately, reducing the amount of bulls from the cows. 
                    cows = (cows-bulls);
                    newGame.showGuessResult(userCode, bulls, cows);

                    /*two conditions for winning and losing, prints the outcome and the game stats and breaks the turn
                    loop.*/
                    if(compCode.equals(userCode)){
                        newGame.displayMessage("You won in " + turn + " turns!");
                        ++wins;
                        guessesWhenWinning = (guessesWhenWinning + turn);
                        double wins1 = wins;
                        double numOfGames1 = numOfGames;
                        double guessesWhenWinning1 = guessesWhenWinning;
                        newGame.showStats(numOfGames, wins, wins1/numOfGames1, guessesWhenWinning1/wins1);
                        break;
                    }
                    if(numOfGuesses==turn){
                        newGame.displayMessage("You lost! You failed to find the code!");
                        if(wins>0){
                            double wins1 = wins;
                            double numOfGames1 = numOfGames;
                            double guessesWhenWinning1 = guessesWhenWinning;                            
                            newGame.showStats(numOfGames, wins, wins1/numOfGames1, guessesWhenWinning1/wins1);
                            break;
                        }
                        else{
                            double wins1 = wins;
                            double numOfGames1 = numOfGames;
                            double guessesWhenWinning1 = guessesWhenWinning;
                            newGame.showStats(numOfGames, wins, wins1/numOfGames1, Double.NaN);
                            break;
                        }
                    } 
                    bulls = 0;
                    cows = 0;
                }
                /*two conditions for quitting. if the user says no the UI closes. if the user responds with a "yes"
                he's given with two more options, reset game parameters and don't reset.*/
                quit = newGame.askYesNo("Another game?");
                if(quit==false){
                    newGame.close();
                    break;
                }
                else{
                    quit1 = newGame.askYesNo("Do you want to change the game options?");
                    if(quit1==true){
                        break;
                    }
                    else{
                        newGame.clear();
                    }
                }                                                  
            }
        } while(quit); 
    }     
}


