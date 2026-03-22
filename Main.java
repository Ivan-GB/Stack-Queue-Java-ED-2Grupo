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

    private static void testLinkedList (LinkedLists L, int[] data, int index, int dato, List<String[]> rows, int size, String name){
        //Llenamos full la list para hacer las mediciones en estructuras llenas:
        for(int i=0; i<size; i++) L.pushFront(data[i]);
       
        long t = measure(()->{
            L.pushFront(data[0]);
        });
        rows.add(row(name, "PushFront", size, t));
        
        t = measure(()->{
            L.pushBack(data[size-1]);
        });
        rows.add(row(name, "PushBack", size, t));
        
        t = measure(()->{
            bh.consume(L.isEmpty());
        });
        rows.add(row(name, "isEmpty", size, t));
        
        t = measure(()->{
            L.addAfter(data[index], dato);
        });
        rows.add(row(name, "AddAfter", size, t));
        
        t = measure(()->{
            L.addBefore(data[index], dato);
        });
        rows.add(row(name, "AddBefore", size, t));
        
        t = measure(()->{
            L.find(data[index]);
        });
        rows.add(row(name, "Find", size, t));
        
        t = measure(()->{
            //Pasamos un elemento que sí o sí debe estar
            L.erase(data[index]); 
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

    public static void main(String[]args) throws IOException {
        //Diferentes tamaños a probar
        int []sizes = {1000, 10000, 100000, 1000000, 10000000, 100000000};
        
        //Esta es la dirección en que se van a guardar los resultados
        String csvPath = "results.csv";
        
        //Aquí almacenamos la info
        List<String[]>rows;
        
        //El orden en que lo vamos a hacer es importa. Vamos de estructura en estructura
        for(int size: sizes){
            rows = new ArrayList<>(); //limpiamos arraylist en cada pasada

            DataGenerator generator = new DataGenerator(0, size-1, 323213); //Min, max, seed
            int[]data = generator.generate(size);
            //Esto generará un número random para el index del addBefore y el addAfter
            int ind = generator.next();
            int dato = generator.next();
            
            //Single Linked List No Tail 
            LinkedList<Integer> sll = new LinkedList<>();
            
            testLinkedList (sll, data, ind, dato, rows, size, "SingleLLNoTail");
            
            //Single Linked List With Tail        
            LinkedListWithTail<Integer> sllt = new LinkedListWithTail<>();
            testLinkedList (sllt, data, ind, dato, rows, size, "SingleLLWithTail");
               
            //Doubly Linked List No Tail
            DoublyLinkedNoTail<Integer> dll = new DoublyLinkedNoTail<>();
            testLinkedList (dll, data, ind, dato, rows, size, "DoublyLLNoTail");
            
            //Doubly Linked List With Tail
            DoublyLinked<Integer> dllt = new DoublyLinked<>();
            testLinkedList (dll, data, ind, dato, rows, size, "DoublyLLWithTail");
            
            //MyQueue
            MyQueue<Integer> mq = new MyQueue<>();
            
            long t = measure(()->{
                mq.enqueue(data[0]);
            });
            rows.add(row("MyQueue", "Enqueue", size, t));
            
            //Llenamos la queue:
            for(int i=1; i<size; i++) mq.enqueue(data[i]);
            
            t = measure(()->{
               bh.consume(mq.dequeue());
            });
            rows.add(row("MyQueue", "Dequeue", size, t));
            
            t = measure(()->{
                mq.delete(data[ind]);
            });
            rows.add(row("MyQueue", "Delete", size, t));
            
            //Lo dejo como front porque así aparece en el doc guía
            t = measure(()->{
                bh.consume(mq.peek());
            });
            rows.add(row("MyQueue", "Front", size, t));
            
            t = measure(()->{
                bh.consume(mq.size());
            });
            rows.add(row("MyQueue", "Size", size, t));
            
            t = measure(()->{
                bh.consume(mq.isEmpty());
            });
            rows.add(row("MyQueue", "isEmpty", size, t));

            //Stack
            MyStack<Integer> ms = new MyStack<>();
            
            t = measure(()->{
                ms.push(data[0]);
            });
            rows.add(row("MyStack", "Push", size, t));
            
            //Llenamos la queue:
            for(int i=1; i<size; i++) ms.push(data[i]);
            
            t = measure(()->{
               bh.consume(ms.pop());
            });
            rows.add(row("MyStack", "Pop", size, t));
            
            t = measure(()->{
                ms.delete(data[ind]);
            });
            rows.add(row("MyStack", "Delete", size, t));
            
            t = measure(()->{
                bh.consume(ms.peek());
            });
            rows.add(row("MyStack", "Peek", size, t));
            
            t = measure(()->{
                bh.consume(ms.size());
            });
            rows.add(row("MyStack", "Size", size, t));
            
            t = measure(()->{
                bh.consume(ms.isEmpty());
            });
            rows.add(row("MyStack", "isEmpty", size, t));

            //Exportamos la info que quedó contenido en rows.
            CsvExporter.exportar(rows, csvPath);
        }
    }
    
}
