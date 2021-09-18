//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import sprites.Background;
import sprites.Sprite;

import java.awt.Color;

/**
 * class Game Over is an animation displayed to the player if all lives in the game flow field ended,
 * because the player lot all balls too many times. it shows the player all the screen from the level still.
 *
 * @author Matan Shamir.
 */
public class About implements Animation {
    private final boolean stop;
    private final Sprite background;

    /**
     * class constructor.
     *
     * @param background- the background of the menu.
     */
    public About(Background background) {
        this.stop = false;
        this.background = background;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //display the background and the sprites on the screen to show continuation on the screen.
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        //show game over on the screen.
        d.drawText(d.getWidth() / 2 - 140, 100, "This game was programmed by Matan Shamir,", 15);
        d.drawText(d.getWidth() / 2 - 140, 140, "a CS student in Bar-ilan University.", 15);
        d.drawText(d.getWidth() / 2 - 140, 180, "This game combines usage of OOP principles,", 15);
        d.drawText(d.getWidth() / 2 - 140, 220, "and is written in Java language.", 15);
        d.drawText(d.getWidth() / 2 - 80, 360, "ENJOY!", 30);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
