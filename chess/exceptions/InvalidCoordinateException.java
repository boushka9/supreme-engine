package chess.exceptions;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException(String message) {
        super(message);
    }
}

/**Note from danny** 
 
Throwable            <- holds the message in a private field (detailMessage)
  Exception
    RuntimeException
      InvalidMoveException   <- our packaged class that inherits all the runtime exceptions powers

*/