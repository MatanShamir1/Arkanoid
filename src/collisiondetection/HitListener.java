//Matan Shamir 206960239
package collisiondetection;

import shapes.Ball;
import sprites.Block;

/**
 * class HitListener: the class is the interface of all hit listeners in the game.
 * it holds methods concerning the occurrence of a hit event, and each hit listener
 * is to do a certain action whenever a hit occurs.
 *
 * @author Matan Shamir.
 */
public interface HitListener {
    /**
     * hitEvent is a method triggered whenever a hit occurs, and it is triggered by
     * the hit notifier. this method changes data in the game when a hit occurs such as the
     * amount of sprites and collidables in the game, and the score of the user.
     *
     * @param beingHit the collidable being hit in the collision.
     * @param hitter   the ball that hits the collidable in the collision.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
