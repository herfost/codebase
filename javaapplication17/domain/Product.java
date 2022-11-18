package javaapplication17.domain;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable, Keyable<String>, MyCloneable<Product> {

    private String key;
    private String description;
    private int quantity;

    public Product(String key, String description, int quantity) {
        this.key = key;
        this.description = description;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
        final Product other = (Product) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product { " + "key = " + key + ", description = " + description + ", quantity = " + quantity + " }";
    }
}
