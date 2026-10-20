package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;

public class Pawn extends Figure{

    public Pawn() {
        super();
        setPieceType(PieceType.PAWN);
    }

    public Pawn(ChessColor color, LocationX column, int row) {
        super(PieceType.PAWN, color, column, row);
    }

    @Override
    public Boolean moveTo(LocationX newX, int newY) {
        // White moves up (+1), Black moves down (-1); one square, same column
        int direction;
        if (getColor() == ChessColor.WHITE) {
            direction = 1;
        } else {
            direction = -1;
        }
        int rowDifference = newY - this.getY();
        //can only move one space 'forward' for that pieces color
        if (newX != getX() || rowDifference != direction) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Pawn";
    }
}