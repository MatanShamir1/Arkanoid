//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Counter;
import game.SpriteCollection;
import levels.LevelInformation;
import sprites.Sprite;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

/**
 * class Game Over is an animation displayed to the player if all lives in the game flow field ended,
 * because the player lot all balls too many times. it shows the player all the screen from the level still.
 *
 * @author Matan Shamir.
 */
public class GameOver implements Animation {
    private final boolean stop;
    private final Counter score;
    private final Color textColor;
    private final SpriteCollection gameScreen;
    private final Sprite background;

    /**
     * class constructor.
     *
     * @param score       the score counter of the game, to display achievements.
     * @param gameScreen  the sprite collection from the recently over level.
     * @param information the information, to display score.
     */
    public GameOver(Counter score, SpriteCollection gameScreen, LevelInformation information) {
        this.stop = false;
        this.score = score;
        this.textColor = information.countdownColor();
        this.background = information.getBackground();
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //display the background and the sprites on the screen to show continuation on the screen.
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(this.textColor);
        //show game over on the screen.
        d.drawText(d.getWidth() / 2 - 80, 100, "Game Over.", 40);
        d.drawText(d.getWidth() / 2 - 180, d.getHeight() / 3 * 2,
                "Your score is:" + this.score.getValue(), 55);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
