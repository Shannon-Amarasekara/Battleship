import java.util.List;
import java.util.Scanner;

public class Plateau {

    private List<Case> cases;

    public Plateau(List<Case> cases) {
        this.cases = cases;
    }

    public void afficherPlateau() {
        for (int i = 0; i < cases.size(); i++) {
            Case aCase = cases.get(i);
            aCase.afficherCase();
        }
        System.out.println();
    }

    public static Plateau creerUnPlateau10Cases() {
        Case case1 = new Case("X");
        Case case2 = new Case("X");
        Case case3 = new Case("X");
        Case case4 = new Case("X");
        Case case5 = new Case("X");
        Case case6 = new Case("X");
        Case case7 = new Case("X");
        Case case8 = new Case("X");
        Case case9 = new Case("X");
        Case case10 = new Case("X");

        Plateau plateau = new Plateau(List.of(case1, case2, case3, case4, case5, case6, case7, case8, case9, case10));

        return plateau;
    }

    public void placerUnBateauSurLePlateau(int positionBateau) {
        Scanner scanner = new Scanner(System.in);
        while (positionBateau < 1 || positionBateau > 10) {
            System.out.println("Choisissez une position entre 1 et 10.");
            positionBateau = scanner.nextInt();
        }
        positionBateau = positionBateau - 1;
        Case caseBateau = cases.get(positionBateau);
        caseBateau.setValeur("B");
    }
}