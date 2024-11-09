package entitys;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant un animal. Cette classe est mappée à la table "animal" de la base de données.
 * Elle contient des informations de base sur un animal, telles que sa date de naissance, sa couleur et son magasin d'animaux associé.
 * Cette classe utilise la stratégie d'héritage "JOINED", ce qui permet d'avoir une hiérarchie d'objets avec une table par sous-classe.
 */
@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)  // Utilisation de la stratégie JOINED pour l'héritage
public class Animal implements Serializable {

    /**
     * Identifiant unique de l'animal. C'est la clé primaire de la table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * Date de naissance de l'animal.
     */
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    /**
     * Couleur de l'animal.
     */
    @Column(name = "COLOR")
    private String color;

    /**
     * Le magasin d'animaux auquel appartient cet animal.
     */
    @ManyToOne
    @JoinColumn(name = "ID_PET_STORE")
    private PetStore petStore;

    /**
     * Constructeur par défaut pour JPA.
     * Il est nécessaire pour que JPA puisse instancier l'objet sans paramètres.
     */
    public Animal() {
    }

    /**
     * Constructeur pour créer un animal avec les informations de base.
     *
     * @param petStore  Le magasin d'animaux auquel appartient l'animal.
     * @param birthDate La date de naissance de l'animal.
     * @param color     La couleur de l'animal.
     */
    public Animal(PetStore petStore, Date birthDate, String color) {
        this.birthDate = birthDate;
        this.color = color;
        setPetStore(petStore);  // Utilisation du setter pour gérer la relation bidirectionnelle avec le magasin
    }

    /**
     * Récupère l'identifiant de l'animal.
     *
     * @return L'identifiant unique de l'animal.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'animal.
     *
     * @param id L'identifiant de l'animal.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la date de naissance de l'animal.
     *
     * @return La date de naissance de l'animal.
     */
    public Date getbirthDate() {
        return birthDate;
    }

    /**
     * Définit la date de naissance de l'animal.
     *
     * @param birthDate La date de naissance de l'animal.
     */
    public void setbirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Récupère la couleur de l'animal.
     *
     * @return La couleur de l'animal.
     */
    public String getColor() {
        return color;
    }

    /**
     * Définit la couleur de l'animal.
     *
     * @param color La couleur de l'animal.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Récupère le magasin d'animaux auquel cet animal appartient.
     *
     * @return Le magasin d'animaux associé à cet animal.
     */
    public PetStore getPetStore() {
        return petStore;
    }

    /**
     * Définit le magasin d'animaux auquel cet animal appartient.
     * Cette méthode assure la gestion bidirectionnelle de la relation entre l'animal et le magasin.
     * Si l'animal change de magasin, il est ajouté à la liste des animaux du nouveau magasin et retiré de celle de l'ancien magasin.
     *
     * @param petStore Le magasin d'animaux auquel cet animal va être associé.
     */
    public void setPetStore(PetStore petStore) {
        // Si l'animal appartient déjà à un magasin, on l'enlève de la liste des animaux de ce magasin
        if (this.petStore != null) {
            this.petStore.getAnimals().remove(this);
        }

        // Mise à jour de l'animal avec le nouveau magasin
        this.petStore = petStore;

        // Si un magasin est défini, on ajoute l'animal à la liste des animaux du magasin
        if (this.petStore != null) {
            this.petStore.getAnimals().add(this);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'animal.
     *
     * @return Une chaîne représentant l'objet animal, incluant ses attributs et son magasin d'animaux.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Animal{");
        sb.append("id=").append(id);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", color='").append(color).append('\'');
        sb.append(", petStore=").append(petStore);
        sb.append('}');
        return sb.toString();
    }
}
