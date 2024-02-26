package assignment_3.player;

import java.util.HashMap;
import java.util.Map;

/**
 * An auxiliary class for translating between note pitches and their string representations.
 */
public class PitchHelper {
    // Static elements:
    //-----------------

    // noteToPitch is a static map which maps note names to pitches at the 4th octave:
    private static Map<String, Integer> nameToPitch;

    private static String[] noteNames = {
      "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"
    };

    static {
        nameToPitch = new HashMap<>();
        nameToPitch.put("C", 60);
        nameToPitch.put("C#", 61);
        nameToPitch.put("Db", 61);
        nameToPitch.put("D", 62);
        nameToPitch.put("D#", 63);
        nameToPitch.put("Eb", 63);
        nameToPitch.put("E", 64);
        nameToPitch.put("F", 65);
        nameToPitch.put("F#", 66);
        nameToPitch.put("Gb", 66);
        nameToPitch.put("G", 67);
        nameToPitch.put("G#", 68);
        nameToPitch.put("Ab", 68);
        nameToPitch.put("A", 69);
        nameToPitch.put("A#", 70);
        nameToPitch.put("Bb", 70);
        nameToPitch.put("B", 71);
    }

    /**
     * A static method to translate a string representation of a note
     * into the corresponding pitch.
     *
     * @param name Note name.
     * @return the pitch of the given note in the given octave.
     */
    public static int nameToPitch(String name) {
        int octave = name.charAt(name.length()-1) - '0';
        return nameToPitch.get(name.substring(0, name.length()-1)) + (octave-4) * 12;
    }

    public static String pitchToName(int pitch) {
        int octave = (pitch - 12) / 12;
        int noteIx =Math.abs((pitch - 12) % 12);
        return noteNames[noteIx] + octave;
    }

    public static String pitchToName(int[] pitches) {
        String name;
        if (pitches == null || pitches.length == 0) {
            name = "break";
        }
        else {
            name = pitchToName(pitches[0]);
            for (int i = 1; i < pitches.length; i++) {
                name += ", " + pitchToName(pitches[i]);
            }
        }
        return name;
    }

}
