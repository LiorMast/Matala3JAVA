package assignment_3.instruments.decorators;

import assignment_3.instruments.MusicalInstrument;
import assignment_3.player.*;

public abstract class MusicalInstrumentDecorator implements MusicalInstrument {

    protected MusicalInstrument musicalInstrument;
    public MusicalInstrumentDecorator(MusicalInstrument musicalInstrument) {
        this.musicalInstrument = musicalInstrument;
    }

    @Override
    public void playSong(Song song) {
        musicalInstrument.playSong(song);
    }

    @Override
    public void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException {
        musicalInstrument.play(soundSet);
    }

    @Override
    public String getName() {
        return musicalInstrument.getName();
    }

    @Override
    public int getNSimultaneousSounds() {
        return musicalInstrument.getNSimultaneousSounds();
    }
}
