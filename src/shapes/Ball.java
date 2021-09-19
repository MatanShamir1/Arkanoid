//Matan Shamir 206960239
package shapes;

import biuoop.DrawSurface;
import collisiondetection.CollisionInfo;
import collisiondetection.HitListener;
import collisiondetection.HitNotifier;
import game.GameLevel;
import game.SpriteGenerator;
import game.GameEnvironment;
import sprites.Paddle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Ball.
 * <p>
 * the class supports basic operations on a ball: constructors, getters, setters,
 * and other operations concerning the location of the ball in space: changing its
 * location, color, speed, and other actions. each ball is a sprite in the game,
 * and thus implements its required methods concerning the game.
 *
 * @author Matan Shamir.
 */
public class Ball extends Circle implements Sprite, HitNotifier {

    //different constants for the default ball size and etc for further usage.
    public static final int BALL_RADIUS = 5;
    private static final int CORNER_HIT = 3;
    private static final int CONNECTION_HIT = 2;

    //the velocity of the ball, combined by right-left speed and up-down.
    private Velocity velocity;

    //the ball's game environment, for object's collision calculation.
    private GameEnvironment gameEnvironment;

    private boolean activated;

    private final List<HitListener> hitListeners;

    /**
     * class constructor- with a given point, without given borders.
     *
     * @param x               the x value of the center of the ball.
     * @param y               the y value of the center of the ball.
     * @param r               the radius of the ball in pixels.
     * @param color           the color of the ball.
     * @param gameEnvironment the collidable objects in the game for the ball to run into.
     * @param velocity        a velocity for the ball, set by the game initializer.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment, Velocity velocity) {
        //set each field to the given variable, and create a point to the center.
        super(new Point(x, y), r, color, color);
        //when creating a ball, set its velocity to a specified one by game initializer.
        this.velocity = velocity;
        //set the game environment of the ball to the given one for the ball to know.
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
        this.activated = true;
    }

    /**
     * class constructor- with a given point, without given borders.
     *
     * @param x        the x value of the center of the ball.
     * @param y        the y value of the center of the ball.
     * @param r        the radius of the ball in pixels.
     * @param velocity a velocity for the ball, set by the game initializer.
     * @param colorIn  the color inside the ball.
     */
    public Ball(int x, int y, int r, Color colorIn, Velocity velocity) {
        //set each field to the given variable, and create a point to the center.
        super(new Point(x, y), r, colorIn, Color.BLACK);
        //when creating a ball, set its velocity to a specified one by game initializer.
        this.velocity = velocity;
        //set the game environment of the ball to the given one for the ball to know.
        this.gameEnvironment = null;
        this.hitListeners = new ArrayList<>();
        this.activated = true;
    }

    /**
     * class constructor- with a given point, without given borders.
     *
     * @param center          the center point of the ball.
     * @param r               the radius of the ball in pixels.
     * @param velocity        a velocity for the ball, set by the game initializer.
     * @param color           the color of the ball: in this case, only one default color.
     * @param gameEnvironment a refernce for the game environment for the ball to possess.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment, Velocity velocity) {
        super(center, r, color, color);
        //when creating a ball, set its velocity to a specified one by game initializer.
        this.velocity = velocity;
        //set the game environment of the ball to the given one for the ball to know.
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
        this.activated = true;
    }

    /**
     * class accessor.
     *
     * @return the X value of the center point.
     */

    public int getX() {
        return (int) super.getCenter().getX();
    }

    /**
     * class accessor.
     *
     * @return the Y value of the center point.
     */
    public int getY() {
        return (int) super.getCenter().getY();
    }

