//Matan Shamir 206960239
package shapes;

import biuoop.DrawSurface;
import sprites.Shape;

import java.awt.Color;

/**
 * Class Line.
 * <p>
 * the class supports basic actions and detail management for a Line, made of 2 points.
 * the class supports operations such as length, line setters and getters, the slope
 * of the line, its equation components, and different methods concerning the
 * possible intersection of two different lines in space- endless or limited.
 *
 * @author Matan Shamir.
 */

public class Line implements Shape {
    /*
   infinity is a usable number in this class because it means that the
    slope of a certain line is parallel to the Y axis.
     */
    static final double INF = Double.POSITIVE_INFINITY;

    /*
    zero is a usable number in this class because it means that the
    slope of a certain line is parallel to the X axis.
     */
    static final int ZERO = 0;

    //the starting point of a line.
    private Point start;

    //the ending point of a line.
    private Point end;

    private Color color;

    /**
     * class constructor- two given points.
     *
     * @param start for the starting point of the line.
     * @param end   for the ending point of the line.
     *              the two points connect together to a line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = Color.BLACK;
    }

    /**
     * class constructor with a specific color.
     *
     * @param start the staring point of the line.
     * @param end   the ending point of the line.
     * @param color the color of the line.
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * class constructor- 4 integer coordination.
     *
     * @param x1 the x value of the first point.
     * @param y1 the y value of the first point.
     * @param x2 the x value of the second point.
     * @param y2 the y value of the second point.
     *           two points are created and being set to form a Line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //generate new points from the values and set them to start and end.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * class constructor with a specific color.
     *
     * @param x1    the x value of the first point.
     * @param y1    the y value of the first point.
     * @param x2    the x value of the second point.
     * @param y2    the y value of the second point.
     * @param color the color of the line.
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        //generate new points from the values and set them to start and end.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    /**
     * measure the length in pixels from the start point to the end.
     *
     * @return the length of the line using class Point's 'distance' method.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * class getter.
     *
     * @return the starting point of the Line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * class getter.
     *
     * @return the ending point of the Line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * class setter: replace between starting and ending point altogether.
     */
    public void replacePoints() {
        //a regular replacement method, implemented with temporary variable.
        Point temp = this.start;
        this.start = this.end;
        this.end = temp;
    }

    /**
     * setLine: determine whether or not the line's points need to be
     * replaced, if so- replace them. otherwise, do nothing and return.
     * the starting point is preferably the one with the lower 'x' value.
     * if the 'x' values are the same, determine by lower 'y' value.
     */
    public void setLine() {
        // if 'x' values are the same- replace the points if start's y is by chance smaller.
        if (this.start.getX() == this.end.getX()) {
            if (this.start.getY() > this.end.getY()) {
                this.replacePoints();
            }
        }
        //if the 'x' values are different, replace only if start's x is by chance bigger.
        if (this.start.getX() > this.end.getX()) {
            this.replacePoints();
        }
    }

    /**
     * this method calculates the value of the slope of its line.
     *
     * @return the double value of the slope of the line.
     */
    public double getSlope() {
        //the calculation is y2-y1/x2-x1, for the slope of the line.
        double slope = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        //if the slope is -inf, for further calculations ease, set it to be inf.
        if (slope == -INF) {
            slope = -slope;
        }
        return slope;
    }

    /**
     * the 'B' value of the line, which is the intersection with Y axis.
     *
     * @return the infinite intersection with Y axis.
     */
    public double getB() {
        //the calculation of a Y axis is: y=ax+b <--> b=y-ax
        return this.start.getY() - (this.getSlope() * this.start.getX());
    }

    /**
     * return the possible point of an intersection between parallel lines.
     *
     * @param cThis  the first line.
     * @param cOther the second line.
     * @return null for no intersection, point of intersection for intersecting lines.
     */
    public Point parallelIntersection(Line cThis, Line cOther) {
        //in case the lines are consecutive, they meet in one point and so intersecting.
        if (cThis.start.equals(cOther.end)) {
            return cThis.start;
            //the other case for which the lines may be consecutive:
        } else if (cThis.end.equals(cOther.start)) {
            return cThis.end;
        /*
        otherwise, the lines are not consecutive and for so dont intersect:
        they may be colliding or far from each one, but they dont intersect.
         */
        } else {
            return null;
        }
    }

    /**
     * check if a given point is in the segment of the line.
     *
     * @param p for the point to be checked if its on the line.
     * @return 'true' for point in segment, 'false' otherwise.
     */
    public boolean isInSegment(Point p) {
        //calculate the max and min values of the line first to know segments.
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        //check whether or not the x and y values of the point are in between.
        return (((minX <= p.getX()) && (p.getX() <= maxX))
                && ((minY <= p.getY()) && (p.getY() <= maxY)));
    }

    /**
     * check if a given line is intersecting with our line.
     *
     * @param other for the other line to be checked if its intersecting.
     * @return 'true' for intersecting lines, 'false' otherwise.
     */
    public boolean isIntersecting(Line other) {

        /*
        the edge case is when the lines' slope and Y axis intersection are the same.
        it means that their equation is identical and because of that they may not
        intersect in only one point(parallel intersection), but they could collide.
        for that option, in which the intersection point is null, check if one line's
        edge is in the segment of the other and if so, return 'true' for intersecting.
         */
        if ((this.getSlope() == other.getSlope()) && (this.getB() == other.getB())) {
            Line cThis = new Line(this.start, this.end);
            Line cOther = new Line(other.start, other.end);
            cThis.setLine();
            cOther.setLine();
            if (cThis.isInSegment(cOther.end) || (cOther.isInSegment(cThis.end))) {
                return true;
            }
        }
        /*
        else, the line's equation is different, and an intersection is plausible.
        if the value returned is null, return false. otherwise an intersection has
        been found, and return true.
         */
        return this.intersectionWith(other) != null;
    }

