package Domain;

import java.util.Objects;

public class Square {

    private ValueOfSquare valueOfSquare;

    public Square(ValueOfSquare valueOfSquare) {
        this.valueOfSquare = valueOfSquare;
    }

    public void setValueOfSquare(ValueOfSquare valueOfSquare) {
        this.valueOfSquare = valueOfSquare;
    }

    public ValueOfSquare getValueOfSquare() {
        return valueOfSquare;
    }


    public void placeBoat() {
        setValueOfSquare(ValueOfSquare.BOAT);
    }

    public boolean hasBoat() {
        return getValueOfSquare().equals(ValueOfSquare.BOAT);
    }

    public void sinkBoat() {
        setValueOfSquare(ValueOfSquare.SUNK_BOAT);
    }

    public boolean boatDetectedAndSunk() {
        if (hasBoat()) {
            sinkBoat();
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Square square = (Square) obj;
        return Objects.equals(valueOfSquare, square.valueOfSquare)
                && Objects.equals(valueOfSquare, square.valueOfSquare);
    }
}
