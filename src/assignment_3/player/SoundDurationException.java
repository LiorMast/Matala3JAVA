package assignment_3.player;

public class SoundDurationException extends SoundException{
    public SoundDurationException(String msg){super("Sound duration must be nonnegative, got "+msg+"ms.");}

}
