import javax.print.DocFlavor;

public class BoatDetectionService {

    public boolean aBoatIsInThisPosition(Board board, int row, int column) {
        return getValueOfSquare(board, row, column).equals(Square.ValueOfSquare.BOAT);
    }

    public Square.ValueOfSquare getValueOfSquare(Board board, int row, int column) {
        return board.getSquares().get(row).get(column).getValueOfSquare();
    }

    public boolean boardContainsNoBoats(Board board) {
        for (int i = 0; i < board.getSquares().size(); i++) {
            for (int j = 0; j < board.getSquares().get(i).size(); j++) {

                Square square = board.getSquares().get(i).get(j);
                String valueOfSquare = square.getValueOfSquare().getRepresentationOnTheBoard();
                String value = Square.ValueOfSquare.BOAT.getRepresentationOnTheBoard();

                if (valueOfSquare.equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setValueOfSquareToSunkBoat(Board board, int row, int column) {
        board.getSquares().get(row - 1).get(column).setValueOfSquare(Square.ValueOfSquare.SUNK_BOAT);
    }

}


