package audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AudioPlayer {

    private static ArrayList<AudioPlayer> players = new ArrayList<>();

    private Clip clip;

    public static void StopAllSound() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).close();
            players.remove(i);
            i--;
        }
    }

    private void Constructor(String s, float gain) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(s);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream dais = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(dais);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);

            players.add(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AudioPlayer(String s) {
        Constructor(s, 0);
    }

    public AudioPlayer(String s, float gain) {
        Constructor(s, gain);
    }

    public boolean isPlaying() {
        return clip.isRunning();
    }

    public void play() {
        if (clip == null) return;
        stop();
        clip.setFramePosition(0);
        while (!clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }
}