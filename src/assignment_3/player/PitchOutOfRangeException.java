package assignment_3.player;

public class PitchOutOfRangeException extends SoundException {

    public PitchOutOfRangeException(String msg){super("Invalid pitch. Pitch value must be between 0 and 127, got "+msg+".");}


}
