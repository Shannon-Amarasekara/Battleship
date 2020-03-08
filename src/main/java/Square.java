import java.util.Objects;

public class Square {
    private ValueOfSquare valueOfSquare;

    public Square(ValueOfSquare valueOfSquare) {
        this.valueOfSquare = valueOfSquare;
    }

    public void displayRepresentationOnTheBoard() {
        System.out.print(this.valueOfSquare.getRepresentationOnTheBoard());
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
        Square aCase = (Square) o;
        return Objects.equals(valueOfSquare, aCase.valueOfSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueOfSquare);
    }
}





