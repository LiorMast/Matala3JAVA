package assignment_3.player;

import assignment_3.instruments.UnknownInstrumentException;

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Player {
    private static final Map<String, Integer> instrumentToIx;
    private Instrument[] instr;
    private MidiChannel channel;

    static {
        instrumentToIx = new HashMap<>();
        instrumentToIx.put("Piano", 0);
        instrumentToIx.put("Xylophone", 13);
        instrumentToIx.put("Electric Guitar", 26);
        instrumentToIx.put("Distortion Electric Guitar", 30);
        instrumentToIx.put("Trumpet", 56);
        instrumentToIx.put("Muted Trumpet", 59);
    }

    public Player(String instrument) throws UnknownInstrumentException {
        try {
            boolean exists = false;
            String[] check_inst = {"Piano", "Xylophone", "Electric Guitar", "Distortion Electric Guitar", "Trumpet", "Muted Trumpet"};
            for(String i : check_inst){
                if(i.equals(instrument)){
                    exists = true;
                }
            }
            if (!exists){
                throw new UnknownInstrumentException("Unknown instrument: "+instrument+".");
            }
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            instr = midiSynth.getDefaultSoundbank().getInstruments();
            channel = midiSynth.getChannels()[0];

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        setInstrument(instrument);
    }


    public void playSound(SoundSet soundSet){

        int[] pitches = soundSet.getPitches();
        try {
            for (int pitch: pitches) {
                channel.noteOn(pitch, 100); // playing the note
            }
            TimeUnit.MILLISECONDS.sleep(soundSet.getDuration()); // wait the duration (in milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            for (int pitch: pitches) {
                channel.noteOff(pitch); // stop playing the note
            }
        }
    }

    public void setInstrument(String instrument) {
        Integer instrumentIx = instrumentToIx.get(instrument);
        channel.programChange(instr[instrumentIx].getPatch().getProgram());
    }

}
