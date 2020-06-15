package Domain;

import java.util.List;
import java.util.Objects;

public class Square {

    private int row;
    private int column;
    private ValueOfSquare valueOfSquare;

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
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

    public boolean playerBoatIsInThisPosition() {
        return getValueOfSquare().equals(ValueOfSquare.BOAT);
    }

    public boolean enemyBoatIsInThisPosition() {
        return getValueOfSquare().equals(ValueOfSquare.BOAT);
    }

    public void sinkEnemyBoat() {
        setValueOfSquare(ValueOfSquare.SUNK_BOAT);
    }

    public void sinkPlayerBoat() {
        setValueOfSquare(ValueOfSquare.SUNK_BOAT);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getColumnRepresentationOnTheBoard(){
        List<String> list = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        return list.get(getColumn());
    }

    public String getPositionRepresentationOnTheBoard() {
        String position = getColumnRepresentationOnTheBoard() + getRow();
        System.out.println("position is " + position);
        return position;
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


