import javax.print.DocFlavor;
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

    private void displayBoard() {
        for (int i = 0; i < squares.size(); i++) {
            System.out.println();
            for (int j = 0; j < squares.get(i).size(); j++) {
                Square square = squares.get(i).get(j);
                square.displayRepresentationOnTheBoard();
            }
        }
        System.out.println();
    }

    void displayEnemyBoard(Board board) {
        System.out.println("Here is the enemy board.");
        board.displayBoard();
    }

    void displayPlayerBoard(Board board) {
        System.out.println("Here is your board.");
        board.displayBoard();
    }

    private static List<Square> createListOfTenSquares() {
        List<Square> squaresList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squaresList.add(new Square(Square.ValueOfSquare.EMPTY));
        }
        return squaresList;
    }

    static Board createABoard() {
        List<List<Square>> squares = new ArrayList<>();
        for(int i = 0; i < 10 ; i++){
            squares.add(createListOfTenSquares());
        }
        return new Board(squares);
    }

    static Board createAnEnemyBoard() {
        Board enemyBoard = createABoard();
        int randomCounterOfEnemyBoats = 1 + (int) (Math.random() * 3);

        for (int i = 0; i < randomCounterOfEnemyBoats; i++) {
            int randomColumn = 1 + (int) (Math.random() * 10);
            int randomRow = 1 + (int) (Math.random() * 10);

            randomColumn = randomColumn - 1;
            enemyBoard.placeABoatOnTheBoard(randomColumn, randomRow);
        }
        return enemyBoard;
    }

    void placeABoatOnTheBoard(int column, int row) {
        Square squareBoat = squares.get(row -1).get(column);
        squareBoat.setValueOfSquare(Square.ValueOfSquare.BOAT);
    }
}