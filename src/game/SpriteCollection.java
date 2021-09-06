//Matan Shamir 206960239
package game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.LinkedList;
import java.util.List;

/**
 * Class spriteCollection.
 * <p>
 * the class holds a list of the sprite objects in the game. it can give general orders
 * for every sprite and that is why it is easier- to let all the objects of the game to
 * know together to do something, such as being drawn, and be added to the game, or even
 * know that a certain amount of time has passed in the game.
 *
 * @author Matan Shamir.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * class constructor: create a new list.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * add a given sprite to the list of all sprites.
     *
     * @param s the sprite to add to the list of all sprites in the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * remove a certain sprite from the sprite collection (after a hit occurs).
     *
     * @param s the sprite to be removed from the sprite collection.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites. in case a sprite has been deleted, the list is changing,
     * and thus we iterate back to the last sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new LinkedList<>(this.sprites);
        for (Sprite sprite : copySprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface for the sprites to be drawn on.
     */
    public void drawAllOn(DrawSurface d) {

        // iterate through the whole list, send "draw on" on each one.
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}