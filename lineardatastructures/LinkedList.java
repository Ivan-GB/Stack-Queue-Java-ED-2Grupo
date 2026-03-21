package lineardatastructures;

public class LinkedList<T> {
    
    public class Node<T> {
        T value;
        Node<T> next;
        
        public Node(T value) {
            this.value =  value;
            this.next = null;
        }
    }
    
    protected Node<T> head;
    
    public LinkedList() {
        head = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void pushFront(T value) {
        Node<T> node = new Node<>(value);
        
        node.next = head;
        head = node;
    }
    
    public void pushBack(T value) {
        Node<T> node = new Node<>(value);
        
        if(this.isEmpty()) {
            head = node;
            return;
        }
        
        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        
        curr.next = node;
    }
    
    public T popFront() throws RuntimeException {
        if(this.isEmpty()) {
            throw new RuntimeException("List is empty"); 
        }
        
        T value = head.value;
        head = head.next;
        return value;
    }
    
    public T popBack() throws RuntimeException {
        if(this.isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        if(head.next == null) {
            T value = head.value;
            head = null;
            return value;
        }
        
        Node<T> curr = head;
        while(curr.next.next != null) {
            curr = curr.next;
        }
        
        T value = curr.next.value;
        curr.next = null;
        return value;
    }
    
    public T topFront() throws RuntimeException {
        if(this.isEmpty()) {
            throw new RuntimeException("List is empty"); 
        }
        
        return head.value;
    }
    
    public T topBack() throws RuntimeException {
        if(this.isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        
        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        
        return curr.value;
    }
    
    public Node<T> find(T value) {
        Node<T> curr = head;
        
        while(curr != null) {
            if(curr.value.equals(value)) {
                return curr;
            }
            curr = curr.next;
        } 
        
        return null;
    }
    
    public void erase(T value) throws RuntimeException {
        if(this.isEmpty()) {
            throw new RuntimeException("Element not found");
        }
        
        if(head.value.equals(value)) {
            head = head.next;
            return;
        }
        
        Node<T> curr = head;
        while(curr.next != null && !curr.next.value.equals(value)) {
            curr = curr.next;
        }
        
        if(curr.next == null) {
            throw new RuntimeException("Element not found");
        }
        
        curr.next = curr.next.next;
    }
    
    public void addBefore(T target, T value) throws RuntimeException {
        if(this.isEmpty()){
            throw new RuntimeException("Element not found");
        }
        
        if(head.value.equals(target)) {
            Node<T> node = new Node<>(value);
            
            node.next = head;
            head = node;
            return;
        }
        
        Node<T> curr = head;
        while(curr.next != null && !curr.next.value.equals(target)) {
            curr = curr.next;
        }
        
        if(curr.next == null) {
            throw new RuntimeException("Element not found");
        }
        
        Node<T> node = new Node<>(value);
        node.next = curr.next;
        curr.next = node;
    }
    
    public void addAfter(T target, T value) throws RuntimeException{
        Node<T> curr = head;
        
        while(curr != null) {
            if(curr.value.equals(target)) { 
                Node<T> node = new Node<>(value);
                
                node.next = curr.next;
                curr.next = node;
                return;
            }
            curr = curr.next;
        }
        
        throw new RuntimeException("Element not found");
    }
    
    public void print() {
        Node<T> curr = head;
        String result = "";
        
        while(curr != null) {
            result += curr.value + " ";
            curr = curr.next;
        }
        
        System.out.println(result);
    }
    
}
