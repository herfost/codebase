package genericpersistence.persistence;

import java.util.List;

public interface IPersistence<K, T extends IPersistenceObject<K>> {

    public void create(T value) throws IllegalArgumentException;

    public T read(K key) throws IllegalArgumentException;

    public void update(T value) throws IllegalArgumentException;

    public void delete(K key) throws IllegalArgumentException;

    public List<T> getAll();
}