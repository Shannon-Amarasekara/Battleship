package Application;

import java.util.Arrays;
import java.util.List;

public class InputValidationService {

    public boolean isValidPlayerPositionInput(String position) {
        List<Character> characterList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');

        if (position.length() < 2 || position.length() > 3) {
            return false;
        }

        if (characterList.contains(position.charAt(0))) {
            if (position.length() == 2) {
                if (Character.isDigit(position.charAt(1))) {
                    int row = Character.getNumericValue(position.charAt(1));
                    return row > 0 && row < 9;
                }
            } else {
                if (Character.isDigit(position.charAt(1)) && Character.isDigit(position.charAt(2))) {
                    int row = Integer.parseInt(position.substring(1, 3)) - 1;
                    return row > 0 && row < 10;
                }
            }
        }
        return false;
    }

    public int getColumnFromPlayerInput(String position) {
        List<Character> characterList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
        return characterList.indexOf(position.charAt(0));

    }

    public int getRowFromPlayerInput(String position) {
        if (position.length() == 2) {
            return Character.getNumericValue(position.charAt(1)) - 1;
        } else {
            return Integer.parseInt(position.substring(1, 3)) - 1;
        }
    }
}
