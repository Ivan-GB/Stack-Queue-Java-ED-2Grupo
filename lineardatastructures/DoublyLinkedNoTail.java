package lineardatastructures;

public class DoublyLinkedNoTail<T> implements LinkedLists<T> {
    public class Node<T> implements NodeInterface<T> {
        T value;
        Node<T> next, prev;
        
        public Node(T val) {
            this.value = val;
        }
    }
    
    Node<T> head;
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void pushBack(T val) {
        Node<T> n = new Node<>(val);
        if (isEmpty()) {
            head = n;
        } else {
            Node<T> m = head;
            while (m.next != null) {
                m = m.next;
            }
            m.next = n;
            m.next.prev = m;
        }
    }
    
    public void pushFront(T val) {
        Node<T> n = new Node<>(val);
        if (isEmpty()) {
            head = n;
        } else {
            n.next = head;
            n.next.prev = n;
            head = n;
        }
    }
    
    public T popBack() {
        if (!isEmpty()) {
            Node<T> m = head;
            while (m.next != null) {
                m = m.next;
            }
            T t = m.value;
            m = m.prev;
            m.next = null;
            return t;
        }
        return null;
    }
    
    public T popFront() {
        if (!isEmpty()) {
            T t = head.value;
            head = head.next;
            head.prev = null;
            return t;
        }
        return null;
    }
    
    public T top() {
        return head.value;
    }
    
    public T rear() {
        Node<T> m = head;
        while (m.next != null) {
            m = m.next;
        }
        return m.value;
    }
    
    public NodeInterface<T> find(T val) {
        Node<T> n = head;
        
        while (n != null) {
            if (n.value.equals(val)) {
                break;
            }
            n = n.next;
        }
        
        return n;
    }
    
    public void erase(T val) throws RuntimeException {
        Node<T> n = (Node<T>) find(val);
        if (n != null) {
            Node<T> m = n.prev;
            Node<T> o = n.next;
            
            if (m != null) m.next = o;
            if (o != null) o.prev = m;
            
            if (n == head) head = o;
        } else {
            throw new RuntimeException("Element Not Found");
        }
    }
    
    public void addBefore(T iVal, T val) throws RuntimeException {
        Node<T> node = new Node<>(val);
        Node<T> n = (Node<T>) find(iVal);
        if (n != null) {
            if (n == head) head = node;
            node.next = n;
            node.prev = n.prev;
            if (n.prev != null) n.prev.next = node;
            n.prev = node;
        } else {
            throw new RuntimeException("Element Not Found");
        }
    }
    
    public void addAfter(T iVal, T val) throws RuntimeException {
        Node<T> node = new Node<>(val);
        Node<T> n = (Node<T>) find(iVal);
        if (n != null) {
            node.prev = n;
            node.next = n.next;
            if (n.next != null) n.next.prev = node;
            n.next = node;
        } else {
            throw new RuntimeException("Element Not Found");
        }
    }
    
    public void show() {
        Node<T> n = head;
        while (n != null) {
            System.out.println(n.value);
            n = n.next;
        }
    }
    
}
