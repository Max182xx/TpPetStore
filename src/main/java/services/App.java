package services;

import entitys.*;
import enumerations.FishLivEnv;
import enumerations.ProdType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        /* Création d'objets Adresse */
        Adress adress1 = new Adress("3B", "Impasse des moutons", "83500", "La Seyne sur Mer");
        Adress adress2 = new Adress("666", "rue du paradis", "66666", "L'enfer");
        Adress adress3 = new Adress("50", "rue de paris", "75000", "Paris");

        /* Création d'objets PetStore */
        PetStore petStore1 = new PetStore("Baba", "Dialo", adress1);
        PetStore petStore2 = new PetStore("Bob", "L'Eponge", adress2);
        PetStore petStore3 = new PetStore("Sam", "Sauvage", adress3);

        /* Création d'objets Product */
        Product product1 = new Product("1", "eco", ProdType.ACCESSORY, 29.99);
        Product product2 = new Product("2", "bio", ProdType.FOOD, 30.99);
        Product product3 = new Product("3", "toxic", ProdType.CLEANING, 30.8);

        /* Création d'objets Animal, Fish et Cat */
        Fish fish1 = new Fish(new Date(), "bleu", petStore1, FishLivEnv.FRESH_WATER);
        Fish fish2 = new Fish(new Date(), "pink", petStore2, FishLivEnv.SEA_WATER);
        Cat cat1 = new Cat(new Date(), "white", petStore3, "Inconnu");
        Cat cat2 = new Cat(new Date(), "black", petStore3, "Inconnu");


        /* Ajout des animaux aux PetStore */
        petStore1.addAnimal(fish1);
        petStore2.addAnimal(fish2);
        petStore3.addAnimal(cat1);
        petStore3.addAnimal(cat2);

        /* Ajout des produits aux PetStore */
        petStore1.addProducts(product1);
        petStore2.addProducts(product2);
        petStore3.addProducts(product3);

        /* Paramétrage de création de la DB avec JPA */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TpPetstore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        /* Insertion des objets adresse en DB */
        em.persist(adress1);
        em.persist(adress2);
        em.persist(adress3);

        /* Insertion dans la DB en cascade */
        em.persist(petStore1);
        em.persist(petStore2);
        em.persist(petStore3);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
