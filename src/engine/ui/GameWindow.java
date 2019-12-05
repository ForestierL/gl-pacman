package engine.ui;

import engine.audios.MusicManager;
import engine.audios.SoundManager;
import game.PacmanGameScene;
import game.PacmanWorld;
import game.objects.Pacman;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;


public class GameWindow extends Application implements Observer
{
    private String name;
    private int width, height;

    private boolean currentGame = false;
    private boolean pause = false;
    AnimationTimer animationTimer;

    public MusicManager musicManager = new MusicManager(new File("resources/audio/musics/music1.mp3").toURI().toString(), 0.6);

    private Stage stage;
    Label hpText;
    Label scoreText;

    protected Group menuGroup = new Group();
    protected Group gameGroup = new Group();
    protected Group scoresGroup = new Group();
    protected Group endGameGroup = new Group();
    protected Group pauseGroup = new Group();
    protected Group settingsGroup = new Group();

    private MenuScene menuScene;
    private GameScene gameScene;
    private ScoresScene scoresScene = new ScoresScene(scoresGroup, this, width, height);
    private EndGameScene endGameScene = new EndGameScene(endGameGroup, this, width, height);
    private PauseScene pauseScene = new PauseScene(pauseGroup, this);
    private SettingsScene settingsScene = new SettingsScene(settingsGroup, this, menuScene);

    private int updatedScore, updatedLives;

    public GameWindow(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        musicManager.playMusic();

        stage.setResizable(false);
        stage.setTitle(name);

        stage.setScene(menuScene);
        stage.show();
    }

    public void startGame()
    {
        stage.setScene(gameScene);
        if (!currentGame){
            System.out.println("test1");
            gameScene.setRoot(new Group());
            PacmanGameScene scene = new PacmanGameScene(gameGroup, this);
            setGameScene(scene);
            stage.setScene(scene);
            currentGame = true;
            initialize();
            run();
            stage.show();
        }
            else{
            System.out.println("test2");
                stage.setScene(gameScene);
                this.pause = false;
                animationTimer.start();
            }

    }

    public void pauseGame(){
        if (!pause) {
            this.pause = true;
            animationTimer.stop();
        }
        setPauseScene(pauseScene);
        stage.setScene(pauseScene);
    }

    public  void openSettings(Scene origin){
        settingsScene.setOrigin(origin);
        setSettingsScene(settingsScene);
        stage.setScene(settingsScene);
    }

    public void changeScene(Scene scene){
        stage.setScene(scene);
    }

    public void openScores(){
        setScoresScene(scoresScene);
        stage.setScene(scoresScene);
    }

    public void returnToMenu(){
        pause = false;
        currentGame = false;
        stage.setScene(menuScene);
    }

    public void endGame(int score){
        pause = false;
        currentGame = false;
        animationTimer.stop();
        setEndGameScene(endGameScene, score);
        stage.setScene(endGameScene);
    }

    public void setMenuScene(MenuScene menuScene, String[] options)
    {
        this.menuScene = menuScene;
        menuScene.setRoot(menuGroup);
        menuGroup.getChildren().add(menuScene.createContent(width, height, options));
        menuScene.addEvents();
    }

    public void setPauseScene(PauseScene pauseScene){
        this.pauseScene = pauseScene;
        pauseScene.setRoot(pauseGroup);
        pauseGroup.getChildren().add(pauseScene.createContent(width,height));
    }

    public void setSettingsScene(SettingsScene settingsScene){
        this.settingsScene = settingsScene;
        settingsScene.setRoot(settingsGroup);
        if (settingsScene.getFirstTime()) settingsGroup.getChildren().add(settingsScene.createContent(width,height));
    }

    public void setGameScene(GameScene gameScene)
    {
        this.gameScene = gameScene;
        gameScene.setRoot(gameGroup);
        gameScene.createGame();

        gameGroup.getChildren().addAll(gameScene.getBackgroundDisplay().getGridLayers());
        gameGroup.getChildren().add(gameScene.getGraphicsDisplay());


        SimpleIntegerProperty scoreValue = new SimpleIntegerProperty(0);
        scoreText = new Label("Score : 0");
        scoreText.setTextFill(Color.CRIMSON);
        scoreText.setFont( new Font(16) );

        hpText = new Label("Lives left : 3");
        hpText.setTextFill(Color.CRIMSON);
        hpText.setFont( new Font(16) );


        HBox hudPane = new HBox();
        hudPane.setSpacing(20);
        hudPane.getChildren().addAll(scoreText, hpText);

        gameGroup.getChildren().add(hudPane);
        gameGroup.getChildren().get(2).setLayoutY(this.height - 30);
        gameGroup.getChildren().get(2).setLayoutX(this.width / 3);

    }

    public void setEndGameScene(EndGameScene endGameScene, int score){
        this.endGameScene = endGameScene;
        endGameScene.setRoot(endGameGroup);
        endGameGroup.getChildren().add(endGameScene.createContent(width, height, score));
    }

    public void setScoresScene(ScoresScene scoresScene){
        this.scoresScene = scoresScene;
        scoresScene.setRoot(scoresGroup);
        scoresGroup.getChildren().add(scoresScene.createContent(width,height));
    }

    private void initialize()
    {
        System.out.println("TestWindow : initialize.");
    }

    private void run()
    {
        System.out.println("TestWindow : run loop.");
        animationTimer = new AnimationTimer()
        {
            private long lastHandle;

            @Override
            public void start()
            {
                super.start();
                lastHandle = System.nanoTime();
            }

            @Override
            public void handle(long currentNanoTime)
            {
                long elapsed = currentNanoTime - lastHandle;
                gameScene.getWorld().udpate(elapsed);
                gameScene.getGraphicsDisplay().render();
                lastHandle = currentNanoTime;
            }
        };
        animationTimer.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() != PacmanWorld.class)
            this.endGame((int) arg);
        else {
            PacmanWorld pc = (PacmanWorld) arg;
            scoreText.setText("Score : "+pc.getPlayerScore());
            hpText.setText("Lives left : "+pc.pacman.lives);
        }
    }
}