import java.util.List;

public class Board {

    private List<Square> squares;

    public Board(List<Square> squares) {
        this.squares = squares;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void displayBoard() {

        for (int i = 0; i < squares.size(); i++) {
            Square aSquare = squares.get(i);
            aSquare.displayRepresentationOnTheBoard();
        }
        System.out.println();
    }

    public static Board createABoardOf10Squares() {
        Square square1 = new Square(ValueOfSquare.EMPTY);
        Square square2 = new Square(ValueOfSquare.EMPTY);
        Square square3 = new Square(ValueOfSquare.EMPTY);
        Square square4 = new Square(ValueOfSquare.EMPTY);
        Square square5 = new Square(ValueOfSquare.EMPTY);
        Square square6 = new Square(ValueOfSquare.EMPTY);
        Square square7 = new Square(ValueOfSquare.EMPTY);
        Square square8 = new Square(ValueOfSquare.EMPTY);
        Square square9 = new Square(ValueOfSquare.EMPTY);
        Square square10 = new Square(ValueOfSquare.EMPTY);

        Board board = new Board(List.of(square1, square2, square3, square4, square5, square6, square7, square8, square9, square10));

        return board;
    }

    public void placeABoatOnTheBoard(int boatPosition) {

        boatPosition = boatPosition - 1;
        Square squareBateau = squares.get(boatPosition);

        squareBateau.setValueOfSquare(ValueOfSquare.BOAT);
    }
}