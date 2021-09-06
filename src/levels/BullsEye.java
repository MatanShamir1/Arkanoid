//Matan Shamir 206960239
package levels;

import game.GameLevel;
import game.SpriteGenerator;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import shapes.Circle;
import sprites.Background;
import sprites.Block;
import sprites.Shape;
import sprites.Sprite;
import shapes.Ball;
import sprites.Paddle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * class BullsEye is an information for the game. here all of the information for a level is created:
 * the ball velocities, balls, blocks, and background.
 *
 * @author Matan Shamir.
 */
public class BullsEye implements LevelInformation {
    @Override
    public int numberOfBalls() {
        //only one ball in this level.
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //let the ball go up to hit the only  block directly.
        List<Velocity> velocityList = new LinkedList<>();
        velocityList.add(new Velocity(0, -6));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        //speed of paddle is 5.
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Bulls Eye";
    }

    @Override
    public Sprite getBackground() {
        //these are for further easier writing.
        int centerX = (int) (GameLevel.SCREEN_WIDTH / 2);
        int centerY = (int) (GameLevel.SCREEN_HEIGHT / 3) + 15;
        int lineLen = 115;
        Point center = new Point(centerX, centerY);
        //create a list of shapes for the background.
        List<Shape> shapes = new LinkedList<>();
        //generatee three circles and add them to the list- a direct hit sign.
        Shape back = new Rectangle(new Point(0, 0), GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT, Color.BLACK);
        shapes.add(back);
        Shape big = new Circle(center, 120, Color.BLACK, Color.BLUE);
        shapes.add(big);
        Shape medium = new Circle(center, 90, Color.BLACK, Color.BLUE);
        shapes.add(medium);
        Shape small = new Circle(center, 60, Color.BLACK, Color.BLUE);
        shapes.add(small);
        Shape upLine = new Line(new Point(centerX, 70),
                new Point(centerX, 70 + lineLen), Color.BLUE);
        shapes.add(upLine);
        Shape rightLine = new Line(new Point(centerX + 30, centerY),
                new Point(centerX + 30 + lineLen, centerY), Color.BLUE);
        shapes.add(rightLine);
        Shape downLine = new Line(new Point(centerX, 245),
                new Point(centerX, 245 + lineLen), Color.BLUE);
        shapes.add(downLine);
        Shape leftLine = new Line(new Point(centerX - 140, centerY),
                new Point(centerX - 140 + lineLen, centerY), Color.BLUE);
        shapes.add(leftLine);
        //return a new background with all the shapes.
        return new Background(shapes);
    }

    @Override
    public List<Block> blocks() {
        //the list of blocks is only one little red block!
        List<Block> blocksList = new LinkedList<>();
        blocksList.add(new Block(new Point((int) (GameLevel.SCREEN_WIDTH / 2) - 15,
                (int) (GameLevel.SCREEN_HEIGHT / 3)), 30, 30, Color.RED));
        return blocksList;
    }

    @Override
    public List<Ball> balls() {
        //generate a ball on top of the paddle.
        List<Ball> ballList = new LinkedList<>();
        ballList.add(new Ball(GameLevel.SCREEN_WIDTH / 2, GameLevel.SCREEN_HEIGHT
                - SpriteGenerator.HORIZON_BORDER_HEIGHT - Paddle.PADDLE_HEIGHT - Ball.BALL_RADIUS,
                Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        return ballList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Color countdownColor() {
        return Color.WHITE;
    }
}
