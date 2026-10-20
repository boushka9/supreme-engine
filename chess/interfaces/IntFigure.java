package chess.interfaces;

import chess.enums.LocationX;

//Promise to provide a compatible moveTo(targets) method for each figure
public interface IntFigure {
    Boolean moveTo(LocationX x, int y);
}


// IntFigure == Gen piece movement	 -> specific Figure implements w the override 

// *extends* is about inheriting from something, 
// *implements* is about agreeing to provide certain behaviors