//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import game.Counter;
import game.SpriteCollection;
import levels.LevelInformation;
import sprites.Sprite;

import java.awt.Color;

/**
 * class PauseScreen is an animation that is displayed when the player wants to stop the game. it
 * shows the current screen in the game for the player to remember where is he in the game, and shows
 * a text of the current screen with text. the class is usually wrapped with a stoppable animation decorator.
 *
 * @author Matan Shamir.
 */
public class PauseScreen implements Animation {
    private final boolean stop;
    private final Counter score;
    private final Color textColor;
    private final SpriteCollection gameScreen;
    private final Sprite background;

    /**
     * class constructor.
     *
     * @param score       a reference of the score to be displayed on the screen.
     * @param gameScreen  a reference to the sprite collection to be displayed on screen while stoppage.
     * @param information a reference to the information of the game to be able to display background, etc.
     */
    public PauseScreen(Counter score, SpriteCollection gameScreen, LevelInformation information) {
        this.stop = false;
        this.score = score;
        this.textColor = information.countdownColor();
        this.background = information.getBackground();
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw all objects of the game in every frame.
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 2 - 80, 100, "Paused", 40);
        d.drawText(d.getWidth() / 2 - 150, d.getHeight() / 3 * 2,
                "Current score:" + this.score.getValue(), 40);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
