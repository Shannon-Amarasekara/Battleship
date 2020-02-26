import java.util.Objects;

public class Case {
    private ValeurDeCase valeurDeCase;

    public Case(ValeurDeCase valeurDeCase) {
        this.valeurDeCase = valeurDeCase;
    }

    public void afficherRepresentationSurLePlateau() {
        System.out.print(this.valeurDeCase.getRepresentationSurLePlateau());
    }

    public void setValeurDeCase(ValeurDeCase valeurDeCase) {
        this.valeurDeCase = valeurDeCase;
    }

    public ValeurDeCase getValeurDeCase() {
        return valeurDeCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(valeurDeCase, aCase.valeurDeCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeurDeCase);
    }
}





