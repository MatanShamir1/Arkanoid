//Matan Shamir 206960239
package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Counter;

/**
 * class KeyPressStoppableAnimation is an animation, that decorates another animation. it wraps the animation
 * and holds a reference to it, and it gives the animation a possibility for key press behaviour, to be able
 * to pass to another animation, thus end this current animation. it uses the animation do one frame
 * method and then adds the key press behaviour, and changes the "should stop" from afar.
 *
 * @author Matan Shamir.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final Animation animation;
    private boolean stop;
    private final Counter textCounter;
    private boolean isOn;
    private boolean isAlreadyPressed;

    /**
     * class constructor.
     *
     * @param k         the keyboard sensor from the game flow class.
     * @param animation the animation to hold reference to.
     */
    public KeyPressStoppableAnimation(KeyboardSensor k, Animation animation) {
        this.keyboard = k;
        this.animation = animation;
        this.stop = animation.shouldStop();
        //turn off\on the "press space to continue" every 20 frames, use counter.
        this.textCounter = new Counter(20);
        this.isOn = true;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //do one frame in the original animation.
        this.animation.doOneFrame(d);
        //the logic to turn off and on the display of "press space to continue".
        this.textCounter.decrease(1);
        if (this.textCounter.getValue() == 0) {
            this.isOn = !this.isOn;
            this.textCounter.increase(20);
        }
        if (this.isOn) {
            d.drawText(230, d.getHeight() / 3 * 2 + 100, "Press space to continue", 32);
        }
        /*
        if entered the animation and the key to move on was already pressed, the player's will was not
        to press it in the current animation. we fix this bug using the "already pressed" logic.
         */
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {

            //if the key was not already pressed, stop the animation.
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
            //elsewhere, the key was already pressed when entering this animation, do not stop animation!
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
