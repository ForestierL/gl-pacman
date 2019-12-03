package engine.ui;

public class Score {
    private String name, score;

    public Score(String name, String score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }

    public String getScore(){
        return this.score;
    }
}
