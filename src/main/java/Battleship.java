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
        return Board.createABoardOf10Squares();
    }

    private static Board createAnEnemyBoard() {
        Board enemyBoard = createABoard();
        int randomCounterOfEnemyBoats = 1 + (int) (Math.random() * 3);
        for (int i = 0; i < randomCounterOfEnemyBoats; i++) {
            int randomBoatPosition = 1 + (int) (Math.random() * 10);
            enemyBoard.placeABoatOnTheBoard(randomBoatPosition);
        }
        return enemyBoard;
    }

    private static boolean verifyTheBoatPlacementPosition(Board board, int boatPosition) {
        if (boatPosition >= 1 && boatPosition <= 10) {
            Square squareBoat = board.getSquares().get(boatPosition - 1);
            return !squareBoat.getValueOfSquare().equals(ValueOfSquare.BOAT);
        }
        return false;
    }


    private static void placeOneObligatoryBoat(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a position for your boat between 1 and 10.");
        int boatPosition = scanner.nextInt();

        while (!verifyTheBoatPlacementPosition(board, boatPosition)) {
            System.out.println("Choose a different position for your boat between 1 and 10.");
            boatPosition = scanner.nextInt();
        }

        board.placeABoatOnTheBoard(boatPosition);
        System.out.println("You have placed your first boat.");
        placeSupplementaryBoats(board);

    }


    private static void placeSupplementaryBoats(Board board) {
        Scanner scanner = new Scanner(System.in);
        int boatCount = 1;

        while (boatCount < 3) {
            System.out.println("Would you like to place another boat? (yes/no)");
            String answer = scanner.next();

            if (answer.equals("yes")) {
                System.out.println("Choose a position between 1 and 10.");
                int boatPosition = scanner.nextInt();

                while (!verifyTheBoatPlacementPosition(board, boatPosition)) {
                    System.out.println("Position not available. Choose a position between 1 and 10.");
                    boatPosition = scanner.nextInt();
                }
                if (verifyTheBoatPlacementPosition(board, boatPosition)) {
                    board.placeABoatOnTheBoard(boatPosition);
                    System.out.println("You have placed this boat in the position " + boatPosition);
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
        System.out.println("Which position between 1 and 10 would you like to attack?");
        int attackPosition = scanner.nextInt();

        while (attackPosition < 1 || attackPosition > 10) {
            System.out.println("This position is not available.");
            System.out.println("Attack a position between 1 and 10.");
            attackPosition = scanner.nextInt();
        }

        if (verifyIfABoatIsInThisPosition(enemyBoard, attackPosition)) {

            setValueOfSquareToSunkBoat(enemyBoard, attackPosition);
            System.out.println("Well done! You have sunk an enemy boat.");

        } else {
            System.out.println("No boat was sunk...");
        }
        enemyBoard.displayEnemyBoard(enemyBoard);
    }

    private static boolean verifyIfABoatIsInThisPosition(Board enemyBoard, int attackPosition){
        return getValueOfSquare(enemyBoard, attackPosition).equals(ValueOfSquare.BOAT);
    }

    private static ValueOfSquare getValueOfSquare(Board board, int attackPosition) {
        return board.getSquares().get(attackPosition - 1).getValueOfSquare();
    }

    public static void setValueOfSquareToSunkBoat(Board board, int position){
        board.getSquares().get(position - 1).setValueOfSquare(ValueOfSquare.SUNK_BOAT);
    }


    private static int calculateRandomPositionToAttack() {
        return 1 + (int) (Math.random() * 10);
    }


    private static void enemyAttacksThePlayerBoard(Board playerBoard) {
        int randomPosition = calculateRandomPositionToAttack();

        Square attackSquare = playerBoard.getSquares().get(randomPosition - 1);

        if (attackSquare.getValueOfSquare().equals(ValueOfSquare.BOAT)) {
            attackSquare.setValueOfSquare(ValueOfSquare.SUNK_BOAT);
            System.out.println("The enemy attacked position " + randomPosition + " .");
            System.out.println("One of your boats was sunk!");
        } else {
            System.out.println("The enemy attacked position " + randomPosition + " .");
            System.out.println("No boat was hit!");
        }

        playerBoard.displayPlayerBoard(playerBoard);
    }

    private static void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        Square squareContainingBoat = new Square(ValueOfSquare.BOAT);
        while (true) {

            attackTheEnemyBoard(enemyBoard);
            if (!enemyBoard.getSquares().contains(squareContainingBoat)) {
                playerWins();
            }

            enemyAttacksThePlayerBoard(playerBoard);
            if (!playerBoard.getSquares().contains(squareContainingBoat)) {
                playerLoses();
            }
        }
    }

    private static void playerWins(){
        System.out.println("WIN !");
        System.exit(0);
    }

    private static void playerLoses(){
        System.out.println("YOU LOSE");
        System.exit(0);
    }
}
