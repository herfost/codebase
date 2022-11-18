package javaapplication17;

import javaapplication17.domain.Product;
import javaapplication17.persistence.Persistence;
import javaapplication17.persistence.ProductPersistence;

public class JavaApplication17 {

    public static void main(String[] args) {
        ProductPersistence pp = ProductPersistence.getInstance();
        
        Persistence<String, Product> p = pp.getPeristence();
        
        Product product = new Product("Key", "description", 0);
    }
}
