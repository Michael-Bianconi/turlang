package turlang;
import turlang.State;
import turlang.TurlangError.StatelessTransitionError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

public class Parser {

    private final static String COMMENTSTARTREGEX = "(?i)^comment$";
    private final static String COMMENTENDREGEX = "(?i)^endcomment$";
    private final static String STATEHEADREGEX = "^\\S+:$";
    private final static String STATEBODYREGEX =
        "(?i)^(\\S|any|end|space|newline|tab|baz)\\s+->" +
        "\\s+(\\S|none|space|newline|tab|baz)" +
        "\\s+(left|right)" +
        "\\s+\\S+$";

    public static List<State> parse(File tlfile)
            throws FileNotFoundException, IOException {
            
        // Prep the file for reading
        Charset enc = Charset.defaultCharset();
        InputStream inStream = new FileInputStream(tlfile);
        Reader reader = new InputStreamReader(inStream, enc);
        BufferedReader turlang = new BufferedReader(reader);
        boolean incomment = false;
        String line = "";
        State activeState = null;
        List<State> states = new ArrayList<>();
        int linecount = 0;

        while ((line = turlang.readLine()) != null) {
        
            line = line.trim();
            linecount++;
            
            if (incomment && line.matches(COMMENTENDREGEX)) {
                incomment = false;
            }
            else if (incomment || line.matches("")) {
                continue;
            }
            else if (line.matches(COMMENTSTARTREGEX)) {
                incomment = true;
            }
            else if (line.matches(STATEHEADREGEX)) {
                String label = line.substring(0,line.length()-1);
                activeState = new State(label);
                states.add(activeState);
            }
            else if (line.matches(STATEBODYREGEX)) {
                if (activeState == null) {
                    throw new StatelessTransitionError(linecount);
                }
                String[] tokens = line.split("\\s+");
                String in = tokens[0];
                String out = tokens[2];
                String tape = tokens[3];
                String go = tokens[4];
                if (!activeState.addTransition(in,out,tape,go)) {
                    throw new TurlangError("Error on line: " + linecount);
                }
            }
            else {
                System.out.println("Error: " + line);
            }
        }
        
        return states;
    }
    
    public static void main(String[] args) {
        try {
            List<State> states = parse(new File(args[0]));
            for (State s : states) s.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
