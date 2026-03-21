//No importamos ningún paquete porque todas las clases las metimos dentro de la misma carpeta
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter{
    public static void exportar(List<String[]>rows, String filePath) throws IOException{
        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath))){
        //Encabezado de la csv. Lo voy a poner sin tildes porque no quiero que se corrompa ese parcero. Póngalas bajo su propio riesgo
        pw.println("DS_Name, Operacion, tamanoDatos, Tiempo_Ejecucion_NanoSeg");
        for(String[] row : rows) pw.println(String.join(",", row));
        }
        System.out.println("Los datos se exportaron a: " + filePath);
    }
}
