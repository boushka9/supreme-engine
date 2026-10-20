package chess.interfaces;
import chess.enums.LocationX;

// Promise that the bishop will tell you if the bishop can move there 
public interface IntBishop {
    Boolean moveToBishop(LocationX x, int y);
}

// IntBishop == Bishop-style movement	->  Bishop & Queen implement it too