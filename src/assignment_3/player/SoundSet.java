package assignment_3.player;

public class SoundSet {

    private int duration;
    private int[] pitches;

    public SoundSet(int duration) throws SoundDurationException{
        if(duration<0){throw new SoundDurationException("Sound duration must be nonnegative, got "+duration+"ms.");}
        this.duration = duration;
        pitches = new int[0];
    }

    public SoundSet(int duration, int... pitches) throws SoundDurationException, PitchOutOfRangeException {
        if(duration<0){throw new SoundDurationException("Sound duration must be nonnegative, got "+duration+"ms.");}
        for(int pitch:pitches){
            if(pitch<0||pitch>127)
                throw new PitchOutOfRangeException("Invalid pitch. Pitch must be between 0 and 127,got "+pitch);
        }
        this.duration = duration;
        this.pitches = pitches;
    }

    public SoundSet(int duration, String... notes) throws SoundDurationException,PitchOutOfRangeException{
        if(duration<0){throw new SoundDurationException("Sound duration must be nonnegative, got "+duration+"ms.");}
        this.duration = duration;
        pitches = new int[notes.length];
        for (int i = 0; i < notes.length; i++) {
            pitches[i] = PitchHelper.nameToPitch(notes[i]);
        }
        for(int pitch:pitches){
            if(pitch<0||pitch>127)
                throw new PitchOutOfRangeException("Invalid pitch. Pitch must be between 0 and 127,got "+pitch);
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
