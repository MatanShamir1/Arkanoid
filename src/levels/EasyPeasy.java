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
 * class Easy Peasy is another information of the game. it is responsible for the second level and returns an
 * information concerning amount of balls, velocities, background, and blocks.
 *
 * @author Matan Shamir.
 */
public class EasyPeasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //initiate using angle and speed.
        List<Velocity> velocityList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(300 + i * 13, 6));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return GameLevel.SCREEN_WIDTH / 3 * 2;
    }

    @Override
    public String levelName() {
        return "Easy Peasy";
    }

    @Override
    public Sprite getBackground() {
        //these are for further easier usage.
        int centerX = 120;
        int centerY = 130;
        Point center = new Point(centerX, centerY);
        //create a list of shapes for the background.
        List<Shape> shapes = new LinkedList<>();
        Shape back = new Rectangle(new Point(0, 0), GameLevel.SCREEN_WIDTH,
                GameLevel.SCREEN_HEIGHT, Color.WHITE);
        shapes.add(back);
        int endY = 250;
        Color color = new Color(237, 229, 175);

        //this loop creates the lines of the "sun".
        for (int i = 0; i < 100; i++) {
            shapes.add(new Line(center, new Point(i * 7, endY), color));
        }
        //these circles are the sun.
        Shape big = new Circle(center, 55, color, color);
        shapes.add(big);
        color = new Color(234, 213, 72);
        Shape medium = new Circle(center, 45, color, color);
        shapes.add(medium);
        color = new Color(253, 223, 24);
        Shape small = new Circle(center, 35, color, color);
        shapes.add(small);
        //return the list of shapes.
        return new Background(shapes);
    }

    @Override
    public List<Block> blocks() {
        //create a list of blocks.
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};
        List<Block> blocksList = new LinkedList<>();
        int len = (GameLevel.SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH * 2) / 15;

        //this is the loop that creates wach block one after the other.
        for (int i = 0; i < 15; i++) {
            blocksList.add(new Block(new Point(SpriteGenerator.SIDE_BORDER_WIDTH + i * len,
                    250), len, Block.BLOCK_HEIGHT, colors[i]));
        }
        //return the list of blocks.
        return blocksList;
    }

    @Override
    public List<Ball> balls() {
        //these variables are for further easier usage.
        int centerX = (int) (GameLevel.SCREEN_WIDTH / 2);
        int centerY = GameLevel.SCREEN_HEIGHT - SpriteGenerator.HORIZON_BORDER_HEIGHT
                - Paddle.PADDLE_HEIGHT - Ball.BALL_RADIUS;
        List<Ball> ballList = new LinkedList<>();

        /*
        initiate balls in the required positions with 0 velocities: after the game is initiated,
        ball velocities will be set to the list above.
         */
        for (int i = 0; i < 10; i++) {
            ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        }
        return ballList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public Color countdownColor() {
        return Color.BLACK;
    }
}
