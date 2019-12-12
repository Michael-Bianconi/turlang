package turlang;

import turlang.State;
import turlang.TurlangError;
import turlang.TurlangError.*;
import turlang.Tape;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.Charset;

public class Turlang {

    private final static String COMMENTREGEX = "(?i)^//.*$";
    private final static String STATEHEADREGEX = "^\\S+:$";
    private final static String STATEBODYREGEX =
        "(?i)^(\\S|any|end|space|newline|tab|baz)\\s+->" +
        "\\s+(\\S|none|space|newline|tab|baz)" +
        "\\s+(left|right)" +
        "\\s+\\S+$";

    private static List<State> parse(File tlfile)
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
            
            if (line.matches("") || line.matches(COMMENTREGEX)) {
                continue;
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

    private static boolean simulate(
            List<State> states,
            State active,
            Tape tape) {
        
        while (true) {
            String c = tape.read();
            String next = active.transition(c, tape);
            if (next == null) return false;
            if (next.toLowerCase().equals("accept")) return true;
            if (next.toLowerCase().equals("reject")) return false;
            active = findState(states, next);
            if (active == null) {
                throw new UndefinedStateError(next);
            }
        }
    }
    
    private static State findState(List<State> states, String label) {
        for (State s : states) {
            if (s.getLabel().equals(label)) return s;
        }
        return null;
    }
    
    public static void main(String[] args) {
        
        try {
        
            List<State> states = parse(new File(args[0]));
            Tape tape = new Tape(args[1], 1024);
            boolean result = simulate(states, states.get(0), tape);
            System.out.println(new Boolean(result));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
