package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;
import chess.interfaces.IntBishop;

// extends: Queen IS a Rook (inherits the straight-line rule from Rook.moveTo)
// implements: Queen also promises the bishop-style diagonal rule from IntBishop
public class Queen extends Rook implements IntBishop {

    public Queen() {
        super();
        setPieceType(PieceType.QUEEN);
    }

    public Queen(ChessColor color, LocationX column, int row) {
        super(color, column, row);
        // the chain went Queen -> Rook -> Figure, and Rook set the type to ROOK, so overwrite it
        setPieceType(PieceType.QUEEN);
    }

    // diagonal rule, uses isDiagonalMove() inherited from Figure
    @Override
    public Boolean moveToBishop(LocationX x, int y) {
        if (!isDiagonalMove(x, y)) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    // try Rook's rule first, and only if it throws, try the bishop rule
    // if the diagonal rule fails too, its exception leaves this method and reaches the catch in Run
    @Override
    public Boolean moveTo(LocationX newX, int newY) {
        try {
            return super.moveTo(newX, newY);
        } catch (InvalidMoveException e) {
            return moveToBishop(newX, newY);
        }
    }

    @Override
    public String toString() {
        return "Queen";
    }
}