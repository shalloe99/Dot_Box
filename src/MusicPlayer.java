//代码参考自https://blog.csdn.net/hunt_er/article/details/84320980

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

class MusicPlayer extends Thread {
    Player player;
    String music;

    public MusicPlayer(String file) {
        this.music = file;
    }

    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }

    public void run() {
        try {
            play();
        } catch (FileNotFoundException | JavaLayerException e) {

        }
    }
}