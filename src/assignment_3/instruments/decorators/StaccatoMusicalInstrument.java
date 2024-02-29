package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class StaccatoMusicalInstrument extends MusicalInstrumentDecorator{

    /**
     * Constructor that creates a StaccatoMusicalInstrument instance
     * @param musicalInstrument The instrument to be decorated
     */
    public StaccatoMusicalInstrument(MusicalInstrument musicalInstrument) {
        super(musicalInstrument);
    }

    /**
     * Overrides the `play` method to apply the staccato effect.
     * - If the sound set duration is less than or equal to 20 milliseconds, it's played normally.
     * - Otherwise, it's split into:
     *     - A shortened sound set with a duration of 20 milliseconds played with the original instrument.
     *     - A new sound set with the remaining duration played by the original instrument.
     * This simulates the short and detached notes characteristic of staccato.
     * @param soundSet The original sound set
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */
    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        if (soundSet.getDuration() <= 20) {
            super.play(soundSet);
        } else {
            super.play(new SoundSet(20, soundSet.getPitches()));
            super.play(new SoundSet(soundSet.getDuration() - 20));
        }
    }

    /**
     * Returns a string representation of the staccato instrument, indicating the applied effect
     * @return String representation of the staccato instrument
     */
    public String toString(){
        return musicalInstrument.toString()+" with staccato";
    }
}

