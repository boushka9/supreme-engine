package chess.app;

import chess.board.ChessBoard;
import chess.enums.*;
import chess.exceptions.*;
import chess.figures.*;
import java.util.Scanner;
// * signals 'all', gets all files in the folder

/*Emma Boushka
Clarissa Esparza
Alfredo Herrera



Run by: 
    javac -d out $(find chess -name "*.java")
    java -cp out chess.app.Run
 */
public class Run{
    // dec global scanner/board, all pieces use them
    private static final Scanner scanner = new Scanner(System.in);
    private static final ChessBoard board = new ChessBoard();

    public static void main(String[] args) {
        // 6 pieces, superclass figure doesn't extend to bishop tho, so only 5 pieces descend from figure
        Figure[] pieces = new Figure[5];

        // bishop implements IntBishop and extends nothing, so it has nothinging to do with 'Figure', and can't be converted to it
        Bishop bishop = null;
        int count = 0;

        // one flag per piece type, ordinal() is the enum's position (PAWN = 0 ... KING = 5), handles case where no bishop is entered, even tho there are six prompt rounds 
        boolean[] usedTypes = new boolean[PieceType.values().length];

        // Building out one piece (but 6 timse)
        for (int i = 1; i <= 6; i++) {
            System.out.println("\nPiece " + i + " of 6 ");
            PieceType type = null;

            // each of the 6 types is used exactly once
            boolean validType = false;
    
            while (!validType) {
                type = promptForPieceType();
                if (usedTypes[type.ordinal()]) {
                    System.out.println(type + " was already entered, each piece type is used once.");
                } else {
                    usedTypes[type.ordinal()] = true;
                    validType = true;
                }
            }                                   // look group... .ordinal() ...is not a good fix, it's bad practice in most cases, we're probably going to have to change this approach for any future changes -danny

            ChessColor color = promptForColor();

            LocationX column = null;
            int row = 0;
            boolean validPos = false;
            // validation for input type in each prompt

            //validate current possition is on the board
            while (!validPos) {
                try {
                    column = promptForColumn();
                    row = promptForRow();
                    validPos = board.verifyCoordinate(column, row);
                } catch (InvalidCoordinateException e) {
                    System.out.println(e.getMessage());
                }
            }

            // after collecting type, color, column, row:
            if (type == PieceType.BISHOP) {
                bishop = new Bishop(color, column, row); // doin it's own thing
            } else {//
                pieces[count++] = createFigure(type, color, column, row);
            }
        }
       


        //call prompt for each target pos coordinate
        System.out.println("\nEnter the target position:");
        LocationX targetCol = null;
        int targetRow = 0;
        boolean valid = false;
        while (!valid) {
            //error handling uses super throw from exceptions file
            try {
                targetCol = promptForColumn();
                targetRow = promptForRow();
                valid = board.verifyCoordinate(targetCol, targetRow);
            } catch (InvalidCoordinateException e) {
                System.out.println(e.getMessage()); // throws bad target instead of false
            }
        }


        // traverse the stored pieces, print if they can move
        for (int i = 0; i < pieces.length; i++) {
            Figure piece = pieces[i];
            String verdict = " can";
            // polymorphism <3 -> each object runs its own slighlty more specific moveTo, an illegal move throws custom invaldiMoveException instead of returning false or just the print warning
            try {
                piece.moveTo(targetCol, targetRow);
            } catch (InvalidMoveException e) {
                verdict = " can NOT";
            }

            System.out.println(
                piece.getPieceType() + " at " + piece.getX() + piece.getY() +
                 verdict + " move to " + targetCol + targetRow
            );
        }

        // Point of looping over figure array is to call the promised moveTo() method for that piece, but that's not compatible with bishop bc it's got it's own moveToBishop() implementation 

        // Try bishop after array traversal 
        if (bishop != null) {
            String verdict = " can";
            try {
                bishop.moveToBishop(targetCol, targetRow);
            } catch (InvalidMoveException e) {
                verdict = " can NOT";
            }
            System.out.println(bishop + verdict + " move to " + targetCol + targetRow);
        }
    }

    // split into helper that throws exception when the prompt loop catches, couldn't fix it any other way 

    // enum validated input, use built in enum.pieceType.valueOf to check if its a match to something in the enum
    private static PieceType promptForPieceType() {
        while (true) {
            System.out.print("Select piece (PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return parsePieceType(input);
            } catch (InvalidPieceNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // valueOf throws a built in IllegalArgumentException for a non-match,
    // translate it into our own InvalidPieceNameException so the caller handles one custom type
    private static PieceType parsePieceType(String input) {
        try {
            return PieceType.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new InvalidPieceNameException(input + " is not a chess piece");
        }
    }

    //enum to validate column in
    private static LocationX promptForColumn() {
        while (true) {
            System.out.print("Column (a-h): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return LocationX.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(input + " is not a valid column. Use a-h.");
            }
        }
    }

    //make sure its an int, but chessboard handles if it's w/in min/max boundary
    private static int promptForRow() {
        while (true) {
            System.out.print("Row (1-8): ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a valid row number.");
            }
        }
    }

    private static ChessColor promptForColor() {
        while (true) {
            System.out.print("Color (WHITE or BLACK): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return ChessColor.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Color must be WHITE or BLACK.");
            }
        }
    }

    //create piece type based on user input, returns (so no break) type Figure
    // bishop isn't a Figure, so it has no case here, it's built directly in main
    private static Figure createFigure(PieceType type, ChessColor color, LocationX column, int row) {
        switch (type) {
             case PAWN:
                return new Pawn(color, column, row);
              case KNIGHT:
                return new Knight(color, column, row);
              case ROOK:
                return new Rook(color, column, row);
              case QUEEN:
                return new Queen(color, column, row);
              case KING:
                return new King(color, column, row);
                default:     throw new IllegalStateException("That's not a real piece..?");
        }
    }

}

/* File structre for packages:
git repo/
  chess/
    app/          Run.java                      
    enums/        PieceType.java  ChessColor.java  LocationX.java
    exceptions/   InvalidPieceNameException.java  InvalidMoveException.java  InvalidCoordinateException.java
    interfaces/   IntFigure.java  IntBishop.java  IntChessBoard.java
    figures/      Figure.java  Rook.java  ...
    board/        ChessBoard.java


  /out and .gitignore out here, don't track .class, etc

*/



         