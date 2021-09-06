//Matan Shamir 206960239
package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import collisiondetection.CollisionInfo;
import shapes.Point;
import shapes.Line;
import sprites.Block;
import sprites.Collidable;

/**
 * Class GameEnvironment.
 * <p>
 * the class holds a list of all the collidable objects in a game. it can compute different
 * calculations regarding the list of collidables- such as a closest collision to a given line, etc.
 *
 * @author Matan Shamir.
 */
public class GameEnvironment {
    //the list of the collidables in the game.
    private final List<Collidable> collidables;
    /*
    the death region is the collidable set in the bottom of the screen, to notify when a ball escapes
    from the screen.
     */
    private final Block deathRegion;

    /**
     * class constructor: create a new linked list for the collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
        this.deathRegion = new Block(new Point(0, GameLevel.SCREEN_HEIGHT + Block.BLOCK_HEIGHT),
                GameLevel.SCREEN_WIDTH * 2, Block.BLOCK_HEIGHT, Color.GRAY);
    }

    /**
     * class getter.
     *
     * @return the collidable which is the death region of the game.
     */
    public Block getDeathRegion() {
        return this.deathRegion;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the new collidable to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove a certain collidable from the game environment (after a hit occurs).
     *
     * @param c the collidable to be removed from the game.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * class getter.
     *
     * @return the collidables list for further usage.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * return the closest collision between a given trajectory of the ball
     * to the list of collidables that exist in this environment.
     *
     * @param trajectory the line to calculate intersections with.
     * @return the collision info of the closest intersection.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //the default is no collision- set components of collision info to null.
        Point closestCollision = null;
        Collidable closest = null;

        //run through the whole list of collidables.
        for (Collidable collidable : this.collidables) {

            //for each one, compute the closest intersection to the start of the trajectory.
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.
                    getCollisionRectangle());

            //if it is null, move on to next collidable.
            if (collisionPoint == null) {
                continue;
            }

            //else, a collision was found. if the closest one is null, this is the closest one.
            if (closestCollision == null) {
                //set components to be this collidable collision point and object.
                closest = collidable;
                closestCollision = collisionPoint;
            }
            //else, there is a closest one. check if this collision is closer than that.
            if (collisionPoint.distance(trajectory.start())
                    < closestCollision.distance(trajectory.start())) {
                //if it is, set this collision to be the closest.
                closest = collidable;
                closestCollision = collisionPoint;
            }
        }
        //if a closest collision wasn't found, return null.
        if (closest == null) {
            return null;
        }
        //else, return the collision that was found.
        return new CollisionInfo(closestCollision, closest);
    }
}