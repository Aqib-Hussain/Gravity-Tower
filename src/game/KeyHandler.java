package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler extends JFrame implements KeyListener {
    private Player player;
    private float r = 0;
    private Staff staff;
    private GravityGun gravityGun;
    private GameLevel gameLevel;
    private Game game;
    private static boolean isPaused = false;
    public MyView view;
    private PauseMenu escMenu;

    // construct the key handler
    KeyHandler(GameLevel gameLevel, Player player, MyView view, Game game) {
        this.gameLevel = gameLevel;
        this.game = game;
        this.player = player;
        this.view = view;
        staff = new Staff(gameLevel, this.player);
        gravityGun = new GravityGun(gameLevel, this.player);
        escMenu = new PauseMenu(game, view);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // if the game is not paused, pause it
            if (!isPaused) {
                view.add(escMenu.getPauseMenu(), BorderLayout.CENTER);
                game.pause();
                Game.frame.revalidate();
                isPaused = true;
            } else {
                // if the game is paused, unpause it
                view.remove(escMenu.getPauseMenu());
                game.start();
                Game.frame.revalidate();
                isPaused = false;
            }

        }

        // move right
        if (e.getKeyChar() == 'd') {
            if (player.getGravityScale() == -1) {
                playerMovementForwards();
                if (player.getStaffImage().isFlippedVertical()) {
                } else {
                    player.getStaffImage().flipVertical();
                }
            } else {
                playerMovementForwards();
            }
        }
        // move left
        if (e.getKeyChar() == 'a') {
            if (player.getGravityScale() == -1) {
                playerMovementBackwards();
                if (player.getStaffImage().isFlippedVertical()) {
                } else {
                    player.getStaffImage().flipVertical();
                }
            } else {
                playerMovementBackwards();
            }
        }
        // jump
        if (e.getKeyChar() == 'w') {
            player.jump(4);
        }
        // rotates the players staff anti-clockwise around the player depending on if the player is in antigrav
        if (e.getKeyChar() == 'h') {
            if (r <= 200) {
                if (player.getGravityScale() == -1) {
                    r = r + 5;
                    if (player.getStaffImage().isFlippedVertical()) {
                    } else {
                        player.getStaffImage().flipVertical();
                    }
                    player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                } else {
                    r = r + 5;
                    player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                }
            }

        }
        // rotates the players staff clockwise around the player depending on if the player is in antigrav
        if (e.getKeyChar() == 'j') {
            if (r >= -20) {
                if (player.getGravityScale() == -1) {
                    r = r - 5;
                    if (player.getStaffImage().isFlippedVertical()) {
                    } else {
                        player.getStaffImage().flipVertical();
                    }
                    player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                } else {
                    r = r - 5;
                    player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                }
            }
        }
        // reverses the players gravity
        if (e.getKeyChar() == 'e') {
            if (player.getGravityScale() == 1) {
                player.setGravityScale(-1);
                player.flipPlayerImageVertical();
                player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                player.getStaffImage().flipVertical();
                player.applyForce(new Vec2(0, 0.1f));
            } else {
                if (player.getGravityScale() == -1) {
                    player.setGravityScale(1);
                    player.flipPlayerImageVertical();
                    player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
                    player.applyForce(new Vec2(0, -0.1f));
                    player.getStaffImage().flipVertical();

                }
            }
        }
        // shoots a gravity shot
        if (e.getKeyChar() == 'k') {
            gravityGun.GravityShotSpawnPoint(r);
        }
        // shoots a fireball
        if (e.getKeyChar() == 'l') {
            staff.fireSpawnPoint(r);
        }
    }

    // The method for moving the player backwards
    private void playerMovementBackwards() {
        player.startWalking(-4);
        player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
        if (player.getPlayerImage().isFlippedHorizontal()) {
        } else {
            player.getPlayerImage().flipHorizontal();
        }
    }

    // The method for moving the player forwards
    private void playerMovementForwards() {
        player.startWalking(4);
        player.getStaffImage().setRotation((float) (Math.toRadians(r) - Math.PI / 2));
        if (player.getPlayerImage().isFlippedHorizontal()) {
            player.getPlayerImage().flipHorizontal();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // The player stops walking when the forward or backward keys are released
        if (e.getKeyChar() == 'd') {
            player.stopWalking();
        }
        if (e.getKeyChar() == 'a') {
            player.stopWalking();
        }


    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void setIsPaused(boolean isPaused) {
        KeyHandler.isPaused = isPaused;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }


}


