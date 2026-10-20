package chess.exceptions;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String message) {
        super(message);
    }
}


/**Note from danny** 
 * Throwable is a superclass that holds all the built in exceptions,
 * and it's got built in .getMessage(), ~super~ useful
 * so we reach parent constructors with super() bc it's so super.. get it? :3
* Anywho, this is the chain:

Throwable            <- holds the message in a private field (detailMessage)
  Exception
    RuntimeException
      InvalidMoveException   <- our packaged class that inherits all the runtime exceptions powers

*/