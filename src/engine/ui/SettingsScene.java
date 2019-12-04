package engine.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SettingsScene extends Scene {
    private GameWindow gameWindow;
    private Scene origin;

    public SettingsScene(Parent root, GameWindow gameWindow, Scene origin){
        super(root);
        this.gameWindow = gameWindow;
        this.origin = origin;
    }

    private Pane root;
    private int currentItem = 3;
    private boolean firstTime = true;
    private HBox soundBox;

    public  boolean getFirstTime(){
        return firstTime;
    }
    private static final Font FONT = Font.font("", FontWeight.BOLD,30);
    private static final Font titleFont = Font.font("", FontWeight.BOLD,60);

    public void setOrigin(Scene newOrigin){
        this.origin=newOrigin;
    }
    private int getCurrentItem(){
        return currentItem;
    }

    private void setCurrentItem(int number){
        currentItem = currentItem + number;
    }

    Node createContent(int width, int height)
    {
        if (firstTime) {
            root = new Pane();
            root.setPrefSize(width, height);
            this.addEvents();

            Text title = new Text(140, 90, "Settings");
            title.setFont(titleFont);

            soundBox = new HBox(10);

            Text sound = new Text(200,220,"Volume");
            sound.setFont(FONT);

            for (int i=0; i<=5; i++){
                MenuItem value = new MenuItem(Integer.toString(i*20));
                value.setActive(false);
                soundBox.getChildren().add(value);
            }

            soundBox.setTranslateY(240);
            soundBox.setTranslateX(120);
            getMenuItem(3).setActive(true);

            root.getChildren().addAll(title, sound, soundBox);
            firstTime = false;
        }
        return root;
    }

    private SettingsScene.MenuItem getMenuItem(int index) {
        return (SettingsScene.MenuItem)soundBox.getChildren().get(index);
    }

    void addEvents()
    {
        addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case RIGHT:
                    if (getCurrentItem() < soundBox.getChildren().size() - 1){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(1);
                        getMenuItem(getCurrentItem()).setActive(true);
                        gameWindow.soundManager.setVolume(Double.parseDouble(getMenuItem(getCurrentItem()).getText().getText())/100);
                    }
                    break;
                case LEFT:
                    if (getCurrentItem() > 0){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(-1);
                        getMenuItem(getCurrentItem()).setActive(true);
                        gameWindow.soundManager.setVolume(Double.parseDouble(getMenuItem(getCurrentItem()).getText().getText())/100);
                    }
                    break;
                case ESCAPE:
                    gameWindow.changeScene(origin);
                    break;
            }
        });
    }

    public static class MenuItem extends HBox
    {
        private Text text;

        MenuItem(String name)
        {
            super(15);

            text = new Text(name);
            text.setFont(FONT);

            getChildren().add(text);
        }

        Text getText(){
            return this.text;
        }

        void setActive(boolean b){
            text.setFill(b ? Color.BLACK : Color.GREY);
        }
    }
}
