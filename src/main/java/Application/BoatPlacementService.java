package Application;

import Domain.*;

import java.util.*;

public class BoatPlacementService {

    private Scanner scanner = new Scanner(System.in);
    private InputValidationService inputValidationService = new InputValidationService();

    public void playerPlacesOneToThreeBoats(Board board) {
        board.placeOneToThreeBoats(playerChoosesSquaresToPlaceOneToThreeBoats(board));
    }


    public List<Square> playerChoosesSquaresToPlaceOneToThreeBoats(Board board) {
        List<Square> listOfSquaresForBoats = new ArrayList<>();
        List<String> listOfBoatsPlaced = new ArrayList<>();

        listOfSquaresForBoats.add(playerChoosesPositionToPlaceABoat(board, listOfBoatsPlaced));
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
                listOfSquaresForBoats.add(playerChoosesPositionToPlaceABoat(board, listOfBoatsPlaced));
                Collections.sort(listOfBoatsPlaced);

            } else {
                System.out.println("You have placed " + listOfBoatsPlaced.size() + " boats in the following positions: " + listOfBoatsPlaced);
                break;
            }
            System.out.println("You have placed " + listOfBoatsPlaced.size() + " boats in the following positions: " + listOfBoatsPlaced);
        }
        return listOfSquaresForBoats;
    }


    public Square playerChoosesPositionToPlaceABoat(Board board, List<String> list) {
        int column;
        int row;

        while (true) {
            System.out.println("Choose which position you want to place your boat. (e.g. 'A1')");
            String position = scanner.next().toUpperCase();

            if (list.contains(position)) {
                System.out.println("You already placed a boat in the position " + position + ".");
            } else {
                if (inputValidationService.isValidPlayerPositionInput(position)) {
                    column = inputValidationService.getColumnFromPlayerInput(position);
                    row = inputValidationService.getRowFromPlayerInput(position);
                    list.add(position);
                    break;
                }
                System.out.println("Something went wrong, no boat was placed. Try again.");
            }
        }
        return board.findTheSquareOnTheBoard(row, column);
    }
}