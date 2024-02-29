package assignment_3.instruments;

import assignment_3.player.*;

public interface MusicalInstrument {

    /**
     * Plays a song on the instrument. The implementation should iterate through the
     * sound sets in the song and play them individually using the `play(SoundSet)` method.
     * @param song The song to be played
     */
    void playSong(Song song);

    /**
     * Plays a set of sounds on the instrument. The implementation should handle sound
     * generation and any instrument-specific limitations. It may throw exceptions for
     * invalid sound sets (e.g., too many simultaneous sounds, invalid pitches, negative duration).
     * @param soundSet The set of sounds to be played
     * @throws TooManySoundsException If the number of pitches exceeds the instrument's limit
     * @throws SoundDurationException If the sound set has a negative duration
     * @throws PitchOutOfRangeException If a pitch value is outside the valid range
     */
    void play(SoundSet soundSet) throws TooManySoundsException, SoundDurationException, PitchOutOfRangeException;

    /**
     * Returns the name of the instrument
     * @return The name of the instrument
     */
    String getName();

    /**
     * Returns the maximum number of simultaneous sounds the instrument can play
     * @return The maximum number of simultaneous sounds
     */
    int getNSimultaneousSounds();

    /**
     * Returns a string representation of the instrument (usually its name)
     * @return String representation of the instrument
     */
    String toString();
}
