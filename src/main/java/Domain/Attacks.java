package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Attacks {

    public boolean enemyAttacks(Board playerBoard, HashMap<Integer, Square> listOfPositions) {
        int positionToAttack = calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(positionToAttack);
        int row = enemyGetsRowForAttack(positionToAttack);
        int column = enemyGetsColumnToAttack(positionToAttack);

        Square square = playerBoard.getSquarePosition(row, column);

        if (enemyAttacksPlayerPosition(playerBoard, row, column)) {
            square.sinkPlayerBoat();
            return true;

        }
        return false;
    }


    public int calculateRandomPositionToAttack(HashMap<Integer, Square> listOfPositions) {
        Random random = new Random();
        List<Integer> keys = new ArrayList<>(listOfPositions.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    public HashMap<Integer, Square> createListOfBoardPositions() {
        HashMap<Integer, Square> listOf99Squares = new HashMap<>();
        for (int i = 0; i < 100; i++) {
//            Square square = new Square(ValueOfSquare.EMPTY);
//            listOf99Squares.put(i, square);
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

        if (squareToAttack.enemyBoatIsInThisPosition()) {
            squareToAttack.sinkEnemyBoat();
            return true;
        }
        return false;
    }

    public boolean enemyAttacksPlayerPosition(Board board, int row, int column) {
        Square square = board.getSquarePosition(row, column);

        if (square.getValueOfSquare().equals(ValueOfSquare.BOAT)) {
            square.sinkPlayerBoat();
            return true;
        }
        return false;
    }
}
