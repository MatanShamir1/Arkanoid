//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * sprite interface
 * <p>
 * the interface holds together a definition of a sprite object in the game.
 * all object seen in the game are sprites, and can draw themselves on screen,
 * be notified that time has passed for velocity changes and place adjustments,
 * and of course to be added to the game.
 *
 * @author Matan Shamir.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface for the sprite to be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     *
     * @param game the game for the sprite to be added to.
     */
    void addToGame(GameLevel game);
}