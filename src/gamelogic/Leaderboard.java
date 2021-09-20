//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import sprites.Background;
import sprites.Sprite;

import java.awt.Color;
import java.io.*;

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
    public void doOneFrame(DrawSurface d) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Arkanoid", "results.txt")));
        //display the background and the sprites on the screen to show continuation on the screen.
        this.background.drawOn(d);
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / 2 - 97, 53, "Top 10:", 50);
        d.setColor(Color.BLACK);
        //show game over on the screen.
        d.drawText(d.getWidth() / 2 - 100, 50, "Top 10:", 50);
        for (int i = 1; i<=10; i++){
            d.drawText(30, 100+40*i, i +":" , 15);
            String line;
            if ((line =br.readLine())!=null){
                d.drawText(70, 100+40*i, line, 15);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
