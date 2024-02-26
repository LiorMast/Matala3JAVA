package assignment_3.instruments;

import assignment_3.player.*;

public abstract class MusicalInstrument {
    private String name;
    protected Player player;
    private int nSimultaneousSounds;

    public MusicalInstrument(String name, int nSimultaneousSounds) {
        this.name = name;
        this.nSimultaneousSounds = nSimultaneousSounds;
        player = new Player(name);
    }

    public MusicalInstrument(String name) {
        this(name, 1);
    }

    public void playSong(Song song){
        System.out.println("Playing " + song.getTitle() + " on " + this.toString());
        song.reset();

        while (song.hasNext()) {
            play(song.next());
        }

        System.out.println("Done playing " + song.getTitle());
    }

    public void play(SoundSet soundSet){
        System.out.println("Playing " + soundSet.toString());
        player.playSound(soundSet);
    }

    public String getName(){
        return name;
    }

    public int getNSimultaneousSounds() {
        return nSimultaneousSounds;
    }

    public String toString(){
        return name;
    }
}
