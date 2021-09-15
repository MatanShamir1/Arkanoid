package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import levels.*;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import sprites.Background;
import sprites.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Manu implements Animation {
    private Background background;
    private final String[] options;
    private int toDo;
    private AnimationRunner runner;
    private boolean isAlreadyPressed;

    public Manu() {
        this.background = this.initBackground();
        this.options = new String[]{"Start", "Leader Board", "Help", "About"};
        this.toDo = 0;
        //create a runner for the whole game, each level uses the same runner.
        this.runner = new AnimationRunner();
        this.isAlreadyPressed = true;
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
        this.drawScreen(d);
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

    private void drawScreen(DrawSurface d) {
        this.background.drawOn(d);
        Color defaultColor = Color.BLACK;
        d.setColor(defaultColor);
        for (int j = 0; j <= 1; j++) {
            int initialPosition = 100;
            for (int i = 0; i < this.options.length; i++) {
                d.setColor(defaultColor);
                if (i == this.toDo && j == 1) {
                    d.setColor(Color.BLUE);
                }
                d.drawText(d.getWidth() / 2 - 80 - j * 3, initialPosition - j * 3, this.options[i], 50);
                initialPosition += 150;
            }
            defaultColor = Color.CYAN;
        }
    }

    public void activate() {
        if(this.toDo == 0) {
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
        } else if (this.toDo == 1){

        } else if (this.toDo == 2){

        } else if (this.toDo == 3){

        }
    }

    public static void main(String[] args) {
        Manu manu = new Manu();
        manu.runner.run(manu);
    }
}
