package turlang;

import java.util.Map;
import java.util.HashMap;

public class State {
    
    private String label;
    private Map<String,String[]> transitions;
    
    public State(String label) {
        this.label = label;
        this.transitions = new HashMap<>();
    }
    
    public boolean addTransition(
            String in,
            String out,
            String tape,
            String go) {
            
        if (this.transitions.containsKey(in)
        || (this.transitions.containsKey("any")
        &&  !in.equals("end"))) {
            return false;
        }
        
        else {
            this.transitions.put(in, new String[] {out, tape, go});
            return true;
        }
    }
    
    public void print() {
        System.out.println(this.label + ":");
        for (Map.Entry<String,String[]> entry : this.transitions.entrySet()) {
            String in = entry.getKey();
            String out = entry.getValue()[0];
            String tape = entry.getValue()[1];
            String go = entry.getValue()[2];
            String str = String.format("\t%s -> %s %s %s", in, out, tape, go);
            System.out.println(str);
        }
    }
}
