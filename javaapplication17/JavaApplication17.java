package javaapplication17;

import javaapplication17.domain.Product;
import javaapplication17.persistence.Persistence;
import javaapplication17.persistence.ProdottoPersistence;

public class JavaApplication17 {

    public static void main(String[] args) {
        ProdottoPersistence pp = ProdottoPersistence.getInstance();
        
        Persistence<String, Product<String>> p = pp.getPeristence();
        
        Product<String> product = new Product<>("Key", "description", 0);
    }
}
