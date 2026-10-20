package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;

public class Knight extends Figure{

    public Knight() {
        super();
        setPieceType(PieceType.KNIGHT);
    }

    public Knight(ChessColor color, LocationX column, int row) {
        super(PieceType.KNIGHT, color, column, row);
    }

    //implements promise from IntFigure to proved moveTo() method
    @Override
    public Boolean moveTo(LocationX newX, int newY) {
        // L-shape -> 2 squares one way and 1 the other
        int c = columnDistance(newX);
        int r = rowDistance(newY);
        if (!((c == 2 && r == 1) || (c == 1 && r == 2))){
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Knight";
    }
}