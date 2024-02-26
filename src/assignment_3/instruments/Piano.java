package assignment_3.instruments;

import assignment_3.player.SoundSet;


public class Piano extends MusicalInstrument {


    public Piano() {
        super("Piano", 10);
    }

    public void playSlide(int from, int to, int duration) {
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
    }

}
