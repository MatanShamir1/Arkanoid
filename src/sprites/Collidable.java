//Matan Shamir 206960239
package sprites;

import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

/**
 * collidable interface
 * <p>
 * the interface holds together a definition of a collidable object in space for the
 * ball to be able to run into. it is created because different objects may posses
 * same methods in the game, and we dont want to do the operations on them separately.
 *
 * @author Matan Shamir.
 */
public interface Collidable {
    /**
     * @return return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit method returns a new velocity for the ball after finding out where the
     * ball hit the collidable using the point and current velocity of the ball.
     *
     * @param collisionPoint  the collision point between the ball and the collidable.
     * @param currentVelocity the current velocity of the ball to be changed.
     * @param hitter          the ball hitter in the collision.
     * @return a new velocity based on the calculation.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
