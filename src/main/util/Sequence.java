package main.util;

interface SequenceAdt<T> {
    T get(int i);
    void set(int i, T x);
    void insert(int i, T x);
    void delete(int i);
}

public class Sequence<T> implements SequenceAdt<T> {

    public static final int DEFAULT_CAPACITY = 64;
    public Object[] array;

    public Sequence() {
        this(DEFAULT_CAPACITY);
    }

    public Sequence(int maxSize) {
        this.array = new Object[maxSize];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) this.array[i];
    }

    @Override
    public void set(int i, T x) {
        this.array[i] = x;
    }

    @Override
    public void insert(int i, T x) {
        while (i > this.array.length - 1) {
            this.resize();
        }
        this.set(i, x);
    }

    @Override
    public void delete(int i) {
        this.array[i] = null;
    }

    public int size() {
        return this.array.length;
    }

    private void resize() {
        Object[] newArray = new Object[this.array.length * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }
}
