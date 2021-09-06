//Matan Shamir 206960239
package game;

import collisiondetection.HitListener;
import shapes.Ball;
import sprites.Block;

/**
 * class ScoreTrackingListener:
 * the class is a listener, but a different one- it holds no reference to the game because its
 * only job is to keep track of the score of the player, so it doesn't need to make any changes
 * in the game, only to be notified when a hit occurs and thus to increment the amount of points that
 * the player collected throughout the game.
 *
 * @author Matan Shamir.
 */
public class ScoreTrackingListener implements HitListener {
    //the amount of score the player accumulated throughout the game.
    private final Counter currentScore;

    /**
     * class constructor.
     *
     * @param scoreCounter indicating the score the initiate the game with.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * class getter.
     *
     * @return the current score of the player.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //whenever a hit occurs, increment the score by 5.
        this.currentScore.increase(5);
    }
}