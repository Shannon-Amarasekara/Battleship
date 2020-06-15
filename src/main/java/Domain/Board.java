package Domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Square>> squares;

    private Board(List<List<Square>> squares) {
        this.squares = squares;
    }

    public List<List<Square>> getSquares() {
        return squares;
    }

    public static List<Square> createListOfTenSquares() {
        List<Square> squaresList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squaresList.add(new Square(i, i));
            squaresList.get(i).setValueOfSquare(ValueOfSquare.EMPTY);
        }
        return squaresList;
    }

    public static Board createABoard() {
        List<List<Square>> squares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squares.add(createListOfTenSquares());
        }
        return new Board(squares);
    }

    public static Board createBoardWithRandomlyPlacedBoats(int playerBoatsPlacedCount) {
        Board enemyBoard = createABoard();
        for (int i = 0; i < playerBoatsPlacedCount; i++) {
            int randomColumn = (int) (10 * Math.random());
            int randomRow = (int) (10 * Math.random());
            Square square = enemyBoard.getSquarePosition(randomRow, randomColumn);
            square.placeBoat();
        }
        return enemyBoard;
    }

    public boolean rowExists(int row) {
        return ((row <= squares.size()) && row > 0);
    }

    public boolean columnExists(int column) {
        return ((column <= squares.size()) && column > 0);
    }

    public Square getSquarePosition(int row, int column) {
        return squares.get(row).get(column);
    }

    public boolean boardContainsNoBoats() {
        for (List<Square> rows : squares) {
            for (Square square : rows) {
                if (square.getValueOfSquare().equals(ValueOfSquare.BOAT)) {
                    return false;
                }
            }
        }
        return true;
    }
}