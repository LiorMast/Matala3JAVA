package assignment_3.player;


/**
 * This exception is thrown when a sound set contains more pitches than the instrument's
 * maximum number of simultaneous sounds.
 * It inherits from the `SoundException` class and provides a specific error message
 * that includes details about the instrument, its capacity, and the attempted number of sounds.
 */
public class TooManySoundsException extends SoundException{

    /**
     * Constructor that takes information about the instrument and the invalid sound set
     * @param name The name of the instrument
     * @param nsimsounds The maximum number of simultaneous sounds the instrument can play
     * @param notenum The number of pitches in the attempted sound set
     */
    public TooManySoundsException(String name, int nsimsounds,int notenum)
    {
        super(name+" can play at most "+nsimsounds+" simultaneous notes, cannot play a sound set with "+notenum+" notes!");
    }
}
