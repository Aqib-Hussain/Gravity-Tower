package game;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class HeartDisplay extends StaticBody {

    private static BodyImage heartImage = new BodyImage("data/heart-image.png", 2.25f);

    // an array of hearts
    private ArrayList<AttachedImage> hearts = new ArrayList<>();

    // construct the heart display
    HeartDisplay(World world) {
        super(world);
        heartImage = new BodyImage("data/heart-image.png", 2.25f);
        setFullHealth();
        this.setPosition(new Vec2(-13,10));
    }

    // sets the players health to max
    private void setFullHealth(){
        for (int i = 0; i < Player.getpHealth(); i++) {
            hearts.add(new AttachedImage(this, heartImage, 2, 0, new Vec2(-5+i, 0)));
        }
    }

    // add another heart
    void addHeart() {
        hearts.add(new AttachedImage(this, heartImage, 2, 0, new Vec2(hearts.get(hearts.size() - 1).getOffset().x + 1, 0)));
    }

    ArrayList<AttachedImage> getHearts() {
        return hearts;
    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);
    }
}
