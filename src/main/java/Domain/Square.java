package Domain;
import java.util.Objects;
public enum Square {

    EMPTY("X"),
    BOAT("B"),
    SUNK_BOAT("S");

    public String valueOfSquare;

    Square(String valueOfSquare) {
        this.valueOfSquare = valueOfSquare;
    }

    public void setValueOfSquare(String valueOfSquare) {
        this.valueOfSquare = valueOfSquare;
    }

    public String getValueOfSquare() {
        return valueOfSquare;
    }
}

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Square aSquare = (Square) o;
//        return Objects.equals(valueOfSquare, aSquare.valueOfSquare);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(valueOfSquare);
//    }
//}


