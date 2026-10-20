import java.util.Scanner;

/*Emma Boushka
Clarissa Esparza
Alfredo Herrera
 */
public class run{
    // dec global scanner/board, all pieces use them
    private static final Scanner scanner = new Scanner(System.in);
    private static final Chessboard board = new Chessboard();

    public static void main(String[] args) {
        // 6 pieces, 6 prompts 
        ChessPiece[] pieces = new ChessPiece[6];
 
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = promptForPiece(i + 1);
        }
 
        //call prompt for each target pos coordinate
        System.out.println("\nEnter the target position:");
        ColType targetCol = promptForColumn();
        int targetRow = promptForRow();

        // Loop until target coordinates are on the board too
        while (!board.withinChessboard(targetCol, targetRow)) {
            System.out.println("Target position invalid - columns a-h, rows 1-8. Try again.");
            targetCol = promptForColumn();
            targetRow = promptForRow();
        }
 
        // traverse the stored pieces, print if they can move
        for (int i = 0; i < pieces.length; i++) {
            ChessPiece piece = pieces[i];
            boolean canMove = piece.verifyMove(targetCol, targetRow);

            String verdict = " can";
            if(!canMove){ verdict = " cannot"; }

            System.out.println(
                piece.getPieceType() + " at " + piece.getX() + piece.getY() +
                 verdict + " move to " + targetCol + targetRow
            );
        }
    }
 
    // Building out one piece (but 6 timse)
    private static ChessPiece promptForPiece(int pieceNumber) {
        System.out.println("\nPiece " + pieceNumber + " of 6 ");
        PieceType type = promptForPieceType();
        String color = promptForColor();
 
        ColType column = promptForColumn();
        int row = promptForRow();
        // validation for input type in each prompt

        //validate current possition is on the board
        while (!board.withinChessboard(column, row)) {
            System.out.println("Position invalid. Only columns a-h, & rows 1-8. Try again.");
            column = promptForColumn();
            row = promptForRow();
        }
 

        ChessPiece piece = createPiece(type);
        piece.setPieceType(type);
        piece.setPieceColor(color);
        piece.setX(column);
        piece.setY(row);
        return piece;
    }
 
    // enum validated input 
    private static PieceType promptForPieceType() {
        while (true) {
            System.out.print("Select piece (PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING): ");
            String input = scanner.nextLine().trim().toUpperCase();
            // on enum, use built in .valueOf to check if its a match to a part of enum
            try {
                return PieceType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(input + " is not a valid chess piece name. Try again.");
            }
        }
    }
 
    //enum to validate column in
    private static ColType promptForColumn() {
        while (true) {
            System.out.print("Column (a-h): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return ColType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(input + " is not a valid column. Use a-h.");
            }
        }
    }
 
    //make sure its an int, but chessboard handles w/in min/max boundary 
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
 
    private static String promptForColor() {
        while (true) {
            System.out.print("Color (WHITE or BLACK): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("WHITE") || input.equals("BLACK")) {
                return input;
            }
            System.out.println("Color must be WHITE or BLACK.");
        }
    }
 
    //create piece type based on user input, returns (so no break) type ChessPiece
    private static ChessPiece createPiece(PieceType type) {
        switch (type) {
             case PAWN:
                return new Pawn();
              case KNIGHT:
                return new Knight();
              case ROOK:
                return new Rook();
              case BISHOP:
                return new Bishop();
              case QUEEN:
                return new Queen();
              case KING:
                return new King();
                default:     throw new IllegalStateException("Unreachable?");
        }
    }

}

/* File structre for packages: 
git repo/
  run.java                      (default access point: no package line)
  chess/
    enums/        PieceType.java  ChessColor.java  LocationX.java
    exceptions/   InvalidPieceNameException.java  InvalidMoveException.java  InvalidCoordinateException.java
    interfaces/   IntFigure.java  IntBishop.java  IntChessBoard.java
    figures/      Figure.java  Rook.java  ...
    board/        ChessBoard.java

*/