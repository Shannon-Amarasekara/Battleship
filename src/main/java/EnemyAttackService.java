import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Random;

public class EnemyAttackService {

    BoatDetectionService boatDetectionService = new BoatDetectionService();
    BoardDisplayService boardDisplayService = new BoardDisplayService();

    public void enemyAttacksThePlayerBoard(Board playerBoard, ArrayList<Integer> listOfPositions) {
        int positionToAttack = calculateRandomPositionToAttack(listOfPositions);
        listOfPositions.remove(listOfPositions.indexOf(positionToAttack));
        int row = positionToAttack / 10;
        int column = positionToAttack % 10;

        List<String> listOfColumnsAtoJ = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        String columnAttacked = listOfColumnsAtoJ.get(column);

        if (boatDetectionService.aBoatIsInThisPosition(playerBoard, row, column)) {
            boatDetectionService.setValueOfSquareToSunkBoat(playerBoard, row, column);
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("One of your boats was sunk!");

        } else {
            System.out.println("The enemy attacked position " + columnAttacked + (row + 1) + " .");
            System.out.println("No boat was hit!");
        }
        boardDisplayService.displayBoard(playerBoard);
        System.out.println("Here is your board.");
    }

    public int calculateRandomPositionToAttack(List<Integer> listOfPositions) {
        Random random = new Random();
        return listOfPositions.get(random.nextInt(listOfPositions.size()));
    }
}
