package Application;
import Domain.Board;
import Domain.Square;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardAttackService {

    UserInputValidationService userInputValidationService = new UserInputValidationService();
    BoardDisplayService boardDisplayService = new BoardDisplayService();

    public void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        HashMap<Integer, Square> positionsAlreadyAttackedByEnemy = createListOfBoardPositions();
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
            String columnToAttack = boardDisplayService.getColumnRepresentationOnTheBoard(column);

            if (positionsAlreadyAttacked.contains(columnToAttack + row)) {
                System.out.println("You have already attacked this position previously.");

            } else {
                positionsAlreadyAttacked.add(columnToAttack + row);
                System.out.println("You have attacked the enemy board position " + columnToAttack + row);

                if (boardDisplayService.enemyBoatIsInThisPosition(row, column, board)) {
                    boardDisplayService.sinkEnemyBoat(row, column, board);
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

    public void displayPositionsPreviouslyAttackedByPlayer(List<String> positionsAlreadyAttacked) {
        HashMap<String, Integer> columnsAndRows = createHashMapOfColumnsAndRowsRepresentationOnTheBoard();

        List<List<String>> positionsInOrder = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            positionsInOrder.add(new CopyOnWriteArrayList<>());
        }

        for (String position : positionsAlreadyAttacked) {
            String columnOfPosition = String.valueOf(position.charAt(0));
            int keyOfColumn = columnsAndRows.get(columnOfPosition);
            positionsInOrder.get(keyOfColumn).add(position);
        }

        System.out.println("You previously attacked the following positions: ");
        for (List<String> columnList : positionsInOrder) {
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
        int positionToAttack = calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(positionToAttack);
        int row = enemyGetsRowForAttack(positionToAttack);
        int column = enemyGetsColumnToAttack(positionToAttack);
        //TODO refactor

        String columnAttacked = boardDisplayService.getColumnRepresentationOnTheBoard(column);

        if (boardDisplayService.playerBoatIsInThisPosition(row, column, playerBoard)) {
            boardDisplayService.sinkPlayerBoat(row, column, playerBoard);
            System.out.println("The enemy attacked position " + columnAttacked + (boardDisplayService.getRowRepresentationOnTheBoard(row)) + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + columnAttacked + (boardDisplayService.getRowRepresentationOnTheBoard(row)) + " .");
            System.out.println("No boat was hit!");
        }
        boardDisplayService.displayBoard(playerBoard);
        System.out.println("Here is your board.");
    }

    public int enemyGetsRowForAttack(int position){
        return position / 10;
    }

    public int enemyGetsColumnToAttack(int position){
        return position % 10;
    }

    public int calculateRandomPositionToAttack(HashMap<Integer, Square> listOfPositions) {
        Random random = new Random();
        List<Integer> keys = new ArrayList<>(listOfPositions.keySet());
        return keys.get(random.nextInt(keys.size()));
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

    public HashMap<Integer, Square> createListOfBoardPositions() {
        HashMap<Integer, Square> listOf99Squares = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Square square = Square.EMPTY;
            listOf99Squares.put(i, square);
        }
        return listOf99Squares;
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
