package Application;

import java.util.Scanner;

public class GameIntroductionService {

    private Scanner scanner = new Scanner(System.in);

    public void introductionToTheGame() {
        System.out.println("                WELCOME TO THE BATTLESHIP GAME. \n \n" +
                "GAME OBJECTIVE: \n" +
                "To sink all the enemy boats. \n\n" +
                "HOW TO PLAY: \n" +
                "Place between 1 to 3 boats on your board. The enemy will place the same number of boats. \n" +
                "In turns, you and the enemy will attack one position each. \n");

        System.out.println("BOARD DISPLAY : \n" +
                "Your boats on the board will be represented by the letter: B \n" +
                "Your empty positions on the board will be represented by the letter: X \n" +
                "Your sunk boats on the board will be represented by the letter: S \n");
    }

    public void askToPlay() {
        while (true) {
            System.out.println("Would you like to start a game? (yes/no)");
            String answer = scanner.next().toUpperCase();
            if (answer.equals("YES")) {
                System.out.println("Great! Game is loading...");
                break;
            } else if (answer.equals("NO")) {
                System.out.println("Too bad, see you next time...");
                System.exit(0);
                break;
            } else {
                System.out.println("You need to enter yes or no.");
            }
        }
    }
}
