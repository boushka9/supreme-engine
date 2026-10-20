package chess.interfaces;
import chess.enums.LocationX;

// promise that the chessboard will verify the target
public interface IntChessBoard {
    BBoolean verifyCoordinate(LocationX x, int y);
}