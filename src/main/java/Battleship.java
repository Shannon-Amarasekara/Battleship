
import java.util.*;
import java.util.List;

public class Battleship {

    public static void main(String[] args) {

        introductionToTheGame();

        Board playerBoard = Board.createABoard();
        displayBoard(playerBoard);
        System.out.println("Here is your board.");
        int boatCount = placeOneToThreeBoats(playerBoard);

        Board enemyBoard = Board.createBoardWithRandomlyPlacedBoats(boatCount);
        displayBoard(enemyBoard);
        System.out.println("Here is the enemy board");

        playerAndEnemyAttackEachOther(playerBoard, enemyBoard);


    }

    private static void introductionToTheGame() {
        System.out.println("Welcome to Battleship.");
    }

    private static void displayBoard(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            System.out.println();
            for (int j = 0; j < board.getSquares().get(i).size(); j++) {
                Square square = board.getSquares().get(i).get(j);
                System.out.print(" " + square.getValueOfSquare().getRepresentationOnTheBoard() + " ");
            }
        }
        System.out.println();
    }

    private static int placeOneToThreeBoats(Board board) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listOfBoatsPlaced = new ArrayList<>();
        placeBoat(board, listOfBoatsPlaced);
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
                placeBoat(board, listOfBoatsPlaced);
                Collections.sort(listOfBoatsPlaced);
                System.out.println("You have placed " + listOfBoatsPlaced.size() + " boats in the following positions: " + listOfBoatsPlaced);
            } else {
                break;
            }
        }
        displayBoard(board);
        System.out.println("Here is your board");
        return listOfBoatsPlaced.size();
    }

    private static void placeBoat(Board board, ArrayList<String> listOfBoatsPlaced) {
        Scanner scanner = new Scanner(System.in);
        List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        int column;
        while (true) {
            System.out.println("Choose which column you want to place your boat. (A - J)");
            if (scanner.hasNextInt()) {
                System.out.println("You need to enter a character, not a number. (A -J");
                scanner.next();
            } else {
                String columnInput = scanner.next();
                if (columnsAToJ.contains(columnInput)) {
                    column = columnsAToJ.indexOf(columnInput);
                    if (board.columnExists(column + 1)) {
                        break;
                    }
                } else {
                    System.out.println("This column is unavailable");
                }
            }
        }

        int row;
        while (true) {
            System.out.println("Choose a row for your first boat. (1-10)");
            if (!scanner.hasNextInt()) {
                System.out.println("You did not enter a number.");
                scanner.next();
            } else {
                row = scanner.nextInt();
                if (board.rowExists(row)) {
                    break;
                } else {
                    System.out.println("This row is unavailable.");
                }
            }
        }

        if (!aBoatIsInThisPosition(board, row, column)) {
            board.placeABoatOnTheBoard(column, row);
            String position = columnsAToJ.get(column) + row;

            if (!listOfBoatsPlaced.contains(position)) {
                System.out.println("You have placed your first boat in the position " + position + ".");
                listOfBoatsPlaced.add(position);
            } else {
                System.out.println("You already placed a boat in this position " + position);
            }
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
            System.out.println("You have previously attacked the following positions: " + positionsAlreadyAttacked + ".");
        }

        while (reset.equals("yes")) {

            System.out.println("Which column would you like to attack? (A-J)");
            String columnToAttack = scanner.next();
            int column = columnsAToJ.indexOf(columnToAttack);

            while (!enemyBoard.columnExists(column)) {
                System.out.println("This columns does not exist. Choose a different column to attack. (A - J)");
                columnToAttack = scanner.next();
                column = columnsAToJ.indexOf(columnToAttack);
            }

            int row;
            while (true) {
                System.out.println("Which row would you like to attack? (1-10)");
                if (!scanner.hasNextInt()) {
                    System.out.println("You did not enter a number.");
                    scanner.next();
                } else {
                    row = scanner.nextInt();
                    if (enemyBoard.rowExists(row)) {
                        break;
                    } else {
                        System.out.println("This row is unavailable.");
                    }
                }
            }

            if (positionsAlreadyAttacked.contains(columnToAttack + row)) {
                System.out.println("You have already attacked this position. Choose another position.");
                reset = "yes";
            } else {
                column = columnsAToJ.indexOf(columnToAttack);
                positionsAlreadyAttacked.add(columnToAttack + row);
                System.out.println("You have attacked the enemy board position " + columnToAttack + row + ".");

                if (aBoatIsInThisPosition(enemyBoard, row, column)) {
                    setValueOfSquareToSunkBoat(enemyBoard, row, column);
                    System.out.println("Well done! You have sunk an enemy boat.");
                } else {
                    System.out.println("No boat was sunk...");
                }
                reset = "no";
            }
        }
        Collections.sort(positionsAlreadyAttacked);
        displayBoard(enemyBoard);
        System.out.println("Here is the enemy board.");
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
        displayBoard(playerBoard);
        System.out.println("Here is your board.");
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
                String valueOfSquare = square.getValueOfSquare().getRepresentationOnTheBoard();
                String value = Square.ValueOfSquare.BOAT.getRepresentationOnTheBoard();

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
