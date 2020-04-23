
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
                board.placeABoatOnTheBoard(column, row);
                System.out.println("You have placed your boat in the position " + position + ".");
                listOfBoatsPlaced.add(position);
            } else {
                System.out.println("You already placed a boat in the position " + position);
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

    private static void attackTheEnemyBoard(Board enemyBoard, ArrayList<String> positionsAlreadyAttacked) {
        String reset = "yes";
        Scanner scanner = new Scanner(System.in);

        if (positionsAlreadyAttacked.size() > 0) {
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new ArrayList<>());
            }

            for (String position : positionsAlreadyAttacked) {
                char columnChar = position.charAt(0);
                switch (columnChar) {
                    case 'A':
                        list.get(0).add(position);
                        break;
                    case 'B':
                        list.get(1).add(position);
                        break;
                    case 'C':
                        list.get(2).add(position);
                        break;
                    case 'D':
                        list.get(3).add(position);
                        break;
                    case 'E':
                        list.get(4).add(position);
                        break;
                    case 'F':
                        list.get(5).add(position);
                        break;
                    case 'G':
                        list.get(6).add(position);
                        break;
                    case 'H':
                        list.get(7).add(position);
                        break;
                    case 'I':
                        list.get(8).add(position);
                        break;
                    case 'J':
                        list.get(9).add(position);
                        break;
                }
            }

            //TODO: find a way to sort the positions ending in "10" to the end of the list
            System.out.println("You previously attacked the following positions: ");
            for (ArrayList<String> columnList : list) {

                if (!columnList.isEmpty()) {
                    Collections.sort(columnList);

                    for (String position : columnList) {

                        if (position.endsWith("10")) {
                            System.out.println("There is a number ending in 10");
//                            columnList.remove(position);
                        }
                    }
                    System.out.println(columnList);
                }
            }
        }


        while (reset.equals("yes")) {
            List<String> columnsAToJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
            int column;
            while (true) {
                System.out.println("Choose which column you want to attack. (A - J)");
                if (scanner.hasNextInt()) {
                    System.out.println("You need to enter a character, not a number. (A -J");
                    scanner.next();
                } else {
                    String columnInput = scanner.next();
                    if (columnsAToJ.contains(columnInput)) {
                        column = columnsAToJ.indexOf(columnInput);
                        if (enemyBoard.columnExists(column + 1)) {
                            break;
                        }
                    } else {
                        System.out.println("This column is unavailable.");
                    }
                }
            }

            int row;
            while (true) {
                System.out.println("Which row would you like to attack? (1-10)");
                if (!scanner.hasNextInt()) {
                    System.out.println("You need to enter a number. (1-10)");
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

            String columnToAttack = columnsAToJ.get(column);
            if (positionsAlreadyAttacked.contains(columnToAttack + row)) {
                System.out.println("You have already attacked this position previously.");
                reset = "yes";
            } else {
                reset = "no";
                positionsAlreadyAttacked.add(columnToAttack + row);
                System.out.println("You have attacked the enemy board position " + columnToAttack + row);

                if (aBoatIsInThisPosition(enemyBoard, row, column)) {
                    setValueOfSquareToSunkBoat(enemyBoard, row, column);
                    System.out.println("Well done! You have sunk an enemy boat.");
                } else {
                    System.out.println("No boat was hit...");
                }
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
        ArrayList<Integer> positionsAlreadyAttackedByEnemy = createListOfBoardPositions();
        ArrayList<String> positionsAlreadyAttackedByPlayer = new ArrayList<>();

        while (true) {
            attackTheEnemyBoard(enemyBoard, positionsAlreadyAttackedByPlayer);
            if (boardContainsNoBoats(enemyBoard)) {
                playerWins();
            }

            enemyAttacksThePlayerBoard(playerBoard, positionsAlreadyAttackedByEnemy);
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
