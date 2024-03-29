package engine.ui;

import engine.audios.SoundManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class MenuScene extends Scene
{
    //Ecrant de menu de jeu classique
    //Accès aux réglages, au jeu, aux score et permet de sortir du jeu

    private GameWindow gameWindow;

    public MenuScene(Parent root, GameWindow gameWindow){
        super(root);
        this.gameWindow = gameWindow;
    }

    private VBox menuBox;
    private int currentItem = 0;

    private static final Font FONT = Font.font("", FontWeight.BOLD,30);
    private static final Font titleFont = Font.font("", FontWeight.BOLD,60);
    private SoundManager soundManager = new SoundManager(new File("resources/audio/sounds/ui_tic.mp3").toURI().toString(),1.0);
    private SoundManager soundManager2 = new SoundManager(new File("resources/audio/sounds/ui_validation.mp3").toURI().toString(),1.0);
    private int getCurrentItem(){
        return currentItem;
    }

    private void setCurrentItem(int number){
        currentItem = currentItem + number;
    }

    //Création du contenu
    Node createContent(int width, int height, String options[])
    {
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(15, 80, "Diamond Crystal");
        title.setFont(titleFont);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(20);

        for(int i = 0; i < options.length; i++)
        {
            MenuItem menuItem = new MenuItem(options[i]);
            switch (options[i]) {
                case "Play":
                    menuItem.setOnActivate(() -> gameWindow.startGame());
                    break;
                case "Score":
                    menuItem.setOnActivate(() -> gameWindow.openScores());
                    break;
                case "Settings":
                    menuItem.setOnActivate(() -> gameWindow.openSettings(this));
                    break;
            }
            menuBox.getChildren().add(menuItem);

            getMenuItem(i).setActive(false);
        }
        menuBox.getChildren().add(itemExit);
        getMenuItem(options.length).setActive(false);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(200);
        menuBox.setTranslateY(150);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(menuBox,title);

        return root;
    }

    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    //Ajout des controls
    void addEvents()
    {
        addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case DOWN:soundManager.playMusic();
                    if (getCurrentItem() < menuBox.getChildren().size() - 1){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(1);
                        getMenuItem(getCurrentItem()).setActive(true);
                    }
                    break;
                case UP:
                    soundManager.playMusic();
                    if (getCurrentItem() > 0){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(-1);
                        getMenuItem(getCurrentItem()).setActive(true);
                    }
                    break;
                case ENTER:
                    soundManager2.playMusic();
                    getMenuItem(getCurrentItem()).activate();
                    break;
            }
        });
    }

    public static class MenuItem extends VBox
    {
        private Text text;
        private Runnable script;

        MenuItem(String name)
        {
            super(15);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);

            getChildren().add(text);
        }

        void setActive(boolean b){
            text.setFill(b ? Color.BLACK : Color.GREY);
        }

        void setOnActivate(Runnable r){
            script = r;
        }

        void activate()
        {
            if(script != null)
                script.run();
        }
    }
}
