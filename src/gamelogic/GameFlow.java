//Matan Shamir 206960239
package gamelogic;

import biuoop.KeyboardSensor;
import game.Counter;
import game.GameLevel;
import levels.LevelInformation;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * class GameFlow is responsible for the game's logic.
 * it runs all levels one after the other, and holds fields concerning the whole game, beyond each level, and
 * updates them: such as the amount of lives left, the score, and the runner which is relevant for
 * every level.
 *
 * @author Matan Shamir.
 */
public class GameFlow {

    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter scoreCounter;
    private final Counter lives;

    /**
     * class constructor.
     *
     * @param ar the animation runner that is relevant for every level.
     * @param ks the keyboard sensor for every level.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreCounter = new Counter(0);
        this.lives = new Counter(0);
    }

    /**
     * this method runs all of the levels one after the other, given from the main method.
     *
     * @param levels a list of the level's information, to be able to create suitable levels.
     */
    public void runLevels(List<LevelInformation> levels) throws Exception {
        //run a for-each loop and do the same for every level:
        for (LevelInformation levelInformation : levels) {
            //create a new level with the information, and give references to this runner, keyboard, etc.
            GameLevel level = new GameLevel(levelInformation, this.animationRunner, this.keyboardSensor,
                    this.scoreCounter, this.lives);
            //initialize and run the game.
            level.initialize();
            level.run();
            //in case the player lost all balls and thus the level exited, run again with one life less.
            while (level.getRemainingBalls().getValue() == 0) {
                if (lives.getValue() > 0) {
                    this.lives.decrease(1);
                    level.run();
                    //in case no lives are left: create a new game over animation.
                } else {
                    this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                            new GameOver(this.scoreCounter, level.getSprites(), level.getInformation())));
                    this.updateLeaderBoard();
                    return;
                }
            }
            //if reached here, the level ended with all blocks popped, increase points and level up.
            this.scoreCounter.increase(100);
        }
        //if all levels are over without moving to game over, the game is won, perform you win animation.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                new YouWin(this.scoreCounter)));
        this.updateLeaderBoard();
    }

    private void updateLeaderBoard() throws IOException {
        LinkedList<Integer> leaderBoard = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Arkanoid", "results.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            leaderBoard.addLast(Integer.parseInt(line));
        }
        br.close();
        int i;
        boolean added = false;
        int originalSize= leaderBoard.size();
        for (i = 0; i < originalSize; i++) {
            if (this.scoreCounter.getValue() > leaderBoard.get(i)) {
                leaderBoard.add(i, this.scoreCounter.getValue());
                added = true;
            }
        }
        if ((i < 9) && (!added)) {
            leaderBoard.add(i, this.scoreCounter.getValue());
        }
        if (i == 10) {
            leaderBoard.remove(10);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Arkanoid\\results.txt"));
        for (Integer score : leaderBoard) {
            bw.write(Integer.toString(score));
            bw.write("\n");
        }

        bw.close();
    }
}

