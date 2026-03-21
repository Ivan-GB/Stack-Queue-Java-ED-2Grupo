import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Main{
    //Este parcero nos devuelve el tiempo de ejecución
    public static long measure(Runnable task){
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }
    public static void main(String[]args) throws IOException {
        //Aquí ponemos los que necesitamos. Pongo datos temporales para las pruebas
        int []sizes = {1000, 10000, 100000, 1000000};
        //Este es para no nos haga trampas el compilador por no usar los returns
        BlackHole bh = new BlackHole();
        
        //Esta es la dirección en que se van a guardar los resultados
        String csvPath = "results.csv";
        
        //Min, max, seed. Se pueden cambiar. Los puse con esos valores para saber si funciona
        DataGenerator gen = new DataGenerator(1, 750, 330020108);
        //Vamos a pregenerar los datos para que no contaminen la data. La key es el sie el value una lista con los valores
        Map<Integer, int[]>pregenData = new HashMap<>();
        for(int size: sizes){
            pregenData.put(size, gen.generate(size));
        }
        //Aquí almacenamos la info
        
        List<String[]>rows = new ArrayList<>();
        
        //El orden en que lo vamos a hacer es importa. Vamos de estrcutura en estructura
        for(int size: sizes){
            //Esto generará un número random para el index del addBeore y el addAfter
            DataGenerator indexes = new DataGenerator(0, size-1, 323213);
            int ind = indexes.next(); 
            int dato = indexes.next();
            int[]data = pregenData.get(size);
            
            //Single Linked List No Tail
            
            LinkedList<Integer> sll = new LinkedList<>();
            //push front. Acá estamos haciendo un push front al sll
            long t = measure(()->{
                //Sólo agregamos uno. Si se quiere contabilizar cuánto se demora con los n del size toca meterlo dentro del for
                //for(int i=0; i<size; i++) 
                sll.pushFront(data[0]);
            });
            rows.add(row("SingleLLNoTail", "PushFront", size, t));
            
            //Llenamos full la list para hacer luego los otros métodos:
            for(int i=1; i<size; i++) sll.pushFront(i);
            //El último elemento se repite dos veces
            long t2 = measure(()->{
                sll.pushBack(data[size-1]);
            });
            rows.add(row("SingleLLNOTail", "PushBack", size, t2));
            //sll llena del paso anterior. 
            long t3 = measure(()->{
                //Si es toca hacer más mediciones que solo una única instancia, hay que poner el ciclo for
                //for(int i=0; i<size; i++)
                bh.consume(sll.isEmpty());
            });
            rows.add(row("SingleLLNoTail", "isEmpty", size, t3));
            
            long t4 = measure(()->{
                sll.addAfter(data[ind], dato);
            });
            rows.add(row("SingleLLNoTail", "AddAfter", size, t4));
            
            long t5 = measure(()->{
                sll.addBefore(data[ind], dato);
            });
            rows.add(row("SingleLLNoTail", "AddBefore", size, t5));
            
            long t6 = measure(()->{
                sll.find(data[ind]);
            });
            rows.add(row("SingleLLNoTail", "Find", size, t6));
            
            long t7 = measure(()->{
                //Pasamos un elemento que sí o sí debe estar
               sll.erase(data[ind]); 
            });
            rows.add(row("SingleLLNoTail", "Erase", size, t7));
            
            long t8 = measure(()->{
                bh.consume(sll.popFront());
            });
            rows.add(row("SingleLLNoTail", "PopFront", size, t8));
            
            long t9 = measure(()->{
                bh.consume(sll.popBack());
            });
            rows.add(row("SingleLLNoTail", "PopBack", size, t9));
            
            //Single Linked List With Tail
            
            LinkedListWithTail<Integer> llst = new LinkedListWithTail<>();
            t = measure(()->{
            //Sólo agregamos uno. Si se quiere contabilizar cuánto se demora con los n del size toca meterlo dentro del for
            //for(int i=0; i<size; i++) 
            llst.pushFront(data[0]);
            });
            rows.add(row("SingleLLWithTail", "PushFront", size, t));
            
            //Llenamos full la list para hacer luego los otros métodos:
            for(int i=1; i<size; i++) llst.pushFront(i);
            //El último elemento se repite dos veces
            t2 = measure(()->{
                llst.pushBack(data[size-1]);
            });
            rows.add(row("SingleLLWithTail", "PushBack", size, t2));
            //sll llena del paso anterior. 
            t3 = measure(()->{
                //Si es toca hacer más mediciones que solo una única instancia, hay que poner el ciclo for
                //for(int i=0; i<size; i++)
                bh.consume(llst.isEmpty());
            });
            rows.add(row("SingleLLWithTail", "isEmpty", size, t3));
            
            t4 = measure(()->{
                llst.addAfter(data[ind], dato);
            });
            rows.add(row("SingleLLWithTail", "AddAfter", size, t4));
            
            t5 = measure(()->{
                llst.addBefore(data[ind], dato);
            });
            rows.add(row("SingleLLWithTail", "AddBefore", size, t5));
            
            t6 = measure(()->{
                llst.find(data[ind]);
            });
            rows.add(row("SingleLLWithTail", "Find", size, t6));
            
            t7 = measure(()->{
                //Pasamos un elemento que sí o sí debe estar
               llst.erase(data[ind]); 
            });
            rows.add(row("SingleLLWithTail", "Erase", size, t7));
            
            t8 = measure(()->{
                bh.consume(llst.popFront());
            });
            rows.add(row("SingleLLWithTail", "PopFront", size, t8));
            
            t9 = measure(()->{
                bh.consume(llst.popBack());
            });
            rows.add(row("SingleLLWithTail", "PopBack", size, t9));
            
            //Doubly Linked List No Tail
            
            DoublyLinkedNoTail<Integer> dll = new DoublyLinkedNoTail<>();
            t = measure(()->{
            //Sólo agregamos uno. Si se quiere contabilizar cuánto se demora con los n del size toca meterlo dentro del for
            //for(int i=0; i<size; i++) 
                dll.pushFront(data[0]);
            });
            rows.add(row("DoublyLLNoTail", "PushFront", size, t));
            
            //Llenamos full la list para hacer luego los otros métodos:
            for(int i=1; i<size; i++) dll.pushFront(i);
            //El último elemento se repite dos veces
            t2 = measure(()->{
                dll.pushBack(data[size-1]);
            });
            rows.add(row("DoublyLLNoTail", "PushBack", size, t2));
            //dll llena del paso anterior. 
            t3 = measure(()->{
                //Si es toca hacer más mediciones que solo una única instancia, hay que poner el ciclo for
                //for(int i=0; i<size; i++)
                bh.consume(dll.isEmpty());
            });
            rows.add(row("DoublyLLNoTail", "isEmpty", size, t3));
            
            t4 = measure(()->{
                dll.addAfter(data[ind], dato);
            });
            rows.add(row("DoublyLLNoTail", "AddAfter", size, t4));
            
            t5 = measure(()->{
                dll.addBefore(data[ind], dato);
            });
            rows.add(row("DoublyLLNoTail", "AddBefore", size, t5));
            
            t6 = measure(()->{
                dll.find(data[ind]);
            });
            rows.add(row("DoublyLLNoTail", "Find", size, t6));
            
            t7 = measure(()->{
                //Pasamos un elemento que sí o sí debe estar
               dll.erase(data[ind]); 
            });
            rows.add(row("DoublyLLNoTail", "Erase", size, t7));
            
            t8 = measure(()->{
                bh.consume(dll.popFront());
            });
            rows.add(row("DoublyLLNoTail", "PopFront", size, t8));
            
            t9 = measure(()->{
                bh.consume(dll.popBack());
            });
            rows.add(row("DoublyLLNoTail", "PopBack", size, t9));
            
            //Doubly Linked List With Tail
            
            DoublyLinked<Integer> dllt = new DoublyLinked<>();
            
            t = measure(()->{
            //Sólo agregamos uno. Si se quiere contabilizar cuánto se demora con los n del size toca meterlo dentro del for
            //for(int i=0; i<size; i++) 
                dllt.pushFront(data[0]);
            });
            rows.add(row("DoublyLLWithTail", "PushFront", size, t));
            
            //Llenamos full la list para hacer luego los otros métodos:
            for(int i=1; i<size; i++) dllt.pushFront(i);
            //El último elemento se repite dos veces
            t2 = measure(()->{
                dllt.pushBack(data[size-1]);
            });
            rows.add(row("DoublyLLWithTail", "PushBack", size, t2));
            //dll llena del paso anterior. 
            t3 = measure(()->{
                //Si es toca hacer más mediciones que solo una única instancia, hay que poner el ciclo for
                //for(int i=0; i<size; i++)
                bh.consume(dllt.isEmpty());
            });
            rows.add(row("DoublyLLWithTail", "isEmpty", size, t3));
            
            t4 = measure(()->{
                dllt.addAfter(data[ind], dato);
            });
            rows.add(row("DoublyLLWithTail", "AddAfter", size, t4));
            
            t5 = measure(()->{
                dllt.addBefore(data[ind], dato);
            });
            rows.add(row("DoublyLLWithTail", "AddBefore", size, t5));
            
            t6 = measure(()->{
                dllt.find(data[ind]);
            });
            rows.add(row("DoublyLLWithTail", "Find", size, t6));
            
            t7 = measure(()->{
                //Pasamos un elemento que sí o sí debe estar
               dllt.erase(data[ind]); 
            });
            rows.add(row("DoublyLLWithTail", "Erase", size, t7));
            
            t8 = measure(()->{
                bh.consume(dllt.popFront());
            });
            rows.add(row("DoublyLLWithTail", "PopFront", size, t8));
            
            t9 = measure(()->{
                bh.consume(dllt.popBack());
            });
            rows.add(row("DoublyLLWithTail", "PopBack", size, t9));
            
            //MyQueue
            
            MyQueue<Integer> mq = new MyQueue<>();
            
            t = measure(()->{
                mq.enqueue(data[0]);
            });
            rows.add(row("MyQueue", "Enqueue", size, t));
            
            //Llenamos la queue:
            for(int i=1; i<size; i++) mq.enqueue(data[i]);
            
            t2 = measure(()->{
               bh.consume(mq.dequeue());
            });
            rows.add(row("MyQueue", "Dequeue", size, t2));
            
            t3 = measure(()->{
                mq.delete(data[ind]);
            });
            rows.add(row("MyQueue", "Delete", size, t3));
            
            //Lo dejo como front porque así aparece en el doc guía
            t4 = measure(()->{
                bh.consume(mq.peek());
            });
            rows.add(row("MyQueue", "Front", size, t4));
            
            t5 = measure(()->{
                bh.consume(mq.size());
            });
            rows.add(row("MyQueue", "Size", size, t5));
            
            t6 = measure(()->{
                bh.consume(mq.isEmpty());
            });
            rows.add(row("MyQueue", "isEmpty", size, t6));

            //MyStack
            
            MyStack<Integer> ms = new MyStack<>();
            
            t = measure(()->{
                ms.push(data[0]);
            });
            rows.add(row("MyStack", "Push", size, t));
            
            //Llenamos la queue:
            for(int i=1; i<size; i++) ms.push(data[i]);
            
            t2 = measure(()->{
               bh.consume(ms.pop());
            });
            rows.add(row("MyStack", "Pop", size, t2));
            
            t3 = measure(()->{
                ms.delete(data[ind]);
            });
            rows.add(row("MyStack", "Delete", size, t3));
            
            t4 = measure(()->{
                bh.consume(ms.peek());
            });
            rows.add(row("MyStack", "Peek", size, t4));
            
            t5 = measure(()->{
                bh.consume(ms.size());
            });
            rows.add(row("MyStack", "Size", size, t5));
            
            t6 = measure(()->{
                bh.consume(ms.isEmpty());
            });
            rows.add(row("MyStack", "isEmpty", size, t6));
        }
        
        //Exportamos la info que quedó contenido en rows. La path se cambia desde la variable que está al inicio
        CsvExporter.exportar(rows, csvPath);
    }
    
    private static String[] row(String DS, String op, int size, long nanoTime){
        return new String[]{DS, op, String.valueOf(size), String.valueOf(nanoTime)};
    }
}
