package turlang;
public class TurlangError extends RuntimeException {

    public TurlangError(String message) {
        super(message);
    }
    
    public static class StatelessTransitionError
            extends TurlangError {
            
        public StatelessTransitionError(int line) {
            super("State body with no state on line " +
                    Integer.toString(line));
        }
    }
    
    public static class MemoryOverflowError
            extends TurlangError {
        public MemoryOverflowError() {
            super("Memory limit exceeded.");
        }
    }
    
    public static class UndefinedStateError
            extends TurlangError {
        public UndefinedStateError(String label) {
            super("Undefined state: " + label);
        }
    }
}
