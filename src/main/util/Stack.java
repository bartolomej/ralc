package main.util;



interface Collection<T> {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";
    static final String ERR_MSG_INVALID_SIZE = "Invalid collection size.";

    boolean isEmpty();
    boolean isFull();
    int size();
    void addAll(Collection<T> c) throws CollectionException;
    T[] toArray(T[] a) throws CollectionException;
    String toString();
}


interface StackAdt<T> extends Collection<T> {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}


public class Stack<T> implements StackAdt<T> {

    public static final int DEFAULT_CAPACITY = 64;
    public Object[] array;
    private int length;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int maxSize) {
        this.array = new Object[maxSize];
        this.length = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean isFull() {
        return this.size() == this.array.length;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public T top() throws CollectionException {
        return this.getItem(this.size() - 1);
    }

    @Override
    public void push(T x) throws CollectionException {
        if (this.size() == this.array.length) {
            throw new CollectionException(ERR_MSG_FULL);
        }
        this.array[this.length] = x;
        this.length++;
    }

    @Override
    public T pop() throws CollectionException {
        T e = this.getItem(this.length - 1);
        this.length--;
        return e;
    }

    public void clear() {
        this.length = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addAll(Collection<T> c) throws CollectionException {
        T[] a = c.toArray((T[]) new Object[c.size()]);
        for (T e : a) {
            this.push(e);
        }
    }

    public T[] toArray(T[] a) throws CollectionException {
        if (a.length != this.length) {
            throw new CollectionException(ERR_MSG_INVALID_SIZE);
        }
        System.arraycopy(this.array, 0, a, 0, this.length);
        return (T[]) a;
    }

    @SuppressWarnings("unchecked")
    private T getItem(int index) throws CollectionException {
        if (this.size() == 0) {
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return (T) array[index];
    }

}
