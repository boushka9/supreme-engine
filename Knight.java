public class Knight extends Figure{

    public Knight() {
        super();
        setPieceType(PieceType.KNIGHT);
    }

    public Knight(String color, ColType column, int row) {
        super(PieceType.KNIGHT, color, column, row);
    }

    @Override
    public boolean verifyMove(ColType newX, int newY) {
        // L-shape -> 2 squares one way and 1 the other
        int c = columnDistance(newX);
        int r = rowDistance(newY);
        if (!c == 2 && r == 1) || (c == 1 && r == 2){
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Knight";
    }
}