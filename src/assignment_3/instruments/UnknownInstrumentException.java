package assignment_3.instruments;

/**
 * This exception is thrown when an attempt is made to create an instrument of an unknown type.
 * It extends the built-in `Exception` class and provides a specific error message.
 */
public class UnknownInstrumentException extends Exception{

    /**
     * Constructor that takes a message describing the unknown instrument
     * @param msg The message describing the unknown instrument type
     */
    public UnknownInstrumentException(String msg){super("Unknown instrument: "+msg+".");}
}
