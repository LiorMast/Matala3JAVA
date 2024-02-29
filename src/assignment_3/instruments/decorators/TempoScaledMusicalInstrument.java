package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class TempoScaledMusicalInstrument extends MusicalInstrumentDecorator{

    private final double scaleFactor;

    public TempoScaledMusicalInstrument(MusicalInstrument musicalInstrument, double scaleFactor) {
        super(musicalInstrument);
        this.scaleFactor = scaleFactor;
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
        super.play(new SoundSet((int) (soundSet.getDuration()*scaleFactor), soundSet.getPitches()));
    }

    @Override
    public String toString() {
        return scaleFactor + " tempo scaled " + musicalInstrument.toString();
    }
}
