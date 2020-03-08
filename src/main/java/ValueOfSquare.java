public enum ValueOfSquare {

    EMPTY("X"),
    BOAT("B"),
    SUNK_BOAT("C");

    private String representationOnTheBoard;

     ValueOfSquare(String representationOnTheBoard){
        this.representationOnTheBoard = representationOnTheBoard;
    }

    public String getRepresentationOnTheBoard() {
        return representationOnTheBoard;
    }
}
