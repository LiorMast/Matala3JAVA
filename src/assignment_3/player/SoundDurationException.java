package assignment_3.player;

/**
 * This exception is thrown when a sound set has a negative duration.
 * It inherits from the `SoundException` class and provides a specific error message.
 */
public class SoundDurationException extends SoundException{

    /**
     * Constructor that takes the invalid duration as a message
     * @param msg The invalid duration (as a string) that caused the exception
     */
    public SoundDurationException(String msg){super("Sound duration must be nonnegative, got "+msg+"ms.");}

}
