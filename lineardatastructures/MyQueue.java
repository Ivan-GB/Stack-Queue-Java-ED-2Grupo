package lineardatastructures;

public class MyQueue<T> {
    T[] Stack;
    int  size=0;
    int capacity=1;
    int top=0;
    public MyQueue(){
        Stack=(T[])new Object[1];
    }
    public void enqueue(T o){
        resize();
        Stack[(top+size)%capacity]=o;
        size++;
    }
    public void resize(){
        if(size==Stack.length){
            capacity*=2;
            T[] NStack=(T[])new Object[capacity];
            for(int i=0;i<size;i++){
                NStack[i]=Stack[(top+i)%(capacity/2)];
            }
            top=0;
            Stack=NStack;
        }
    }
    public T dequeue(){
        if(size==0){
            return null;
        }
        T o=Stack[top];
        top=(top+1)%capacity;
        size--;
        return o;
    }
    public T peek(){
        if(size==0){
            return null;
        }
        return Stack[top];
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void delete(T o){
        for(int i=0;i<size;i++){
            if(o==Stack[(top+i)%capacity]){
                for(int j=i;j<size-1;j++){
                    Stack[(top+j)%capacity]=Stack[(top+j+1)%capacity];
                }
                size--;
                break;
            }
        }
    }
}
