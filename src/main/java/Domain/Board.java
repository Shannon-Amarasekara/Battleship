package Domain;
import java.security.PublicKey;
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
            squaresList.add(new Square(Square.ValueOfSquare.EMPTY));
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
            int randomColumn = 1 + (int) (Math.random() * 10);
            int randomRow = 1 + (int) (Math.random() * 10);
            randomColumn = randomColumn - 1;
            enemyBoard.placeABoatOnTheBoard(randomColumn, randomRow);
        }
        return enemyBoard;
    }

    public boolean rowExists(int row) {
        return ((row <= squares.size()) && row > 0);
    }

    public boolean columnExists(int column) {
        return ((column <= squares.size()) && column > 0);
    }

    public void placeABoatOnTheBoard(int column, int row) {
        Square squareBoat = squares.get(row - 1).get(column);
        squareBoat.setValueOfSquare(Square.ValueOfSquare.BOAT);
    }

    public boolean aBoatIsInThisPosition(int row, int column) {
        return getValueOfSquare(row, column).equals(Square.ValueOfSquare.BOAT);
    }

    public Square.ValueOfSquare getValueOfSquare(int row, int column) {
        return getSquarePosition(row, column).getValueOfSquare();
    }

    public Square getSquarePosition(int row, int column){
        return squares.get(row).get(column);
    }

    public List<Square> getRow(int row){
        return squares.get(row);
    }

    public boolean boardContainsNoBoats() {
        for (int i = 0; i < squares.size(); i++) {
            for (int j = 0; j < squares.get(i).size(); j++) {

                Square square = squares.get(i).get(j);
                String valueOfSquare = square.getValueOfSquare().getRepresentationOnTheBoard();
                String value = Square.ValueOfSquare.BOAT.getRepresentationOnTheBoard();

                if (valueOfSquare.equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setValueOfSquareToSunkBoat(int row, int column) {
        squares.get(row).get(column).setValueOfSquare(Square.ValueOfSquare.SUNK_BOAT);
    }
}