public abstract class Figure implements IntFigure{

    private PieceType type;
    private String color;
    private LocationX column;
    private int row;

    //empty constructor
    public Figure(){}

    //constructor with new parameters
    public Figure(PieceType typeIn, String colorIn, LocationX x, int y){
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


    public final void setPieceColor(String color) {
        if (!"WHITE".equals(color) && !"BLACK".equals(color)) {
            throw new IllegalArgumentException("This color cannot be applied, it must be either WHITE or BLACK");
        }
        this.color = color;
    }

    public void setColumn(LocationX x){
        this.column = x;
    }

    public void setRow(int y){
        this.row = y;
    }
    
    //helper methods
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

    public abstract boolean verifyMove(LocationX newX, int newY);

    public abstract String toString();
}