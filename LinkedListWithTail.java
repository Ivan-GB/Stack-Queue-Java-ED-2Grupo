public class LinkedListWithTail<T> extends LinkedList<T> {
    
    private Node<T> tail;
    
    public LinkedListWithTail() {
        super();
        tail = null;
    }
    
    @Override
    public void pushFront(T value) {
        Node<T> node = new Node<>(value);
        
        if(isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }
    
    @Override
    public void pushBack(T value) {
        Node<T> node = new Node<>(value);
        
        if(isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
    
    @Override
    public T popFront() {
        if(isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        T value = head.value;
        head = head.next;
        
        if (head == null) {
            tail = null;
        }
        
        return value;
    }
    
    @Override
    public T popBack() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        if (head == tail) {
            T value = head.value;
            head = tail = null;
            return value;
        }
        
        Node<T> curr = head;
        while (curr.next != tail) {
            curr = curr.next;
        }
        
        T value = tail.value;
        curr.next = null;
        tail = curr;
        
        return value;
    }
    
    @Override
    public T topBack() {
        if(isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        return tail.value;
    }
}