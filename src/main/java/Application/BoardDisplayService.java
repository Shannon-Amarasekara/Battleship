package Application;

import Domain.Board;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BoardDisplayService {

    private List<Character> columns = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');

    public void displayPlayerBoard(Board board) {
        System.out.println("\n\n           PLAYER BOARD ");
        displayBoard(board);
    }

    public void displayEnemyBoard(Board board) {
        System.out.println("\n\n           ENEMY BOARD ");
        displayBoard(board);
    }

    public void displayBoard(Board board) {
        String columnsForBoardDisplay = columns.toString()
                .replace(',', ' ')
                .replace('[', ' ')
                .replace(']', ' ');

        System.out.print("\n  " + columnsForBoardDisplay);
        for (int j = 0; j < board.getSquares().size(); j++) {
            System.out.println();
            for (int i = 0; i < board.getSquares().get(j).size(); i++) {
                if (i == 0) {
                    if (j == 9) {
                        System.out.print(j + 1);
                    } else {
                        System.out.print((j + 1) + " ");
                    }
                }
                System.out.print(" " + board.findTheSquareOnTheBoard(j, i).getValueOfSquare().getRepresentationOnTheBoard() + " ");
            }
        }
        System.out.println();
    }

    public int convertRowToBoardRepresentation(int row) {
        return row + 1;
    }

    public Character convertColumnToBoardRepresentation(int column) {
        List<Character> columns = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        return columns.get(column);
    }


    public List<List<String>> createListOfPreviousPlayerAttacksForBoardDisplay(List<String> pastPlayerAttacks) {
        List<List<String>> positionNamesAttackedByPlayer = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            positionNamesAttackedByPlayer.add(new CopyOnWriteArrayList<>());
        }

        List<String> positions = new CopyOnWriteArrayList<>(pastPlayerAttacks);
        for (String position : positions) {
            char column = position.charAt(0);
            int keyOfColumn = columns.indexOf(column);
            positionNamesAttackedByPlayer.get(keyOfColumn).add(position);
        }
        return positionNamesAttackedByPlayer;
    }

    public void displayPositionsAttackedByPlayer(List<String> pastPlayerAttacks) {
        if (pastPlayerAttacks.isEmpty()) {
            return;
        }
        List<List<String>> previousAttacks = createListOfPreviousPlayerAttacksForBoardDisplay(pastPlayerAttacks);
        System.out.println("\nYou previously attacked the following positions: ");
        for (List<String> columnList : previousAttacks) {
            if (!columnList.isEmpty()) {
                Collections.sort(columnList);
                for (String position : columnList) {
                    if (position.endsWith("0")) {
                        columnList.remove(position);
                        columnList.add(columnList.size(), position);
                    }
                }
                System.out.println(columnList);
            }
        }
    }
}
