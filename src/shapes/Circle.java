//Matan Shamir 206960239
package shapes;

import biuoop.DrawSurface;
import sprites.Shape;

import java.awt.Color;

/**
 * class Circle is a specific shape, a more basic "ball" for a simpler drawing.
 * @author Matan Shamir.
 */
public class Circle implements Shape {
    private final Color innerColor;
    private final Color outColor;
    private final int radius;
    private Point center;

    /**
     * class constructor.
     *
     * @param center     the center of the circle.
     * @param radius     the radius of the circle.
     * @param innerColor the inner color of the circle.
     * @param outColor   the color that defines the edge of the circle.
     */
    public Circle(Point center, int radius, Color innerColor, Color outColor) {
        this.center = center;
        this.radius = radius;
        this.innerColor = innerColor;
        this.outColor = outColor;
    }

    /**
     * class getter.
     *
     * @return the center of the circle for class ball usage, and further drawings.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * class getter.
     *
     * @return the inner color.
     */
    public Color getInnerColor() {
        return innerColor;
    }

    /**
     * class setter.
     *
     * @param center1 set the center of the circle, to be able to change the ball later!
     *               because a ball is basically a circle wrapped with more abilities.
     */
    protected void setCenter(Point center1) {
        this.center = center1;
    }

    @Override
    public void drawBeneath(DrawSurface d) {
        /*
        set the location of the ball on the screen to be the coordinates of the ball.
        also cast to int so it will be in pixel size.
         */
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        //set the color of the drawing to be the ball color.
        d.setColor(this.innerColor);
        //draw the ball on the screen using the details and the radius.
        d.fillCircle(x, y, this.radius);
        //fill a small circle in red inside of the ball for design.
        d.setColor(this.outColor);
        d.drawCircle(x, y, this.radius);
    }
}
