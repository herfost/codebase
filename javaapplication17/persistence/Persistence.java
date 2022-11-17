package javaapplication17.persistence;

import javaapplication17.domain.Keyable;
import javaapplication17.domain.MyCloneable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persistence<K, T extends Serializable & Keyable<K> & MyCloneable<T>> implements IPersistence<K, T> {

    private final ArrayList<T> items;

    public Persistence() {
        items = new ArrayList<>();
    }

    @Override
    public synchronized void create(T value) throws IllegalArgumentException {
        try {
            T item = read(value.getKey());
            throw new IllegalArgumentException("Key already been used: @key = " + item.getKey());
        } catch (IllegalArgumentException ex) {
            items.add(value.getClone());
        }
    }

    @Override
    public synchronized  T read(K key) throws IllegalArgumentException {
        T item = getItem(key);
        if (item == null) {
            throw new IllegalArgumentException("Invalid Key");
        }

        return item.getClone();
    }

    @Override
    public synchronized void update(T value) throws IllegalArgumentException {
        T item = getItem(value.getKey());
        if (item == null) {
            throw new IllegalArgumentException("Invalid Key");
        }

        item = value.getClone();
    }

    @Override
    public synchronized void delete(K key) throws IllegalArgumentException {
        T item = getItem(key);

        if (item == null) {
            throw new IllegalArgumentException("Invalid Key");
        }

        items.remove(getItem(item.getKey()));
    }

    @Override
    public synchronized List<T> getAll() throws IllegalArgumentException {
        if (!items.isEmpty()) {
            List<T> copy = new ArrayList<>();
            for (T item : items) {
                copy.add(item.getClone());
            }
            return copy;
        }

        throw new IllegalArgumentException("Empty Persistence");
    }

    private synchronized T getItem(K key) {
        for (T item : items) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }

        return null;
    }

    @Override
    synchronized public String toString() {
        StringBuilder builder = new StringBuilder();
        items.forEach(item -> {
            builder.append(item.toString()).append('\n');
        });

        return builder.toString();
    }
}
