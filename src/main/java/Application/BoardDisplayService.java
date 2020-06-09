package Application;

import Domain.Board;
import Domain.Square;

import java.util.List;

public class BoardDisplayService {

    public void displayBoard(Board board) {
        for (List<Square> rows : board.getSquares()) {
            System.out.println();
            for (Square square : rows) {
                System.out.print(" " + square.getValueOfSquare().getRepresentationOnTheBoard() + " ");
            }
        }
        System.out.println();
    }

    public String getSquarePositionRepresentationOnTheBoard(int column, int row) {
        return getColumnRepresentationOnTheBoard(column) + row;
    }

    public String getColumnRepresentationOnTheBoard(int column) {
        return createListOfColumnRepresentationOnTheBoard().get(column);
    }

    public List<String> createListOfColumnRepresentationOnTheBoard() {
        return List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    }
}
