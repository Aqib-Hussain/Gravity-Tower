package game;

import city.cs.engine.*;

public class Ladder extends StaticBody {

    public Ladder(World world){ super(world, new BoxShape(1, 2));
         addImage( new BodyImage("data/ladder.png", 3.7f));
    }

}
