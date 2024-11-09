package entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/**
 * Classe représentant un chat, qui est une sous-classe d'animal.
 * Cette classe hérite des attributs de la classe `Animal` et ajoute une fonctionnalité spécifique au chat : le numéro de puce.
 * Les informations concernant le chat sont mappées à la table "CAT" de la base de données.
 */
@Entity
@Table(name = "CAT")  // La table associée à cette entité est "CAT"
public class Cat extends Animal {

    /**
     * Numéro de puce de l'animal
     */
    @Column(name = "CHIPLD")
    private String chipld;

    /**
     * Constructeur par défaut pour JPA.
     * Ce constructeur est nécessaire pour que JPA puisse instancier un objet sans paramètres.
     */
    public Cat() {
        super();  // Appel au constructeur par défaut de la classe parente Animal
    }

    /**
     * Constructeur pour créer un objet Chat avec toutes ses propriétés.
     *
     * @param birthDate La date de naissance du chat.
     * @param color     La couleur du chat.
     * @param petStore  Le magasin d'animaux auquel ce chat appartient.
     * @param chipld    Le numéro de puce du chat.
     */
    public Cat(Date birthDate, String color, PetStore petStore, String chipld) {
        super(petStore, birthDate, color);  // Appel du constructeur de la classe parente Animal
        this.chipld = chipld;  // Initialisation du numéro de puce
    }

    /**
     * Récupère le numéro de puce du chat.
     *
     * @return Le numéro de puce du chat.
     */
    public String getChipld() {
        return chipld;
    }

    /**
     * Définit le numéro de puce du chat.
     *
     * @param chipld Le numéro de puce à associer au chat.
     */
    public void setChipld(String chipld) {
        this.chipld = chipld;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Cat.
     * Cette méthode inclut les informations spécifiques au chat ainsi que celles héritées de la classe parente `Animal`.
     *
     * @return Une chaîne représentant l'objet `Cat`, incluant le numéro de puce et les attributs de la classe parente.
     */
    @Override
    public String toString() {
        return "Cat{" +
                "chipld='" + chipld + '\'' +
                ", " + super.toString() +  // Appel à la méthode toString de la classe parente Animal
                '}';
    }
}