    /**
     * class accessor.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return super.getInnerColor();
    }

    public void activate() {
        this.activated = true;
    }

    public void deactivate() {
        this.activated = false;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void setCenter(double x, double y) {
        super.setCenter(new Point(x, y));
    }

    /**
     * draw a ball on a given draw surface, using details from the class.
     *
     * @param surface the draw surface for the ball to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        /*
        set the location of the ball on the screen to be the coordinates of the ball.
        also cast to int so it will be in pixel size.
         */
        super.drawBeneath(surface);
    }

    /**
     * isInPaddle is a function for the ball to recognize that its in the paddle after
     * making a move, so such a move can be prevented. it is possible because the balls
     * trajectory doesn't take into consideration the paddle movement and they might move
     * into each other in the same frame. the method is private because it is only relevant
     * for the ball time passed method and none of the classes need to know about it.
     *
     * @param paddle the paddle for the ball to check if it is in.
     * @return true for ball in paddle, false otherwise.
     */
    private boolean isInPaddle(Paddle paddle) {
        //generate a rectangle from the paddle's body.
        Rectangle rect = paddle.getCollisionRectangle();
        //if the ball is within the segments of x and y values of the rectangle, return true.
        return (this.getX() >= rect.getUpperLeft().getX()) && (this.getX() <= rect.getLowerRight().getX())
                && (this.getY() >= rect.getUpperLeft().getY()) && (this.getY() <= rect.getLowerRight().getY());
    }

    /**
     * after the possibility of the ball being in the paddle after making its move, it needs
     * to make another move (as long as its in the game boundaries), getting itself out of
     * the paddle, using the paddles speed.
     *
     * @param paddle the paddle for the ball to know where to get out from.
     */
    private void freeFromPaddle(Paddle paddle) {
        //remember the old velocity to know what to set to the ball back later.
        double oldDx = this.velocity.getDx();
        double oldDy = this.velocity.getDy();

        /*
        check if the ball need to change its dx because it might have hit the paddle
        from the side moving against it or towards it: if it did move against it,
        it means it didnt recognize a hit and it needs to change its dx to -dx.
         */
        if ((super.getCenter().distance(paddle.getCollisionRectangle().getUpperLeft())
                < super.getCenter().distance(paddle.getCollisionRectangle().getUpperRight()))
                && (this.velocity.getDx() >= 0)) {
            this.velocity.setDx(-this.velocity.getDx() - Point.EPSILON);
            //do for both sides.
        } else if ((super.getCenter().distance(paddle.getCollisionRectangle().getUpperRight())
                < super.getCenter().distance(paddle.getCollisionRectangle().getUpperLeft()))
                && (this.velocity.getDx() < 0)) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        /*
        set the balls velocity to the paddles velocity but dont move it upwards or downwards so it wont
        accidentally move into the borders.
         */
        this.velocity = new Velocity(this.velocity.getSignDx() * paddle.getSpeed()
                + this.velocity.getDx(), 0);
        //move it out of the paddle.
        this.moveOneStep();
        //return the velocity to the old one after getting out of the paddle and making the hit.
        this.velocity = new Velocity(oldDx, oldDy);
    }

    /**
     * this method finds out given a collision info of the ball, if it hit an edge
     * of the collidable, for better understanding and thus editing ball's velocity.
     *
     * @param collisionInfo information about the closest collision of the ball
     * @return true for an edge hit, false otherwise.
     */
    private boolean hitAnEdge(CollisionInfo collisionInfo) {
        //for each corner- check if the hit point equals to its corner.
        if ((collisionInfo.collisionPoint().equals(collisionInfo.collisionObject().
                getCollisionRectangle().getUpperLeft()))) {
            //if it fits, change the direction to exactly where the corner would send it.
            this.setVelocity(Velocity.fromAngleAndSpeed(315, this.velocity.getSpeed()));
            return true;
            // do the same for every corner of the object.
        } else if ((collisionInfo.collisionPoint().equals(collisionInfo.collisionObject().
                getCollisionRectangle().getUpperRight()))) {
            this.setVelocity(Velocity.fromAngleAndSpeed(45, this.velocity.getSpeed()));
            return true;
        } else if ((collisionInfo.collisionPoint().equals(collisionInfo.collisionObject().
                getCollisionRectangle().getLowerLeft()))) {
            this.setVelocity(Velocity.fromAngleAndSpeed(225, this.velocity.getSpeed()));
            return true;
        } else if ((collisionInfo.collisionPoint().equals(collisionInfo.collisionObject().
                getCollisionRectangle().getLowerRight()))) {
            this.setVelocity(Velocity.fromAngleAndSpeed(135, this.velocity.getSpeed()));
            return true;
            //if after checking it hadn't hit an edge, return false.
        } else {
            return false;
        }
    }

    /**
     * adjustVelocityToHit adjusts the velocity of the ball to the information of the
     * ball hit place and objects. there are special cases concerning the hit, so
     * each of them is being checked.
     *
     * @param collisionInfo the information about the hit object and location.
     */
    private void adjustVelocityToHit(CollisionInfo collisionInfo) {
        /*
        take closest one, extract info about collision object to find out which line in
        the rectangle was run into, to understand how to adjust new velocity.
        */
        if (collisionInfo.collisionPoint().isPointOnLine(this.gameEnvironment.
                getDeathRegion().getCollisionRectangle().getUpper())) {
            notifyHit(this);
        }
        int hitType = collisionInfo.collisionPoint().diffCollidablesAmount(this.gameEnvironment);

        /*
        if you hit three different objects, as in a corner, you need to go back where
        you came from instead of doing what the hit target tells you.
         */
        if (hitType == CORNER_HIT) {
            this.setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
            /*
            if you hit the connection between two blocks, its just one big block and thus
            you need to change only your dx or your dy.
             */
        } else if (hitType == CONNECTION_HIT) {

            //check which connection it was to know what to change- dx or dy.
            if ((collisionInfo.collisionPoint().isPointOnLine(collisionInfo.collisionObject().
                    getCollisionRectangle().getLower()))
                    || (collisionInfo.collisionPoint().isPointOnLine(collisionInfo.collisionObject().
                    getCollisionRectangle().getLower()))) {
                this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
            } else {
                this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
            }
            /*
            in case that the ball hit an edge of a block,, the ball hits exactly the corner.
            it needs to do another move in order to avoid seeing another false collision after
            hitting a block, so for easier setting of ball location the logic is done under
            ball and not "hit" method.
             */
        } else if (this.hitAnEdge(collisionInfo)) {
            this.moveOneStep();
            /*
            else, the hit is a regular one without connections, compute it using "hit".
             */
        } else {
            this.setVelocity(collisionInfo.collisionObject().hit(this,
                    collisionInfo.collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * when the paddle hits the ball, it moves it fast. but it might move it out of the
     * game accidentally, for this case we check first if the paddle is in the game bounds indeed.
     *
     * @param paddle the paddle for the ball to know if is within bounds according to the paddle.
     * @return true for ball inside bounds, false otherwise.
     */
    public boolean isInBounds(Paddle paddle) {
        //check if the ball is withing bounds using constants of game properties.
        return (this.getX() <= GameLevel.SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH - paddle.getSpeed() * 4)
                && (this.getX() >= SpriteGenerator.SIDE_BORDER_WIDTH + paddle.getSpeed() * 4);
    }

    /**
     * timePassed should notify the ball of passage of time in order to adjust ball
     * position in next frame. but it also checks the environment for possible collisions
     * in order to set the balls velocity to a new one and thus stay inside the screen
     * and bounce between game objects.
     */
    public void timePassed() {
        if (!this.activated) {
            return;
        }
        CollisionInfo collisionInfo = null;
        //generate a copy of the paddle by taking it from the first place of the environment.
        if (this.gameEnvironment != null) {
            if (this.gameEnvironment.getCollidables().get(0) instanceof Paddle) {
                Paddle paddle = (Paddle) this.gameEnvironment.getCollidables().get(0);

                //if the ball is in the paddle, move it out and adjust to it solely.
                if (this.isInPaddle(paddle)) {

                    //if the ball is within bounds, move by the paddles speed out of it.
                    if (this.isInBounds(paddle)) {
                        this.freeFromPaddle(paddle);
                        return;
                    }
                    //else, you moved towards the wall by the paddle, and disappear from the game.
                    this.notifyHit(this);
                }
            }

        /*
        create an ending point for the trajectory- twice the velocity direction, so that
        the ball will bounce back before hitting an object and adding the ball radius
        length(ball size) to the calculation as well.
         */
            Point end = new Point(this.getX() + this.velocity.getDx() + this.velocity.getSignDx(),
                    this.getY() + this.velocity.getDy() + this.velocity.getSignDy());
            Line trajectory = new Line(super.getCenter(), end);
            //get possible collision with game objects within trajectory length from ball.
            collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        }
        //if there was a collision, adjust a renewed velocity according to the found info.
        if (collisionInfo != null) {
            this.adjustVelocityToHit(collisionInfo);
        } else {
            //move the ball to the next location, regardless if there was a hit or not.
            this.moveOneStep();
        }
    }

    /**
     * class setter:
     * set the velocity of the ball to a new one, using a given velocity.
     *
     * @param v for the new wanted velocity.
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * set the velocity of the ball, using dx and dy double values.
     *
     * @param dx for the velocity to right/left.
     * @param dy for the velocity to up/down.
     */
    public void setVelocity(double dx, double dy) {
        //no need to generate new velocity, simply change this velocity using this methods.
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * set the environment of the ball: it may need to change throughout the game progression.
     *
     * @param environment the new environment of the game.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * class accessor: returns the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moveOneStep sets the ball's center point values according to its velocity.
     */
    public void moveOneStep() {
        super.setCenter(this.getVelocity().applyToPoint(super.getCenter()));
    }

    /**
     * this function adds the ball to a given game- it adds it to the list of sprites.
     *
     * @param game the game for the ball to be added to.
     */
    public void addToGame(GameLevel game) {
        //the ball is generally a sprite, add it to the game.
        game.addSprite(this);
    }

    /**
     * remove this ball from the game.
     *
     * @param game the game for the ball to be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify the list of listeners when a hit occurs, so they would make adjustments in the game.
     *
     * @param hitter the hitter in the collision event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this.gameEnvironment.getDeathRegion(), hitter);
        }
    }
}

