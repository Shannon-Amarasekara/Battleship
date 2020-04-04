import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        introductionToTheGame();

        Board playerBoard = createABoard();
        playerBoard.displayPlayerBoard(playerBoard);

        placeOneObligatoryBoat(playerBoard);
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
        Board enemyBoard = createABoard();
        int randomCounterOfEnemyBoats = 1 + (int) (Math.random() * 3);

        for (int i = 0; i < randomCounterOfEnemyBoats; i++) {
            int randomColumn = 1 + (int) (Math.random() * 10);
            int randomRow = 1 + (int) (Math.random() * 10);

            randomColumn = randomColumn - 1;
            enemyBoard.placeABoatOnTheBoard(randomColumn, randomRow);
        }
        return enemyBoard;
    }

    private static boolean verifyTheBoatPlacementPositionExists(String column, String rowBoat, List<String> columnsAtoJ) {
        List<String> listOfTenPositions = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        if (!listOfTenPositions.contains(rowBoat) || (!columnsAtoJ.contains(column))) {

            if ((!columnsAtoJ.contains(column))) {
                System.out.println("The column " + column + " is not a valid column.");
            }
            if (!listOfTenPositions.contains(rowBoat)) {
                System.out.println("The row " + rowBoat + " is not a valid row.");
            }
            return false;
        }
        return true;
    }

    private static void placeOneObligatoryBoat(Board board) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        System.out.println("Choose which column you want to place your first boat. (A - J)");
        String columnBoat = scanner.next();
        System.out.println("Choose which row you want to place your first boat. (1-10)");
        String rowBoat = scanner.next();

        while (!verifyTheBoatPlacementPositionExists(columnBoat, rowBoat, columnsAToJ)) {
            System.out.println("Choose a different position for your boat.");
            System.out.println("Choose which column you want to place your first boat. (A - J)");
            columnBoat = scanner.next();
            System.out.println("Choose which row you want to place your first boat. (1-10)");
            rowBoat = scanner.next();
        }

        int row = Integer.parseInt(rowBoat);

        int column = columnsAToJ.indexOf(columnBoat);
        board.placeABoatOnTheBoard(column, row);
        System.out.println("You have placed your first boat in the position " + columnBoat + row + ".");
        placeSupplementaryBoats(board);
    }

    private static void placeSupplementaryBoats(Board board) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        int boatCount = 1;

        while (boatCount < 3) {
            System.out.println("Would you like to place another boat? (yes/no)");
            String answer = scanner.next();

            while ((!answer.equals("yes")) && (!answer.equals("no"))) {
                System.out.println("Answer not recognised");
                System.out.println("Would you like to place another boat? (yes/no)");
                answer = scanner.next();
            }

            if (answer.equals("yes")) {
                System.out.println("Choose which column you want to place your boat. (A - J)");
                String columnBoat = scanner.next();
                int column = columnsAToJ.indexOf(columnBoat);
                System.out.println("Choose which row you want to place your boat. (1-10)");
                String rowBoat = scanner.next();

                while (!verifyTheBoatPlacementPositionExists(columnBoat, rowBoat, columnsAToJ)) {
                    System.out.println("Choose a different position for your boat.");
                    System.out.println("Choose which column you want to place your boat. (A - J)");
                    columnBoat = scanner.next();
                    System.out.println("Choose which row you want to place your boat. (1-10)");
                    rowBoat = scanner.next();
                    column = columnsAToJ.indexOf(columnBoat);
                }

                if (verifyTheBoatPlacementPositionExists(columnBoat, rowBoat, columnsAToJ)) {
                    int row = Integer.parseInt(rowBoat);
                    board.placeABoatOnTheBoard(column, row);
                    System.out.println("You have placed this boat in the position " + columnBoat + rowBoat + " .");
                    boatCount = boatCount + 1;
                }

            } else {
                boatCount = 3;
            }

            if (boatCount == 3) {
                System.out.println("You have placed all your boats.");
            }
        }
    }

    private static void attackTheEnemyBoard(Board enemyBoard) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        System.out.println("Which column would you like to attack? (A-J)");
        String columnToAttack = scanner.next();
        System.out.println("Which row would you like to attack? (1-10)");
        String rowToAttack = scanner.next();

        while (!verifyTheBoatPlacementPositionExists(columnToAttack, rowToAttack, columnsAToJ)) {
            System.out.println("Choose a different position to attack.");
            System.out.println("Which column would you like to attack? (A-J)");
            columnToAttack = scanner.next();
            System.out.println("Which row would you like to attack? (1-10)");
            rowToAttack = scanner.next();
        }

        int row = Integer.parseInt(rowToAttack);
        int column = columnsAToJ.indexOf(columnToAttack);

        if (verifyIfABoatIsInThisPosition(enemyBoard, row, column)) {
            setValueOfSquareToSunkBoat(enemyBoard, row, column);
            System.out.println("Well done! You have sunk an enemy boat.");


        } else {
            System.out.println("No boat was sunk...");
        }
        enemyBoard.displayEnemyBoard(enemyBoard);
    }

    private static boolean verifyIfABoatIsInThisPosition(Board board, int row, int column) {
        return getValueOfSquare(board, row, column).equals(Square.ValueOfSquare.BOAT);
    }

    private static Square.ValueOfSquare getValueOfSquare(Board board, int row, int column) {
        return board.getSquares().get(row - 1).get(column).getValueOfSquare();
    }

    private static void setValueOfSquareToSunkBoat(Board board, int row, int column) {
        board.getSquares().get(row - 1).get(column).setValueOfSquare(Square.ValueOfSquare.SUNK_BOAT);
    }

    private static List<Integer> createListOfTenPositions() {
        return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private static int calculateRandomPositionToAttack(List<Integer> listOfPositions) {
        Random random = new Random();
        return listOfPositions.get(random.nextInt(listOfPositions.size()));
    }

    private static void enemyAttacksThePlayerBoard(Board playerBoard, List<Integer> listOfRows, List<Integer> listOfColumns) {
        int randomRow = calculateRandomPositionToAttack(listOfRows);
        int randomColumn = calculateRandomPositionToAttack(listOfColumns);

        listOfRows.remove(listOfRows.indexOf(randomRow));
        listOfColumns.remove(listOfColumns.indexOf(randomColumn));

        List<String> listOfColumnsAtoJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        Square squareUnderAttack = playerBoard.getSquares().get(randomRow).get(randomColumn);
        String column = listOfColumnsAtoJ.get(randomColumn - 1);

        if (squareUnderAttack.getValueOfSquare().equals(Square.ValueOfSquare.BOAT)) {
            squareUnderAttack.setValueOfSquare(Square.ValueOfSquare.SUNK_BOAT);
            System.out.println("The enemy attacked position " + column + randomRow + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + column + randomRow + " .");
            System.out.println("No boat was hit!");
        }
        playerBoard.displayPlayerBoard(playerBoard);
    }

    private static boolean boardContainsBoats(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            for (int j = 0; j < board.getSquares().get(i).size(); j++) {

                Square square = board.getSquares().get(i).get(j);
                if (square.getValueOfSquare() == Square.ValueOfSquare.BOAT) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        List<Integer> listOfRows = createListOfTenPositions();
        List<Integer> listOfColumns = createListOfTenPositions();

        while (true) {

            attackTheEnemyBoard(enemyBoard);
//            if (!boardContainsBoats(enemyBoard)) {
//                playerWins();
//            }

            enemyAttacksThePlayerBoard(playerBoard, listOfRows, listOfColumns);
//            if (!boardContainsBoats(playerBoard)) {
//                playerLoses();
//            }
        }
    }

    private static void playerWins() {
        System.out.println("WIN !");
        System.exit(0);
    }

    private static void playerLoses() {
        System.out.println("YOU LOSE");
        System.exit(0);
    }
}
