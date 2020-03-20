import java.util.List;

class Board {

    private List<Square> squares;

    private Board(List<Square> squares) {
        this.squares = squares;
    }

    List<Square> getSquares() {
        return squares;
    }

    private void displayBoard() {
        for (int i = 0; i < squares.size(); i++) {
            Square aSquare = squares.get(i);
            aSquare.displayRepresentationOnTheBoard();
        }
        System.out.println();
    }

    void displayEnemyBoard(Board board){
        System.out.println("Here is the enemy board.");
        board.displayBoard();
    }

    void displayPlayerBoard(Board board){
        System.out.println("Here is your board.");
        board.displayBoard();
    }

    static Board createABoardOf10Squares() {
        Square square1 = new Square(Square.ValueOfSquare.EMPTY);
        Square square2 = new Square(Square.ValueOfSquare.EMPTY);
        Square square3 = new Square(Square.ValueOfSquare.EMPTY);
        Square square4 = new Square(Square.ValueOfSquare.EMPTY);
        Square square5 = new Square(Square.ValueOfSquare.EMPTY);
        Square square6 = new Square(Square.ValueOfSquare.EMPTY);
        Square square7 = new Square(Square.ValueOfSquare.EMPTY);
        Square square8 = new Square(Square.ValueOfSquare.EMPTY);
        Square square9 = new Square(Square.ValueOfSquare.EMPTY);
        Square square10 = new Square(Square.ValueOfSquare.EMPTY);

        Board board = new Board(List.of(square1, square2, square3, square4, square5, square6, square7, square8, square9, square10));

        return board;
    }

    void placeABoatOnTheBoard(int boatPosition) {

        boatPosition = boatPosition - 1;
        Square squareBateau = squares.get(boatPosition);

        squareBateau.setValueOfSquare(Square.ValueOfSquare.BOAT);
    }
}