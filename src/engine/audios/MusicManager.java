package engine.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
    MediaPlayer mediaPlayer;
    String source;

    public MusicManager(String source, double Volume){
        this.source = source;
        this.mediaPlayer = new MediaPlayer(new Media(source));
        this.mediaPlayer.setAutoPlay(true);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
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
