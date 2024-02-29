package assignment_3.instruments;

import assignment_3.player.PitchOutOfRangeException;
import assignment_3.player.SoundDurationException;
import assignment_3.player.TooManySoundsException;
import assignment_3.player.SoundSet;


public class Piano extends MusicalInstrumentImpl {


    public Piano() throws UnknownInstrumentException {
        super("Piano", 10);
    }

    public void playSlide(int from, int to, int duration) {
        try {
            int diff = 1;
            if (to < from) {
                diff = -1;
            }

            int slideSoundDuration = duration/10;
            play(new SoundSet(duration, from));
            for (int noteIx = from+diff; noteIx != to; noteIx += diff){
                play(new SoundSet(slideSoundDuration, noteIx));
            }
            play(new SoundSet(duration, to));
        } catch (SoundDurationException sde) {
            System.out.println(sde.getMessage());
        }
        catch (TooManySoundsException tmse)
        {
            System.out.println("This should never happen, playslide threw TooManySoundsException: " + tmse.getMessage());
        }
        catch (PitchOutOfRangeException e){
            System.out.println(e.getMessage());
        }
    }

}
