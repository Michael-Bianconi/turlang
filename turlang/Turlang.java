package turlang;

import turlang.State;
import turlang.Parser;
import turlang.TurlangError;
import turlang.TurlangError.UndefinedStateError;
import turlang.Tape;
import java.util.List;
import java.io.File;

public class Turlang {

    private static boolean simulate(
            List<State> states,
            State active,
            Tape tape) {
        
        while (true) {
            Character c = tape.read();
            String next = active.transition(c, tape);
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
        
            List<State> states = Parser.parse(new File(args[0]));
            Tape tape = new Tape(args[1], 1024);
            boolean result = simulate(states, states.get(0), tape);
            //System.out.println(new Boolean(result));
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