    /**
     * check and return the intersection between two infinite lines using their equation.
     *
     * @param cThis  for the copy of this line to check the intersection.
     * @param cOther for the copy of the other line.
     * @return the intersection point between two endless given lines.
     */
    public Point findInfiniteIntersection(Line cThis, Line cOther) {
        /*
        first, find the lines equations to check if they collide when the lines are
        endless. in order to do so, find slope and y axis intersection for each one.
        */
        double slopeOther = cOther.getSlope();
        double slopeThis = cThis.getSlope();
        double intersectionX;
        double intersectionY;

        /*
         the only case in which their slope is the same and they intersect is
         if one of them continues the other(and doesn't cover it at all):
         */
        if (slopeThis == slopeOther) {
            //this function checks if the lines have only 1 combined point:
            return parallelIntersection(cThis, cOther);

        /*
        if one line is parallel to Y, it has no 'b' in the equation and thus
        we need to calculate the intersection differently:
         */
        } else if (slopeThis == INF) {
            //the intersection is in the x value of the parallel line.
            intersectionX = cThis.start.getX();
            //simply put the x value in the other line equation and set it.
            intersectionY = slopeOther * intersectionX + cOther.getB();

            //if the other one is parallel to Y axis, do the same for it.
        } else if (slopeOther == INF) {
            intersectionX = cOther.start.getX();
            intersectionY = slopeThis * intersectionX + cThis.getB();

            //in this case, all values exist, and the regular calculation is made:
        } else {
            //calculate the y axis intersections of the line for further calculation.
            double bOther = cOther.getB();
            double bThis = cThis.getB();
            /*
            this is an explanation of the equation we calculate:
            mx+b=m'x+b' --> mx-m'x=b'-b --> (m-m')x=b'-b --> x=(b'-b)/(m-m')
             */
            intersectionX = (bOther - bThis) / (slopeThis - slopeOther);
            //to find y, simply plug the x that was found into mx+b of one of the lines.
            intersectionY = slopeThis * intersectionX + bThis;
        }
        return new Point(intersectionX, intersectionY);
    }

    /**
     * find the possible point of intersecting between two lines.
     *
     * @param other for the other line to check if it intersects with our line.
     * @return point of intersection for intersecting lines, null otherwise.
     */
    public Point intersectionWith(Line other) {
        //first, generate copies and arrange the lines so the start x value is smaller.
        Line cThis = new Line(this.start, this.end);
        Line cOther = new Line(other.start, other.end);
        cThis.setLine();
        cOther.setLine();

        //if both lines are actually a point, check if they're the same.
        if ((cThis.length() == ZERO) && (cOther.length() == ZERO)) {
            if (cThis.equals(cOther)) {
                return cThis.start;
            } else {
                return null;
            }

            //if only one of them is a point- check if the point is on the line.
        } else if (cThis.length() == ZERO) {
            if (cThis.start.isPointOnLine(cOther)) {
                return cThis.start;
            } else {
                return null;
            }

            //do for both cases.
        } else if (cOther.length() == ZERO) {
            if (cOther.start.isPointOnLine(cThis)) {
                return cOther.start;
            } else {
                return null;
            }

            //otherwise, both are actual lines and check intersection:
        } else {
            //find the intersection between the lines if they were endless.
            Point intersectionP = findInfiniteIntersection(cThis, cOther);

            //now check if the intersection is within both line segments.
            if ((intersectionP == null) || (!((cThis.isInSegment(intersectionP))
                    && (cOther.isInSegment(intersectionP))))) {
                return null;
            } else {
                return intersectionP;
            }
        }
    }

    /**
     * check if a given line is equal to our line.
     *
     * @param other the other line to compare with this line.
     * @return 'true' for equal lines, 'false' otherwise.
     */
    public boolean equals(Line other) {
        //check one by one, the start and the end of the line.
        return other.start.equals(this.start) && other.end.equals(this.end)
                || other.start.equals(this.end) && other.end.equals(this.start);
    }

    /**
     * find the closest intersection to the start of this line with a given rectangle.
     *
     * @param rect the rectangle for the line to find the intersection with.
     * @return the closest intersection to start of line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //create a list of all intersections after calling a method and finding them.
        java.util.List<Point> intersectionsList = rect.intersectionPoints(this);

        //if its empty there is no closest one, return null.
        if (intersectionsList.isEmpty()) {
            return null;
        }

        //set the first one to be the closest first.
        Point closest = intersectionsList.get(0);

        //check all others- if they are closer, set them as closest.
        for (Object point : intersectionsList) {
            if (this.start.distance((Point) point) < this.start.distance(closest)) {
                closest = (Point) point;
            }
        }
        //return the closest one.
        return closest;
    }

    /**
     * draw this line on a given draw surface using details from the class.
     *
     * @param d for the draw surface for the line to be drawn on.
     */

    @Override
    public void drawBeneath(DrawSurface d) {
        //extract the parameters for the draw surface from the line definitions.
        int x1 = (int) this.start().getX();
        int x2 = (int) this.end().getX();
        int y1 = (int) this.start().getY();
        int y2 = (int) this.end().getY();
        //set the color to be the given color.
        d.setColor(this.color);
        //draw the line on the draw surface.
        d.drawLine(x1, y1, x2, y2);
    }
}
