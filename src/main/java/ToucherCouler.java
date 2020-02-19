import com.sun.jdi.PrimitiveValue;

import java.util.Scanner;

public class ToucherCouler {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        introductionAuJeu();

        Plateau plateauDuJoueur = creerUnPlateau();

        placerDesBateaux(scanner, plateauDuJoueur);

        plateauDuJoueur.afficherPlateau();

        Plateau plateauEnemi = creerUnPlateauEnnemi();
        System.out.println("Voici le plateau ennemi");
        plateauEnemi.afficherPlateau();


        //introduction au jeu
        //creer le plateau joueur
        //placer les bateaux joueurs - max 3
        //creer le plateau enemi
        //placer bateaux 1-3 au hasard dans position au hasard
        //joueur et enemi attaque le plateau de l'autre chacun leur tour
        //quand un bateau a ete touche, il coule
        //une fois que les 3 bateaux (joueur ou enemi) ont ete coule
        //END GAME
    }

    private static void introductionAuJeu() {
        System.out.println("Bienvenue au Toucher Couler.");
        System.out.println("Voice votre plateau.");
    }

    private static Plateau creerUnPlateau() {
        Plateau plateau = Plateau.creerUnPlateau10Cases();
        return plateau;
    }

    private static Plateau creerUnPlateauEnnemi() {
        Plateau plateauEnemi = creerUnPlateau();
        int randomCounterOfEnemiBoats = 1 + (int) (Math.random() * 3);

        for (int i = 0; i < randomCounterOfEnemiBoats; i++) {
            int randomBoatPosition = 1 + (int) (Math.random() * 10);
            plateauEnemi.placerUnBateauSurLePlateau(randomBoatPosition);
        }
        return plateauEnemi;
    }

    private static void placerDesBateaux(Scanner scanner, Plateau plateau) {
        System.out.println("Choisissez une position pour votre bateau entre cases 1 et 10.");
        int positionBateau = scanner.nextInt();

        plateau.placerUnBateauSurLePlateau(positionBateau);
        System.out.println("Vous avez place votre premier bateau.");

        placerBateauxSupplementaires(scanner, plateau);
    }

    private static void verifierSiLaPositionEstDisponible( int poisiton, Plateau plateau){

        Case caseBateau = plateau.getCases().get(poisiton);

        if (caseBateau.getValeur() == "B") {
            System.out.println("Cette position n'est pas disponible. Un bateau est deja place dans cette position.");
        }

        else caseBateau.setValeur("B");
    }

    private static void placerBateauxSupplementaires(Scanner scanner, Plateau plateau) {
        int compteDeBateaux = 1;

        while ((compteDeBateaux) < 3) {

            System.out.println("Voulez vous placer un autre bateau?");
            String reponsePlacerUnAutreBateau = scanner.next();

            if (reponsePlacerUnAutreBateau.equals("non")) {
                compteDeBateaux = 3;

            } else if (reponsePlacerUnAutreBateau.equals("oui")) {

                System.out.println("Choisissez une position entre 1 et 10.");
                int positionBateau = scanner.nextInt();
                plateau.placerUnBateauSurLePlateau(positionBateau);
                compteDeBateaux++;
            }
        }

        System.out.println("Vous avez place tout vos bateaux.");
    }
}

