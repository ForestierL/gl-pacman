package engine.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameMenu extends Application {

    private String name;
    private int width;
    private int height;

    public GameMenu(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private VBox menuBox;
    private int currentItem = 0;

    private static final Font FONT = Font.font("", FontWeight.BOLD,30);

    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();

    private Parent createContent(int width, int height, String options[]) {
        Pane root = new Pane();
        root.setPrefSize(width, height);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(20);
        for(int i = 0; i < options.length; i++){
            menuBox.getChildren().add(new MenuItem(options[i]));
            getMenuItem(i).setActive(false);
        }
        menuBox.getChildren().add(itemExit);
        getMenuItem(options.length).setActive(false);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(220);
        menuBox.setTranslateY(150);

        Text about = new Text("Test");
        about.setFill(Color.BLACK);
        about.setFont(FONT);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(menuBox, about);

        return root;
    }


    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    private static class MenuItem extends VBox{
        private Text text;
        private Runnable script;

        public MenuItem(String name){
            super(15);
            setAlignment(Pos.CENTER);

            text = new Text(name);

            getChildren().add(text);
        }

        public void setActive (boolean b){
            text.setFill(b ? Color.BLACK : Color.GREY);
        }

        public void setOnActivate(Runnable r){
            script = r;
        }

        public void activate() {
//            System.out.println(this.text);
            script.run();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String options[] = {"Play", "Options", "Scores"};
        Scene scene = new Scene(createContent(width,height, options));

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case DOWN:
                    if (currentItem < menuBox.getChildren().size() - 1){
                        getMenuItem(currentItem).setActive(false);
                        getMenuItem(++currentItem).setActive(true);
                    }
                    break;
                case UP:
                    if (currentItem > 0){
                        getMenuItem(currentItem).setActive(false);
                        getMenuItem(--currentItem).setActive(true);
                    }
                    break;
                case ENTER:
                    getMenuItem(currentItem).activate();
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            bgThread.shutdownNow();
        });
        primaryStage.show();
    }
}
