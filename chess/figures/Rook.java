package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.exceptions.InvalidMoveException;


public class Rook extends Figure {
    //extends: "I am a more specific version of this class"
    // Rook is literally a more specific version of Figure. Easy. 
 
    public Rook() {
        super();
        setPieceType(PieceType.ROOK);
    }
 
    public Rook(ChessColor color, LocationX column, int row) {
        super(PieceType.ROOK, color, column, row);
    }
 
   @Override // 
    public Boolean moveTo(LocationX newX, int newY) {
        // Rook moves horizontally or vertically
        if (!isStraightMove(newX, newY)) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Rook";
    }
}