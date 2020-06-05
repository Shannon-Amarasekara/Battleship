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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square aSquare = (Square) o;
        return Objects.equals(valueOfSquare, aSquare.valueOfSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueOfSquare);
    }
}


