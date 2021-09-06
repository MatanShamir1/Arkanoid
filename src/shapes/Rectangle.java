//Matan Shamir 206960239
package shapes;

import biuoop.DrawSurface;
import sprites.Shape;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Block.
 * <p>
 * the block class is a rectangle in the game: it is a colloidal for the ball to run into, and
 * a general sprite in the game. it has different operations such as telling the ball what
 * to do when it runs into it. it is combined by a rectangle and uses its methods widely.
 *
 * @author Matan Shamir.
 */
public class Rectangle implements Shape {
    //the components of the rectangle- four lines defining its borders.
    private final Line left;
    private final Line right;
    private final Line upper;
    private final Line lower;
    private final java.awt.Color color;

    /**
     * class constructor- with a given point, width and length.
     *
     * @param upperLeft the upper left point for the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectngle.
     * @param color     the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        //compute the points of the rectangle, by the height and width.
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(lowerLeft.getX() + width, lowerLeft.getY());
        Point upperRight = new Point(lowerRight.getX(), lowerRight.getY() - height);
        //set the lines to be the ones that connects the points.
        this.left = new Line(lowerLeft, upperLeft);
        this.right = new Line(lowerRight, upperRight);
        this.upper = new Line(upperLeft, upperRight);
        this.lower = new Line(lowerLeft, lowerRight);
        this.color = color;
    }

    /**
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * @return the upper line of the rectangle
     */
    public Line getUpper() {
        return this.upper;
    }

    /**
     * @return the lower line of the rectangle
     */
    public Line getLower() {
        return this.lower;
    }

    /**
     * intersectionPoints returns a possibly empty list of intersections of the
     * rectangle with a given line.
     *
     * @param line the line to check intersections with
     * @return a list of the intersections. note we use list and not linked list
     * to keep encapsulation from the user.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //first, check all possible intersections with each rectangle edge apart.
        Point intersectionLeft = this.left.intersectionWith(line);
        Point intersectionRight = this.right.intersectionWith(line);
        Point intersectionUpper = this.upper.intersectionWith(line);
        Point intersectionLower = this.lower.intersectionWith(line);
        //create linked list for a more time and space effective possible change of size.
        List<Point> intersectionsList = new LinkedList<>();

        /*
        for each intersection, if it isn't null- meaning the line intersects
        with the edge, add it to the list.
         */
        if (intersectionLeft != null) {
            intersectionsList.add(intersectionLeft);
        }
        if (intersectionUpper != null) {
            intersectionsList.add(intersectionUpper);
        }
        if (intersectionRight != null) {
            intersectionsList.add(intersectionRight);
        }

        if (intersectionLower != null) {
            intersectionsList.add(intersectionLower);
        }
        //return the list after adding all possible intersections.
        return intersectionsList;
    }

    /**
     * getWidth returns the width of the rectangle in pixels.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.upper.length();
    }

    /**
     * getHeight returns the height of the rectangle in pixels.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.left.length();
    }

    /**
     * this function returns the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upper.start();
    }

    /**
     * this function returns the lower right point of the rectangle.
     *
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.right.start();
    }

    /**
     * this function returns the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.right.end();
    }

    /**
     * this function returns the lower left point of the rectangle.
     *
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lower.start();
    }

    /**
     * class getter.
     *
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void drawBeneath(DrawSurface d) {
        //set all needed knowledge from the class fields: point, width and height.
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        //set the color of the drawing to be the ball color.
        d.setColor(this.color);
        //draw the rectangle on the surface using its fields.
        d.fillRectangle(x, y, (int) this.upper.length(), (int) this.left.length());
    }
}