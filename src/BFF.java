import java.util.Scanner;

/**
 * This class is meant to serve ITP 265 students as a help for getting input and error checking on input, but
 * may also be used as a general purpose class to store methods which may be needed across lots of projects.
 *
 * @author Kendra Walther and Tea Class
 * @version Fall 2021

 */
public class BFF{
	private Scanner sc;

	public BFF(){
		sc = new Scanner(System.in);
	}

	/**
	 * Prompt the user and read one word of text as a String
	 * @param prompt: the question to ask the user
	 * @return: a one word String - if the user enters multiple words, all other input until the return will be discarded.
	 */
	public String inputWord(String prompt) {
		System.out.print(prompt + "\n>");
		String word = sc.next();
		sc.nextLine(); // remove any "garbage" (like extra whitespace or the return key) after the one word that is read in
		return word;
	}

	/**
	 * Prompt the user and read one line of text as a String
	 * @param prompt: the question to ask the user
	 * @return: a line of user input (including spaces, until they hit enter)
	 */
	public String inputLine(String prompt) {
		System.out.print(prompt + "\n>");
		return sc.nextLine();
	}

	/**
	 * Prompt the user and read an int, clearing whitespace or the enter after the number
	 * @param prompt: the question to ask the user 
	 * @return: an int 
	 */
	public int inputInt(String prompt) {
		boolean gotInt = false;
		int num = 0;
		while(!gotInt){
			System.out.println (prompt);
			if(!sc.hasNextInt()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not an int. Please enter a whole number");

			}
			else{
				gotInt = true;
				num = sc.nextInt();
				sc.nextLine();//clear
			}
		}


		return num;
	}

	/**
	 * Prompt the user and read an int between (inclusive) of minValue and maxValue, clearing whitespace or the enter after the number
	 * @param prompt: the question to ask the user 
	 * @return: an int between minValue and maxValue
	 */
	public int inputInt(String prompt, int minValue, int maxValue) {

		int number = inputInt(prompt);
		while (!(number >= minValue && number <= maxValue))
		{
			System.out.println("Please enter a valid number in the range (" + minValue + " and " + maxValue + "): ");
			number = inputInt(prompt);
		}
		return number;
	}

	public int inputInt(String question, int max) {
		return inputInt(question, 0, max);
	}
	/**
	 * Prompt the user and read a floating point number, clearing whitespace or the enter after the number
	 * @param prompt: the question to ask the user 
	 * @return: a double value 
	 */
	public double inputDouble(String prompt) {

		boolean gotDouble = false;
		double num = 0.0;
		while(!gotDouble){
			System.out.println (prompt);
			if(!sc.hasNextDouble()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not a double. Please enter a floating point number");

			}
			else{
				gotDouble = true;
				num = sc.nextDouble();
				sc.nextLine();//clear
			}
		}
		return num;
	}



	/**
	 * Prompt the user and read a floating point number between (inclusive) of min and max, 
	 * clearing whitespace or the enter after the number
	 * @param prompt: the question to ask the user 
	 * @return: a double value 
	 */
	public double inputDouble(String prompt, double min, double max) {

		double number = inputDouble(prompt);
		boolean closeEnough =Math.abs(number - min) <= 0.001 
				|| Math.abs(number - max) <= 0.001  ;
		while (!((number >= min && number <= max) || closeEnough ))
		{
			System.out.println("Please enter a valid number in the range (" + min + " and " + max + "): ");
			number = inputDouble(prompt);
		}
		return number;

	}

	/**
	 * Prompt the user and read a boolean value, clearing whitespace or the enter after the number
	 * @param prompt: the question to ask the user 
	 * @return: a boolean value 
	 */
	public boolean inputBoolean(String prompt) {
		boolean gotBool = false;
		boolean bool = false;
		while(!gotBool){ // no good
			System.out.println (prompt);
			if(!sc.hasNextBoolean()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not a boolean. Please enter either true or false");

			}
			else{
				gotBool = true; //input was good
				bool = sc.nextBoolean(); //get the answer
				sc.nextLine();//clear
			}
		}

		return bool;

	}

	/**
	 * Prompt the user enter yes or no (will match y/yes and n/no any case) and return true for yes and false for no.
	 * @param prompt: the question to ask the user 
	 * @return: a boolean value 
	 */
	public boolean inputYesNo(String prompt) {

		System.out.println(prompt);
		String output = sc.nextLine();
		while (!(output.equalsIgnoreCase("y") || output.equalsIgnoreCase("n") 
				|| output.equalsIgnoreCase("yes") || output.equalsIgnoreCase("no"))) {
			System.out.println("Sorry, invalid input. Input yes or no.");
			output = sc.nextLine();
		}

		return (output.equalsIgnoreCase("y") || output.equalsIgnoreCase("yes"));


	}

	/**
	 * A shortcut to System.out.println
	 * @param msg: the line to be output
	 * @return: none 
	 */
	public void print(String msg){
		System.out.println(msg);   
	}

	/**
	 * A shortcut to System.out.println which will surround the message with some stars to make it stand out.
	 * @param msg: the line to be output
	 * @return: none 
	 */
	public void printFancy(String msg){
		System.out.println("********************************");
		System.out.println(msg);   
		System.out.println("********************************");
	}

	/**
	 * A newly added method that restricts the user to choosing one of two word options
	 * @param prompt : the question to print to the user for input prompt
	 * @param choice1: the first valid option user can type
	 * @param choice2: the second valid option user can type
	 */
	public String inputWord(String prompt, String choice1, String choice2){
		String userChoice = inputWord(prompt);
		while (!(userChoice.equalsIgnoreCase(choice1) || userChoice.equalsIgnoreCase(choice2))){
			this.print("Sorry " + userChoice + " not recongized as valid response. Please type \"" + choice1 + "\" or \"" + choice2 + "\"");
			userChoice = inputWord(prompt);

		}
		return userChoice;
	}


	/**
	 * A newly added method that restricts the user to choosing one of three word options
	 * @param prompt : the question to print to the user for input prompt
	 * @param choice1: the first valid option user can type
	 * @param choice2: the second valid option user can type
	 * @param choice3: the third valid option user can type
	 */
	public String inputWord(String prompt, String choice1, String choice2, String choice3){
		String userChoice = inputWord(prompt);
		while (!(userChoice.equalsIgnoreCase(choice1) || userChoice.equalsIgnoreCase(choice2) || userChoice.equalsIgnoreCase(choice3))){
			this.print("Sorry " + userChoice + " not recongized as valid response. Please type \"" + choice1 + "\" or \"" + choice2 + "\" or \"" + choice3  +"\"");
			userChoice = inputWord(prompt);

		}
		return userChoice;
	}
	
}

