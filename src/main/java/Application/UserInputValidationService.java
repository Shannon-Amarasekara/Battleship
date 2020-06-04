package Application;
import Domain.Board;
import java.util.List;
import java.util.Scanner;

public class UserInputValidationService {

    Scanner scanner = new Scanner(System.in);

    public int validatePlayerColumnInput(Board board, List<String> columnsAtoJ) {
        int column;
        if (scanner.hasNextInt()) {
            System.out.println("You need to enter a character, not a number. (A -J");
            scanner.next();
        } else {
            String columnInput = scanner.next();
            if (columnsAtoJ.contains(columnInput)) {
                column = columnsAtoJ.indexOf(columnInput);
                if (board.columnExists(column + 1)) {
                    return column;
                }
            } else {
                System.out.println("This column is unavailable.");
            }
        }
        return -1;
    }

    public int validatePlayerRowInput(Board board) {
        int row;
        if (!scanner.hasNextInt()) {
            System.out.println("You need to enter a number. (1-10)");
            scanner.next();
        } else {
            row = scanner.nextInt();
            if (board.rowExists(row)) {
                return row;
            } else {
                System.out.println("This row is unavailable.");
            }
        }
        return -1;
    }
}
