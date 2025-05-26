import acm.graphics.GDimension;
import acm.graphics.GPoint;

public class Geometry {

    private final int windowWidth;
    private final int windowHeight;
    private final int numCols;
    private final int numRows;
    private final double boardPadding;
    private final double cellPadding;

    public Geometry(int windowWidth, int windowHeight, int numCols, int numRows, double boardPadding, double cellPadding) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.numCols = numCols;
        this.numRows = numRows;
        this.boardPadding = boardPadding;
        this.cellPadding = cellPadding;
        //throw new UnsupportedOperationException("Step 4");
    }

    public int getRows() {
        return numRows;
        //throw new UnsupportedOperationException("Step 4");
    }

    public int getColumns() {
        return numCols;
        //throw new UnsupportedOperationException("Step 4");
    }

    public GDimension boardDimension() {
        double totalWidth = windowWidth * (1- 2*boardPadding);
        double totalHeigh = windowHeight * (1- 2*boardPadding);

        return new GDimension(totalWidth, totalHeigh);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GPoint boardTopLeft() {
        double x = boardPadding * windowWidth;
        double y = boardPadding * windowHeight;

        return new GPoint(x, y);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GDimension cellDimension() {
        double cellWidth = boardDimension().getWidth() / numCols;
        double cellHeight = boardDimension().getHeight() / numRows;

        return new GDimension(cellWidth, cellHeight);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GPoint cellTopLeft(int x, int y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellSize = cellDimension();

        double cellX = boardTopLeft.getX() + x * cellSize.getWidth();
        double cellY = boardTopLeft.getY() + y * cellSize.getHeight();

        return new GPoint(cellX, cellY);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GDimension tokenDimension() {
        GDimension cellSize = cellDimension();

        double tokenWidth = cellSize.getWidth() * (1- 2*cellPadding);
        double tokenHeight = cellSize.getHeight() * (1- 2*cellPadding);

        return new GDimension(tokenWidth, tokenHeight);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GPoint tokenTopLeft(int x, int y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellSize = cellDimension();
        GDimension tokenSize = tokenDimension();

        double tokenX = (
                boardTopLeft().getX() + x * cellSize.getWidth() + (cellSize.getWidth() - tokenSize.getWidth()) / 2
        );
        double tokenY = (
                boardTopLeft().getY() + y * cellSize.getHeight() + (cellSize.getHeight() - tokenSize.getHeight()) / 2
        );

        return new GPoint(tokenX, tokenY);
        //throw new UnsupportedOperationException("Step 4");
    }

    public GPoint centerAt(int x, int y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellSize = cellDimension();

        double centerX = (
                    boardTopLeft.getX() + x * cellSize.getWidth() + cellSize.getWidth() / 2
        );
        double centerY = (
                boardTopLeft.getY() + y * cellSize.getHeight() + cellSize.getHeight() / 2
        );

        return new GPoint(centerX, centerY);
        //throw new UnsupportedOperationException("Step 4");
    }

    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
