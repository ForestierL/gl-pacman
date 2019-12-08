package engine.ui;

import engine.audios.MusicManager;
import game.PacmanGameScene;
import game.PacmanWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    private AnimationTimer animationTimer;

    MusicManager musicManager = new MusicManager(new File("resources/audio/musics/music1.mp3").toURI().toString(), 0.6);

    private Stage stage;
    private Label hpText;
    private Label scoreText;

    //Initialisation des scènes

    protected Group menuGroup = new Group();
    protected Group gameGroup = new Group();
    private Group scoresGroup = new Group();
    private Group endGameGroup = new Group();
    private Group pauseGroup = new Group();
    private Group settingsGroup = new Group();

    private MenuScene menuScene;
    private GameScene gameScene;
    private ScoresScene scoresScene = new ScoresScene(scoresGroup, this);
    private EndGameScene endGameScene = new EndGameScene(endGameGroup, this);
    private PauseScene pauseScene = new PauseScene(pauseGroup, this);
    private SettingsScene settingsScene = new SettingsScene(settingsGroup, this, menuScene);

    public GameWindow(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    //lancement de l'application sur l'écran de menu
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

    //Initialisation, réinitialisation et lancement du jeu
    void startGame()
    {
        stage.setScene(gameScene);
        if (!currentGame){
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
                stage.setScene(gameScene);
                this.pause = false;
                animationTimer.start();
            }

    }


    //Lancement des différents écrans

    void pauseGame(){
        if (!pause) {
            this.pause = true;
            animationTimer.stop();
        }
        setPauseScene(pauseScene);
        stage.setScene(pauseScene);
    }

    void openSettings(Scene origin){
        settingsScene.setOrigin(origin);
        setSettingsScene(settingsScene);
        stage.setScene(settingsScene);
    }

    void changeScene(Scene scene){
        stage.setScene(scene);
    }

    void openScores(){
        setScoresScene(scoresScene);
        stage.setScene(scoresScene);
    }

    void returnToMenu(){
        pause = false;
        currentGame = false;
        stage.setScene(menuScene);
    }

    private void endGame(int score){
        pause = false;
        currentGame = false;
        animationTimer.stop();
        setEndGameScene(endGameScene, score);
        stage.setScene(endGameScene);
    }


    //Initialisation des écrans

    protected void setMenuScene(MenuScene menuScene, String[] options)
    {
        this.menuScene = menuScene;
        menuScene.setRoot(menuGroup);
        menuGroup.getChildren().add(menuScene.createContent(width, height, options));
        menuScene.addEvents();
    }

    private void setPauseScene(PauseScene pauseScene){
        this.pauseScene = pauseScene;
        pauseScene.setRoot(pauseGroup);
        pauseGroup.getChildren().add(pauseScene.createContent(width,height));
    }

    private void setSettingsScene(SettingsScene settingsScene){
        this.settingsScene = settingsScene;
        settingsScene.setRoot(settingsGroup);
        if (settingsScene.getFirstTime()) settingsGroup.getChildren().add(settingsScene.createContent(width,height));
    }

    protected void setGameScene(GameScene gameScene)
    {
        this.gameScene = gameScene;
        gameScene.setRoot(gameGroup);
        gameScene.createGame();

        gameGroup.getChildren().addAll(gameScene.getBackgroundDisplay().getGridLayers());
        gameGroup.getChildren().add(gameScene.getGraphicsDisplay());


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

    private void setEndGameScene(EndGameScene endGameScene, int score){
        this.endGameScene = endGameScene;
        endGameScene.setRoot(endGameGroup);
        endGameGroup.getChildren().add(endGameScene.createContent(width, height, score));
    }

    private void setScoresScene(ScoresScene scoresScene){
        this.scoresScene = scoresScene;
        scoresScene.setRoot(scoresGroup);
        scoresGroup.getChildren().add(scoresScene.createContent(width,height));
    }

    private void initialize()
    {
        System.out.println("TestWindow : initialize.");
    }

    //Boucle de jeu

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