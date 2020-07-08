package Application;

import Domain.*;

public class BattleshipGameController {

    private BoatPlacementService boatPlacementService = new BoatPlacementService();
    private BoardDisplayService boardDisplayService = new BoardDisplayService();
    private BoardAttackService boardAttackService = new BoardAttackService();
    private GameIntroductionService gameIntroductionService = new GameIntroductionService();

    public void playBattleship() {

        gameIntroductionService.introductionToTheGame();
        gameIntroductionService.askToPlay();

        Board playerBoard = Board.createABoard();

        boatPlacementService.playerPlacesOneToThreeBoats(playerBoard);

        boardDisplayService.displayPlayerBoard(playerBoard);

        Board enemyBoard = Board.createBoardWithRandomlyPlacedBoats(playerBoard);
        boardDisplayService.displayEnemyBoard(enemyBoard);

        boardAttackService.attackUntilGameEnds(playerBoard, enemyBoard);

    }
}
