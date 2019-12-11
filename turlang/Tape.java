package turlang;
import turlang.TurlangError.MemoryOverflowError;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Tape {

    private List<Character> tape;
    private int head;
    private int maxLength;
    
    public static final Character END = new Character((char) 0);
    public static final Character BAZ = new Character((char) 6);
    public static final Character NEWLINE = new Character('\n');
    public static final Character SPACE = new Character(' ');
    public static final Character TAB = new Character('\t');

    public Tape(String input, int length) {
        if (input.length() > length) {
            throw new MemoryOverflowError();
        }
        this.tape = new ArrayList<>();
        this.tape.add(END);
        for (char c : input.toCharArray()) {
            this.tape.add(new Character(c));
        }
        this.tape.add(END);
        this.head = 1;
        this.maxLength = length;
    }
    
    public void moveLeft() {
        this.head--;
        if (this.head < 0) {
            if (this.tape.size() - this.head > this.maxLength) {
                throw new MemoryOverflowError();
            }
        }
    }
    
    public void moveRight() {
        this.head++;
        if (this.head > 0) {
            if (this.tape.size() + this.head > this.maxLength) {
                throw new MemoryOverflowError();
            }
        }
    }
    
    public Character read() {
        if (this.head >= 0) {
            return this.tape.get(this.head);
        }
        else {
            return END;
        }
    }
    
    public void write(Character c) {
        if (this.head >= 0) {
            this.tape.set(this.head, c);
        }
        else {
            this.tape.add(0, END);
            this.head++;
            this.write(c);
        }
    }
    
    public static Character stringToCharacter(String s) {
        if (s.toLowerCase().equals("end")) return Tape.END;
        if (s.toLowerCase().equals("baz")) return Tape.BAZ;
        if (s.toLowerCase().equals("newline")) return Tape.NEWLINE;
        if (s.toLowerCase().equals("space")) return Tape.SPACE;
        if (s.toLowerCase().equals("tab")) return Tape.TAB;
        if (s.length() > 1) throw new IllegalArgumentException();
        return new Character(s.toCharArray()[0]);
    }
    
    public static String characterToString(Character c) {
        if (c.equals(Tape.END)) return "end";
        if (c.equals(Tape.BAZ)) return "baz";
        if (c.equals(Tape.NEWLINE)) return "newline";
        if (c.equals(Tape.SPACE)) return "space";
        if (c.equals(Tape.TAB)) return "tab";
        else return c.toString();
    }                
}

