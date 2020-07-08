package Application;

import Domain.Attacks;
import Domain.Board;

import java.util.*;

public class BoardAttackService {

    private Scanner scanner = new Scanner(System.in);
    private InputValidationService inputValidationService = new InputValidationService();
    private BoardDisplayService boardDisplayService = new BoardDisplayService();
    private Attacks attacks = new Attacks();

    public void attackUntilGameEnds(Board playerBoard, Board enemyBoard) {
        List<String> pastPlayerAttacks = new ArrayList<>();
        List<Integer> availableEnemyAttacks = attacks.createListOfAllPositions();
        while (playerBoard.boatCountOnABoard() > 0 && enemyBoard.boatCountOnABoard() > 0) {
            playerAndEnemyAttack(playerBoard, enemyBoard, pastPlayerAttacks, availableEnemyAttacks);
        }
    }

    public void playerAndEnemyAttack(Board playerBoard, Board enemyBoard, List<String> pastPlayerAttacks, List<Integer> availableEnemyAttacks) {
        playerAttacks(enemyBoard, pastPlayerAttacks);
        boardDisplayService.displayEnemyBoard(enemyBoard);
        checkForWinner(enemyBoard, playerBoard);

        enemyAttacks(availableEnemyAttacks, playerBoard);
        boardDisplayService.displayPlayerBoard(playerBoard);
        checkForWinner(enemyBoard, playerBoard);
    }


    public void checkForWinner(Board enemyBoard, Board playerBoard) {
        if (enemyBoard.boatCountOnABoard() == 0) {
            System.out.println("You win");
            attacks.exitGame();
        } else if(playerBoard.boatCountOnABoard() == 0) {
            System.out.println("You lose");
            attacks.exitGame();
        }
    }

    public void enemyAttacks(List<Integer> allPositionsAvailable, Board board) {
        String randomPosition = attacks.calculateRandomPosition(allPositionsAvailable);
        int column = attacks.calculateColumnFromRandomPosition(randomPosition);
        int row = attacks.calculateRowFromRandomPosition(randomPosition);

        String positionForBoardDisplay = String.valueOf(boardDisplayService.convertColumnToBoardRepresentation(column))
                + (boardDisplayService.convertRowToBoardRepresentation(row));
        System.out.println("\nThe enemy has attacked position " + positionForBoardDisplay + ".");

        if (attacks.enemySinksBoat(row, column, board)) {
            System.out.println("The enemy has sunk one of your boats.");
        } else {
            System.out.println("None of your boats were hit.");
        }
    }

    public void playerAttacks(Board board, List<String> pastPlayerAttacks) {
        boardDisplayService.displayPositionsAttackedByPlayer(pastPlayerAttacks);
        int numberOfAttacksBefore = pastPlayerAttacks.size();
        int numberOfAttacksAfter = pastPlayerAttacks.size();

        while (numberOfAttacksBefore == numberOfAttacksAfter) {
            System.out.println("Choose which position you want to attack.");
            String position = scanner.next().toUpperCase();

            if (inputValidationService.isValidPlayerPositionInput(position)) {
                int column = inputValidationService.getColumnFromPlayerInput(position);
                int row = inputValidationService.getRowFromPlayerInput(position);

                if (!attacks.positionAlreadyAttacked(pastPlayerAttacks, position)) {

                    if (attacks.playerSinksEnemyBoat(pastPlayerAttacks, position, board, row, column)) {
                        System.out.println("You attacked position " + position + ".");
                        System.out.println("You sunk an enemy boat!");
                        numberOfAttacksAfter = pastPlayerAttacks.size();
                    } else {
                        System.out.println("No enemy boat was hit...");
                        numberOfAttacksAfter = pastPlayerAttacks.size();
                    }
                } else {
                    System.out.println("Position not available.");
                }
            } else {
                System.out.println("Position not recognised.");
            }
        }
    }
}
