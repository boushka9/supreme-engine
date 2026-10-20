public class Bishop extends ChessPiece {
    //already set up piecetype and chesspiece w the works
    public Bishop() {
        super();
        setPieceType(PieceType.BISHOP);
    }
    // use the piece type enum to build specific bishop piece 
    public Bishop(String color, ColType column, int row) {
        super(PieceType.BISHOP, color, column, row);
    }

    @Override
    public boolean verifyMove(ColType newX, int newY) {
        // Bishop moves diagonally
        return isDiagonalMove(newX, newY);
    }
}