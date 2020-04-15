
import java.util.*;
import java.util.List;

public class Battleship {

    public static void main(String[] args) {
        introductionToTheGame();

        Board playerBoard = createABoard();
        playerBoard.displayPlayerBoard(playerBoard);

        placeBoats(playerBoard);
        playerBoard.displayPlayerBoard(playerBoard);

        Board enemyBoard = createAnEnemyBoard();
        enemyBoard.displayEnemyBoard(enemyBoard);

        playerAndEnemyAttackEachOther(playerBoard, enemyBoard);

    }

    private static void introductionToTheGame() {
        System.out.println("Welcome to Battleship.");
    }

    private static Board createABoard() {
        return Board.createABoard();
    }

    private static Board createAnEnemyBoard() {
        return Board.createAnEnemyBoard();
    }

    private static void placeBoats(Board board) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listOfBoatsPlaced = new ArrayList<>();
        placeOneObligatoryBoat(board, listOfBoatsPlaced);
        System.out.println("You have placed " + listOfBoatsPlaced.size() + " boat in the following positions: " + listOfBoatsPlaced);

        while (listOfBoatsPlaced.size() < 3) {
            System.out.println("Would you like to place another boat? (yes/no)");
            String answer = scanner.next();

            while ((!answer.equals("yes")) && (!answer.equals("no"))) {
                System.out.println("Answer not recognised.");
                System.out.println("Would you like to place another boat? (yes/no)");
                answer = scanner.next();
            }

            if (answer.equals("yes")) {
                placeSupplementaryBoats(board, listOfBoatsPlaced);
                System.out.println("You have placed " + listOfBoatsPlaced.size() + " boats in the following positions: " + listOfBoatsPlaced);
            } else {
                break;
            }
        }
    }

    private static void placeOneObligatoryBoat(Board board, ArrayList<String> listOfBoatsPlaced) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        System.out.println("Choose which column you want to place your first boat. (A - J)");
        String columnBoat = scanner.next();
        while (!board.columnPlacementPositionExists(columnBoat, columnsAToJ)) {
            System.out.println("Choose a different column for your first boat. (A - J)");
            columnBoat = scanner.next();
        }

        System.out.println("Choose which row you want to place your first boat. (1-10)");
        String rowBoat = scanner.next();
        while (!board.rowPlacementPositionExists(rowBoat)) {
            System.out.println("Choose a different row for your first boat. (1-10)");
            rowBoat = scanner.next();
        }

        int column = columnsAToJ.indexOf(columnBoat);
        int row = Integer.parseInt(rowBoat);
        board.placeABoatOnTheBoard(column, row);

        String position = columnBoat + rowBoat;
        System.out.println("You have placed your first boat in the position " + position + ".");
        listOfBoatsPlaced.add(position);
    }

    private static void placeSupplementaryBoats(Board board, ArrayList<String> listOfBoatsPlaced) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        System.out.println("Choose which column you want to place your boat. (A - J)");
        String columnBoat = scanner.next();
        int column = columnsAToJ.indexOf(columnBoat);
        while (!board.columnPlacementPositionExists(columnBoat, columnsAToJ)) {
            System.out.println("Choose a different column for your first boat. (A - J)");
            columnBoat = scanner.next();
            column = columnsAToJ.indexOf(columnBoat);
        }

        System.out.println("Choose which row you want to place your boat. (1-10)");
        String rowBoat = scanner.next();
        while (!board.rowPlacementPositionExists(rowBoat)) {
            System.out.println("Choose a different row for your first boat. (1-10)");
            rowBoat = scanner.next();
        }

        int row = Integer.parseInt(rowBoat);

        if (!aBoatIsInThisPosition(board, row, column)) {
            board.placeABoatOnTheBoard(column, row);
            String position = columnBoat + rowBoat;
            System.out.println("You have placed this boat in the position " + position + ".");
            listOfBoatsPlaced.add(position);
        } else {
            System.out.println("A boat has already been placed in this position. ");
        }
    }

    private static boolean aBoatIsInThisPosition(Board board, int row, int column) {
        return getValueOfSquare(board, row, column).equals(Square.ValueOfSquare.BOAT);
    }

    private static Square.ValueOfSquare getValueOfSquare(Board board, int row, int column) {
        return board.getSquares().get(row - 1).get(column).getValueOfSquare();
    }

    private static void setValueOfSquareToSunkBoat(Board board, int row, int column) {
        board.getSquares().get(row - 1).get(column).setValueOfSquare(Square.ValueOfSquare.SUNK_BOAT);
    }

    private static int calculateRandomPositionToAttack(List<Integer> listOfPositions) {
        Random random = new Random();
        return listOfPositions.get(random.nextInt(listOfPositions.size()));
    }

    private static ArrayList<Integer> createListOfBoardPositions() {
        ArrayList<Integer> listOf99Positions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOf99Positions.add(i);
        }
        return listOf99Positions;
    }

    private static void attackTheEnemyBoard(Board enemyBoard, List<String> positionsAlreadyAttacked) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        String reset = "yes";
        if (positionsAlreadyAttacked.size() > 0) {
            System.out.println("You have previously attacked the following positiona: " + positionsAlreadyAttacked + ".");
        }

        while (reset.equals("yes")) {

            System.out.println("Which column would you like to attack? (A-J)");
            String columnToAttack = scanner.next();
            while (!enemyBoard.columnPlacementPositionExists(columnToAttack, columnsAToJ)) {
                System.out.println("This columns does not exist. Choose a different column to attack. (A - J)");
                columnToAttack = scanner.next();
            }

            System.out.println("Which row would you like to attack? (1-10)");
            String rowToAttack = scanner.next();
            while (!enemyBoard.rowPlacementPositionExists(rowToAttack)) {
                System.out.println("This row does not exist. Choose a different row to attack. (1-10)");
                rowToAttack = scanner.next();
            }

            if (positionsAlreadyAttacked.contains(columnToAttack + rowToAttack)) {
                System.out.println("You have already attacked this position. Choose another position.");
                reset = "yes";
            } else {
                int row = Integer.parseInt(rowToAttack);
                int column = columnsAToJ.indexOf(columnToAttack);
                positionsAlreadyAttacked.add(columnToAttack + rowToAttack);
                System.out.println("You have attacked the enemy board position " + columnToAttack + rowToAttack + ".");

                if (aBoatIsInThisPosition(enemyBoard, row, column)) {
                    setValueOfSquareToSunkBoat(enemyBoard, row, column);
                    System.out.println("Well done! You have sunk an enemy boat.");
                } else {
                    System.out.println("No boat was sunk...");
                }
                reset = "no";
            }
        }
        enemyBoard.displayEnemyBoard(enemyBoard);
    }

    private static void enemyAttacksThePlayerBoard(Board playerBoard, ArrayList<Integer> listOfPositions) {
        int positionToAttack = calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(listOfPositions.indexOf(positionToAttack));
        int row = positionToAttack / 10;
        int column = positionToAttack % 10;

        List<String> listOfColumnsAtoJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        Square squareUnderAttack = playerBoard.getSquares().get(row).get(column);
        String columnAttacked = listOfColumnsAtoJ.get(column);

        if (squareUnderAttack.getValueOfSquare().equals(Square.ValueOfSquare.BOAT)) {
            setValueOfSquareToSunkBoat(playerBoard, row + 1, column);
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("No boat was hit!");
        }
        playerBoard.displayPlayerBoard(playerBoard);
    }

    private static void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        ArrayList<Integer> positions = createListOfBoardPositions();
        List<String> positionsAlreadyAttacked = new ArrayList<>();

        while (true) {
            attackTheEnemyBoard(enemyBoard, positionsAlreadyAttacked);
            if (boardContainsNoBoats(enemyBoard)) {
                playerWins();
            }

            enemyAttacksThePlayerBoard(playerBoard, positions);
            if (boardContainsNoBoats(playerBoard)) {
                playerLoses();
            }
        }
    }

    private static boolean boardContainsNoBoats(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            for (int j = 0; j < board.getSquares().get(i).size(); j++) {

                Square square = board.getSquares().get(i).get(j);
                String valueOfSquare = square.getValueOfSquare().getRepresentationSurLePlateau();
                String value = Square.ValueOfSquare.BOAT.getRepresentationSurLePlateau();

                if (valueOfSquare.equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void playerWins() {
        System.out.println("YOU WIN !");
        System.exit(0);
    }

    private static void playerLoses() {
        System.out.println("YOU LOSE");
        System.exit(0);
    }
}
