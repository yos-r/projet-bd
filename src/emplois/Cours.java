package emplois;

/**
 *
 * @author yosrb
 */
public class Cours {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String enseignant;

    public Cours(String classe, String matiere, String jour, String heure, String enseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.enseignant = enseignant;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }
    
}
