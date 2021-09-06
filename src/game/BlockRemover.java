//Matan Shamir 206960239
package game;

import collisiondetection.HitListener;
import shapes.Ball;
import sprites.Block;

/**
 * class BlockRemover: the class implements HitListener, and thus it is a listener
 * in the game: an object holds reference to the game and can change data in it
 * such as the collidables and sprites in the game, and whenever a hit occurs it
 * makes adjustments in the game.
 *
 * @author Matan Shamir.
 */
public class BlockRemover implements HitListener {
    //a reference to the game object, for further changes when a hit occures.
    private final GameLevel game;
    //a counter for the remaining balls in the game, to know when to end it.
    private final Counter remaining;

    /**
     * @param game          a reference to the game, to be able to adjust its data when a hit occurs.
     * @param removedBlocks the amount of blocks available in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remaining = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //whenever a block is being hit, remove it from the game.
        beingHit.removeFromGame(this.game);
        //decrease amount of available blocks in the game by one.
        this.remaining.decrease(1);
    }
}
