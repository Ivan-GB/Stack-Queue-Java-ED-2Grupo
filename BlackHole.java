//Esta clase existe sólo para evitar que se puedan dañar los datos que medimos por el JVM (Java Virtual MAchine)
public class BlackHole{
    //Estos son los tres tipos que nos pueden dar los métodos de las DS
    private volatile int intNP;
    private volatile boolean BoolNP;
    private volatile Object ObjectNP;
    public void consume(int c){ intNP = c;}
    public void consume(boolean c){BoolNP = c;}
    public void consume(Object c){ObjectNP = c;}
}
