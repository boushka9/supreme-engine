public class Rook extends ChessPiece {
 
    public Rook() {
        super();
        setPieceType(PieceType.ROOK);
    }
 
    public Rook(String color, LocationX column, int row) {
        super(PieceType.ROOK, color, column, row);
    }
 
    //implements exception
   @Override 
    public boolean verifyMove(LocationX newX, int newY) {
        if (!isStraightMove(newX, newY)){
            throw new InvalidMoveException("This placement is not valid");
        }return isStraightMove(newX, newY);
    }

    @Override
    public String toString(){
        return "Rook";
    }
}