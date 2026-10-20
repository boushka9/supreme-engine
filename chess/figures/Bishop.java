package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;
import chess.interfaces.IntBishop;

// implements: "I promise to provide this behavior" -> Bishop only promises moveToBishop()
// Bishop does NOT extend Figure, so it can't go in the Figure[] array in Run,
// and it has to keep its own fields + diagonal check (Figure's helpers aren't inherited)
public class Bishop implements IntBishop {

    private PieceType type;
    private ChessColor color;
    private LocationX column;
    private int row;

    //empty constructor
    public Bishop() {
        this.type = PieceType.BISHOP;
    }

    //constructor with parameters for each field
    public Bishop(ChessColor color, LocationX column, int row) {
        this.type = PieceType.BISHOP;
        this.color = color;
        this.column = column;
        this.row = row;
    }

    //getters
    public PieceType getPieceType() {
        return this.type;
    }

    public ChessColor getColor() {
        return this.color;
    }

    public LocationX getX() {
        return this.column;
    }

    public int getY() {
        return this.row;
    }

    //setters
    public void setColumn(LocationX x) {
        this.column = x;
    }

    public void setRow(int y) {
        this.row = y;
    }

    // Bishop moves diagonally -> column distance and row distance must be equal
    // Make sure it is not staying in the same position
    @Override
    public Boolean moveToBishop(LocationX x, int y) {
        int columnDifference = Math.abs(x.ordinal() - this.column.ordinal());
        int rowDifference = Math.abs(y - this.row);

        if (!(columnDifference > 0 && columnDifference == rowDifference)) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    // Run prints the bishop directly, so toString includes its position
    @Override
    public String toString() {
        return this.type + " at " + this.column + this.row;
    }
}