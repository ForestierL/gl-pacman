package engine.ui;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Score {
    private String name;
    private int score;

    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("src/game/scores/scores.txt"));
        String line;
        String[][] elements = new String[10][];
        int count=0, pos=0;
        while ((line = text.readLine()) != null && count<10){
            elements[count] = line.split("/");
            if(Integer.parseInt(elements[count][1]) < Integer.parseInt(elements[pos][1])) {
                pos = count;
            }
            count++;
        }
        if (Integer.parseInt(elements[pos][1]) < this.getScore()) {
            Path path = Paths.get("src/game/scores/scores.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.set(pos, this.getName() + "/" + this.getScore());
            Files.write(path, lines, StandardCharsets.UTF_8);
        }
        text.close();
    }
}
