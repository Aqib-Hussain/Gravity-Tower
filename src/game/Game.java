package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

/**
 * @author Aqib, Hussain
 * @version 3.0
 * @since 1.0
 */
public class Game {
    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;

    /**
     * A graphical display of the world (a specialised JPanel).
     */
    public MyView view;

    /**
     * The variable for the level
     */
    private static Float level;

    /**
     * Allows key-presses
     */
    private KeyHandler keyHandler;

    /**
     * Displays the view in a frame
     */
    final static JFrame frame = new JFrame("Gravity Tower");
    MainMenu mainMenuContainer;

    /**
     * <p>
     * Initialise a new Game.
     * </p>
     */
    public Game() {

        // set the level
        level = -1f;

        // make a blank world
        world = new GameLevel() {
            @Override
            public Vec2 startPosition() {
                return null;
            }

            @Override
            public Vec2 ladderPosition() {
                return null;
            }

            @Override
            public boolean isCompleted() {
                return false;
            }

            @Override
            public int enemyCounter() {
                return 0;
            }
        };
        // make a view
        view = new MyView(world, 850, 550);

        // Main menu
        mainMenuContainer = new MainMenu(this, view);
        frame.add(view);
        view.add(mainMenuContainer.getMainMenu(), BorderLayout.CENTER);

        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        // don't let the game window be resized
        frame.setResizable(false);

        // size the game window to fit the world view
        frame.pack();

        // make the window visible
        frame.setVisible(true);

    }

    /**
     * <p>
     * Is the current level of the game finished?
     *
     * @return </p>
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /**
     * <p>
     * Get the current level
     *
     * @return </p>
     */
    public static Float getLevel() {
        return level;
    }


    /**
     * <p>
     * This method takes the player to the next level
     * </p>
     */
    public void goNextLevel() {
        Float pHealth = Player.getpHealth();

        if (level == 4) {
            System.exit(0);
        } else if (level == 0) {
            level++;
            // remove the main menu
            // get a new world
            world = new Level1();
            // fill it with bodies
            initLevel(5f);
        } else if (level == 1) {
            level++;
            // get a new world
            world = new Level2();
            // fill it with bodies
            initLevel(pHealth);
        } else if (level == 2) {
            level++;
            // get a new world
            world = new Level3();
            // fill it with bodies
            initLevel(pHealth);
        } else if (level == 3) {
            level++;
            // get a new world
            world = new Level4();
            initLevel(pHealth);
            // fill it with bodies
        } else if (level == -1) {
            frame.removeKeyListener(keyHandler);
            view.add(mainMenuContainer.getMainMenu(), BorderLayout.NORTH);

            view.setWorld(new GameLevel() {
                @Override
                public Vec2 startPosition() {
                    return null;
                }

                @Override
                public Vec2 ladderPosition() {
                    return null;
                }

                @Override
                public boolean isCompleted() {
                    return false;
                }

                @Override
                public int enemyCounter() {
                    return 0;
                }
            });
        }
        frame.revalidate();

    }

    /**
     * <p>
     * When initialising a level carry through the previous health value
     *
     * @param pHealth </p>
     *                This method is called to initialise the inside of a level
     */
    private void initLevel(Float pHealth) {
        // remove the previous keyHandler so there aren't 2 levels accepting inputs
        frame.removeKeyListener(keyHandler);
        // create a new world
        world.create(this, pHealth, view);
        // switch the keyboard control to the new player
        keyHandler = new KeyHandler(world, world.getPlayer(), view, this);
        frame.addKeyListener(keyHandler);
        // show the new world in the view
        view.setWorld(world);
        // start the level
        world.start();
    }

    /**
     * <p>
     * Returns the player in the world
     *
     * @return </p>
     */
    public Player getPlayer() {
        return world.getPlayer();
    }

    /**
     * <p>
     * Return the world
     *
     * @return </p>
     */
    public World getWorld() {
        return world;
    }

    /**
     * Pauses the game
     */
    public void pause() {
        world.stop();
    }

    /**
     * Unpauses the game
     */
    public void start() {
        world.start();
    }

    /**
     * Causes the game to exit
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * <p>
     * Set the players current level
     *
     * @param level </p>
     */
    public void setLevel(Float level) {
        Game.level = level;
    }

    /**
     * <p>
     * Runs the game
     *
     * @param args </p>
     */
    public static void main(String[] args) {
        new Game();
    }

}

