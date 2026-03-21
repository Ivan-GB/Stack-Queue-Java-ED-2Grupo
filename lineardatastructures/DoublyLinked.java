package lineardatastructures;

public class DoublyLinked<T> {
    public class Node<T> {
        T value;
        Node<T> next, prev;
        
        public Node(T val) {
            this.value = val;
        }
    }
    
    Node<T> head, tail;
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void pushBack(T val) {
        Node<T> n = new Node<>(val);
        if (isEmpty()) {
            head = n;
            tail = head;
        } else {
            if (head == tail) {
                n.prev = tail;
                n.prev.next = n;
                tail = n;
                head.next = tail;
            } else {
                n.prev = tail;
                n.prev.next = n;
                tail = n;
            }
        }
    }
    
    public void pushFront(T val) {
        Node<T> n = new Node<>(val);
        if (isEmpty()) {
            head = n;
            tail = head;
        } else {
            if (head == tail) {
                n.next = head;
                n.next.prev = n;
                head = n;
                tail.prev = head;
            } else {
                n.next = head;
                n.next.prev = n;
                head = n;
            }
        }
    }
    
    public T popBack() {
        if (!isEmpty()) {
            T t = tail.value;
            tail = tail.prev;
            tail.next = null;
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
        return tail.value;
    }
    
    public Node<T> find(T val) {
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
        Node<T> n = find(val);
        if (n != null) {
            Node<T> m = n.prev;
            Node<T> o = n.next;
            
            if (m != null) m.next = o;
            if (o != null) o.prev = m;
            
            if (n == head) head = o;
            if (n == tail) tail = m;
        } else {
            throw new RuntimeException("Element Not Found");
        }
    }
    
    public void addBefore(T iVal, T val) throws RuntimeException {
        Node<T> node = new Node<>(val);
        Node<T> n = find(iVal);
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
        Node<T> n = find(iVal);
        if (n != null) {
            if (n == tail) tail = node;
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
