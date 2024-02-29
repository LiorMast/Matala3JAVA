package assignment_3.player;

/**
 * This is a generic exception class for all sound-related errors in the program.
 * It serves as a base class for more specific exceptions like `PitchOutOfRangeException`,
 * `SoundDurationException`, and `TooManySoundsException`. It encapsulates a general error message
 * related to sound playback or generation.
 */
public class SoundException extends Exception{

    /**
     * Constructor that takes an error message related to sound issues
     * @param msg The error message describing the sound-related issue
     */
    public SoundException(String msg){
        super(msg);
    }
}
