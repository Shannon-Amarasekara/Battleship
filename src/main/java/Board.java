import java.util.List;
import java.util.Scanner;

public class Board {

    private List<Case> cases;

    public Board(List<Case> cases) {
        this.cases = cases;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void afficherPlateau() {

        for (int i = 0; i < cases.size(); i++) {
            Case aCase = cases.get(i);
            aCase.afficherRepresentationSurLePlateau();
        }
        System.out.println();
    }

    public static Board creerUnPlateau10Cases() {
        Case case1 = new Case(ValeurDeCase.VIDE);
        Case case2 = new Case(ValeurDeCase.VIDE);
        Case case3 = new Case(ValeurDeCase.VIDE);
        Case case4 = new Case(ValeurDeCase.VIDE);
        Case case5 = new Case(ValeurDeCase.VIDE);
        Case case6 = new Case(ValeurDeCase.VIDE);
        Case case7 = new Case(ValeurDeCase.VIDE);
        Case case8 = new Case(ValeurDeCase.VIDE);
        Case case9 = new Case(ValeurDeCase.VIDE);
        Case case10 = new Case(ValeurDeCase.VIDE);

        Board plateau = new Board(List.of(case1, case2, case3, case4, case5, case6, case7, case8, case9, case10));

        return plateau;
    }

    public static Board creerUnPlateau10Cases2() {
        Case case1 = new Case(ValeurDeCase.VIDE);

        Board plateau = new Board(List.of(case1, case1, case1, case1, case1, case1, case1, case1, case1, case1));

        return plateau;
    }

    public void placerUnBateauSurLePlateau(int positionBateau) {

        positionBateau = positionBateau - 1;
        Case caseBateau = cases.get(positionBateau);

        caseBateau.setValeurDeCase(ValeurDeCase.BATEAU);
    }
}
