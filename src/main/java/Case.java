import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Case {
    private String valeur;

    public Case(String valeur) {
        this.valeur = valeur;
    }

    public void afficherCase() {
        System.out.print(this.valeur);
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(valeur, aCase.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }
}





