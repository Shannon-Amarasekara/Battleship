import java.util.Scanner;

public class ToucherCouler {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        introductionAuJeu();
        Plateau plateau = creationDuPlateauJoueur();

        placerLesBateauxDuJoueur(scanner, plateau);

        //creer le plateau du joueur
        //placer des bateaux - 3 maximum
        //creer un plateau enemi
        //placer des bateaux - 3 maximum - au hasard
        //joueur et enemi attaque le plateau de l'autre chacun leur tour
        //quand un bateau a ete touche, il coule
        //une fois que les 3 bateaux (joueur ou enemi) ont ete coule
        //END GAME
    }

    private static void introductionAuJeu(){
        System.out.println("Bienvenue au Toucher Couler.");
        System.out.println("Voice votre plateau.");
    }

    private static Plateau creationDuPlateauJoueur() {
        Plateau plateau = Plateau.creerUnPlateau10Cases();
        plateau.afficherPlateau();
        return plateau;
    }

    private static void placerLesBateauxDuJoueur(Scanner scanner, Plateau plateau) {
        System.out.println("Choisizzez une position pour votre bateau entre 1 et 10.");
        int positionBateauUn = scanner.nextInt();
        plateau.placerUnBateauSurLePlateau(positionBateauUn);
        plateau.afficherPlateau();
        int compteDeBateaux = 1;
        System.out.println(" Voulez vous placez un autre bateau?");
        String reponsePlacerUnAutreBateau = scanner.next();

        while (reponsePlacerUnAutreBateau.equals("oui")) {
            System.out.println("Choisizzez une position pour votre bateau entre 1 et 10.");
            int positionBateau = scanner.nextInt();
            plateau.placerUnBateauSurLePlateau(positionBateau);
            plateau.afficherPlateau();
            compteDeBateaux++;

            if (compteDeBateaux >= 3) {
                System.out.println("Vous avez place tout vos bateaux");
                break;
            } else {
                System.out.println("Voulez vous place un autre bateau?" );
                reponsePlacerUnAutreBateau = scanner.next();
            }
        }
    }

}
