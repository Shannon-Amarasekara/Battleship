package Application;
import Domain.Board;
import Domain.Square;
import java.util.List;

public class BoardDisplayService {

    public void displayBoard(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            System.out.println();
            for (int j = 0; j < board.getRow(i).size(); j++) {
                Square square = board.getSquares().get(i).get(j);
                System.out.print(" " + square.getValueOfSquare() + " ");
            }
        }
        System.out.println();
    }

    public String getColumnRepresentationOnTheBoard(int column) {
        return createListOfColumnRepresentationOnTheBoard().get(column);
    }

    public List<String> createListOfColumnRepresentationOnTheBoard() {
        return List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    }

    public int getRowRepresentationOnTheBoard(int row) {
        return row - 1;
    }

    public boolean playerBoatIsInThisPosition(int row, int column, Board board) {
        return getValueOfSquare(row, column, board).equals(Square.BOAT.valueOfSquare);
    }

    public boolean enemyBoatIsInThisPosition(int row, int column, Board board){
        return getValueOfSquare(getRowRepresentationOnTheBoard(row), column, board).equals(Square.BOAT.valueOfSquare);
    }


    public String getValueOfSquare(int row, int column, Board board) {
        return board.getSquarePosition(row, column).getValueOfSquare();
    }

    public void sinkEnemyBoat(int row, int column, Board board) {
        board.getSquares().get(getRowRepresentationOnTheBoard(row)).get(column).setValueOfSquare(Square.SUNK_BOAT.valueOfSquare);
    }

    public void sinkPlayerBoat(int row, int column, Board board){
        board.getSquares().get(row).get(column).setValueOfSquare(Square.SUNK_BOAT.valueOfSquare);

    }

}
