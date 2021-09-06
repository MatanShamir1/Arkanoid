//Matan Shamir 206960239
package levels;

import game.GameLevel;
import game.SpriteGenerator;
import shapes.Line;
import shapes.Point;
import shapes.Velocity;
import shapes.Circle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;
import shapes.Ball;
import sprites.Paddle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * class Final Level is another information of the game. it is responsible for the second level and returns an
 * information concerning amount of balls, velocities, background, and blocks.
 * @author Matan Shamir.
 */
public class FinalLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //initiate velocities of the balls.
        List<Velocity> velocityList = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(315 + i * 45, 6));
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
        return "Final Level";
    }

    @Override
    public Sprite getBackground() {
        //create a list of shapes for the background to possess.
        List<sprites.Shape> shapes = new LinkedList<>();
        Color color = new Color(87, 139, 205);
        sprites.Shape back = new shapes.Rectangle(new shapes.Point(0, 0),
                GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT, color);
        shapes.add(back);
        Point middlePoint = new Point(205, 345);
        Point endPoint = new Point(170, 600);
        color = new Color(205, 203, 203);

        //create the "rain" of the clouds.
        for (int i = 0; i < 10; i++) {
            shapes.add(new Line(middlePoint.getX() + i * 10, middlePoint.getY(),
                    endPoint.getX() + 10 * i, endPoint.getY(), color));
        }
        //create default clouds on the screen made by the circles.
        sprites.Shape shape1 = new Circle(middlePoint, 25, color, color);
        shapes.add(shape1);
        sprites.Shape shape2 = new Circle(new Point(220, 370), 27, color, color);
        shapes.add(shape2);
        color = new Color(161, 159, 159);
        sprites.Shape shape3 = new Circle(new Point(235, 340), 25, color, color);
        shapes.add(shape3);
        color = new Color(154, 152, 152);
        sprites.Shape shape4 = new Circle(new Point(250, 375), 25, color, color);
        shapes.add(shape4);
        sprites.Shape shape5 = new Circle(new Point(275, 350), 32, color, color);
        shapes.add(shape5);
        Point middlePoint2 = new Point(505, 395);
        Point endPoint2 = new Point(455, 600);
        color = new Color(205, 203, 203);
        for (int i = 0; i < 10; i++) {
            shapes.add(new Line(middlePoint2.getX() + i * 10, middlePoint2.getY(),
                    endPoint2.getX() + 10 * i, endPoint2.getY(), color));
        }
        sprites.Shape shape6 = new Circle(new Point(505, 395), 25, color, color);
        shapes.add(shape6);
        sprites.Shape shape7 = new Circle(new Point(520, 420), 27, color, color);
        shapes.add(shape7);
        color = new Color(161, 159, 159);
        sprites.Shape shape8 = new Circle(new Point(535, 390), 25, color, color);
        shapes.add(shape8);
        color = new Color(154, 152, 152);
        sprites.Shape shape9 = new Circle(new Point(550, 425), 25, color, color);
        shapes.add(shape9);
        sprites.Shape shape10 = new Circle(new Point(575, 400), 32, color, color);
        shapes.add(shape10);
        //return a new background with all the required shapes in it.
        return new Background(shapes);
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        List<Block> blocksList = new LinkedList<>();
        int len = (GameLevel.SCREEN_WIDTH - SpriteGenerator.SIDE_BORDER_WIDTH * 2) / 15;

        //a loop to generate a matrix of blocks in the screen width.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                blocksList.add(new Block(new Point(SpriteGenerator.SIDE_BORDER_WIDTH + j * len,
                        100 + i * Block.BLOCK_HEIGHT), len, Block.BLOCK_HEIGHT, colors[i]));
            }
        }
        return blocksList;
    }

    @Override
    public List<Ball> balls() {
        int centerX = (int) GameLevel.SCREEN_WIDTH / 2;
        int centerY = GameLevel.SCREEN_HEIGHT - SpriteGenerator.HORIZON_BORDER_HEIGHT
                - Paddle.PADDLE_HEIGHT - Ball.BALL_RADIUS;
        List<Ball> ballList = new LinkedList<>();
        ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        ballList.add(new Ball(centerX, centerY, Ball.BALL_RADIUS, Color.WHITE, new Velocity(0, 0)));
        return ballList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public Color countdownColor() {
        return Color.WHITE;
    }
}
