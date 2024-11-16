package dev.asjordi;

import java.util.Random;
import java.util.Scanner;

public class Main {
    
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
        Random r = new Random();
    	int currentAttempts = 0;
        int secretNumber = r.nextInt(1, 101);
        
        welcome();
        int level = getLevel();
        int attempts = level == 1 ? 10 : level == 2 ? 5 : 3; 
        System.out.println("Great! You have selected the " + (level == 1 ? "Easy" : level == 2 ? "Medium" : "Hard") + " difficulty level.");
        System.out.println("Let's start the game!");
        
        while (attempts > 0) {
        	System.out.println("Enter your guess:");
        	String input = sc.nextLine();
        	input = input.replace(" ", "").trim();
        	int userNumber = Integer.parseInt(input);
        	currentAttempts++;
        	
        	if (userNumber == secretNumber) {
				System.out.println("Congratulations! You guessed the correct number in " + currentAttempts +" attempts.");
				break;
			} else {
				System.out.println("The secret number is " + (userNumber > secretNumber ? "less" : "greater"));
				attempts--;
			}
        	
        	if (attempts == 0) {
        		System.out.println("You lost! The secret number was " + secretNumber);
        	}
		}
    }
	
	private static int getLevel() {
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
	        input = input.replace(" ", "").trim();
	        
	        if (!validLevels.contains(input)) continue;
	        
	        level = Integer.parseInt(input);
			valid = true;
		}
		
		return level;
	}
	
	
	private static void welcome() {
		System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have 5 chances to guess the correct number.\n");
	}
}
