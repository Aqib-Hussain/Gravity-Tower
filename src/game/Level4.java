package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level4 extends GameLevel {
    private Slime slime;
    private Sorcerer sorcerer;

    @SuppressWarnings("Duplicates")
    @Override
    public void create(Game game, Float pHealth, MyView view) {
        super.create(game, pHealth, view);

        // make the ground
        Shape shape = new BoxShape(35, 1f);
        Body ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -13.5f));

        // ceiling
        Shape platform2Shape = new BoxShape(35, 1.4f);
        Body platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(0, 12.5f));

        // left side wall
        Shape platform1Shape = new BoxShape(1f, 20);
        Body platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-21, 0f));

        // right side wall
        Shape platform3Shape = new BoxShape(1f, 20);
        Body platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(21, 0f));

        // left wall
        Shape platform4Shape = new BoxShape(0.5f, 11f);
        Body platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(-10, -6.5f));

        // middle wall
        Shape platform5Shape = new BoxShape(0.5f, 11f);
        Body platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(0, 4.5f));

        // right wall
        Shape platform6Shape = new BoxShape(0.5f, 11f);
        Body platform6 = new StaticBody(this, platform6Shape);
        platform6.setPosition(new Vec2(10, -6.5f));

        // far right platform
        Shape platform7Shape = new BoxShape(0.5f, 5f);
        Body platform7 = new StaticBody(this, platform7Shape);
        platform7.setPosition(new Vec2(15, 6.5f));

        // add spikes
        Shape spikeShape = new BoxShape(2, 1);
        Spikes spikes = new Spikes(this, spikeShape);
        spikes.addImage(new BodyImage("data/spikes-upsideDown.png", 2.25f));
        spikes.setPosition(new Vec2(0, 10f));
        Shape spikeShape2 = new BoxShape(2,1);
        Spikes spikes2 = new Spikes(this, spikeShape2);
        spikes2.addImage(new BodyImage("data/spikes-upsideDown.png",2.25f));
        spikes2.setPosition(new Vec2(-17.5f,10f));
        Shape spikeShape3 = new BoxShape(2,1);
        Spikes spikes3 = new Spikes(this, spikeShape3);
        spikes3.addImage(new BodyImage("data/spikes-upsideDown.png",2.25f));
        spikes3.setPosition(new Vec2(12,10f));

        // add health pack
        Shape healthPackShape = new BoxShape(1,1);
        HealthPack healthpack = new HealthPack(this, healthPackShape);
        healthpack.addImage(new BodyImage("data/heart.gif",1.75f));
        healthpack.setPosition(new Vec2(-10,7.5f));

        // add slime
        Shape slimeShape = new PolygonShape(9, 0);
        slime = new Slime(this, slimeShape);
        slime.addImage(new BodyImage("data/slime.gif", 2.25f));
        slime.setPosition(new Vec2(0, -11));

        // add sorcerer
        sorcerer = new Sorcerer(this,Sorcerer.getSorcererShape());
        sorcerer.addImage(Sorcerer.getSorcererImage());
        sorcerer.setPosition(new Vec2(5,-11));

        // adds step listener to the this
        this.addStepListener(new StepHandler(slime, this.getPlayer()));
        this.addStepListener(new StepHandler(slime,this.getPlayer(),this.sorcerer));
        this.getPlayer().addCollisionListener(new CollisionHandler());
        this.getSlime().addCollisionListener(new CollisionHandler());

        // start!
        this.start();
    }

    public Slime getSlime() {
        return slime;
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-17, -11);
    }

    @Override
    public Vec2 ladderPosition() {
        return new Vec2(18f, 9.5f);
    }

    @Override
    public boolean isCompleted() {
        return getEnemyCounter() == 0;
    }

    @Override
    public int enemyCounter() {
        return 2;
    }


}

