package lineardatastructures;

public interface LinkedLists<T> {
    void pushBack(T val);
    void pushFront(T val);
    T popBack();
    T popFront();
    void erase(T val);
    void addBefore(T iVal, T val);
    void addAfter(T iVal, T val);
    boolean isEmpty();
    NodeInterface<T> find(T val);
}

interface NodeInterface<T> { }
