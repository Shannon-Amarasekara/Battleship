import javax.print.DocFlavor;
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
        Square a = new Square(Square.ValueOfSquare.EMPTY);
        Square b = new Square(Square.ValueOfSquare.EMPTY);
        Square c = new Square(Square.ValueOfSquare.EMPTY);
        Square d = new Square(Square.ValueOfSquare.EMPTY);
        Square e = new Square(Square.ValueOfSquare.EMPTY);
        Square f = new Square(Square.ValueOfSquare.EMPTY);
        Square g = new Square(Square.ValueOfSquare.EMPTY);
        Square h = new Square(Square.ValueOfSquare.EMPTY);
        Square i = new Square(Square.ValueOfSquare.EMPTY);
        Square j = new Square(Square.ValueOfSquare.EMPTY);

        return List.of(a, b, c, d, e, f, g, h, i, j);
    }

    static Board createABoard() {
        List<Square> listOne = createListOfTenSquares();
        List<Square> listTwo = createListOfTenSquares();
        List<Square> listThree = createListOfTenSquares();
        List<Square> listFour = createListOfTenSquares();
        List<Square> listFive = createListOfTenSquares();
        List<Square> listSix = createListOfTenSquares();
        List<Square> listSeven = createListOfTenSquares();
        List<Square> listEight = createListOfTenSquares();
        List<Square> listNine = createListOfTenSquares();
        List<Square> listTen = createListOfTenSquares();

        List<List<Square>> squares = List.of(listOne, listTwo, listThree, listFour, listFive, listSix, listSeven, listEight, listNine, listTen);
        return new Board(squares);
    }

    void placeABoatOnTheBoard(String column, int row) {
        List;

        Square squareBateau = squares.get
        squareBateau.setValueOfSquare(Square.ValueOfSquare.BOAT);

    }
}