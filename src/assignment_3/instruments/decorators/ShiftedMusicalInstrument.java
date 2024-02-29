package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class ShiftedMusicalInstrument extends MusicalInstrumentDecorator{
    private int shift; //The number to shift the pitches by

    /**
     * Constructor that creates a ShiftedMusicalInstrument instance
     * @param musicalInstrument The instrument to be decorated
     * @param shift The number to shift the pitches
     */
    public ShiftedMusicalInstrument(MusicalInstrument musicalInstrument,int shift) {
        super(musicalInstrument);
        this.shift=shift;
    }

    /**
     * Overrides the play method to shift the pitches of a sound set before playing
     * @param soundSet The original sound set
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */
    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        int[] shiftedpitches = soundSet.getPitches().clone();
        for (int i = 0; i < shiftedpitches.length; i++) {
            shiftedpitches[i] += shift;
        }
        super.play(new SoundSet(soundSet.getDuration(), shiftedpitches));
    }

    /**
     * Returns a string representation of the shifted instrument, indicating the shift amount
     * @return String representation of the shifted instrument
     */
    @Override
    public String toString(){
        return (shift + " shifted ")+musicalInstrument.toString();
    }
}
