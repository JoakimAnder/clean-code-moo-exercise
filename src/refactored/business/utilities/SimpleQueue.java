package refactored.business.utilities;

public interface SimpleQueue<T> {
    void push(T element);
    T pull();
    boolean isEmpty();
    int length();
}
