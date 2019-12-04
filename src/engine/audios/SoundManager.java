package engine.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
    MediaPlayer mediaPlayer;
    String source;

    public SoundManager(String source, double volume){
        this.source = source;
        this.mediaPlayer = new MediaPlayer(new Media(source));
        this.mediaPlayer.setVolume(volume);
    }

    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    public void setMedia(String newSource){
        this.mediaPlayer = new MediaPlayer(new Media(newSource));
    }

    public void playMusic(){
        this.getMediaPlayer().play();
    }
    public void stopMusic(){
        this.getMediaPlayer().stop();
    }
    public void setVolume(double volume){
        this.getMediaPlayer().setVolume(volume);
    }
}
