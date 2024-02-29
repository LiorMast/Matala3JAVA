package assignment_3.instruments;

import assignment_3.player.TooManySoundsException;
import assignment_3.player.*;

public abstract class MusicalInstrumentImpl implements MusicalInstrument {
    private String name;
    protected Player player;
    private int nSimultaneousSounds;

    /**
     * Constructor that initializes the instrument with a name and
     * the maximum number of simultaneous sounds.
     * @param name The name of the instrument
     * @param nSimultaneousSounds The maximum number of simultaneous sounds
     * @throws UnknownInstrumentException If the instrument type is not supported
     */
    public MusicalInstrumentImpl(String name, int nSimultaneousSounds) throws UnknownInstrumentException {
        player = new Player(name);
        this.name = name;
        this.nSimultaneousSounds = nSimultaneousSounds;
    }

    /**
     * Constructor that initializes the instrument with a name and assumes
     * a default maximum of 1 simultaneous sound.
     * @param name The name of the instrument
     * @throws UnknownInstrumentException If the instrument type is not supported
     */

    public MusicalInstrumentImpl(String name) throws UnknownInstrumentException{
            this(name, 1);
    }

    /**
     * Plays a song by iterating through its sound sets and playing them one by one.
     * Handles errors and prints messages during playback.
     * @param song The song to be played
     */

    @Override
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

    /**
     * Plays a set of sounds, performing validations on sound duration,
     * number of pitches exceeding instrument's limit, and individual pitch values.
     * Throws exceptions for invalid input.
     * @param soundSet The set of sounds to be played
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */

    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException ,SoundDurationException, PitchOutOfRangeException{
        if(soundSet.getDuration()<0){throw new SoundDurationException(""+soundSet.getDuration());}
        if(soundSet.getPitches().length>this.nSimultaneousSounds)
        {
            throw new TooManySoundsException(this.getName(),getNSimultaneousSounds(),soundSet.getPitches().length);
        }
        for(int pitch: soundSet.getPitches()){
            if(pitch<0||pitch>127)
                throw new PitchOutOfRangeException(""+pitch);
        }
        System.out.println("Playing " + soundSet.toString());
        player.playSound(soundSet);
    }

    /**
     * Returns the name of the instrument
     * @return The name of the instrument
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Returns the maximum number of simultaneous sounds the instrument can play
     * @return The maximum number of simultaneous sounds
     */
    @Override
    public int getNSimultaneousSounds() {
        return nSimultaneousSounds;
    }

    /**
     * Returns a string representation of the instrument (usually its name)
     * @return String representation of the instrument
     */
    @Override
    public String toString(){
        return name;
    }
}
