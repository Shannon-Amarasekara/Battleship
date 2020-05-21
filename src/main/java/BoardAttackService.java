import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardAttackService {

    PlayerAttackService playerAttackService = new PlayerAttackService();
    EnemyAttackService enemyAttackService = new EnemyAttackService();
    BoatDetectionService boatDetectionService = new BoatDetectionService();

    public void playerAndEnemyAttackEachOther(Board playerBoard, Board enemyBoard) {
        ArrayList<Integer> positionsAlreadyAttackedByEnemy = createListOfBoardPositions();
        List<String> positionsAlreadyAttackedByPlayer = new CopyOnWriteArrayList<>();

        while (true) {
            playerAttackService.attackTheEnemyBoard(enemyBoard, positionsAlreadyAttackedByPlayer);
            if (boatDetectionService.boardContainsNoBoats(enemyBoard)) {
                playerWins();
            }

            enemyAttackService.enemyAttacksThePlayerBoard(playerBoard, positionsAlreadyAttackedByEnemy);
            if (boatDetectionService.boardContainsNoBoats(playerBoard)) {
                playerLoses();
            }
        }
    }

    public ArrayList<Integer> createListOfBoardPositions() {
        ArrayList<Integer> listOf99Positions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOf99Positions.add(i);
        }
        return listOf99Positions;
    }


    public void playerWins() {
        System.out.println("YOU WIN !");
        System.exit(0);
    }

    public void playerLoses() {
        System.out.println("YOU LOSE");
        System.exit(0);
    }
}
