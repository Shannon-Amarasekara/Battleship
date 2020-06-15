package Application;

import Domain.Attacks;
import Domain.Board;
import Domain.Square;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardAttackService {

    private UserInputValidationService userInputValidationService = new UserInputValidationService();
    private BoardDisplayService boardDisplayService = new BoardDisplayService();
    private Attacks attacks = new Attacks();


    public void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        HashMap<Integer, Square> positionsAlreadyAttackedByEnemy = attacks.createListOfBoardPositions();
        List<String> positionsAlreadyAttackedByPlayer = new CopyOnWriteArrayList<>();
        //TODO refactor

        while (true) {
            attackTheEnemyBoard(enemyBoard, positionsAlreadyAttackedByPlayer);
            if (enemyBoard.boardContainsNoBoats()) {
                playerWins();
            }
            enemyAttacksThePlayerBoard(playerBoard, positionsAlreadyAttackedByEnemy);
            if (playerBoard.boardContainsNoBoats()) {
                playerLoses();
            }
        }
    }

    public void attackTheEnemyBoard(Board board, List<String> positionsAlreadyAttacked) {
        if (positionsAlreadyAttacked.size() > 0) {
            displayPositionsPreviouslyAttackedByPlayer(positionsAlreadyAttacked);
        }

        while (true) {
            int column = playerChoosesColumnToAttack(board);
            int row = playerChoosesRowToAttack(board);
            String positionName = boardDisplayService.getSquarePositionRepresentationOnTheBoard(column, row);

            if (positionsAlreadyAttacked.contains(positionName)) {
                System.out.println("You have already attacked this position previously.");
            } else {
                positionsAlreadyAttacked.add(positionName);
                System.out.println("You have attacked the enemy board position " + positionName);

                if (attacks.attackAnEnemyPosition(board, row, column)) {
                    System.out.println("Well done! You have sunk an enemy boat.");
                    break;
                } else {
                    System.out.println("No boat was hit...");
                    break;
                }
            }
        }
        Collections.sort(positionsAlreadyAttacked);
        boardDisplayService.displayBoard(board);
        System.out.println("Here is the enemy board.");
    }

    public HashMap<String, Integer> createHashMapOfColumnsAndRowsRepresentationOnTheBoard() {
        HashMap<String, Integer> columnsAndRows = new HashMap<>();
        columnsAndRows.put("A", 0);
        columnsAndRows.put("B", 1);
        columnsAndRows.put("C", 2);
        columnsAndRows.put("D", 3);
        columnsAndRows.put("E", 4);
        columnsAndRows.put("F", 5);
        columnsAndRows.put("G", 6);
        columnsAndRows.put("H", 7);
        columnsAndRows.put("I", 8);
        columnsAndRows.put("J", 9);
        return columnsAndRows;
    }

    public List<List<String>> getListOfPositionNamesAttackedByPlayer(List<String> positionsAlreadyAttacked) {
        HashMap<String, Integer> columnsAndRows = createHashMapOfColumnsAndRowsRepresentationOnTheBoard();

        List<List<String>> positionNamesAttackedByPlayer = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            positionNamesAttackedByPlayer.add(new CopyOnWriteArrayList<>());
        }

        for (String position : positionsAlreadyAttacked) {
            String columnOfPosition = String.valueOf(position.charAt(0));
            int keyOfColumn = columnsAndRows.get(columnOfPosition);
            positionNamesAttackedByPlayer.get(keyOfColumn).add(position);
        }
        return positionNamesAttackedByPlayer;
    }

    public void displayPositionsPreviouslyAttackedByPlayer(List<String> positionsAlreadyAttacked) {
        List<List<String>> positionNamesAttackedByPlayer = getListOfPositionNamesAttackedByPlayer(positionsAlreadyAttacked);

        System.out.println("You previously attacked the following positions: ");
        for (List<String> columnList : positionNamesAttackedByPlayer) {
            if (!columnList.isEmpty()) {
                Collections.sort(columnList);
                for (String position : columnList) {
                    if (position.endsWith("0")) {
                        columnList.remove(position);
                        columnList.add(columnList.size(), position);
                    }
                }
                System.out.println(columnList);
            }
        }
    }


    public void enemyAttacksThePlayerBoard(Board playerBoard, HashMap<Integer, Square> listOfPositions) {
        int positionToAttack = attacks.calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(positionToAttack);
        int row = attacks.enemyGetsRowForAttack(positionToAttack);
        int column = attacks.enemyGetsColumnToAttack(positionToAttack);
        String position = boardDisplayService.getSquarePositionRepresentationOnTheBoard(column, row + 1);
        Square square = playerBoard.getSquarePosition(row, column);

        if (square.playerBoatIsInThisPosition()) {
            square.sinkPlayerBoat();
            System.out.println("The enemy attacked position " + position + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + position + " .");
            System.out.println("No boat was hit!");
        }
        boardDisplayService.displayBoard(playerBoard);
        System.out.println("Here is your board.");
    }

    public int playerChoosesColumnToAttack(Board board) {
        int column = -1;
        while (column == -1) {
            System.out.println("which column would you like to attack? (A - J)");
            column = userInputValidationService.validatePlayerColumnInput(board);
        }
        return column;
    }

    public int playerChoosesRowToAttack(Board board) {
        int row = -1;
        while (row == -1) {
            System.out.println("Which row would you like to attack? (1-10)");
            row = userInputValidationService.validatePlayerRowInput(board);
        }
        return row;
    }


    public void playerWins() {
        System.out.println("YOU WIN !");
        System.exit(0);
    }

    public void playerLoses() {
        System.out.println("YOU LOSE");
        System.exit(0);
    }
}
