package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class TempoScaledMusicalInstrument extends MusicalInstrumentDecorator{

    private final double scaleFactor; //The factor by which to scale the tempo (1.0 for normal speed)

    /**
     * Constructor that creates a TempoScaledMusicalInstrument instance
     * @param musicalInstrument The instrument to be decorated
     * @param scaleFactor The factor to scale the tempo by (positive value)
     */
    public TempoScaledMusicalInstrument(MusicalInstrument musicalInstrument, double scaleFactor) {
        super(musicalInstrument);
        this.scaleFactor = scaleFactor;
    }

    /**
     * Overrides the `play` method to adjust the sound set duration before playing.
     * - Creates a new `SoundSet` object with the original pitches and a scaled duration.
     *   - The new duration is calculated by multiplying the original duration by the `scaleFactor`.
     * - Calls the `play` method of the decorated instrument with the modified `SoundSet`.
     * This effectively changes the playback speed by adjusting the duration of each sound.
     * @param soundSet The original sound set
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */
    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        super.play(new SoundSet((int) (soundSet.getDuration()*scaleFactor), soundSet.getPitches()));
    }

    /**
     * Returns a string representation of the tempo-scaled instrument, indicating the applied scaling factor
     * @return String representation of the tempo-scaled instrument
     */
    @Override
    public String toString() {
        return scaleFactor + " tempo scaled " + musicalInstrument.toString();
    }
}
