package assignment_3.player;

public class SoundSet {

    private int duration;
    private int[] pitches;

    public SoundSet(int duration) {
        this.duration = duration;
        pitches = new int[0];
    }

    public SoundSet(int duration, int... pitches) {
        this.duration = duration;
        this.pitches = pitches;
    }

    public SoundSet(int duration, String... notes) {
        this.duration = duration;
        pitches = new int[notes.length];
        for (int i = 0; i < notes.length; i++) {
            pitches[i] = PitchHelper.nameToPitch(notes[i]);
        }
    }

    public int getDuration() {
        return duration;
    }

    public int[] getPitches() {
        return pitches;
    }

    public String toString() {
        return "Duration: " + duration + "ms, notes: " + PitchHelper.pitchToName(pitches);
    }
}
