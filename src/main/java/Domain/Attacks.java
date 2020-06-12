package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Attacks {

    public int calculateRandomPositionToAttack(HashMap<Integer, Square> listOfPositions) {
        Random random = new Random();
        List<Integer> keys = new ArrayList<>(listOfPositions.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    public HashMap<Integer, Square> createListOfBoardPositions() {
        HashMap<Integer, Square> listOf99Squares = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Square square = new Square(ValueOfSquare.EMPTY);
            listOf99Squares.put(i, square);
        }
        return listOf99Squares;
    }

    public int enemyGetsRowForAttack(int position) {
        return position / 10;
    }

    public int enemyGetsColumnToAttack(int position) {
        return position % 10;
    }

    public boolean attackAnEnemyPosition(Board board, int row, int column) {
        Square squareToAttack = board.getSquarePosition(row - 1, column);

        if (board.enemyBoatIsInThisPosition(squareToAttack)) {
            board.sinkEnemyBoat(squareToAttack);
            return true;
        }
        return false;
    }

    public boolean enemyAttacksPlayerPosition(Board board, int row, int column) {
        Square square = board.getSquarePosition(row, column);

        if (board.playerBoatIsInThisPosition(square)) {
            board.sinkPlayerBoat(square);
            return true;
        }
        return false;
    }
}
