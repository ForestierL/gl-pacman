package engine.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;


public class GameWindow extends Application
{
    private String name;
    private int width, height;

    private boolean currentGame = false;
    private boolean pause = false;
    AnimationTimer animationTimer;

    private Stage stage;

    protected Group menuGroup = new Group();
    protected Group gameGroup = new Group();
    protected Group scoresGroup = new Group();
    protected Group endGameGroup = new Group();

    private MenuScene menuScene;
    private GameScene gameScene;
    private ScoresScene scoresScene = new ScoresScene(scoresGroup, this, width, height);
    private EndGameScene endGameScene = new EndGameScene(endGameGroup, this, width, height);

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

        stage.setResizable(false);
        stage.setTitle(name);

        stage.setScene(menuScene);
        stage.show();
    }

    public void startGame()
    {
        stage.setScene(gameScene);
        if (!currentGame){
            currentGame = true;
            initialize();
            run();
        }else {
            pause = false;
            animationTimer.start();
        }
    }

    public void pauseGame(){
        pause = true;
        animationTimer.stop();
        stage.setScene(menuScene);
    }

    public void openScores(){
        setScoresScene(scoresScene);
        stage.setScene(scoresScene);
    }

    public void returnToMenu(){
        stage.setScene(menuScene);
    }

    public void endGame(int score){
        pause = false;
        currentGame = false;
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

    public void setGameScene(GameScene gameScene)
    {
        this.gameScene = gameScene;
        gameScene.setRoot(gameGroup);
        gameScene.createGame();

        gameGroup.getChildren().addAll(gameScene.getBackgroundDisplay().getGridLayers());
        gameGroup.getChildren().add(gameScene.getGraphicsDisplay());
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
}