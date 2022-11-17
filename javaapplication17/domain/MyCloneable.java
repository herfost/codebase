package javaapplication17.domain;

public interface MyCloneable<T> extends Cloneable {
    public T getClone();
}
