package genericpersistence.persistence;

import java.util.ArrayList;
import java.util.List;

public abstract class PersistenceFacade<K, T extends IPersistenceObject<K>> implements IPersistence<K, T> {
    private final List<T> items;

    public PersistenceFacade() {
        items = getPersistence();
    }
    
    public PersistenceFacade(String path) {
        items = getPersistence(path);
    }

    protected abstract List<T> getPersistence();
    protected abstract List<T> getPersistence(String path);

    @Override
    public synchronized void create(T value) throws IllegalArgumentException {
        try {
            T item = read(value.getKey());
            throw new IllegalArgumentException("Key already been used: @key = " + item.getKey());
        } catch (IllegalArgumentException ex) {
            items.add((T) value.getClone());
        }
    }

    @Override
    public synchronized T read(K key) throws IllegalArgumentException {
        T item = getItem(key);
        if (item == null) {
            throw new IllegalArgumentException("Invalid Key");
        }

        return (T) item.getClone();
    }

    @Override
    public synchronized void update(T value) throws IllegalArgumentException {
        T item = getItem(value.getKey());
        if (item == null) {
            throw new IllegalArgumentException("Invalid Key");
        }

        items.set(items.indexOf(item), (T) value.getClone());
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
    public synchronized List<T> getAll() {
        if (items.isEmpty()) {
            return new ArrayList<>();
        }

        List<T> copy = new ArrayList<>();
        items.forEach((T item) -> {
            copy.add((T) item.getClone());
        });
        return copy;
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
