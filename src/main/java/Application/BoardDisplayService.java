package Application;
import Domain.Board;
import Domain.Square;

public class BoardDisplayService {

    public void displayBoard(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            System.out.println();
            for (int j = 0; j < board.getSquares().get(i).size(); j++) {
                Square square = board.getSquares().get(i).get(j);
                System.out.print(" " + square.getValueOfSquare().getRepresentationOnTheBoard() + " ");
            }
        }
        System.out.println();
    }
}
