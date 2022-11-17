package javaapplication17.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product<K> implements Serializable, Keyable<K>, MyCloneable<Product<K>> {

    private K key;
    private String description;
    private int quantity;

    public Product(K key, String description, int quantity) {
        this.key = key;
        this.description = description;
        this.quantity = quantity;
    }

    public Product() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Product getClone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product<?> other = (Product<?>) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product { " + "key = " + key + ", description = " + description + ", quantity = " + quantity + " }";
    }
}
