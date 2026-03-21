package lineardatastructures;

public class MyStack<T>{
    T[] Stack;
    int  size=0;
    int capacity=1;
    
    public MyStack(){
        Stack =(T[])new Object[1];
    }

    public void push(T o){
        resize();
        Stack[size]=o;
        size++;
    }
    public void resize(){
        if(size==Stack.length){
            capacity*=2;
            T[] NStack=(T[])new Object[capacity];
            for(int i=0;i<size;i++){
                NStack[i]=Stack[i];
            }
            Stack=NStack;
        }
    }
    public T pop(){
        if(size==0){
            return null;
        }
        T o=Stack[size-1];
        Stack[size-1]=null;
        size--;
        return o;
    }
    public T peek(){
        if(size==0){
            return null;
        }
        return Stack[size-1];
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void delete(T o){
        for(int i=0;i<size;i++){
          if(Stack[i]==o){
              for(int j=i;j<size-1;j++){
                  Stack[j]=Stack[j+1];
              }
              size--;
              break;
          }
        }
    }
}

