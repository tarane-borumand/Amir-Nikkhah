import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
public class WavPlayer {
    public static void playMusic(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File("E:\\FFOutput\\488502-organic-material-ui-dice-wooden-table-roll-rolling-long-02.wav"));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "err");
        }
    }
    public static void main(String[] args) {
        WavPlayer cc = new WavPlayer();
        playMusic("sda");
        //playMusic("E:\\FFOutput\\488502-organic-material-ui-dice-wooden-table-roll-rolling-long-02.wav");
    }
}

