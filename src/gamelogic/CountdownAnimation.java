//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.Counter;
import game.GameLevel;
import game.SpriteCollection;
import levels.LevelInformation;


/**
 * class CountdownAnimation is an animation that starts each level. it's special feature is showing numbers on
 * the screen that count down before the beginning of the game, so its logic isn't very complex,
 * though it does show the level's sprites on the screen for the player to be ready to play.
 *
 * @author Matan Shamir.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final Counter digit;
    private final SpriteCollection gameScreen;
    private final LevelInformation information;

    /**
     * class constructor.
     *
     * @param numOfSeconds to count before the game starts.
     * @param countFrom    the numbers to count from.
     * @param gameScreen   the sprite collection from the level, to be able to draw on screen.
     * @param information  the information about the game to display it: background of game, etc.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              LevelInformation information) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.digit = new Counter(this.countFrom);
        this.information = information;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        //draw the background, get it from the information of the level's reference.
        this.information.getBackground().drawOn(d);
        //draw all of the sprites that are in the level.
        this.gameScreen.drawAllOn(d);
        d.setColor(this.information.countdownColor());
        //draw the text of the number displayed.
        d.drawText((GameLevel.SCREEN_WIDTH / 2) - 50, d.getHeight() / 2,
                Integer.toString(this.digit.getValue()), 200);
        //show each digit for relative needed amount of time: 2/3 seconds in our default case.
        if (this.digit.getValue() != this.countFrom) {
            sleeper.sleepFor((long) (1000 * (this.numOfSeconds / this.countFrom)));
        }
        this.digit.decrease(1);
    }

    @Override
    public boolean shouldStop() {
        //if the digits are below zero, animation should stop and the game should now start.
        return this.digit.getValue() == -1;
    }
}
