package game;

import java.io.FileWriter;
import java.io.IOException;

public class PlayerStateWriter {
    private String fileName;

    // The constructor for this class
    public PlayerStateWriter(String fileName) {
        this.fileName = fileName;
    }

    // Write the players current status to a file
    public void writeState(float timer, float xPos, float yPos, Float killCount, Float pHealth, Float level, Float gravity) throws IOException {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(timer + "," + xPos + ","+ yPos + "," + killCount + "," + pHealth + "," + level + "," + gravity + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
