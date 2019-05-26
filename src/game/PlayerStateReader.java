package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerStateReader {
    // an array of save data
    ArrayList<Float> saveData = new ArrayList<>();
    float time;
    float xPos;
    float yPos;
    float killCount;
    float pHealth;
    float level;
    float gravity;

    // read the current file stored
    public void readFile() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + "Data/Saves/PlayerState.txt" + " ...");
            fr = new FileReader("Data/Saves/PlayerState.txt");
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                // elements stored in the array and their positions
                time = Float.parseFloat(tokens[0]);
                xPos = Float.parseFloat(tokens[1]);
                yPos = Float.parseFloat(tokens[2]);
                killCount = Float.parseFloat(tokens[3]);
                pHealth = Float.parseFloat(tokens[4]);
                level = Float.parseFloat(tokens[5]);
                gravity = Float.parseFloat(tokens[6]);
                line = reader.readLine();
            }
            // adds data to be saved
            saveData.add(time);
            saveData.add(xPos);
            saveData.add(yPos);
            saveData.add(killCount);
            saveData.add(pHealth);
            saveData.add(level);
            saveData.add(gravity);

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }

    }

    public ArrayList<Float> getSaveData() {
        return saveData;
    }
}
