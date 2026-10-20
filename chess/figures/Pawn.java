public class Pawn extends Figure{

    public Pawn() {
        super();
        setPieceType(PieceType.PAWN);
    }

    public Pawn(String color, LocationX column, int row) {
        super(PieceType.PAWN, color, column, row);
    }

    @Override
    public boolean verifyMove(LocationX newX, int newY) {
        // White moves up (+1), Black moves down (-1); one square, same column
        int direction;
        if (getColor().equals("WHITE")) {
            direction = 1;
        } else {
            direction = -1;
        }
        int rowDifference = newY- this.getY();
        //can only move one space 'forward' for that pieces color
        if (!rowDistance(newY) == direction) {
            throw new InvalidMoveException("This placement is not valid");
        }
        return true;
    }

    @Override
    public String toString(){
        return "Pawn";
    }
}
