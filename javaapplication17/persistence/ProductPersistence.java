package javaapplication17.persistence;

import javaapplication17.domain.Product;

public class ProductPersistence {

    private static ProductPersistence instance;
    private Persistence<String, Product> persistence;

    private ProductPersistence() {
        persistence = new Persistence<>();
    }

    public static synchronized ProductPersistence getInstance() {
        if (instance == null) {
            instance = new ProductPersistence();
        }

        return instance;
    }

    public Persistence<String, Product> getPeristence() {
        return persistence;
    }
}
