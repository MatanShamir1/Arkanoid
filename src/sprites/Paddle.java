//Matan Shamir 206960239
package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.SpriteGenerator;
import shapes.Point;
import shapes.Ball;
import shapes.Velocity;
import shapes.Rectangle;
import shapes.Line;

import java.awt.Color;

/**
 * Class Paddle.
 * <p>
 * the class supports basic operations on a paddle: movement, collisions, etc.
 * the paddle is a sprite in the game and also a collidable object for the ball to hit.
 *
 * @author Matan Shamir.
 */
public class Paddle implements Sprite, Collidable {
    //different constant objects- properties for the default paddle.
    public static final int PADDLE_HEIGHT = 20;
    private static final int SCREEN_WIDTH = 800;
    //the body of the paddle, for further delegating its methods on paddle.
    private Rectangle body;
    private final biuoop.KeyboardSensor keyboard;
    private final Velocity velocity;
    private final int speed;

    /**
     * class constructor- without a given rectangle/body.
     *
     * @param upperLeft the upper left point of the paddle's body.
     * @param width     the width of the paddle.
     * @param height    the height of the paddle.
     * @param color     the required color of the paddle.
     * @param keyboard  a reference of the keyboard of the runner.
     * @param speed     the speed of the paddle, given from the info.
     */
    public Paddle(Point upperLeft, double width, double height, KeyboardSensor keyboard, Color color, int speed) {
        this.speed = speed;
        this.body = new Rectangle(upperLeft, width, height, color);
        this.keyboard = keyboard;
        //set the paddle velocity to 0 at first- it will change when the keyboard is pressed.
        this.velocity = new Velocity(0, 0);
    }

    /**
     * move the paddle to the left with a default paddle velocity.
     */
    public void moveLeft() {
        if (this.body.getUpperLeft().getX() < SpriteGenerator.SIDE_BORDER_WIDTH + this.speed) {
            this.velocity.setDx(this.body.getUpperLeft().getX() - SpriteGenerator.SIDE_BORDER_WIDTH);
        }
        this.velocity.setDx(-this.speed);
        //apply the new upper left according to the velocity set.
        this.body = this.velocity.applyToRectangle(this.body);
    }

    /**
     * move the paddle to the right with a default paddle velocity.
     */
    public void moveRight() {
        this.velocity.setDx(this.speed);
        if (this.body.getUpperLeft().getX() + this.body.getWidth() > SCREEN_WIDTH
                - SpriteGenerator.SIDE_BORDER_WIDTH - this.speed) {
            this.velocity.setDx(SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH
                    - this.body.getUpperLeft().getX() - this.body.getWidth());
        }
        this.body = this.velocity.applyToRectangle(this.body);
    }

    /**
     * the time passed method checks if the user moved the paddle in the run iteration.
     * if he did, move the paddle only if it is still in screen borders.
     */
    public void timePassed() {
        //only move the paddle left if its still in the screen borders, and gives space for the balls.
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            if ((this.body.getUpperLeft().getX() > SpriteGenerator.SIDE_BORDER_WIDTH)) {
                this.moveLeft();
            }
        }
        //only move the paddle right if its still in the screen borders, and gives space for the balls.
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            if (this.body.getUpperLeft().getX() + this.body.getWidth()
                    < SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH) {
                this.moveRight();
            }
        }
    }

    /**
     * the drawOn method committed by the collidable interface, using delegation.
     * from the rectangle's drawOn method
     *
     * @param d the drawSurface for the paddle to be drawn onto.
     */
    public void drawOn(DrawSurface d) {
        //use drawOn method of the rectangle on the paddle body.
        Block block = new Block(this.body.getUpperLeft(), this.body.getWidth(),
                this.body.getHeight(), this.body.getColor());
        block.drawOn(d);
    }

    /**
     * @return the body of the paddle, which is a rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.body;
    }

    /**
     * hit method is the same for every collidable, see in the interface.
     *
     * @param collisionPoint  the collision point between the ball and the collidable.
     * @param currentVelocity the current velocity of the ball to be changed.
     * @param hitter          the ball hitter in the collision.
     * @return a new velocity for the ball which hit the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /*
        if the ball hit the upper part of the paddle, divide it to 5 different parts, so
        each part gives the ball a different angle to go to.
         */
        if (collisionPoint.isPointOnLine(this.body.getUpper())) {
            Line part1 = new Line(this.body.getUpperLeft().getX() + 1, this.body.getUpperLeft().getY(),
                    this.body.getUpperLeft().getX() + this.body.getUpper().length()
                            / 5, this.body.getUpperLeft().getY());
            Line part2 = new Line(part1.end().getX(), part1.end().getY(),
                    part1.end().getX() + this.body.getUpper().length() / 5, part1.end().getY());
            Line part3 = new Line(part2.end().getX(), part2.end().getY(),
                    part2.end().getX() + this.body.getUpper().length() / 5, part2.end().getY());
            Line part4 = new Line(part3.end().getX(), part3.end().getY(),
                    part3.end().getX() + this.body.getUpper().length() / 5, part3.end().getY());
            Line part5 = new Line(part4.end().getX(), part4.end().getY(),
                    part4.end().getX() + this.body.getUpper().length() / 5 - 1, part4.end().getY());

            /*
            for each part of the paddle, check if the hit is in it. if it is, simply
            send it in the required angle with the same speed.
             */
            if (part1.isInSegment(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            } else if (part2.isInSegment(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            } else if (part3.isInSegment(collisionPoint)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            } else if (part4.isInSegment(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            } else if (part5.isInSegment(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        //if the ball didn't hit the upper part, delegate hit method of the block on paddle.
        Block block = new Block(this.body.getUpperLeft(), this.body.getWidth(),
                this.body.getHeight(), this.body.getColor());
        return block.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * the paddle is a collidable and a sprite. add it to both lists of the game.
     *
     * @param g the game for the paddle to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * class getter.
     *
     * @return the speed of the paddle.
     */
    public int getSpeed() {
        return speed;
    }
}