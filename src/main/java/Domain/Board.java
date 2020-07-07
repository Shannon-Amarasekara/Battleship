package Domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Square>> squares;

    public Board(List<List<Square>> squares) {
        this.squares = squares;
    }

    public List<List<Square>> getSquares() {
        return squares;
    }

    public static List<Square> createListOfTenSquares() {
        List<Square> squaresList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Square square = new Square(ValueOfSquare.EMPTY);
            squaresList.add(square);
            square.setValueOfSquare(ValueOfSquare.EMPTY);
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

    public int boatCountOnABoard() {
        int boatCount = 0;
        for (List<Square> rows : squares) {
            for (Square square : rows) {
                if (square.getValueOfSquare().equals(ValueOfSquare.BOAT)) {
                    boatCount++;
                }
            }
        }
        return boatCount;
    }

    public void placeOneToThreeBoats(List<Square> boatPositions) {
        for (Square square : boatPositions) {
            square.placeBoat();
        }
    }

    public static Board createBoardWithRandomlyPlacedBoats(Board board) {
        Board enemyBoard = createABoard();
        int boatCount = board.boatCountOnABoard();

        for (int i = 0; i < boatCount; i++) {
            int randomColumn = (int) (10 * Math.random());
            int randomRow = (int) (10 * Math.random());
            Square square = enemyBoard.getSquares().get(randomRow).get(randomColumn);
            square.placeBoat();
        }
        return enemyBoard;
    }

    public Square findTheSquareOnTheBoard(int row, int column) {
        return squares.get(row).get(column);
    }
}
