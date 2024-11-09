package entitys;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe représentant un magasin d'animaux. Cette classe est mappée à la table "petStore" dans la base de données.
 * Elle contient des informations sur le magasin, les animaux qu'il possède, les produits qu'il vend et son adresse.
 * La classe gère aussi les relations avec les entités associées : `Animal`, `Product`, et `Adress`.
 */
@Entity
@Table(name = "petStore")  // La table associée à cette entité est "petStore"
public class PetStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MANAGER_NAME")
    private String managerName;

    // Relation OneToMany avec l'entité Animal, un magasin peut avoir plusieurs animaux.
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private Set<Animal> animals;

    // Relation ManyToMany avec l'entité Product, un magasin peut vendre plusieurs produits et inversement.
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petStore_product",
            joinColumns = @JoinColumn(name = "ID_PETSTORE", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID"))
    private Set<Product> products;

    // Relation OneToOne avec l'entité Adress, chaque magasin a une adresse.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    private Adress adress;

    /**
     * Initialisation des ensembles `animals` et `products` dans un bloc d'initialisation.
     * Cela garantit que les collections ne soient jamais nulles.
     */
    {
        animals = new HashSet<>();
        products = new HashSet<>();
    }

    /**
     * Constructeur par défaut nécessaire pour JPA.
     * Ce constructeur permet à JPA d'instancier l'objet sans paramètres.
     */
    public PetStore() {
    }

    /**
     * Constructeur permettant d'initialiser un magasin d'animaux avec un nom, un nom de responsable et une adresse.
     *
     * @param name Le nom du magasin.
     * @param managerName Le nom du responsable du magasin.
     * @param adress L'adresse du magasin.
     */
    public PetStore(String name, String managerName, Adress adress) {
        this.name = name;
        this.managerName = managerName;
        this.adress = adress;
    }

    /**
     * Méthode pour ajouter un animal au magasin.
     * Elle met à jour l'association bidirectionnelle en définissant le magasin de l'animal.
     *
     * @param animal L'animal à ajouter au magasin.
     */
    public void addAnimal(Animal animal) {
        animal.setPetStore(this);  // Mise à jour de l'association avec l'animal
    }

    /**
     * Méthode pour ajouter un produit à la liste de produits du magasin.
     * Elle met également à jour l'association bidirectionnelle dans la classe `Product`.
     *
     * @param product Le produit à ajouter au magasin.
     */
    public void addProducts(Product product) {
        if (product != null) {
            this.products.add(product);
            product.getPetStores().add(this);  // Mise à jour de l'association avec le magasin dans Product
        }
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /**
     * Méthode `toString` qui retourne une chaîne représentant l'objet `PetStore`.
     * Elle inclut les informations sur le magasin, les animaux, les produits et l'adresse.
     *
     * @return Une chaîne représentant l'objet `PetStore`.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PetStore{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", managerName='").append(managerName).append('\'');
        sb.append(", animals=").append(animals);
        sb.append(", products=").append(products);
        sb.append(", adress=").append(adress);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Méthode `equals` qui vérifie l'égalité entre deux objets `PetStore`.
     * Deux objets `PetStore` sont considérés égaux si leurs `id`, `name`, `managerName` et `animals` sont identiques.
     *
     * @param o L'objet à comparer.
     * @return true si les deux objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetStore petStore = (PetStore) o;
        return Objects.equals(id, petStore.id) &&
                Objects.equals(name, petStore.name) &&
                Objects.equals(managerName, petStore.managerName) &&
                Objects.equals(animals, petStore.animals);
    }

    /**
     * Méthode `hashCode` qui génère un code de hachage pour l'objet `PetStore`.
     * Le code de hachage est basé sur les attributs `id`, `name`, `managerName` et `animals`.
     *
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, managerName, animals);
    }
}
