public class Chessboard{

    private static final int MAX_ROW = 8;
    private static final int MIN_ROW = 1;
    // private static final ColType column; final declarations have to be used, aslso we pass the column in as a parameter each time, doesn't need to be a stored attribute - danny

    //enum types ensure that it would be within the bounds  
    //as long as it is not null and the rows are within bounds it passes

    public boolean withinChessboard(ColType x, int y) {
        return x != null && y >= MIN_ROW && y <= MAX_ROW;
    }

}
