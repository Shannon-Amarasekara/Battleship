package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Attacks {

    public List<Integer> createListOfAllPositions() {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            positions.add(i);
        }
        return positions;
    }

    public boolean positionAlreadyAttacked(List<String> pastPlayerAttacks, String position) {
        return pastPlayerAttacks.contains(position);
    }

    public boolean playerSinksEnemyBoat(List<String> pastPlayerAttacks, String position, Board board, int row, int column) {
        pastPlayerAttacks.add(position);
        return board.findTheSquareOnTheBoard(row, column).boatDetectedAndSunk();
    }

    public boolean enemySinksBoat(int row, int column, Board board) {
        return board.findTheSquareOnTheBoard(row, column).boatDetectedAndSunk();
    }

    public String calculateRandomPosition(List<Integer> allPositionsAvailable) {
        int randomPosition = allPositionsAvailable.get(new Random().nextInt(allPositionsAvailable.size()));
        allPositionsAvailable.removeAll(Collections.singletonList(randomPosition));
        return String.valueOf(randomPosition);
    }

    public int calculateColumnFromRandomPosition(String randomPosition) {
        return Integer.parseInt(String.valueOf(String.valueOf(randomPosition).charAt(0)));
    }

    public int calculateRowFromRandomPosition(String randomPosition) {
        int row = 0;
        if (String.valueOf(randomPosition).length() > 1) {
            row = Integer.parseInt(String.valueOf(String.valueOf(randomPosition).charAt(1)));
        }
        return row;
    }

    public void exitGame() {
        System.out.println("GAME OVER");
        System.exit(0);
    }
}
