//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;

/**
 * the shape interface is only but a an interface for us to be able to gather all shapes in the same collection
 * and easily order to behave in a way together.
 * all shapes can be drawn beneath the screen.
 *
 * @author Matan Shamir.
 */
public interface Shape {
    /**
     * draw the sprite to the screen, beneath all other objects.
     *
     * @param d the draw surface for the sprite to be drawn on.
     */
    void drawBeneath(DrawSurface d);
}
