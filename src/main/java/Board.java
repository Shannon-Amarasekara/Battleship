import java.util.ArrayList;
import java.util.List;

class Board {
    private List<List<Square>> squares;

    private Board(List<List<Square>> squares) {
        this.squares = squares;
    }

    List<List<Square>> getSquares() {
        return squares;
    }

    private static List<Square> createListOfTenSquares() {
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

    boolean rowExists(int row) {
        return ((row <= squares.size()) && row > 0);
    }

    boolean columnExists(int column) {
        return ((column <= squares.size()) && column > 0);
    }

    public void placeABoatOnTheBoard(int column, int row) {
        if((rowExists(row)) && (columnExists(column))){
            Square squareBoat = squares.get(row - 1).get(column);
            squareBoat.setValueOfSquare(Square.ValueOfSquare.BOAT);
        }
    }
}