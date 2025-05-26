

public class Cell {

    private static final char WHITE = 'w';
    private static final char BLACK = 'b';
    private static final char EMPTY = '·';

    private char state;

    private Cell(char state) {
        this.state = state;
        //throw new UnsupportedOperationException("Step 3");
    }

    public static Cell empty() {
        return new Cell(EMPTY);
        //throw new UnsupportedOperationException("Step 3");
    }

    public static Cell white() {
        return new Cell(WHITE);
        //throw new UnsupportedOperationException("Step 3");
    }

    public static Cell black() {
        return new Cell(BLACK);
        //throw new UnsupportedOperationException("Step 3");
    }

    public boolean isEmpty() {
        return state == EMPTY;
        //throw new UnsupportedOperationException("Step 3");
    }

    public boolean isWhite() {
        return state == WHITE;
        //throw new UnsupportedOperationException("Step 3");
    }

    public boolean isBlack() {
        return state == BLACK;
        //throw new UnsupportedOperationException("Step 3");
    }

    public void setWhite() {
        this.state = WHITE;
        //throw new UnsupportedOperationException("Step 3");
    }

    public void setBlack() {
        this.state = BLACK;
        //throw new UnsupportedOperationException("Step 3");
    }

    public void reverse() {
        if(this.state == WHITE){
            this.state = BLACK;
        }else if(this.state == BLACK){
            this.state = WHITE;
        }
        //throw new UnsupportedOperationException("Step 3");
    }

    public String toString() {
        return String.valueOf(this.state);
    }

    public static Cell cellFromChar(char c) {
        return switch (c) {
            case 'w' -> white();
            case 'b' -> black();
            default -> empty();
        };
    }
}
