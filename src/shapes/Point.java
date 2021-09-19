//Matan Shamir 206960239
package shapes;

import game.GameEnvironment;
import sprites.Collidable;

/**
 * Class Point.
 * <p>
 * the class supports basic details management that a point contains:
 * setters, getters, constructors, and more actions concerning different points.
 * a point is the most basic type constructing different elements.
 *
 * @author Matan Shamir.
 */
public class Point {
    //create a very small number, call it epsilon.
    public static final double EPSILON = Math.pow(10, -2);
    //the number zero, for further calculations.
    static final int ZERO = 0;

    //the x value of a a point
    private final double x;

    //the y value of a a point
    private final double y;

    /**
     * class constructor.
     *
     * @param x for the x value of the point.
     * @param y for the y value of the point.
     *          the values build the coordination of the point together.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * measure the distance in pixels from the point to another one.
     *
     * @param other the other point that we measure the distance from.
     * @return the distance from 'other' in pixels.
     */
    public double distance(Point other) {
        //insert into comfortable variables the x and y values of two points.
        double x1 = other.getX();
        double y1 = other.getY();
        double x2 = this.x;
        double y2 = this.y;
        //to return the distance between them, calculate sqrt of (x1-x2)^2+(y1-y2)^2
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * determine whether or not two given points are identical.
     * create a room for errors - level of precision isn't always perfect.
     *
     * @param other the other point that the method receives.
     * @return 'true' for equal points, 'false' otherwise.
     */
    public boolean equals(Point other) {
        //if the point is in range of epsilon, it is precise enough.
        if (Math.abs(other.getX() - this.getX()) < EPSILON) {
            if (Math.abs(other.getY() - this.getY()) < EPSILON) {
                return true;
            }
        }
        return (other.getX() == this.x) && (other.getY() == this.y);
    }

    /**
     * class accessor.
     *
     * @return the X value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * class accessor.
     *
     * @return the Y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * determine whether or not a point is on a given line.
     *
     * @param line the line we check if the point is on.
     * @return 'true' for a point on line and 'false' otherwise.
     */
    public boolean isPointOnLine(Line line) {

        /*
        edge cases- if the line is parallel to the Y axis, check if the
        point is on the segment of the line.
         */
        if (line.getSlope() == Line.INF) {
            return ((this.getX() == line.start().getX()) && (line.isInSegment(this)));

            //if the line is parallel to X axis, check if its in segment.
        } else if (line.getSlope() == ZERO) {
            return ((this.getY() == line.start().getY()) && (line.isInSegment(this)));

        /*
        the general case- if y=ax+b, as it fits the equation of the line, and
        also in the segment of the line.
         */
        } else {
            return ((this.getY() - line.getSlope() * this.getX() + line.getB() < EPSILON)
                    && (this.getY() - line.getSlope() * this.getX() + line.getB() > EPSILON)
                    && (line.isInSegment(this)));
        }
    }

    /**
     * in case the ball hit a multiple amount of collidables at once, a corner or
     * a line, it needs to change its velocity differently. this method checks if
     * this is the situation or not for the ball's time passed method.
     *
     * @param environment the environment for crossing knowledge of the hit point on different
     *                    objects that the ball has hit.
     * @return the number of collidables that the ball has hit.
     */
    public int diffCollidablesAmount(GameEnvironment environment) {
        //set "count" as the number of objects the ball has hit.
        int count = 0;

        //iterate through the whole list and find all of the collisions.
        for (Collidable collidable : environment.getCollidables()) {
            Rectangle rect = collidable.getCollisionRectangle();
            /*
            the only case in which the intersection point is on multiple blocks, it hits a corner
            between them. so for each block check if the point is its corner. only check the corners,
            because this is the only chance this would happen.
             */
            if ((this.equals(rect.getUpperRight())) || (this.equals(rect.getUpperLeft()))
                    || (this.equals(rect.getLowerRight())) || (this.equals(rect.getLowerLeft()))) {
                count++;
            }
        }
        return count;
    }
}
