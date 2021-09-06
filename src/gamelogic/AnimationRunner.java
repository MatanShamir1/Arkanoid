//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.GameLevel;

/**
 * class AnimationRunner is a class which responsibility is to run the game. the class holds a gui and a sleeper
 * and it performs the most basic run, with the while loop. it receives an animation and is responsible
 * to run it, and each game hold a field with an animation runner.
 *
 * @author Matan Shamir.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * class constructor.
     * <p>
     * no arguments are needed: this object creates the most basic fields in the game.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT);
        this.framesPerSecond = 60;
        //generate new sleeper, compute the time for it using frames per seconds etc.
        this.sleeper = new Sleeper();
    }

    /**
     * Run the game - start the animation loop, using endless while loop.
     *
     * @param animation the animation that the runner should run on the screen.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        // the infinite while loop: compute starting and ending time of each run.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            //make the animation do its adjustments according to the time that passed.
            animation.doOneFrame(d);
            //show all of the objects on the screen.
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            //sleep for the time you measured, unless its smaller than 0.
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * class getter.
     *
     * @return the gui of the game.
     */
    public GUI getGui() {
        return this.gui;
    }
}
