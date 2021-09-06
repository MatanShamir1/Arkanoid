//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import shapes.Circle;
import shapes.Point;
import sprites.Background;
import sprites.Shape;
import sprites.Sprite;
import shapes.Rectangle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * class YouWin is an animation that displays a screen on the game of "you win" text with drawing on it,
 * the animation is displayed in case that the player actually ended all levels.
 *
 * @author Matan Shamir.
 */
public class YouWin implements Animation {
    private final boolean stop;
    private final Counter score;
    private final Sprite background;
    private final Color textColor;

    /**
     * class constructor.
     *
     * @param score a reference to the score that the player collected to display on the screen the achievement.
     */
    public YouWin(Counter score) {
        this.stop = false;
        this.score = score;
        this.textColor = Color.WHITE;
        this.background = this.initializeBackground();
    }

    /**
     * initialize the background of the game.
     *
     * @return a new background.
     */
    private Background initializeBackground() {
        //initialize a list of shaped for the background to posses.
        List<sprites.Shape> shapes = new LinkedList<>();
        Color color = new Color(152, 209, 231);
        Shape back = new Rectangle(new Point(0, 0), GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT, color);
        shapes.add(back);
        color = new Color(153, 108, 102);
        sprites.Shape third = new Rectangle(new Point(200, 510), 120, 90, color);
        shapes.add(third);
        color = new Color(211, 215, 135);
        sprites.Shape second = new Rectangle(new Point(320, 450), 120, 150, color);
        shapes.add(second);
        color = new Color(153, 148, 148);
        sprites.Shape first = new Rectangle(new Point(440, 480), 120, 120, color);
        shapes.add(first);
        color = new Color(232, 209, 143);
        shapes.add(new Circle(new Point(380, 350), 9, color, color));
        color = new Color(142, 118, 46);
        shapes.add(new Rectangle(new Point(370, 360), 22, 50, color));
        shapes.add(new Rectangle(new Point(365, 360), 32, 40, color));
        color = new Color(87, 115, 137);
        shapes.add(new Rectangle(new Point(371, 410), 20, 40, color));
        //return the background with the list of shapes created manually.
        return new Background(shapes);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw the background on the screen.
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        //also draw specific texts on the screen.
        d.drawText(200, 530, "Bronze medal", 18);
        d.drawText(220, 570, "3rd", 30);
        d.drawText(440, 510, "Silver medal", 18);
        d.drawText(460, 570, "2nd", 30);
        d.drawText(320, 480, "Gold medal", 18);
        d.drawText(340, 570, "1st", 30);
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 2 - 200, 220, "You Win!", 100);
        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 3 * 2,
                "Final score:" + this.score.getValue(), 55);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
