package engine.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {

    /*
    Cette classe permet la gestion des musique, lorsqu'elle sont lancées elles sont jouées en boucle jusqu'à ce que l'arrêt soit demandé
     */

    private MediaPlayer mediaPlayer;
    private String source;

    public MusicManager(String source, double volume){
        this.source = source;
        this.mediaPlayer = new MediaPlayer(new Media(source));
        this.mediaPlayer.setAutoPlay(true);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.setVolume(volume);
    }

    //Récupérer le mediaPlayer
    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    //Modifier la musique
    public void setMedia(String newSource){
        this.mediaPlayer = new MediaPlayer(new Media(newSource));
    }

    //Lancer et arrêter la musique
    public void playMusic(){
        this.getMediaPlayer().play();
    }
    public void stopMusic(){
        this.getMediaPlayer().stop();
    }

    //Modifier le volume
    public void setVolume(double volume){
        this.getMediaPlayer().setVolume(volume);
    }
}

