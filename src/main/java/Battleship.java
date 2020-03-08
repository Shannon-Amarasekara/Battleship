import java.util.Scanner;

//introduction au jeu
//creer le plateau joueur
//placer les bateaux joueurs - max 3
//creer le plateau enemi
//placer bateaux 1-3 au hasard dans position au hasard
//joueur et enemi attaque le plateau de l'autre chacun leur tour
//quand un bateau a ete touche, il coule
//une fois que les 3 bateaux (joueur ou enemi) ont ete coule
//END GAME

public class Battleship {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        introductionToTheGame();

        Board plateauDuJoueur = createABoard();

        placerUnBateauObligatoirement(scanner, plateauDuJoueur);

        plateauDuJoueur.displayBoard();

        Board enemyBoard = createAnEnemyBoard();
        System.out.println("Voici le plateau ennemi");
        enemyBoard.displayBoard();

        attackTheEnemyBoard(scanner, enemyBoard);
        enemyAttacksThePlayerBoard(plateauDuJoueur);

    }

    private static void introductionToTheGame() {
        System.out.println("Welcome to Battleship.");
        System.out.println("Here is your board.");
    }

    private static Board createABoard() {
        return Board.createABoardOf10Squares();
    }

    private static Board createAnEnemyBoard() {
        Board enemyBoard = createABoard();
        int randomCounterOfEnemiBoats = 1 + (int) (Math.random() * 3);
        for (int i = 0; i < randomCounterOfEnemiBoats; i++) {
            int randomBoatPosition = 1 + (int) (Math.random() * 10);
            enemyBoard.placeABoatOnTheBoard(randomBoatPosition);
        }
        return enemyBoard;
    }

    private static boolean verifyThePosition(Board board, int boatPosition) {
        if (boatPosition >= 1 && boatPosition <= 10) {
            Square caseBateau = board.getSquares().get(boatPosition - 1);
            return !caseBateau.getValueOfSquare().equals(ValueOfSquare.BOAT);
        }
        return false;
    }


    private static void placerUnBateauObligatoirement(Scanner scanner, Board board) {
        System.out.println("Choose a position to place your boat between 1 and 10.");
        int boatPosition = scanner.nextInt();

        while (!verifyThePosition(board, boatPosition)) {
            System.out.println("Choose a different position to place your boat between 1 and 10.");
            boatPosition = scanner.nextInt();
        }

        board.placeABoatOnTheBoard(boatPosition);
        System.out.println("You have placed your first boat.");
        placeSupplementaryBoats(scanner, board);

    }


    private static void placeSupplementaryBoats(Scanner scanner, Board board) {
        int boatCounter = 1;

        while (boatCounter < 3) {
            System.out.println("Would you like to place another boat?");
            String answer = scanner.next();

            if (answer.equals("yes")) {
                System.out.println("Choose a position to place your boat between 1 and 10.");
                int boatPosition = scanner.nextInt();

                while (!verifyThePosition(board, boatPosition)) {
                    System.out.println("Position not available. Choose a different position to place your boat between 1 and 10.");
                    boatPosition = scanner.nextInt();
                }
                if (verifyThePosition(board, boatPosition)) {
                    board.placeABoatOnTheBoard(boatPosition);
                    System.out.println("You have placed your boat in position " + boatPosition);
                    boatCounter = boatCounter + 1;
                }

            } else {
                boatCounter = 3;
            }

            if (boatCounter == 3) {
                System.out.println("You have placed all your boats.");
            }
        }
    }

    private static void attackTheEnemyBoard(Scanner scanner, Board enemyBoard) {
        System.out.println("Which position between 1 and 10 would you like to attack?");
        int attackPosition = scanner.nextInt();

        while (attackPosition < 1 || attackPosition > 10) {
            System.out.println("This position is not available.");
            System.out.println("Attack a position between 1 and 10.");
            attackPosition = scanner.nextInt();
        }

        if (getValueOfSquare(enemyBoard, attackPosition).equals(ValueOfSquare.BOAT)) {
            enemyBoard.getSquares().get(attackPosition - 1).setValueOfSquare(ValueOfSquare.SUNK_BOAT);
            System.out.println("Well done! You have sunk an enemy boat.");
        } else {
            System.out.println("No boat hit...");
        }
        System.out.println("Here is the enemy board.");
        enemyBoard.displayBoard();
    }

    private static ValueOfSquare getValueOfSquare(Board enemyBoard, int attackPosition) {
        return enemyBoard.getSquares().get(attackPosition - 1).getValueOfSquare();
    }

    private static void enemyAttacksThePlayerBoard(Board playerBoard) {
        int position = 1 + (int) (Math.random() * 10);
        Square squareAttack = playerBoard.getSquares().get(position - 1);

        //ne peut pas attaquer 2x la meme case

        if (squareAttack.getValueOfSquare().equals(ValueOfSquare.BOAT)) {
            squareAttack.setValueOfSquare(ValueOfSquare.SUNK_BOAT);
            System.out.println("Your enemy attacked position " + position + " .");
            System.out.println("One of your boats has been sunk!");
            System.out.println("Here is your board");
        } else {
            System.out.println("Your enemy attacked position " + position + " .");
            System.out.println("No boat was hit.");
        }
        System.out.println("Here is your board");
        playerBoard.displayBoard();
    }
}

//    public static void attaquesEntreJoueurEtEnemi(Scanner scanner, Plateau plateauDuJoueur, Plateau plateauEnemi) {
//
//        if (plateauDuJoueur.getCases().contains(ValeurDeCase.BATEAU.getRepresentationSurLePlateau())) {
//
//            attaquerLePlateauEnemi(scanner, plateauEnemi);
//            enemiAttaquePlateauDuJoueur(plateauDuJoueur);
//
//        } else {
//            System.out.println("GAME OVER");
//        }
//    }
//}
