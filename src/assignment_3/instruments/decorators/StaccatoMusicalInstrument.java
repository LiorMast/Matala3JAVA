package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public class StaccatoMusicalInstrument extends MusicalInstrumentDecorator{
    public StaccatoMusicalInstrument(MusicalInstrument musicalInstrument) {
        super(musicalInstrument);
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
        if (soundSet.getDuration() <= 20) {
            super.play(soundSet);
        } else {
            super.play(new SoundSet(20, soundSet.getPitches()));
            super.play(new SoundSet(soundSet.getDuration() - 20));
        }
    }
    public String toString(){
        return musicalInstrument.toString()+" with staccato";
    }
}

