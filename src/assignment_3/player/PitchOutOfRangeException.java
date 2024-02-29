package assignment_3.player;

/**
 * This exception is thrown when a sound set contains a pitch value outside the valid range.
 * It inherits from the `SoundException` class and provides a specific error message.
 */
public class PitchOutOfRangeException extends SoundException {

    /**
     * Constructor that takes the invalid pitch value as a message
     * @param msg The invalid pitch value that caused the exception
     */
    public PitchOutOfRangeException(String msg){super("Invalid pitch. Pitch value must be between 0 and 127, got "+msg+".");}


}
