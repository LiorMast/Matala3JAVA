package assignment_3.player;

public class Song {
    private String title;
    private SoundSet[] sounds;
    int next; // pointer to current sound

    public Song(String title, SoundSet[] sounds) {
        this.title = title;
        this.sounds = sounds;
        reset();
    }

    public String getTitle() {
        return title;
    }

    public SoundSet[] getSounds() {
        return sounds;
    }

    public SoundSet next() {
        SoundSet soundSet = sounds[next];
        next++;
        return soundSet;
    }

    public void reset(){
        next = 0;
    }

    public boolean hasNext() {
        return next < sounds.length;
    }
}
