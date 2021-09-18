//Matan Shamir 206960239
package game;

import biuoop.KeyboardSensor;
import gamelogic.Animation;
import gamelogic.AnimationRunner;
import gamelogic.CountdownAnimation;
import gamelogic.PauseScreen;
import gamelogic.KeyPressStoppableAnimation;
import levels.LevelInformation;
import sprites.Collidable;
import sprites.Sprite;
import biuoop.DrawSurface;

/**
 * Class GameLevel
 * <p>
 * the class holds together different operations regarding the game:
 * creating the game- its basic lists of objects,
 * initializing it- generating the objects and and the settings of the game,
 * and running it- using the formidable endless while- loop.
 *
 * @author Matan Shamir.
 */
public class GameLevel implements Animation {
    //different constant integers holding properties of the game.
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    //the list of the sprites in the game.
    private final SpriteCollection sprites;
    //the list of the collidables in the game.
    private final GameEnvironment environment;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final ScoreTrackingListener score;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter scoreCounter;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation information;
    private final SpriteGenerator spriteGenerator;
    private final Counter lives;
    private boolean isAlreadyPressed;

    /**
     * class constructor- initiate new lists for collidables and sprites.
     *
     * @param information     the information that each level possesses, to keep responsibility away.
     * @param animationRunner the class that runs the game: the while-true loop.
     * @param keyboardSensor  the keyboard sensor for the game.
     * @param scoreCounter    the counter that keeps track of this level's score.
     * @param lives           a counter to keep track of the player's lives and update from within.
     */
    public GameLevel(LevelInformation information, AnimationRunner animationRunner,
                     KeyboardSensor keyboardSensor, Counter scoreCounter, Counter lives) {
        this.information = information;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(information.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        this.scoreCounter = scoreCounter;
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.score = new ScoreTrackingListener(this.scoreCounter);
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.spriteGenerator = new SpriteGenerator(this, this.information);
        this.lives = lives;
        this.isAlreadyPressed = false;
    }

    /**
     * class getter.
     *
     * @return the amount of remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * class getter.
     *
     * @return the collection of sprites held in this level.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * class getter.
     *
     * @return the amount of remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * class getter.
     *
     * @return the keyboard sensor of the game.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * class getter.
     *
     * @return the block remover of the game.
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * class getter.
     *
     * @return the ball remover of the game.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * class getter.
     *
     * @return return the score listener of the game.
     */
    public ScoreTrackingListener getScore() {
        return this.score;
    }

    /**
     * class getter.
     *
     * @return the game environment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * add a collidable to the list of collidables participating the game.
     *
     * @param c the collidable to be added to the list of the game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite to the list of sprites participating the game.
     *
     * @param s the new sprite to be added to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove a certain collidable from the game (after a hit occurs).
     *
     * @param c the collidable to be removed from the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a certain sprite from the game (after a hit occurs).
     *
     * @param s the sprite to be removed from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //try creating every different sprite with the generator, with specified properties.
        this.spriteGenerator.generatePaddle();
        this.spriteGenerator.generateBorders();
        this.spriteGenerator.generateBlocks();
        this.spriteGenerator.generateIndicator();
        //in case you entered too many blocks or balls for the game, you will know.
    }

    /**
     * this method is responsible for running the game: it sends the required variables to the runner,
     * and orders it to run the game. it also updates the required fields and information in the game,
     * depended on the reason that the game ended.
     */
    public void run() throws Exception{
        /*
        generate the balls only now, to make sure if we re-entered the game, the balls are re-created
        unlike the other sprites in the game!
         */
        this.spriteGenerator.generateBalls();
        //set running to true until game has stopped running for some reason.
        this.running = true;
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.information));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * class getter.
     *
     * @return the amount of lives that the player still has. the game level has a reference to it.
     */
    public Counter getLives() {
        return lives;
    }

    @Override
    public void doOneFrame(DrawSurface d) throws Exception{
        this.information.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(this.information.countdownColor());
        d.drawText(35, 60, "Press P for pause", 12);
        //let each object know that an iteration occurred, and time has passed.
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")||(this.keyboard.isPressed("פ"))||(this.keyboard.isPressed("P"))) {
            if (!isAlreadyPressed) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                        new PauseScreen(this.scoreCounter, this.sprites, this.getInformation())));
                this.isAlreadyPressed = true;
            }
        } else {
            isAlreadyPressed = false;
        }
        if ((this.getRemainingBlocks().getValue() == 0)
                || (this.getRemainingBalls().getValue() == 0)) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * class getter.
     *
     * @return the information that the game has concerning the game.
     */
    public LevelInformation getInformation() {
        return information;
    }
}