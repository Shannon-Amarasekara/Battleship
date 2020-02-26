public enum ValeurDeCase {

    VIDE("X"),
    BATEAU("B"),
    BATEAU_COULE("C");

    private String representationSurLePlateau;

     ValeurDeCase(String representationSurLePlateau){
        this.representationSurLePlateau = representationSurLePlateau;
    }

    public String getRepresentationSurLePlateau() {
        return representationSurLePlateau;
    }
}
