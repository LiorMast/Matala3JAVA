package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public abstract class MusicalInstrumentDecorator implements MusicalInstrument {

    protected MusicalInstrument musicalInstrument; //The musical instrument being decorated

    /**
     * Constructor that takes an existing MusicalInstrument object to decorate
     * @param musicalInstrument The instrument to be decorated
     */
    public MusicalInstrumentDecorator(MusicalInstrument musicalInstrument) {
        this.musicalInstrument = musicalInstrument;
    }

    /**
     * Plays a song by delegating to the wrapped instrument's `playSong` method.
     * Decorators can add additional behavior before or after the
     * original functionality.
     * @param song The song to be played
     */
    @Override
    public void playSong(Song song) {
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
     * Plays a sound set by delegating to the wrapped instrument's `play` method.
     * Decorators can potentially modify the sound set or add additional processing
     * before passing it to the original instrument.
     * @param soundSet The set of sounds to be played
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */
    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        musicalInstrument.play(soundSet);
    }

    /**
     * Delegates the call to the wrapped instrument's `getName` method.
     * @return The name of the underlying instrument
     */
    @Override
    public String getName() {
        return musicalInstrument.getName();
    }

    /**
     * Delegates the call to the wrapped instrument's `getNSimultaneousSounds` method.
     * @return The maximum number of simultaneous sounds of the underlying instrument
     */
    @Override
    public int getNSimultaneousSounds() {
        return musicalInstrument.getNSimultaneousSounds();
    }
}
