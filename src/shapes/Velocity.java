//Matan Shamir 206960239
package shapes;

/**
 * Class Velocity.
 * <p>
 * the class is mostly a part of bigger objects, that need to have certain
 * velocity on a screen. it specifies the change in location under relative
 * passage of time. the class supports velocity by degree and speed, and by
 * dx and dy values too. it also applies itself on a certain point.
 *
 * @author Matan Shamir.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * class constructor.
     *
     * @param dx the change in location relative to X axis.
     * @param dy the change in location relative to Y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * from angle and speed translates angle and speed into dx and dy
     * progression relative to X and Y axis's respectively. it translates
     * the variables and sends to the constructor to generate new velocity
     * and then return it.
     *
     * @param angle the angle of the movement.
     * @param speed the speed of the movement.
     * @return the new velocity, after the angle and speed are translated.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //convert the angle into radians.
        double angleRadians = Math.toRadians(angle);
        /*
        the calculation for dx and dy values is dividing the sin of the angle in
        radians and cos respectively.
         */
        double newDx = speed * Math.sin((angleRadians));
        double newDy = speed * Math.cos((angleRadians));
        //send -dy because y progression is opposite in gui dimension.
        return new Velocity(newDx, -newDy);
    }

    /**
     * class accessor.
     *
     * @return the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * class accessor.
     *
     * @return the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * class setter.
     *
     * @param newDx the new dx value to set the velocity to.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * class setter.
     *
     * @param newDy the new dy value to set the velocity to.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * applyToPoint receives a point and applies the velocity on it.
     * it generates a new point with the new values.
     *
     * @param p the point that the velocity should be applied to.
     * @return the new point after it has been applied with the velocity.
     */
    public Point applyToPoint(Point p) {
        //add the dx and dy values of the velocity to the current location.
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * apply the velocity on a given rectangle.
     *
     * @param rect the rectangle that's location needs to be changed.
     * @return the new rectangle after changing its place.
     */
    public Rectangle applyToRectangle(Rectangle rect) {
        //add the dx and dy values of the velocity to the current location.
        Point newUpperLeft = new Point(rect.getUpperLeft().getX() + this.dx, rect.getUpperLeft().getY());
        return new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight(), rect.getColor());
    }

    /**
     * @return the speed of the ball computed by the dx and dy.
     */
    public double getSpeed() {
        //Pythagorean calculation of the third line of the triangle of the dx-dy.
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    public double getAngle() {
        double radians = Math.asin(dx/this.getSpeed());
        return radians * (180/Math.PI);
    }

    /**
     * @return the sign of the dx.
     */
    public int getSignDx() {
        if (this.dx < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * @return the sign of the dx.
     */
    public int getSignDy() {
        if (this.dy < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
