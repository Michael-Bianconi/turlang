package turlang;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;

public class Parser {

    public static String parse(File tlfile)
            throws FileNotFoundException, IOException {
            
        Charset enc = Charset.defaultCharset();
        InputStream in = new FileInputStream(tlfile);
        Reader reader = new InputStreamReader(in, enc);
        BufferedReader turlang = new BufferedReader(reader);
        String line = "";
        
        while ((line = turlang.readLine()) != null) {
            String[] tokens = line.trim().split("[\\s+|,|->|:]");
            System.out.println(line);
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            parse(new File(args[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
