//Es más óptimo con una clase espec+ifica porque vamos a generar hasta 10**8 datos
import java.util.Random;
public class DataGenerator{
    private Random ran;
    private int min;
    private int max;
    //La seed es para que todos las DS tengan una misma posibilidad de obtener los datos
    public DataGenerator(int mi, int max, long seed){
        min = mi;
        this.max = max;
        ran =  new Random(seed);
    }
    public int[] generate(int n){
        int[] data = new int[n];
        for(int i=0; i<n; i++){
            data[i] = ran.nextInt((max-min)+1)+min;
        }
        return data;
    }
    
    public int next(){
        return ran.nextInt((max-min)+1)+min;
    }
}
