package assignment_3.player;



public class TooManySoundsException extends SoundException{

    public TooManySoundsException(String name, int nsimsounds,int notenum)
    {
        super(name+" can play at most "+nsimsounds+" simultaneous notes, cannot play a sound set with "+notenum+" notes!");
    }
}
