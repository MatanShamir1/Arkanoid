//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import game.ScoreTrackingListener;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;

/**
 * class ScoreIndicator:
 * the class implements sprite. it is a sprite in the game and thus ait appears on the screen,
 * can be drawn, added to the game and be notified for passage of time.
 * its main job is to be drawn on the screen with the score that the user accumulated so far, and thus
 * it holds a reference to a scoreTrackingListener, and its the same one that the game possesses.
 *
 * @author Matan Shamir.
 */
public class ScoreIndicator implements Sprite {
    //the body of the score indicator.
    private final Rectangle body;
    //a reference to the score to be able to draw it on the screen.
    private final ScoreTrackingListener scoreTrackingListener;
    private final String levelName;
    private final Counter lives;

    /**
     * class constructor.
     *
     * @param upperLeft             the upper left point for the body.
     * @param width                 the width of the body.
     * @param height                the height of the body.
     * @param scoreTrackingListener a reference to the score listener of the game.
     * @param color                 the color of the indicator.
     * @param levelName             the name of the level to show on screen.
     * @param lives                 a reference to the lives in the game flow, to show on screen.
     */
    public ScoreIndicator(Point upperLeft, double width, double height,
                          ScoreTrackingListener scoreTrackingListener, Color color, String levelName,
                          Counter lives) {
        this.body = new Rectangle(upperLeft, width, height, color);
        this.scoreTrackingListener = scoreTrackingListener;
        this.levelName = levelName;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //use drawOn method of rectangle on the block's body.
        this.body.drawBeneath(d);
        //now set the color to black and draw the score held within the score listener's counter.
        d.setColor(Color.black);
        d.drawText(GameLevel.SCREEN_WIDTH / 2 - 30, 18,
                "Score:" + this.scoreTrackingListener.getCurrentScore().getValue(), 16);
        d.drawText(GameLevel.SCREEN_WIDTH / 3 * 2, 18,
                "Level Name:" + this.levelName, 16);
        d.drawText(100, 18,
                "Lives:" + this.lives.getValue(), 16);
    }

    @Override
    public void timePassed() {
        //no specific action needs to be done yet when time passes.
    }

    @Override
    public void addToGame(GameLevel game) {
        //call game's method of adding the block to its private lists of objects.
        game.addSprite(this);
    }
}
