//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.util.List;

/**
 * a background is a sprite that possesses a list of shapes and can be drawn on the screen,
 * without being collided into. if drawn on, draw on is called upon all of its shapes.
 *
 * @author Matan Shamir.
 */
public class Background implements Sprite {
    private final List<Shape> drawings;

    /**
     * class constructor.
     *
     * @param drawings a list of all of the shapes to draw.
     */
    public Background(List<Shape> drawings) {
        this.drawings = drawings;
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Shape draw : this.drawings) {
            draw.drawBeneath(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        //game.addSprite(this);
    }
}
