//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;
import collisiondetection.HitListener;
import collisiondetection.HitNotifier;
import game.GameLevel;
import shapes.Point;
import shapes.Ball;
import shapes.Velocity;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Block.
 * <p>
 * the block class is a rectangle in the game: it is a colloidal for the ball to run into, and
 * a general sprite in the game. it has different operations such as telling the ball what
 * to do when it runs into it. it is combined by a rectangle and uses its methods widely.
 *
 * @author Matan Shamir.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //the blocks basic properties for further usage.
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 24;

    //the body of the block- for further delegation.
    private final Rectangle body;
    private final List<HitListener> hitListeners;

    /**
     * class constructor- without using built rectangle.
     *
     * @param upperLeft the upper left point of the body.
     * @param width     the width of the block.
     * @param height    the height of the block.
     * @param color     the color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.body = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the body of the block for using rectangle methods or finding location.
     */
    public Rectangle getCollisionRectangle() {
        return this.body;
    }

    /**
     * hit method returns a new velocity for the ball after finding out where the
     * ball hit the collidable using the point and current velocity of the ball.
     *
     * @param collisionPoint  the collision point between the ball and the collidable.
     * @param currentVelocity the current velocity of the ball to be changed.
     * @param hitter          the ball hitter in the collision event.
     * @return a new velocity based on the calculation.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball hits the down or up part of the rectangle, only change dy.
        if ((collisionPoint.isPointOnLine(this.body.getLower()))
                || (collisionPoint.isPointOnLine(this.body.getUpper()))) {
            currentVelocity.setDy(-currentVelocity.getDy());

            //otherwise, only change the dx- because it hit the left or right line.
        } else {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //return the new velocity after changing it.
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * the drawOn method committed by the collidable interface, using delegation.
     * from the rectangle's drawOn method
     *
     * @param d the drawSurface for the block to be drawn onto.
     */
    public void drawOn(DrawSurface d) {
        //use drawOn method of rectangle on the block's body.
        this.body.drawBeneath(d);
        /*
        additionally, when drawing rectangles we should paint lines on their sides, so
        they are bolded in the eyes of the user.
         */

        this.body.getUpper().drawBeneath(d);
        this.body.getLower().drawBeneath(d);
        this.body.getRight().drawBeneath(d);
        this.body.getLeft().drawBeneath(d);
    }

    /**
     * timePassed should notify the block that a certain amount of time(iteration in
     * the game loop) has passed. so far, blocks should not be affected by that, but
     * still are committed to this function because of the collidable interface.
     */
    public void timePassed() {

    }

    /**
     * the addToGame method adds the block to the game as the sprite and collidable object
     * that it is, to the game environment and to the sprite collection all together.
     *
     * @param game the game for the block to be added to.
     */
    public void addToGame(GameLevel game) {
        //call game's method of adding the block to its private lists of objects.
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * remove this block from the game.
     *
     * @param game the game for the block to be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notify the listeners of this block that a collision occured for proper adjustments in the game.
     *
     * @param hitter the ball that hit the block in the collision.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
