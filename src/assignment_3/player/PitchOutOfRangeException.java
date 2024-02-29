package assignment_3.player;

public class PitchOutOfRangeException extends SoundException {

    public PitchOutOfRangeException(String msg){super(msg);}

    @Override
    public String getMessage() {
        return "Invalid pitch. Pitch value must be between 0 and 127, got "+super.getMessage()+".";
    }
}
