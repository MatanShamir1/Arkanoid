//Matan Shamir 206960239
package collisiondetection;

import shapes.Point;
import sprites.Collidable;

/**
 * Class collisionInfo
 * <p>
 * the class is basically a struct, holding together information about a collision, so
 * that when it occurs in a method, we can be able to return two pieces of information
 * about it in the same time. it doesn't support any special methods besides getters.
 *
 * @author Matan Shamir.
 */
public class CollisionInfo {
    // the collision point and object involving the collision.
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * class constructor.
     *
     * @param collisionPoint  the collision point of the collision
     * @param collisionObject the object the the ball is colliding with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}