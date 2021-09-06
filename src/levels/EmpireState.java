//Matan Shamir 206960239
package levels;

import game.GameLevel;
import game.SpriteGenerator;
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
 * class Empire State is another information of the game. it is responsible for the second level and returns an
 * information concerning amount of balls, velocities, background, and blocks.
 *
 * @author Matan Shamir.
 */
public class EmpireState implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //create a list of the velocities for the balls.
        List<Velocity> velocityList = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(315 + i * 90, 6));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return new String("Empire State");
    }

    @Override
    public Sprite getBackground() {
        //create a new list of shapes for the background to possess.
        List<sprites.Shape> shapes = new LinkedList<>();
        Color color = new Color(39, 101, 29);
        sprites.Shape back = new Rectangle(new Point(0, 0), GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT, color);
        shapes.add(back);
        color = new Color(88, 88, 88);
        Shape small = new Rectangle(new Point(105, 210), 12, 200, color);
        shapes.add(small);
        color = new Color(56, 55, 55);
        sprites.Shape medium = new Rectangle(new Point(95, 400), 30, 70, color);
        shapes.add(medium);
        color = new Color(31, 31, 31);
        sprites.Shape big = new Rectangle(new Point(60, 450), 100, 180, color);
        shapes.add(big);
        color = Color.WHITE;

        //create a new matrix of shapes for the windows on the building.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                shapes.add(new Rectangle(new Point(67 + j * 19, 458 + i * 30), 10, 22, color));
            }
        }
        int centerX = 110;
        int centerY = 215;
        Point center = new Point(centerX, centerY);
        color = new Color(205, 156, 59);
        Shape big1 = new Circle(center, 10, color, color);
        shapes.add(big1);
        color = new Color(217, 79, 46);
        Shape medium1 = new Circle(center, 6, color, color);
        shapes.add(medium1);
        color = Color.WHITE;
        Shape small1 = new Circle(center, 2, color, color);
        shapes.add(small1);
        //return the new background with the list of shapes.
        return new Background(shapes);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new LinkedList<>();
        int numLines = 5;
        //generate an array of colors for each row to be painted differently.
        Color[] arr = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        //generate a line of blocks in each iteration:
        for (int i = 0; i < numLines; i++) {
            Color color = arr[i];
            //the y value of each line is the same: set it to be j.
            int j = GameLevel.SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH - Block.BLOCK_WIDTH;

            //in each line: determine x value for each block one by one.
            for (; j > i * Block.BLOCK_WIDTH + 5 * Block.BLOCK_WIDTH; j -= Block.BLOCK_WIDTH) {
                //generate the block and add it to the game environment.
                blocksList.add(new Block(new Point(j, SpriteGenerator.BLOCK_LINES_START + Block.BLOCK_HEIGHT * i),
                        Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, color));
            }
        }
        return blocksList;
    }

    @Override
    public List<Ball> balls() {
        //initiate a new list of balls, set these variables for further easier usage.
        int centerX = (int) GameLevel.SCREEN_WIDTH / 2;
        int centerY = GameLevel.SCREEN_HEIGHT - SpriteGenerator.HORIZON_BORDER_HEIGHT
                - Paddle.PADDLE_HEIGHT - Ball.BALL_RADIUS;
        List<Ball> ballList = new LinkedList<>();
        ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        return ballList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public Color countdownColor() {
        return Color.WHITE;
    }
}
