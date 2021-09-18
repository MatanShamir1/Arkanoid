//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;

/**
 * Animation is an interface for an animation in the game.
 * each class that implements this interface must be able to do a frame when a certain amount of
 * time passes, and must know whether or not it should stop, because that is how the game loop is built.
 *
 * @author Matan Shamir.
 */
public interface Animation {
    /**
     * in do one frame, the animation performs a change in its details: it tells each sprite in the game
     * that time has passed, checks for actions of the player and bottuns that he might have performed, etc.
     *
     * @param d the drawsurface for the animation to able to draw in the specific frame.
     */
    void doOneFrame(DrawSurface d) throws Exception;

    /**
     * this method checks for actions performed by the player and so it tells the animation whether or
     * not to stop, for every possible reason: the balls might have ended, the blocks, or lives.
     *
     * @return true for should stop, false otherwise.
     */
    boolean shouldStop();
}
