import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import lineardatastructures.*;

public class Main{
    //Este es para no nos haga trampas el compilador por no usar los returns
    private static BlackHole bh = new BlackHole();
    //Este parcero nos devuelve el tiempo de ejecución
    private static long measure(Runnable task){
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }

    private static String[] row(String DS, String op, int size, long nanoTime){
        return new String[]{DS, op, String.valueOf(size), String.valueOf(nanoTime)};
    }

    private static void testLinkedList (LinkedLists L, List<String[]> rows, int size, String name){
        DataGenerator generator = new DataGenerator(0, size-1, 123123); //Min, max, seed
        //Esto generará un número random para el index del addBefore y el addAfter
        int index = generator.next();
        int dato = generator.next();
        
        //Llenamos full la list para hacer las mediciones en estructuras llenas:
        for(int i=0; i<size/2; i++) L.pushFront(generator.next());
        
        long t = measure(()->{
            L.pushFront(index);
        });
        rows.add(row(name, "PushFront", size, t));
        
        for(int i=size/2; i<size; i++) L.pushFront(generator.next());

        t = measure(()->{
            L.pushBack(generator.next());
        });
        rows.add(row(name, "PushBack", size, t));
        
        t = measure(()->{
            bh.consume(L.isEmpty());
        });
        rows.add(row(name, "isEmpty", size, t));
        
        t = measure(()->{
            L.addAfter(index, dato);
        });
        rows.add(row(name, "AddAfter", size, t));
        
        t = measure(()->{
            L.addBefore(index, dato);
        });
        rows.add(row(name, "AddBefore", size, t));
        
        t = measure(()->{
            L.find(index);
        });
        rows.add(row(name, "Find", size, t));
        
        t = measure(()->{
            //Pasamos un elemento que sí o sí debe estar
            L.erase(index); 
        });
        rows.add(row(name, "Erase", size, t));
        
        t = measure(()->{
            bh.consume(L.popFront());
        });
        rows.add(row(name, "PopFront", size, t));
        
        t = measure(()->{
            bh.consume(L.popBack());
        });
        rows.add(row(name, "PopBack", size, t));
    }

    private static void testQueue (MyQueue mq, List<String[]> rows, int size){
        DataGenerator generator = new DataGenerator(0, size-1, 123123); //Min, max, seed
        int dato = generator.next();
        
        //Llenamos la queue:
        for(int i=1; i<size/2; i++) mq.enqueue(generator.next());

        long t = measure(()->{
            mq.enqueue(dato);
        });
        rows.add(row("Queue", "Enqueue", size, t));
        
        for(int i=size/2; i<size; i++) mq.enqueue(generator.next());

        t = measure(()->{
            bh.consume(mq.dequeue());
        });
        rows.add(row("Queue", "Dequeue", size, t));
        
        t = measure(()->{
            mq.delete(dato);
        });
        rows.add(row("Queue", "Delete", size, t));
        
        //Lo dejo como front porque así aparece en el doc guía
        t = measure(()->{
            bh.consume(mq.peek());
        });
        rows.add(row("Queue", "Front", size, t));
        
        t = measure(()->{
            bh.consume(mq.size());
        });
        rows.add(row("Queue", "Size", size, t));
        
        t = measure(()->{
            bh.consume(mq.isEmpty());
        });
        rows.add(row("Queue", "isEmpty", size, t));
    }

    private static void testStack (MyStack ms, List<String[]> rows, int size){
        DataGenerator generator = new DataGenerator(0, size-1, 123123); //Min, max, seed
        int dato = generator.next();
        
        //Llenamos la queue:
        for(int i=1; i<size/2; i++) ms.push(generator.next());

        long t = measure(()->{
            ms.push(dato);
        });
        rows.add(row("Stack", "Push", size, t));
        
        for(int i=size/2; i<size; i++) ms.push(generator.next());
        
        t = measure(()->{
            bh.consume(ms.pop());
        });
        rows.add(row("Stack", "Pop", size, t));
        
        t = measure(()->{
            ms.delete(dato);
        });
        rows.add(row("Stack", "Delete", size, t));
        
        t = measure(()->{
            bh.consume(ms.peek());
        });
        rows.add(row("Stack", "Peek", size, t));
        
        t = measure(()->{
            bh.consume(ms.size());
        });
        rows.add(row("Stack", "Size", size, t));
        
        t = measure(()->{
            bh.consume(ms.isEmpty());
        });
        rows.add(row("Stack", "isEmpty", size, t));
    }

    public static void main(String[]args) throws IOException {
        //Diferentes tamaños a probar
        int []sizes = {10, 50, 75, 100, 500, 750, 1000, 5000, 7500, 10000, 50000, 75000, 100000, 500000, 750000,
                      1000000, 5000000, 7500000, 10000000, 50000000, 75000000, 100000000}; 
        
        //Esta es la dirección en que se van a guardar los resultados
        String csvPath = "results.csv";
        
        //Aquí almacenamos la info
        List<String[]>rows;
        
        //El orden en que lo vamos a hacer es importa. Vamos de estructura en estructura
        for(int size: sizes){
            rows = new ArrayList<>(); //limpiamos arraylist en cada pasada
            
            //Single Linked List No Tail 
            LinkedList<Integer> sll = new LinkedList<>();
            testLinkedList (sll, rows, size, "SingleLLNoTail");
            sll = null;
            System.gc();

            //Single Linked List With Tail        
            LinkedListWithTail<Integer> sllt = new LinkedListWithTail<>();
            testLinkedList (sllt, rows, size, "SingleLLWithTail");
            sllt = null;
            System.gc();

            //Doubly Linked List No Tail
            DoublyLinkedNoTail<Integer> dll = new DoublyLinkedNoTail<>();
            testLinkedList (dll, rows, size, "DoublyLLNoTail");
            dll = null;
            System.gc();

            //Doubly Linked List With Tail
            DoublyLinked<Integer> dllt = new DoublyLinked<>();
            testLinkedList (dllt, rows, size, "DoublyLLWithTail");
            dllt = null;
            System.gc();

            //MyQueue
            MyQueue<Integer> mq = new MyQueue<>();
            testQueue(mq, rows, size);
            mq = null;
            System.gc();

            //Stack
            MyStack<Integer> ms = new MyStack<>();
            testStack(ms, rows, size);    
            ms = null;
            System.gc();

            //Exportamos la info que quedó contenido en rows.
            CsvExporter.exportar(rows, csvPath);
            System.out.printf("Se genero data para, %d\n",size);
        }
    }
    
}
