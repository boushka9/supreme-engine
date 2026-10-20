package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;

// King moves like a Queen, but only one square
public class King extends Queen {

    public King() {
        super();
        setPieceType(PieceType.KING);
    }

    public King(ChessColor color, LocationX column, int row) {
        super(color, column, row);
        // Queen set the type to QUEEN, so overwrite it
        setPieceType(PieceType.KING);
    }

    // super.moveTo() runs Queen's rule (which runs Rook's rule first), and throws if it isn't a Queen move
    // then limit the distance to one square in any direction
    @Override
    public Boolean moveTo(LocationX newX, int newY) {
        super.moveTo(newX, newY);

        if (Math.max(columnDistance(newX), rowDistance(newY)) > 1) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString() {
        return "King";
    }
}