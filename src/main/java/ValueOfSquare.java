public enum ValueOfSquare {

    EMPTY("X"),
    BOAT("B"),
    SUNK_BOAT("C");

    private String representationOnTheBoard;

    ValueOfSquare(String representationSurLePlateau){
        this.representationOnTheBoard = representationSurLePlateau;
    }

    public String getRepresentationSurLePlateau() {
        return representationOnTheBoard;
    }
}