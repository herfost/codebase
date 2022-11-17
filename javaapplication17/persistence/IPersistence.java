package javaapplication17.persistence;

import java.io.Serializable;
import java.util.List;
import javaapplication17.domain.Keyable;
import javaapplication17.domain.MyCloneable;

public interface IPersistence<K, T extends Serializable & Keyable<K> & MyCloneable<T>> {
    public void create(T value) throws IllegalArgumentException;
    public T read(K key) throws IllegalArgumentException;
    public void update(T value) throws IllegalArgumentException;
    public void delete(K key) throws IllegalArgumentException;
    public List<T> getAll() throws IllegalArgumentException;
}