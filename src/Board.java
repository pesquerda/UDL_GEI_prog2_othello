
import java.util.StringTokenizer;

public class Board {
    private final Cell[][] cells;
    private final Display display;
    private final int order;

    private int black;
    private int white;

    public Board(int order, Display display) {
        this.order = order;
        this.cells = new Cell[2*order][2*order];
        this.black = 0;
        this.white = 0;
        this.display = display;
        initBoard();
    }

    public Board(int order) {
        this(order, null);
    }

    private void initBoard() {
        for(int row = 0; row < size(); row++){
            for(int col = 0; col < size(); col++){
                cells[row][col] = Cell.empty();
            }
        }

        int mid = size() / 2;

        if(display != null) {

            setWhite(new Position(mid -1, mid -1));
            setBlack(new Position(mid -1, mid));
            setBlack(new Position(mid, mid -1));
            setWhite(new Position(mid, mid));

        }
        black = 2;
        white = 2;
        //throw new UnsupportedOperationException("Step 5.1");
    }

    public int size() {
        return 2 * order;
    }

    public boolean contains(Position position) {
        int row = position.getRow();
        int col = position.getColumn();
        return row >= 0 && row < size() && col >= 0 && col < size();
        //throw new UnsupportedOperationException("Step 5.1");
    }

    public boolean isEmpty(Position position) {
        if(!contains(position)){
            return false;
        }else{
            return cells[position.getRow()][position.getColumn()].isEmpty();
        }
        //throw new UnsupportedOperationException("Step 5.1");
    }

    public boolean isWhite(Position position) {
        if(!contains(position)){
            return false;
        }else{
            return cells[position.getRow()][position.getColumn()].isWhite();
        }
        //throw new UnsupportedOperationException("Step 5.1");
    }

    public boolean isBlack(Position position) {
        if(!contains(position)){
            return false;
        }else {
            return cells[position.getRow()][position.getColumn()].isBlack();
        }
        //throw new UnsupportedOperationException("Step 5.1");
    }

    public void setWhite(Position position) {
        if(contains(position) && isEmpty(position)){
            cells[position.getRow()][position.getColumn()].setWhite();
            white++;
        }else{
            return;
        }

        if (display != null) display.setWhite(position);
        //throw new UnsupportedOperationException("Step 5.2");
    }

    public void setBlack(Position position) {
        if(contains(position) && isEmpty(position)){
            cells[position.getRow()][position.getColumn()].setBlack();
            black++;
        }else{
            return;
        }

        if (display != null) display.setBlack(position);
        //throw new UnsupportedOperationException("Step 5.2");
    }

    public void reverse(Position position) {
        if(isBlack(position)){
            cells[position.getRow()][position.getColumn()].setWhite();
            black--;
            white++;
            if(display != null) display.setWhite(position);
        }else if(isWhite(position)){
            cells[position.getRow()][position.getColumn()].setBlack();
            black++;
            white--;
            if(display != null) display.setBlack(position);
        }
        //if (display != null) display.setWhite(position);
        //throw new UnsupportedOperationException("Step 5.2");
    }

    public void loadBoard(String str) {
        StringTokenizer st = new StringTokenizer(str, "\n");
        int row = 0;
        this.white = 0;
        this.black = 0;
        while (st.hasMoreTokens()) {
            String rowChars = st.nextToken();
            loadRow(this.cells[row], rowChars);
            row += 1;
        }
    }

    private void loadRow(Cell[] cellRow, String rowChars) {
        for (int column = 0; column < cellRow.length; column++) {
            Cell cell = Cell.cellFromChar(rowChars.charAt(column));
            cellRow[column] = cell;
            if (cell.isWhite()) white += 1;
            else if (cell.isBlack()) black += 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        for (Cell[] cell : this.cells) {
            for (Cell value : cell) {
                sb.append(value.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getStatus() {
        return String.format("B:%3d W:%3d", this.black, this.white);
    }
}
