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
public class Leaderboard implements Animation {
    private final boolean stop;
    private final Sprite background;

    /**
     * class constructor.
     *
     * @param background- the background of the menu.
     */
    public Leaderboard(Background background) {
        this.stop = false;
        this.background = background;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //display the background and the sprites on the screen to show continuation on the screen.
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        //show game over on the screen.
        d.drawText(d.getWidth() / 2 - 140, 50, "Top 10:", 30);
        d.drawText(30, 100, "1:", 15);
        d.drawText(30, 140, "2:", 15);
        d.drawText(30, 180, "3:", 15);
        d.drawText(30, 220, "4:", 15);
        d.drawText(30, 260, "5:", 15);
        d.drawText(30, 300, "6:", 15);
        d.drawText(30, 340, "7:", 15);
        d.drawText(30, 380, "8:", 15);
        d.drawText(30, 420, "9:", 15);
        d.drawText(30, 480, "10:", 15);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
