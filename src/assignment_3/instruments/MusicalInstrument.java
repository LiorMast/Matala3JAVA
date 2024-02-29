package assignment_3.instruments;

import assignment_3.player.*;

public interface MusicalInstrument {
    void playSong(Song song);

    void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException;

    String getName();

    int getNSimultaneousSounds();

    String toString();
}
