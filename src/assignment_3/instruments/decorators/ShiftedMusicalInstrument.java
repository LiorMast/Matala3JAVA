package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class ShiftedMusicalInstrument extends MusicalInstrumentDecorator{
    private int shift;
    public ShiftedMusicalInstrument(MusicalInstrument musicalInstrument,int shift) {
        super(musicalInstrument);
        this.shift=shift;
    }

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

    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        int[] shiftedpitches = soundSet.getPitches().clone();
        for (int i = 0; i < shiftedpitches.length; i++) {
            shiftedpitches[i] += shift;
        }
        super.play(new SoundSet(soundSet.getDuration(), shiftedpitches));
    }

    @Override
    public String toString(){
        return (shift + " shifted ")+musicalInstrument.toString();
    }
}
