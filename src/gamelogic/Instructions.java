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
public class Instructions implements Animation {
    private final boolean stop;
    private final Sprite background;

    /**
     * class constructor.
     *
     * @param background- the background of the menu.
     */
    public Instructions(Background background) {
        this.stop = false;
        this.background = background;
    }
/*
Press 'Up' or 'Down' arrows to navigate through the manu.
Press 'Enter' to select an option\ to start a game.
Use the 'Left' and 'Right' arrows to control the paddle.
Press 'p' to pause.
Press 'Space' to re-enter the game.
 */
    @Override
    public void doOneFrame(DrawSurface d) {
        //display the background and the sprites on the screen to show continuation on the screen.
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        //show game over on the screen.
        d.drawText(d.getWidth() / 2 - 140, 100, "Press 'Up' or 'Down' arrows to navigate through the manu.", 15);
        d.drawText(d.getWidth() / 2 - 140, 140, "Press 'Enter' to select an option\\ to start a game.", 15);
        d.drawText(d.getWidth() / 2 - 140, 180, "Use the 'Left' and 'Right' arrows to control the paddle.", 15);
        d.drawText(d.getWidth() / 2 - 140, 220, "Press 'p' to pause.", 15);
        d.drawText(d.getWidth() / 2 - 140, 260, "Press 'Space' to re-enter the game.", 15);
        d.drawText(d.getWidth() / 2 - 80, 380, "ENJOY!", 30);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
