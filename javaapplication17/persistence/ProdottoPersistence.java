package javaapplication17.persistence;

import javaapplication17.domain.Product;

public class ProdottoPersistence {

    private static ProdottoPersistence instance;
    private Persistence<String, Product<String>> persistence;

    private ProdottoPersistence() {
        persistence = new Persistence<>();
    }

    public static synchronized ProdottoPersistence getInstance() {
        if (instance == null) {
            instance = new ProdottoPersistence();
        }

        return instance;
    }

    public Persistence<String, Product<String>> getPeristence() {
        return persistence;
    }
}
