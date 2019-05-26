package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class LadderListener implements CollisionListener {

    private Game game;

    public LadderListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent ladderCollision) {
        // when the level is complete and the player collides with the ladder go to the next level
        Player player = game.getPlayer();
        if (ladderCollision.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            game.goNextLevel();
        }
    }
}

