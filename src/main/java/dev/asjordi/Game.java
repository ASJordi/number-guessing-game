package dev.asjordi;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Game {
    
	private final Scanner sc = new Scanner(System.in);
	private final Random r = new Random();
	private final UUID id = UUID.randomUUID();
	private int currentAttempts;
	private int secretNumber;
	private int level;
	private int attempts;
	private long duration;
	
	public Game() {
		this.currentAttempts = 0;
		this.secretNumber = generateNumber();
	}

	public void play() {
        welcome();
        
        this.level = calculateLevel();
        this.attempts = this.level == 1 ? 10 : this.level == 2 ? 5 : 3; 
        System.out.println("Great! You have selected the " + (this.level == 1 ? "Easy" : this.level == 2 ? "Medium" : "Hard") + " difficulty level.");
        System.out.println("Let's start the game!\n");
        
        var startTime = Instant.now();
        
        while (attempts > 0) {
        	System.out.println("Enter your guess:");
        	String input = sc.nextLine();
        	input = Utils.cleanString(input);
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
        
        var endTime = Instant.now();
        this.duration = Duration.between(startTime, endTime).getSeconds();
        System.out.println("Total time " + this.duration + " seconds");
        
	}
	
	private int calculateLevel() {
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
	        input = Utils.cleanString(input);
	        
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

	public UUID getId() {
		return id;
	}

	public int getCurrentAttempts() {
		return currentAttempts;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public int getLevel() {
		return level;
	}

	public long getDuration() {
		return duration;
	}	
	
}
