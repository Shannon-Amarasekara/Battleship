import java.util.Scanner;

public class ToucherCouler {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        introductionAuJeu();

        Plateau plateauDuJoueur = creerUnPlateau();

        placerUnBateauObligatoirement(scanner, plateauDuJoueur);

        plateauDuJoueur.afficherPlateau();

        Plateau plateauEnemi = creerUnPlateauEnnemi();
        System.out.println("Voici le plateau ennemi");
        plateauEnemi.afficherPlateau();

        attaquerLePlateauEnemi(scanner, plateauEnemi);
        enemiAttaquePlateauDuJoueur(plateauDuJoueur);


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

    private static boolean verifierLaPosition(Plateau plateau, int positionBateau) {
        if (positionBateau >= 1 && positionBateau <= 10) {
            Case caseBateau = plateau.getCases().get(positionBateau - 1);
            if (caseBateau.getValeur() != "B") {
                return true;
            }
        }
        return false;
    }


    private static void placerUnBateauObligatoirement(Scanner scanner, Plateau plateau) {
        System.out.println("Choisissez une position pour votre bateau entre cases 1 et 10.");
        int positionBateau = scanner.nextInt();

        while (!verifierLaPosition(plateau, positionBateau)) {
            System.out.println("Choisissez une autre position pour votre bateau entre cases 1 et 10.");
            positionBateau = scanner.nextInt();
        }

        plateau.placerUnBateauSurLePlateau(positionBateau);
        System.out.println("Vous avez place votre premier bateau.");
        placerBateauxSupplementaires(scanner, plateau);

    }


    private static void placerBateauxSupplementaires(Scanner scanner, Plateau plateau) {
        int compteDeBateaux = 1;

        while (compteDeBateaux < 3) {
            System.out.println("Voulez vous placer un autre bateau?");
            String reponse = scanner.next();

            if (reponse.equals("oui")) {
                System.out.println("Choisissez une position entre 1 et 10");
                int positionBateau = scanner.nextInt();

                while (!verifierLaPosition(plateau, positionBateau)) {
                    System.out.println("Position non disponible. Choisissez une autre position entre 1 et 10");
                    positionBateau = scanner.nextInt();
                }
                if (verifierLaPosition(plateau, positionBateau)) {
                    plateau.placerUnBateauSurLePlateau(positionBateau);
                    System.out.println("Vous avez place ce bateau dans la position " + positionBateau);
                    compteDeBateaux = compteDeBateaux + 1;
                }

            } else {
                compteDeBateaux = 3;
            }

            if (compteDeBateaux == 3) {
                System.out.println("Vous avez place tous vos bateaux");
            }
        }
    }

    public static void attaquerLePlateauEnemi(Scanner scanner, Plateau plateauEnemi) {
        System.out.println("Quelle position entre 1 et 10 voulez vous attaquer?");
        int positionAttaque = scanner.nextInt();

        while (positionAttaque < 1 || positionAttaque > 10) {
            System.out.println("Cette position n'est pas disponible.");
            System.out.println("Attaquer une position entre 1 et 10");
            positionAttaque = scanner.nextInt();
        }

        positionAttaque = positionAttaque - 1;
        Case caseAttaque = plateauEnemi.getCases().get(positionAttaque);

        if (caseAttaque.getValeur().equals("B")) {
            plateauEnemi.getCases().get(positionAttaque).setValeur("D");
            System.out.println("Bravo! Vous avez detruit un bateau enemi.");
        } else if (!caseAttaque.getValeur().equals("B")) {
            System.out.println("Aucun bateau touche...");
        }
        System.out.println("Voici le plateau adversaire");
        plateauEnemi.afficherPlateau();
    }

    public static void enemiAttaquePlateauDuJoueur(Plateau plateauDuJoueur) {
        int position = 1 + (int) (Math.random() * 10);
        Case caseAttaque = plateauDuJoueur.getCases().get(position - 1);

        //ne peut pas attaquer 2x la meme case

        if (caseAttaque.getValeur().equals("B")) {
            caseAttaque.setValeur("D");
            System.out.println("Votre adversaire a attaque la position " + position + " .");
            System.out.println("Un de vos bateaux a ete coule!");
            System.out.println("Voici votre plateau");
        } else if (!caseAttaque.getValeur().equals("B")) {
            System.out.println("Votre adversaire a attaque la position " + position + " .");
            System.out.println("Aucun bateaux n'a ete touche!");
        }
        System.out.println("Voici votre plateau.");
        plateauDuJoueur.afficherPlateau();
    }
}
