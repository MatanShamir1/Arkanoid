package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameEnvironment;
import game.GameLevel;
import game.SpriteCollection;
import levels.*;
import shapes.*;
import sprites.Background;
import sprites.Block;
import sprites.Shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu implements Animation {
    private final Background background;
    private final String[] options;
    private int toDo;
    private final AnimationRunner runner;
    private boolean isAlreadyPressed;
    private SpriteCollection spriteCollection;
    private Ball ballLeft;
    private Ball ballRight;

    public Menu() {
        this.background = this.initBackground();
        this.options = new String[]{"Start", "Leader Board", "Instructions", "About"};
        this.toDo = 0;
        //create a runner for the whole game, each level uses the same runner.
        this.runner = new AnimationRunner();
        this.isAlreadyPressed = true;
        createMenuEnvironment();
    }

    private void createMenuEnvironment(){
        this.spriteCollection = new SpriteCollection();
        this.ballRight = new Ball(630,400,8,Color.BLUE,new Velocity(2,-0.1));
        Ball ball1 = new Ball(630-16,400,8,Color.BLUE,new Velocity(0,0));
        Ball ball2 = new Ball(630-16*2,400,8,Color.BLUE,new Velocity(0,0));
        Ball ball3 = new Ball(630-16*3,400,8,Color.BLUE,new Velocity(0,0));
        this.ballLeft = new Ball(630-16*4,400,8,Color.BLUE,new Velocity(-2,-0.1));
        spriteCollection.addSprite(ballLeft);
        spriteCollection.addSprite(ballRight);
        spriteCollection.addSprite(ball1);
        spriteCollection.addSprite(ball2);
        spriteCollection.addSprite(ball3);
    }

    private Background initBackground() {
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
    public void doOneFrame(DrawSurface d) {
        this.drawMenu(d);
        this.spriteCollection.notifyAllTimePassed();
        this.spriteCollection.drawAllOn(d);
        if (this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.ENTER_KEY)) {
            this.activate();
        }
        /*
        if entered the animation and the key to move on was already pressed, the player's will was not
        to press it in the current animation. we fix this bug using the "already pressed" logic.
         */
        if ((this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.UP_KEY))
                || (this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.DOWN_KEY))) {

            //if the key was not already pressed, enable pressing.
            if (!this.isAlreadyPressed) {
                this.isAlreadyPressed = true;
                if (this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.UP_KEY)) {
                    this.toDo -= 1;
                }
                if (this.runner.getGui().getKeyboardSensor().isPressed(KeyboardSensor.DOWN_KEY)) {
                    this.toDo += 1;
                }
                if (this.toDo <= -1) {
                    this.toDo = 0;
                } else if (this.toDo >= 4) {
                    this.toDo = 3;
                }
            }
            //elsewhere, the key was already pressed when entering this animation, do not enable pressing!
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    private void drawMenu(DrawSurface d) {
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(203, 103, "Arkanoid", 70);
        d.setColor(Color.RED);
        d.drawText(200, 100, "Arkanoid", 70);
        Color defaultColor = Color.BLACK;
        d.setColor(defaultColor);
        for (int j = 0; j <= 1; j++) {
            int initialPosition = 200;
            for (int i = 0; i < this.options.length; i++) {
                d.setColor(defaultColor);
                if (i == this.toDo && j == 1) {
                    d.setColor(Color.BLUE);
                }
                d.fillCircle(50 - j * 3, initialPosition - 17 - j * 3, 10);
                d.drawText(70 - j * 3, initialPosition - j * 3, this.options[i], 50);
                initialPosition += 110;
            }
            defaultColor = Color.CYAN;
        }
        d.setColor(Color.BLACK);
        d.drawText(35, 30, "Press ENTER to choose", 12);
        d.fillRectangle(540, 240, 170, 10);
        d.drawLine(700, 250, this.ballRight.getX(), this.ballRight.getY());
        d.drawLine(550, 250, this.ballLeft.getX(), this.ballLeft.getY());
        if ((this.ballRight.getVelocity().getAngle() < 90)&& (this.ballRight.getVelocity().getAngle() > 45)) {
            ballLeft.setVelocity(Velocity.fromAngleAndSpeed(this.ballLeft.getVelocity().getAngle() + 0.7, this.ballLeft.getVelocity().getSpeed()));
            ballRight.setVelocity(Velocity.fromAngleAndSpeed(this.ballRight.getVelocity().getAngle() - 0.7, this.ballLeft.getVelocity().getSpeed()));
        } else if((this.ballRight.getVelocity().getAngle() <= 45)||(this.ballRight.getVelocity().getAngle() >= 270)) {
            ballRight.setVelocity(-this.ballRight.getVelocity().getDx(), -this.ballRight.getVelocity().getDy());
            ballLeft.setVelocity(-this.ballLeft.getVelocity().getDx(), -this.ballLeft.getVelocity().getDy());
        }else if ((this.ballRight.getVelocity().getAngle() > 224)&&(this.ballRight.getVelocity().getAngle() < 270)) {
            ballLeft.setVelocity(Velocity.fromAngleAndSpeed(this.ballLeft.getVelocity().getAngle() - 0.7, this.ballLeft.getVelocity().getSpeed()));
            ballRight.setVelocity(Velocity.fromAngleAndSpeed(this.ballRight.getVelocity().getAngle() + 0.7, this.ballLeft.getVelocity().getSpeed()));
        }
    }

    public void activate() {
        try {
            if (this.toDo == 0) {
                //create a new game flow.
                GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor());
                //create a list of levels.
                List<LevelInformation> levels = new ArrayList<>();
                levels.add(new BullsEye());
                levels.add(new EasyPeasy());
                levels.add(new EmpireState());
                levels.add(new FinalLevel());
                //run the game flow.
                gameFlow.runLevels(levels);
            } else if (this.toDo == 1) {
                this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), new Leaderboard(this.background)));
            } else if (this.toDo == 2) {
                this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), new Instructions(this.background)));
            } else if (this.toDo == 3) {
                this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), new About(this.background)));
            }
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.runner.run(menu);
        }catch (Exception ignored){

        }
    }
}
