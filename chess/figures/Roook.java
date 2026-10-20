public class Rook extends ChessPiece {
 
    public Rook() {
        super();
        setPieceType(PieceType.ROOK);
    }
 
    public Rook(String color, ColType column, int row) {
        super(PieceType.ROOK, color, column, row);
    }
 
   @Override 
    public boolean verifyMove(ColType newX, int newY) {
        // Rook moves horizontally or vertically
        return isStraightMove(newX, newY);
    }
}
