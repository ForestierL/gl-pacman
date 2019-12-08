package engine.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    /*
    Cette classe permet la gestion de sons "simple" comme le ramassage d'objet ou les sons de menus
     */

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

    //permet de modifier le son
    public void setMedia(String newSource){
        this.mediaPlayer = new MediaPlayer(new Media(newSource));
    }

    //lancement du son
    public void playMusic(){
        this.stopMusic();
        this.getMediaPlayer().play();
    }
    //arrêt du son
    public void stopMusic(){
        this.getMediaPlayer().stop();
    }
    //modification du volume
    public void setVolume(double volume){
        this.getMediaPlayer().setVolume(volume);
    }
}
