package turlang;
import turlang.TurlangError.MemoryOverflowError;
import java.util.List;
import java.util.ArrayList;

public class Tape {

    private List<String> tape;
    private int head;
    private int maxLength;
    
    public static final String END = "end";
    public static final String BAZ = "baz";
    public static final String NEWLINE = "newline";
    public static final String SPACE = "space";
    public static final String TAB = "tab";

    public Tape(String input, int length) {
        if (input.length() > length) {
            throw new MemoryOverflowError();
        }
        this.tape = new ArrayList<>();
        this.tape.add(END);
        for (String s : input.split("")) {
            this.tape.add(s);
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
    
    public String read() {
        if (this.head >= 0) {
            return this.tape.get(this.head);
        }
        else {
            return END;
        }
    }
    
    public void write(String s) {
        if (this.head >= 0) {
            this.tape.set(this.head, s);
        }
        else {
            this.tape.add(0, END);
            this.head++;
            this.write(s);
        }
    }              
}

