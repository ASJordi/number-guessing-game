package dev.asjordi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GameController {
	
	private final Scanner sc = new Scanner(System.in);
	private final List<GameRecord> games = new ArrayList<>();
	
	public void start() {
		boolean end = true;
    	
    	while (end) {
    		var game = new Game();
    		game.play();
    		games.add(new GameRecord(game.getId(), game.getLevel(), game.getCurrentAttempts(), game.getDuration(), game.getSecretNumber()));
    		end = playAgain();
    	}
    	
    	showScore();
	}
	
	private boolean playAgain() {
		String validOptions = "12";
		String input = "";
		
		while(true) {
			System.out.println("Please select an option:");
			System.out.println("1. Play again");
			System.out.println("2. Exit and Show score");
			
			input = sc.nextLine();
	        input = Utils.cleanString(input);
	        
	        if (validOptions.contains(input)) break;
		}		

		return input.equalsIgnoreCase("1") ? true : false;
	}
	
	private void showScore() {
		System.out.println("User’s high score by level and attempts");
		games.sort(Comparator.comparingInt(GameRecord::level).thenComparing(GameRecord::attempts));
    	games.forEach(System.out::println);
	}
	
	record GameRecord(UUID id, int level, int attempts, long duration, int secretNumber) {}

}
