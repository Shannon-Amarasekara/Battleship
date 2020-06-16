package Application;

import Domain.Board;
import Domain.Square;
import Domain.ValueOfSquare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BoatPlacementService {

    private Scanner scanner = new Scanner(System.in);
    private BoardDisplayService boardDisplayService = new BoardDisplayService();
    private UserInputValidationService userInputValidationService = new UserInputValidationService();

    public int placeOneToThreeBoats(Board board) {
        ArrayList<String> listOfBoatsPlaced = new ArrayList<>();
        checkPositionIsAvailableForPlayerToPlaceBoat(board, listOfBoatsPlaced);
        System.out.println("You have placed " + listOfBoatsPlaced.size() + " boat in the following positions: " + listOfBoatsPlaced);

        while (listOfBoatsPlaced.size() < 3) {
            System.out.println("Would you like to place another boat? (yes/no)");
            String answer = scanner.next().toLowerCase();

            while ((!answer.equals("yes")) && (!answer.equals("no"))) {
                System.out.println("Answer not recognised.");
                System.out.println("Would you like to place another boat? (yes/no)");
                answer = scanner.next().toLowerCase();
            }

            if (answer.equals("yes")) {
                checkPositionIsAvailableForPlayerToPlaceBoat(board, listOfBoatsPlaced);
                Collections.sort(listOfBoatsPlaced);
                System.out.println("You have placed " + listOfBoatsPlaced.size() + " boats in the following positions: " + listOfBoatsPlaced);
            } else {
                break;
            }
        }
        boardDisplayService.displayBoard(board);
        System.out.println("Here is your board");
        return listOfBoatsPlaced.size();
    }

    public Square playerChoosesSquareToPlaceBoat(Board board) {
        int column = playerChoosesColumnToPlaceBoat(board);
        int row = playerChoosesRowToPlaceBoat(board);
        return board.getSquarePosition(row, column);
//        return new Square(row, column);
    }


    public void checkPositionIsAvailableForPlayerToPlaceBoat(Board board, ArrayList<String> listOfBoatsPlaced) {
        Square square = playerChoosesSquareToPlaceBoat(board);
        String position = square.getPositionRepresentationOnTheBoard();

        if (square.playerBoatIsInThisPosition()) {
            square.placeBoat();
            System.out.println("You have placed your boat in the position " + position + ".");
            listOfBoatsPlaced.add(position);
        } else
            System.out.println("You already placed a boat in the position " + position);
    }

    public int playerChoosesColumnToPlaceBoat(Board board) {
        int column = -1;
        while (column == -1) {
            System.out.println("Choose which column you want to place your boat. (A - J)");
            column = userInputValidationService.validatePlayerColumnInput(board);
        }
        return column;
    }

    public int playerChoosesRowToPlaceBoat(Board board) {
        int row = -1;
        while (row == -1) {
            System.out.println("Choose a row for your first boat. (1-10)");
            row = userInputValidationService.validatePlayerRowInput(board);
        }
        return row;
    }
}
