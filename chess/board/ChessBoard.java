package chess.board;
// access the other packages by importing along file paths 
import chess.enums.LocationX;
import chess.exceptions.InvalidCoordinateException;
import chess.interfaces.IntChessBoard;
//implements: "I promise to provide this behavior"


public class ChessBoard implements IntChessBoard {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;

    @Override
    public Boolean verifyCoordinate(LocationX x, int y) {
        if (x == null || y < MIN_ROW || y > MAX_ROW) {
            throw new InvalidCoordinateException("Columns are A-H and rows are 1-8");
        }
        return true;
    }
}