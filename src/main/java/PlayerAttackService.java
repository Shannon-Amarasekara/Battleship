import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerAttackService {

    BoardDisplayService boardDisplayService = new BoardDisplayService();
    BoatDetectionService boatDetectionService = new BoatDetectionService();
    UserInputValidationService userInputValidationService = new UserInputValidationService();

    public void attackTheEnemyBoard(Board board, List<String> positionsAlreadyAttacked) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        displayPositionsPreviouslyAttackedByPlayer(positionsAlreadyAttacked);

        while (true) {

            int column = playerChoosesColumnToAttack(board, columnsAToJ, scanner);
            int row = playerChoosesRowToAttack(board, scanner);
            String columnToAttack = columnsAToJ.get(column);

            if (positionsAlreadyAttacked.contains(columnToAttack + row)) {
                System.out.println("You have already attacked this position previously.");

            } else {
                positionsAlreadyAttacked.add(columnToAttack + row);
                System.out.println("You have attacked the enemy board position " + columnToAttack + row);

                if (boatDetectionService.aBoatIsInThisPosition(board, (row - 1), column)) {
                    boatDetectionService.setValueOfSquareToSunkBoat(board, row, column);
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

    public int playerChoosesColumnToAttack(Board board, List<String> columnsAtoJ, Scanner scanner) {
        int column = -1;
        while (column == -1) {
            System.out.println("which column would you like to attack? (A - J)");
            column = userInputValidationService.validatePlayerColumnInput(scanner, board, columnsAtoJ);
        }
        return column;
    }


    public int playerChoosesRowToAttack(Board board, Scanner scanner) {
        int row = -1;
        while (row == -1) {
            System.out.println("Which row would you like to attack? (1-10)");
            row = userInputValidationService.validatePlayerRowInput(scanner, board);
        }
        return row;
    }
}
