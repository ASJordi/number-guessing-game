package dev.asjordi;

import java.util.Random;
import java.util.Scanner;

public class Main {
    
	private final Scanner sc = new Scanner(System.in);
	private final Random r = new Random();
	
	public static void main(String[] args) {
		Main m = new Main();
    	m.play();
    }
	
	public void play() {
		int currentAttempts = 0;
        int secretNumber = generateNumber();
        
        welcome();
        
        int level = getLevel();
        int attempts = level == 1 ? 10 : level == 2 ? 5 : 3; 
        System.out.println("Great! You have selected the " + (level == 1 ? "Easy" : level == 2 ? "Medium" : "Hard") + " difficulty level.");
        System.out.println("Let's start the game!\n");
        
        while (attempts > 0) {
        	System.out.println("Enter your guess:");
        	String input = sc.nextLine();
        	input = cleanString(input);
        	int userNumber = Integer.parseInt(input);
        	currentAttempts++;
        	
        	if (userNumber == secretNumber) {
				System.out.println("Congratulations! You guessed the correct number in " + currentAttempts +" attempts.");
				break;
			} else {
				System.out.println("The secret number is " + (userNumber > secretNumber ? "less" : "greater") + " than " + userNumber);
				attempts--;
			}
        	
        	if (attempts == 0) {
        		System.out.println("You lost! The secret number was " + secretNumber);
        	}
		}
	}
	
	private int getLevel() {
		boolean valid = false;
		int level = -1;
		String validLevels = "123";
		
		while (!valid) {
			System.out.println("Please select the difficulty level:");
	        System.out.println("1. Easy (10 chances)");
	        System.out.println("2. Medium (5 chances)");
	        System.out.println("3. Hard (3 chances)");
	        System.out.println("Enter your choice: ");
	        
	        String input = sc.nextLine();
	        input = cleanString(input);
	        
	        if (!validLevels.contains(input)) continue;
	        
	        level = Integer.parseInt(input);
			valid = true;
		}
		
		return level;
	}
	
	private int generateNumber() {
		return this.r.nextInt(1, 101);
	}
	
	
	private void welcome() {
		System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have N chances to guess the correct number.\n");
	}
	
	private String cleanString(String str) {
		return str.replace(" ", "").trim();
	}
}
