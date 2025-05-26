import java.io.PipedInputStream;

public class Game {

    private final Board board;
    private State state;

    public Game(Board board) {
        this.board = board;
        // Suponemos que el jugador con las fichas negras puede hacer la primera jugada
        // (es decir, el tablero es de orden como mínimo 2)
        this.state = State.BLACK;
    }

    public boolean isFinished() {
        if(!canPlay(State.WHITE) && !canPlay(State.BLACK)){
            this.state = State.FINISHED;
            return true;
        }
        return false;
        //throw new UnsupportedOperationException("Step 6.1");
    }

    public boolean isSame(State player, Position position) {
        if(player == State.BLACK){
            return board.isBlack(position);
        }else if(player == State.WHITE) {
            return board.isWhite(position);
        }
        return false;
        //throw new UnsupportedOperationException("Step 6.1");
    }

    public boolean isOther(State player, Position position) {
        if(player == State.BLACK){
            return board.isWhite(position);
        }else if(player == State.WHITE) {
            return board.isBlack(position);
        }
        return false;
        //throw new UnsupportedOperationException("Step 6.1");
    }

    public boolean someSame(State player, Position position, Direction direction) {
        Position currentPosition = position;
        if(isSame(player, currentPosition)){
            return true;
        }
        if(board.isEmpty(position)){
            return false;
        }
        Position nextPosition = direction.move(currentPosition);
        while(board.contains(nextPosition) && !board.isEmpty(nextPosition)){
            if(isSame(player, nextPosition)){
                return true;
            }
            nextPosition = direction.move(nextPosition);
        }
        return false;
        //throw new UnsupportedOperationException("Step 6.1");
    }

    public boolean isReverseDirection(State player, Position position, Direction direction) {
        Position currentPosition = direction.move(position);

        if(!board.contains(currentPosition) || board.isEmpty(currentPosition)){
            return false;
        }

        boolean isOponent = false;
        while(board.contains(currentPosition) && !board.isEmpty(currentPosition)){
            if(isOther(player, currentPosition)){
                isOponent = true;
                currentPosition = direction.move(currentPosition);
            }else{
                break;
            }
        }

        if(!isOponent){
            return false;
        }
        return isSame(player, currentPosition);
        //throw new UnsupportedOperationException("Step 6.2");
    }

    public boolean[] directionsOfReverse(State player, Position position) {
        boolean[] result = new boolean[Direction.ALL.length];

        for(int i = 0; i < Direction.ALL.length; i++){
            Direction direction = Direction.ALL[i];
            result[i] = isReverseDirection(player, position, direction);
        }
        return result;
        //throw new UnsupportedOperationException("Step 6.2");
    }

    private static boolean allFalse(boolean[] bools) {
        for(int i = 0; i < bools.length; i++){
            if(bools[i]){
               return false;
            }
        }
        return true;
        //throw new UnsupportedOperationException("Step 6.2");
    }

    public boolean canPlayPosition(State player, Position position) {
        if(this.board.isEmpty(position)){
            boolean[] directions = this.directionsOfReverse(player, position);

            for(int i = 0; i < directions.length; i++){
                if(directions[i]){
                    return true;
                }
            }

        }
        return false;
        //throw new UnsupportedOperationException("Step 6.3");
    }

    public boolean canPlay(State player) {
        int size = board.size();

        for(int row = 0; row < size; row ++){
            for(int col = 0; col < size; col ++) {
                var position = new Position(row, col);
                if(canPlayPosition(player, position)){
                    return true;
                }
            }
        }
        return false;
        //throw new UnsupportedOperationException("Step 6.3");
    }

    private void disk(Position position) {
        if(this.state == State.BLACK){
            board.setBlack(position);
        }else if(this.state == State.WHITE){
            board.setWhite(position);
        }
        //throw new UnsupportedOperationException("Step 6.3");
    }

    private void reverse(Position position, Direction direction) {
        Position now = direction.move(position);

        while(isOther(this.state, now)){
            board.reverse(now);
            now = direction.move(now);
        }
        //throw new UnsupportedOperationException("Step 6.3");
    }

    private void reverse(Position position, boolean[] directions) {
        if(!allFalse(directions)){
            for(int i = 0; i < directions.length; i++){
                if(directions[i]){
                    reverse(position, Direction.ALL[i]);
                }
            }
        }
        //throw new UnsupportedOperationException("Step 6.3");
    }

    private void changeTurn() {
        State opponent;
        if(this.state == State.BLACK){
            opponent = State.WHITE;
        }else{
            opponent = State.BLACK;
        }

        if(canPlay(opponent)){
            this.state = opponent;
        }else if(!canPlay(this.state)){
            this.state = State.FINISHED;
        }
        //throw new UnsupportedOperationException("Step 6.3");
    }


    public void move(Position position) {
        if (!this.board.isEmpty(position)) return;
        boolean[] directions = this.directionsOfReverse(this.state, position);
        if (allFalse(directions)) return;
        this.disk(position);
        this.reverse(position, directions);
        this.changeTurn();
    }

    public String getStatus() {
        String move;
        if (this.state == State.FINISHED) {
            move = "FINISHED";
        } else {
            move = (this.state == State.BLACK ? "BLACK" : "WHITE") + " moves";
        }
        return String.format("%s - %s", this.board.getStatus(), move);
    }
}

