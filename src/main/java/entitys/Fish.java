package entitys;

import enumerations.FishLivEnv;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

/**
 * Classe représentant un poisson, qui est une sous-classe d'`Animal`.
 * Cette classe hérite des attributs de la classe `Animal` et ajoute un environnement de vie spécifique pour le poisson.
 * Les informations concernant le poisson sont mappées à la table "FISH" dans la base de données.
 */
@Entity
@Table(name = "FISH")  // La table associée à cette entité est "FISH"
public class Fish extends Animal {

    /** Environnement de vie du poisson (eau douce, eau salée, etc.) */
    @Column(name = "FISH_LIV_ENV")
    private FishLivEnv linvingEnv;

    /**
     * Constructeur par défaut pour JPA.
     * Ce constructeur est nécessaire pour que JPA puisse instancier un objet sans paramètres.
     */
    public Fish() {
    }

    /**
     * Constructeur pour créer un objet `Fish` avec toutes ses propriétés.
     *
     * @param birthDate La date de naissance du poisson.
     * @param color La couleur du poisson.
     * @param petStore Le magasin d'animaux auquel ce poisson appartient.
     * @param linvingEnv L'environnement de vie du poisson (eau douce, eau salée, etc.).
     */
    public Fish(Date birthDate, String color, PetStore petStore, FishLivEnv linvingEnv) {
        super(petStore, birthDate, color);  // Appel du constructeur de la classe parente Animal
        this.linvingEnv = linvingEnv;  // Initialisation de l'environnement de vie du poisson
    }

    /**
     * Récupère l'environnement de vie du poisson.
     *
     * @return L'environnement de vie du poisson.
     */
    public FishLivEnv getLinvingEnv() {
        return linvingEnv;
    }

    /**
     * Définit l'environnement de vie du poisson.
     *
     * @param linvingEnv L'environnement de vie à associer au poisson.
     */
    public void setLinvingEnv(FishLivEnv linvingEnv) {
        this.linvingEnv = linvingEnv;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet `Fish`.
     * Cette méthode inclut les informations spécifiques au poisson ainsi que celles héritées de la classe parente `Animal`.
     *
     * @return Une chaîne représentant l'objet `Fish`, incluant l'environnement de vie et les attributs de la classe parente.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fish{");
        sb.append("linvingEnv=").append(linvingEnv);  // Ajout de l'environnement de vie du poisson
        sb.append('}');
        return sb.toString();
    }
}
