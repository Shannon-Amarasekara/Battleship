package Application;
import Domain.Board;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardAttackService {

   UserInputValidationService userInputValidationService = new UserInputValidationService();
    BoardDisplayService boardDisplayService = new BoardDisplayService();

    public void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        ArrayList<Integer> positionsAlreadyAttackedByEnemy = createListOfBoardPositions();
        List<String> positionsAlreadyAttackedByPlayer = new CopyOnWriteArrayList<>();

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
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        displayPositionsPreviouslyAttackedByPlayer(positionsAlreadyAttacked);

        while (true) {

            int column = playerChoosesColumnToAttack(board, columnsAToJ);
            int row = playerChoosesRowToAttack(board);
            String columnToAttack = columnsAToJ.get(column);

            if (positionsAlreadyAttacked.contains(columnToAttack + row)) {
                System.out.println("You have already attacked this position previously.");

            } else {
                positionsAlreadyAttacked.add(columnToAttack + row);
                System.out.println("You have attacked the enemy board position " + columnToAttack + row);

                if (board.aBoatIsInThisPosition(row - 1, column)) {
                    board.setValueOfSquareToSunkBoat(row, column);
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

    public void displayPositionsPreviouslyAttackedByPlayer(List<String> positionsAlreadyAttacked) {
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

        if (positionsAlreadyAttacked.size() > 0) {
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
    }

    public void enemyAttacksThePlayerBoard(Board playerBoard, ArrayList<Integer> listOfPositions) {
        int positionToAttack = calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(listOfPositions.indexOf(positionToAttack));
        int row = positionToAttack / 10;
        int column = positionToAttack % 10;

        List<String> listOfColumnsAtoJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        String columnAttacked = listOfColumnsAtoJ.get(column);

        if (playerBoard.aBoatIsInThisPosition(row, column)) {
            playerBoard.setValueOfSquareToSunkBoat(row, column);
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("No boat was hit!");
        }
        boardDisplayService.displayBoard(playerBoard);
        System.out.println("Here is your board.");
    }

    public int calculateRandomPositionToAttack(List<Integer> listOfPositions) {
        Random random = new Random();
        return listOfPositions.get(random.nextInt(listOfPositions.size()));
    }

    public int playerChoosesColumnToAttack(Board board, List<String> columnsAtoJ) {
        int column = -1;
        while (column == -1) {
            System.out.println("which column would you like to attack? (A - J)");
            column = userInputValidationService.validatePlayerColumnInput(board, columnsAtoJ);
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

    public ArrayList<Integer> createListOfBoardPositions() {
        ArrayList<Integer> listOf99Positions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOf99Positions.add(i);
        }
        return listOf99Positions;
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
