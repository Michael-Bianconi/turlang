package turlang;
import turlang.Tape;
import java.util.Map;
import java.util.HashMap;

public class State {
    
    private String label;
    private Map<String,String[]> transitions;
    
    public State(String label) {
        this.label = label;
        this.transitions = new HashMap<>();
    }
    
    public String getLabel() {return this.label;}
    
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
    
    public String transition(Character c, Tape tape) {

        String s = Tape.characterToString(c);
        
        if (!s.equals("end") && this.transitions.containsKey("any")) {
            s = "any"; 
        }

        if (this.transitions.containsKey(s)) {
            String[] t = this.transitions.get(s);
            tape.write(Tape.stringToCharacter(t[0]));
            if (t[1].equals("left")) tape.moveLeft();
            else if (t[1].equals("right")) tape.moveRight();
            return t[2];
        }
        return null;
    }
    
    public void print() {
        System.out.println(this.label + ":");
        for (Map.Entry<String,String[]> entry : this.transitions.entrySet()) {
            String in = entry.getKey();
            String out = entry.getValue()[0];
            String tape = entry.getValue()[1].toLowerCase();
            String go = entry.getValue()[2];
            if (in.length() > 1) in = in.toLowerCase();
            if (out.length() > 1) out = out.toLowerCase();
            String str = String.format("\t%s -> %s %s %s", in, out, tape, go);
            System.out.println(str);
        }
    }
}
