package chess.figures;

import chess.enums.ChessColor;
import chess.enums.LocationX;
import chess.enums.PieceType;
import chess.interfaces.IntFigure;

public abstract class Figure implements IntFigure{

    private PieceType type;
    private ChessColor color;
    private LocationX column;
    private int row;

    //empty constructor
    public Figure(){}

    //constructor with new parameters
    public Figure(PieceType typeIn, ChessColor colorIn, LocationX x, int y){
        this.type = typeIn;
        this.color = colorIn;     // not "color", which would assign the field to itself
        this.column = x;
        this.row = y;
    }


    //getters
    public PieceType getPieceType(){
        return this.type;
    }

    public ChessColor getColor(){
        return this.color;
    }

    public LocationX getX(){
        return this.column;
    }

    public int getY(){
        return this.row;
    }

    //setters
    protected final void setPieceType(PieceType typeIn){
        this.type = typeIn;
    }

    public final void setPieceColor(ChessColor color) {
        this.color = color;
    }

    public void setColumn(LocationX x){
        this.column = x;
    }

    public void setRow(int y){
        this.row = y;
    }

    //helper methods <3

    protected int columnDistance(LocationX newX) {
        return Math.abs(newX.ordinal() - column.ordinal());
    }

    protected int rowDistance(int newY) {
        return Math.abs(newY - row);
    }

    // Horizontal or vertical, and not staying in place
    protected boolean isStraightMove(LocationX newX, int newY) {
        int c = columnDistance(newX);
        int r = rowDistance(newY);
        return (c == 0 && r > 0) || (c > 0 && r == 0);
    }

    // Equal column and row distance, and not staying in place
    protected boolean isDiagonalMove(LocationX newX, int newY) {
        int c = columnDistance(newX);
        return c > 0 && c == rowDistance(newY);
    }

    public abstract String toString();
}
