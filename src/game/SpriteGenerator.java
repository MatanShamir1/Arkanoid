//Matan Shamir 206960239
package game;

import levels.LevelInformation;
import shapes.Ball;
import shapes.Point;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;

import java.awt.Color;

/**
 * Class spriteGenerator.
 * <p>
 * this class is in charge of generating different objects for initializing the game.
 * it makes it easier for the initialize method to simply change the properties
 * without trying to understand lots of code.
 *
 * @author Matan Shamir.
 */
public class SpriteGenerator {
    //properties for the borders and objects to know where to be generated in.
    public static final int SIDE_BORDER_WIDTH = 25;
    public static final int HORIZON_BORDER_HEIGHT = 22;
    public static final int BLOCK_LINES_START = 150;
    private final GameLevel game;
    private final LevelInformation info;

    /**
     * class constructor.
     *
     * @param game        the game to generate sprites to.
     * @param information of the game to know details about sprite creation.
     */
    public SpriteGenerator(GameLevel game, LevelInformation information) {
        this.game = game;
        this.info = information;
    }

    /**
     * generateBorders adds blocks with default properties to the game, without choosing
     * its place: because a game has to have borders.
     */
    public void generateBorders() {
        //generate borders in the side of the screen.
        Block upBorder = new Block(new Point(0, 0), GameLevel.SCREEN_WIDTH, HORIZON_BORDER_HEIGHT * 2, Color.GRAY);
        Block leftBorder = new Block(new Point(0, 0), SIDE_BORDER_WIDTH, GameLevel.SCREEN_HEIGHT, Color.GRAY);
        Block rightBorder = new Block(new Point(GameLevel.SCREEN_WIDTH - SIDE_BORDER_WIDTH, 0),
                SIDE_BORDER_WIDTH, GameLevel.SCREEN_HEIGHT, Color.GRAY);
        //add them all to the game environment.
        upBorder.addToGame(this.game);
        leftBorder.addToGame(this.game);
        rightBorder.addToGame(this.game);
        this.game.getEnvironment().getDeathRegion().addToGame(this.game);
    }

    /**
     * generate a new score indicator for the game and add it to the game.
     */
    public void generateIndicator() {
        //initiate a new indicator in the borders of the game.
        ScoreIndicator score = new ScoreIndicator(new Point(0, 0), GameLevel.SCREEN_WIDTH, HORIZON_BORDER_HEIGHT,
                this.game.getScore(), Color.lightGray, this.info.levelName(), this.game.getLives());
        //add it to the game.
        score.addToGame(this.game);

    }

    /**
     * generateBlocks generate lines of blocks aligned one below the other, when
     * each line is one block shorter then the one above.
     */
    public void generateBlocks() {
        if (this.info.blocks() == null) {
            return;
        }
        for (int i = 0; i < this.info.numberOfBlocksToRemove(); i++) {
            Block block = this.info.blocks().get(i);
            //for every block created, add it to the game's block remover list.
            block.addHitListener(this.game.getBlockRemover());
            //also add the block to the score listener for it to be able to keep track of the score.
            block.addHitListener(this.game.getScore());
            block.addToGame(this.game);
        }
    }

    /**
     * generatePaddle generates a moving block in the game exactly in the middle
     * of the screen above the lower border.
     */
    public void generatePaddle() {
        int paddleWidth = this.info.paddleWidth();
        //use properties from the game to know where to generate the paddle.
        Paddle paddle = new Paddle(new Point((int) (GameLevel.SCREEN_WIDTH / 2) - (int) (paddleWidth / 2),
                GameLevel.SCREEN_HEIGHT - HORIZON_BORDER_HEIGHT - Paddle.PADDLE_HEIGHT), paddleWidth,
                Paddle.PADDLE_HEIGHT, this.game.getKeyboard(), Color.ORANGE,
                this.game.getInformation().paddleSpeed());
        //add it to the game environment.
        paddle.addToGame(this.game);
    }

    /**
     * generate the balls for the game level according top the given info.
     */
    public void generateBalls() {
        if (this.info.balls() == null) {
            return;
        }
        //else, iterate through required num of balls to be generated and added:
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            //set each one below the other and add them to the game environment.
            Ball ball = info.balls().get(i);
            ball.setEnvironment(this.game.getEnvironment());
            ball.setVelocity(info.initialBallVelocities().get(i));
            ball.addToGame(this.game);
            //for each ball, add the game's ball remover as a listener.
            ball.addHitListener(this.game.getBallRemover());
            this.game.getRemainingBalls().increase(1);
        }
    }
}
