package emplois;
/**
 *
 * @author yosrb
 */
public class Enseignant {
    private String matricule;
    private String nom;
    private String contact;

    @Override
    public String toString() {
        return "Enseignant{" + "matricule=" + matricule + ", nom=" + nom + ", contact=" + contact + '}';
    }

    public Enseignant(String matricule, String nom, String contact) {
        this.matricule = matricule;
        this.nom = nom;
        this.contact = contact;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
}
