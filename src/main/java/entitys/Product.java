package entitys;

import enumerations.ProdType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant un produit vendu dans un magasin d'animaux.
 * Cette classe est mappée à la table "product" dans la base de données.
 * Elle contient des informations sur le produit, y compris son code, son label, son type, son prix et les magasins associés.
 */
@Entity
@Table(name = "product")  // La table associée à cette entité est "product"
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "TYPE")
    private ProdType prodType;

    @Column(name = "PRICE")
    private double price;

    // Relation ManyToMany avec l'entité PetStore, un produit peut être vendu dans plusieurs magasins.
    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petStores;

    /**
     * Initialisation de la liste `petStores` dans un bloc d'initialisation.
     * Cela garantit que la collection ne sera jamais nulle.
     */
    {
        petStores = new HashSet<>();
    }

    /**
     * Constructeur par défaut nécessaire pour JPA.
     * Ce constructeur permet à JPA d'instancier l'objet sans paramètres.
     */
    public Product() {
    }

    /**
     * Constructeur permettant d'initialiser un produit avec son code, son label, son type et son prix.
     *
     * @param code Le code du produit.
     * @param label Le label du produit.
     * @param type Le type du produit.
     * @param price Le prix du produit.
     */
    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.prodType = type;
        this.price = price;
    }

    /**
     * Méthode pour ajouter un magasin à la liste des magasins associés au produit.
     * Elle met à jour l'association bidirectionnelle en appelant la méthode `addProducts` du magasin.
     *
     * @param petStore Le magasin à ajouter.
     */
    public void addPetStore(PetStore petStore) {
        if (petStore != null) {
            petStore.addProducts(this);  // Mise à jour de l'association bidirectionnelle
        }
    }

    // Getters et Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProdType getProdType() {
        return prodType;
    }

    public void setProdType(ProdType prodType) {
        this.prodType = prodType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<PetStore> getPetStores() {
        return petStores;
    }

    /**
     * Setter pour `petStores`.
     *
     * @param petStores La collection de magasins associés au produit.
     */
    public void setPetStores(Set<PetStore> petStores) {
        this.petStores = petStores;
    }

    /**
     * Méthode `toString` qui retourne une chaîne représentant l'objet `Product`.
     * Elle inclut les informations sur le produit et les magasins associés.
     *
     * @return Une chaîne représentant l'objet `Product`.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", prodType=").append(prodType);
        sb.append(", price=").append(price);
        sb.append(", petStores=").append(petStores);
        sb.append('}');
        return sb.toString();
    }
}
