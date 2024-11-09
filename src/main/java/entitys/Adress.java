package entitys;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Classe représentant une adresse.
 * Cette classe est mappée à la table "adress" de la base de données.
 * Elle contient des informations telles que le numéro, la rue, le code postal et la ville.
 * Une adresse peut être associée à un magasin d'animaux via une relation de type One-to-One.
 */
@Entity
@Table(name = "adress")
public class Adress implements Serializable {

    /** Identifiant unique de l'adresse. C'est la clé primaire de la table. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /** Numéro de l'adresse. */
    @Column(name = "NUMBER")
    private String number;

    /** Rue de l'adresse. */
    @Column(name = "STREET")
    private String street;

    /** Code postal de l'adresse. */
    @Column(name = "ZIP_CODE")
    private String zipCode;

    /** Ville associée à l'adresse. */
    @Column(name = "CITY")
    private String city;

    /** Le magasin d'animaux associé à cette adresse (relation One-to-One). */
    @OneToOne(mappedBy = "adress")
    private PetStore petStore;

    /**
     * Constructeur par défaut pour JPA.
     * Il est requis pour que JPA puisse instancier l'objet sans paramètres.
     */
    public Adress() {
    }

    /**
     * Constructeur avec paramètres pour initialiser une adresse.
     *
     * @param number Le numéro de l'adresse.
     * @param street La rue de l'adresse.
     * @param zipCode Le code postal de l'adresse.
     * @param city La ville de l'adresse.
     */
    public Adress(String number, String street, String zipCode, String city) {
        this.number = number;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * Récupère l'identifiant de l'adresse.
     *
     * @return L'identifiant de l'adresse.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'adresse.
     *
     * @param id L'identifiant de l'adresse.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le numéro de l'adresse.
     *
     * @return Le numéro de l'adresse.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Définit le numéro de l'adresse.
     *
     * @param number Le numéro de l'adresse.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Récupère la rue de l'adresse.
     *
     * @return La rue de l'adresse.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Définit la rue de l'adresse.
     *
     * @param street La rue de l'adresse.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Récupère le code postal de l'adresse.
     *
     * @return Le code postal de l'adresse.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Définit le code postal de l'adresse.
     *
     * @param zipCode Le code postal de l'adresse.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Récupère la ville de l'adresse.
     *
     * @return La ville de l'adresse.
     */
    public String getCity() {
        return city;
    }

    /**
     * Définit la ville de l'adresse.
     *
     * @param city La ville de l'adresse.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Récupère le magasin d'animaux associé à cette adresse.
     *
     * @return Le magasin d'animaux associé à cette adresse.
     */
    public PetStore getPetStore() {
        return petStore;
    }

    /**
     * Définit le magasin d'animaux associé à cette adresse.
     *
     * @param petStore Le magasin d'animaux à associer.
     */
    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'adresse.
     *
     * @return Une chaîne représentant l'objet adresse.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Adress{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", petStore=").append(petStore);
        sb.append('}');
        return sb.toString();
    }
}
