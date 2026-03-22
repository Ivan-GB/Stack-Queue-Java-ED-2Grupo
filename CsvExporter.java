import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter{
    public static void exportar(List<String[]>rows, String filePath) throws IOException{
        File file = new File(filePath);
        boolean writeHeader = !file.exists() || file.length() == 0;

        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath, true))){
            if (writeHeader) {
                pw.println("DS_Name, Operacion, TamanoDatos, Tiempo_Ejecucion_ns");
            }
            for(String[] row : rows) pw.println(String.join(",", row));
        }
    }
}
