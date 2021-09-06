//Matan Shamir 206960239
package levels;

import shapes.Ball;
import shapes.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * the level information interface is an interface for the information requireed for a specific level
 * to run. it holds information about the background, the balls, the blocks, and if a game level
 * possesses an information, it must have the methods below information.
 *
 * @author Matan Shamir.
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the game.
     */
    int numberOfBalls();

    /**
     * @return a list of the velocities for the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name (as a string).
     */
    String levelName();

    /**
     * @return the background of the game.
     */
    Sprite getBackground();

    /**
     * @return the list of blocks in the game.
     */
    List<Block> blocks();

    /**
     * @return a list of balls.
     */
    List<Ball> balls();

    /**
     * @return the number of balls to remove to know when to end the level.
     */
    int numberOfBlocksToRemove();

    /**
     * @return the color for the countdown in the beginning, according to the color of the background.
     */
    Color countdownColor();
}
