package assignment_3.player;

import java.util.HashMap;
import java.util.Map;

public class SongLibrary {
    private Map<String, Song> songs;

    public static final SongLibrary library = new SongLibrary();

    private SongLibrary() {
        Song[] songArr = {
                littleJonathan(),
                hissingOfSummerLawns(),
                iFeelTheEarthMove(),
                trill(),
        };

        songs = new HashMap<>();
        for(Song song: songArr) {
            songs.put(song.getTitle(), song);
        }
    }

    public Song getSong(String songName) {
        return songs.get(songName);
    }

    private Song littleJonathan() {
        try {
            int quarter = 250;
            int half = 2 * quarter;

            SoundSet[] sounds = {
                    new SoundSet(quarter, "G4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(half, "E4"),
                    new SoundSet(quarter, "F4"),
                    new SoundSet(quarter, "D4"),
                    new SoundSet(half, "D4"),

                    new SoundSet(quarter, "C4"),
                    new SoundSet(quarter, "D4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(quarter, "F4"),
                    new SoundSet(quarter, "G4"),
                    new SoundSet(quarter, "G4"),
                    new SoundSet(half, "G4"),

                    new SoundSet(quarter, "G4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(half, "E4"),
                    new SoundSet(quarter, "F4"),
                    new SoundSet(quarter, "D4"),
                    new SoundSet(half, "D4"),

                    new SoundSet(quarter, "C4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(quarter, "G4"),
                    new SoundSet(quarter, "G4"),
                    new SoundSet(half*2, "C4"),
            };

            return new Song("Little Jonathan", sounds);
        } catch (SoundDurationException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (PitchOutOfRangeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Song iFeelTheEarthMove() {
        try {
            int eighth = 256;
            int quarter = 2 * eighth;

            String[] chordC7 = {"C3", "G3", "Bb3", "C4"};
            String[] chordC7v2 = {"C3", "G3", "Bb3", "C4", "Bb4"};
            String[] chordC7v3 = {"C3", "G3", "Bb3", "C4", "G4"};
            String[] chordC7v4 = {"C2", "F4"};
            String[] chordC7v5 = {"C2", "Eb4"};
            String[] chordFOnC = {"C3", "F3", "A3", "C4"};
            String[] chordCm = {"C3", "Eb3", "G3", "C4"};
            String[] chordCmv2 = {"C3", "Eb3", "G3", "G4"};
            String[] chordFOnCv2 = {"C3", "F3", "A3", "F4"};
            String[] chordF7OnC = {"C3", "F3", "A3", "Eb4"};

            SoundSet c2Eight = new SoundSet(eighth, "C2");
            SoundSet chordC7Quarter = new SoundSet(quarter, chordC7);
            SoundSet chordC7Eight = new SoundSet(eighth, chordC7);
            SoundSet chordC7v2Eight = new SoundSet(eighth, chordC7v2);
            SoundSet chordC76Eight = new SoundSet(eighth, chordC7v3);
            SoundSet chordFOnCQuarter = new SoundSet(quarter, chordFOnC);
            SoundSet chordCmEight = new SoundSet(eighth, chordCm);

            SoundSet chordCm_v2Eight = new SoundSet(eighth, chordCmv2);
            SoundSet chordFOnC_v2Eight = new SoundSet(eighth, chordFOnCv2);
            SoundSet chordF7OnCEight = new SoundSet(eighth, chordF7OnC);
            SoundSet c2g4Eight = new SoundSet(eighth, "C2", "G4");
            SoundSet c2f4Eight = new SoundSet(eighth, "C2", "F4");
            SoundSet c2eb4Eight = new SoundSet(eighth, "C2", "Eb4");

            SoundSet[] sounds = {
                    c2Eight, c2Eight, chordC7Quarter,
                    c2Eight, c2Eight, chordC7Quarter,
                    c2Eight, c2Eight, chordC7Eight, chordFOnCQuarter,
                    chordCmEight, chordFOnCQuarter,

                    c2Eight, c2Eight, chordC7Quarter,
                    c2Eight, c2Eight, chordC7Quarter,
                    c2Eight, c2Eight, chordC7Eight, chordFOnCQuarter,
                    chordCm_v2Eight, chordFOnC_v2Eight, chordF7OnCEight, // I feel the

                    c2g4Eight, c2Eight, chordC7Quarter, // earth
                    c2g4Eight, c2Eight, chordC7Quarter, // move
                    c2f4Eight, c2eb4Eight, new SoundSet(eighth, "C3", "G3", "Bb3", "C4"), // un-der my
                    new SoundSet(quarter, "C3", "G3", "Bb3", "Eb4"), // feet
                    chordCm_v2Eight, chordFOnC_v2Eight, chordF7OnCEight, // I feel the

                    c2g4Eight, c2Eight, chordC7v2Eight, chordC76Eight, // sky tum-bel-
                    new SoundSet(eighth, chordC7v4), new SoundSet(eighth, chordC7v5), // -ling down
                    new SoundSet(quarter-eighth/4, chordC7),
                    new SoundSet(eighth/4, "F#4"), new SoundSet(eighth, "C2", "G4", "Bb4"),
                    new SoundSet(eighth, "C2", "F4", "A4"), new SoundSet(eighth, "C3", "Eb4", "G4"),
                    new SoundSet(eighth, "C3", "C4"), new SoundSet(eighth, "C2", "Eb4"),
                    new SoundSet(eighth, "C2", "G4"), new SoundSet(eighth, "C3", "F4"), // I feel
                    new SoundSet(eighth, "C3", "Eb4"), //my
                    new SoundSet(quarter*4, "F2", "Ab3", "C4", "Eb4", "F4") // heart...
            };

            return new Song("I Feel the Earth Move", sounds);
        } catch (SoundDurationException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (PitchOutOfRangeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Song hissingOfSummerLawns() {
        try {
            int eighth = 180;
            int sixth = eighth * 8 / 6;
            int quarter = 2 * eighth;
            int half = 4 * eighth;
            int eighth3 = 3 * eighth;

            SoundSet[] sounds = {
                    new SoundSet(eighth, "D4"),
                    new SoundSet(quarter, "F#4"),
                    new SoundSet(quarter, "A4"),
                    new SoundSet(half, "B4"),
                    new SoundSet(sixth, "A4"),
                    new SoundSet(sixth, "A4"),
                    new SoundSet(sixth, "A4"),
                    new SoundSet(quarter, "A4"),
                    new SoundSet(eighth),

                    new SoundSet(eighth, "B3"),
                    new SoundSet(eighth, "B3"),
                    new SoundSet(eighth, "B3"),
                    new SoundSet(eighth, "D4"),
                    new SoundSet(eighth, "B3"),
                    new SoundSet(quarter, "F#4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(quarter, "D4"),
                    new SoundSet(eighth, "B3"),
                    new SoundSet(eighth3, "E4"),
                    new SoundSet(eighth, "B3"),

                    new SoundSet(half+quarter),
                    new SoundSet(eighth, "F#4"),
                    new SoundSet(eighth, "E4"),
                    new SoundSet(eighth, "F#4"),
                    new SoundSet(eighth, "E4"),
                    new SoundSet(eighth, "E4"),
                    new SoundSet(eighth, "D4"),
                    new SoundSet(eighth, "D4"),
                    new SoundSet(eighth, "Bb3"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(quarter),
                    new SoundSet(eighth, "D4"),
                    new SoundSet(eighth, "B3"),

                    new SoundSet(eighth3, "D4"),
                    new SoundSet(eighth3, "E4"),
                    new SoundSet(quarter, "E4"),
                    new SoundSet(half+eighth),
                    new SoundSet(quarter, "F#4"),
                    new SoundSet(eighth, "A4"),
                    new SoundSet(half + quarter, "B4"),
                    new SoundSet(half + eighth3, "A4"),
                    new SoundSet(quarter, "F#4"),
                    new SoundSet(eighth, "F#4"),

                    new SoundSet(eighth, "F#4"),
                    new SoundSet(quarter, "D4"),
                    new SoundSet(eighth3, "E4"),
                    new SoundSet(eighth, "A3"),
                    new SoundSet(eighth, "A3"),

                    new SoundSet(eighth, "F#4"),
                    new SoundSet(eighth, "F#4"),
                    new SoundSet(eighth3, "E4"),
                    new SoundSet(eighth, "D4"),
                    new SoundSet(eighth, "B3"),
                    new SoundSet(eighth, "E4"),
                    new SoundSet(half*2, "B3"),
            };

            return new Song( "Hissing of Summer Lawns", sounds);
        } catch (SoundDurationException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (PitchOutOfRangeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Song trill() {
        try {
            SoundSet first = new SoundSet(50, "C4");
            SoundSet second = new SoundSet(50, "C#4");
            SoundSet[] sounds = {
                 first, second, first, second, first, second, first, second
            };

            return new Song("Trill", sounds);
        } catch (SoundDurationException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (PitchOutOfRangeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

