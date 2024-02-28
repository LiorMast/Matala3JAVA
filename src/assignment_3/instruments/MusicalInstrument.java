package assignment_3.instruments;

import assignment_3.player.TooManySoundsException;
import assignment_3.player.*;

public abstract class MusicalInstrument {
    private String name;
    protected Player player;
    private int nSimultaneousSounds;

    public MusicalInstrument(String name, int nSimultaneousSounds) throws UnknownInstrumentException {
        player = new Player(name);
        this.name = name;
        this.nSimultaneousSounds = nSimultaneousSounds;
    }

    public MusicalInstrument(String name) throws UnknownInstrumentException{
            this(name, 1);
    }

    public void playSong(Song song){
        System.out.println("Playing " + song.getTitle() + " on " + this.toString());
        song.reset();

        try {
            while (song.hasNext()) {
                play(song.next());
            }
        } catch (Exception e) {
            System.out.println("Stopped playing " + song.getTitle() + " due to error: "+e.getMessage());
            return;
        }

        System.out.println("Done playing " + song.getTitle());
    }

    public void play(SoundSet soundSet) throws TooManySoundsException ,SoundDurationException, PitchOutOfRangeException{
        if(soundSet.getPitches().length>this.nSimultaneousSounds)
        {
            throw new TooManySoundsException(this.getName()+" can play at most "+getNSimultaneousSounds()+" simultaneous notes, cannot play a sound set with "+soundSet.getPitches().length+" notes!");
        }
        System.out.println("Playing " + soundSet.toString());
        player.playSound(soundSet);
    }

    public String getName(){
        return name;
    }

    public int getNSimultaneousSounds() {
        return nSimultaneousSounds;
    }

    public String toString(){
        return name;
    }
}
