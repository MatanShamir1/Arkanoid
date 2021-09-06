//Matan Shamir 206960239
package game;

import collisiondetection.HitListener;
import shapes.Ball;
import sprites.Block;

/**
 * class BallRemover: the class implements HitListener, and thus it is a listener
 * in the game: an object holds reference to the game and can change data in it
 * such as the collidables and sprites in the game, and whenever a hit occurs it
 * makes adjustments in the game.
 *
 * @author Matan Shamir.
 */
public class BallRemover implements HitListener {
    //a reference to the game object, for further changes when a hit occures.
    private final GameLevel game;
    //a counter for the remaining balls in the game, to know when to end it.
    private final Counter remaining;

    /**
     * class constructor.
     *
     * @param game         a reference to the game, to be able to adjust its data when a hit occurs.
     * @param removedBalls the amount of balls available in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remaining = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //when a hit with the death region occurs- remove the ball from the game.
        hitter.removeFromGame(this.game);
        //decrease amount of available balls by one.
        this.remaining.decrease(1);
    }
}
