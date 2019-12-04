package engine.ui;

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

public class MenuScene extends Scene
{

    private GameWindow gameWindow;

    public MenuScene(Parent root, GameWindow gameWindow){
        super(root);
        this.gameWindow = gameWindow;
    }

    private VBox menuBox;
    private int currentItem = 0;

    private static final Font FONT = Font.font("", FontWeight.BOLD,30);
    private static final Font titleFont = Font.font("", FontWeight.BOLD,60);

    private int getCurrentItem(){
        return currentItem;
    }

    private void setCurrentItem(int number){
        currentItem = currentItem + number;
    }

    Node createContent(int width, int height, String options[])
    {
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(90, 80, "Game Name");
        title.setFont(titleFont);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(20);

        for(int i = 0; i < options.length; i++)
        {
            MenuItem menuItem = new MenuItem(options[i]);
            if(options[i].equals("Play"))
            {
                menuItem.setOnActivate(() -> gameWindow.startGame());
            }else if(options[i].equals("Score"))
            {
                menuItem.setOnActivate(() -> gameWindow.openScores());
            }else if(options[i].equals("Settings"))
            {
                menuItem.setOnActivate(() -> gameWindow.openSettings(this));
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

    void addEvents()
    {
        addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case DOWN:
                    if (getCurrentItem() < menuBox.getChildren().size() - 1){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(1);
                        getMenuItem(getCurrentItem()).setActive(true);
                    }
                    break;
                case UP:
                    if (getCurrentItem() > 0){
                        getMenuItem(getCurrentItem()).setActive(false);
                        setCurrentItem(-1);
                        getMenuItem(getCurrentItem()).setActive(true);
                    }
                    break;
                case ENTER:
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
