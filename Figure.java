public abstract class Figure implements IntFigure{

    private PieceType type;
    private String color;
    private ColType column;
    private int row;

    //empty constructor
    public Figure(){}

    //constructor with new parameters
    public Figure(PieceType typeIn, String colorIn, ColType x, int y){
        this.type = typeIn;
        this.color = color;
        this.column = x;
        this.row = y;
    }

    //getters
    public PieceType getType(){
        return this.type;
    }

    public String getColor(){
        return this.color;
    }

    public ColType getX(){
        return this.column;
    }

    public int getY(){
        return this.row;
    }

    //setters
    protected final void setPieceType(PieceType typeIn){
        this.type = typeIn;
    }


    public final void setPieceColor(String color) {
        if (!"WHITE".equals(color) && !"BLACK".equals(color)) {
            throw new IllegalArgumentException("This color cannot be applied, it must be either WHITE or BLACK");
        }
        this.color = color;
    }

    public void setColumn(ColType x){
        this.column = x;
    }

    public void setRow(int y){
        this.row = y;
    }
    
    //helper methods
    protected int columnDistance(ColType newX) {
        return Math.abs(newX.ordinal() - column.ordinal());
    }

    protected int rowDistance(int newY) {
        return Math.abs(newY - row);
    }

    // Horizontal or vertical, and not staying in place
    protected boolean isStraightMove(ColType newX, int newY) {
        int c = columnDistance(newX);
        int r = rowDistance(newY);
        return (c == 0 && r > 0) || (c > 0 && r == 0);
    }

    public abstract boolean verifyMove(ColType newX, int newY);

    public abstract String toString();
}