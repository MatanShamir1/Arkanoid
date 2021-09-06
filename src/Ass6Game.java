//Matan Shamir 206960239

import gamelogic.AnimationRunner;
import gamelogic.GameFlow;
import levels.BullsEye;
import levels.EasyPeasy;
import levels.EmpireState;
import levels.LevelInformation;
import levels.FinalLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Ass6Game.
 * <p>
 * this class is in charge of running the game. it creates a new game,
 * initializes and runs it using its main method.
 *
 * @author Matan Shamir.
 */
public class Ass6Game {
    /**
     * the main method creates the game, initializes and runs it.
     *
     * @param args no arguments given by the cmd.
     */
    public static void main(String[] args) {
        //create a runner for the whole game, each level uses the same runner.
        AnimationRunner runner = new AnimationRunner();
        //create a new game flow.
        GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        //create a list of levels.
        List<LevelInformation> levels = new ArrayList<>();
        //from every argument in the cmd which is a valid number, add to the levels list a new suitable level.
        for (String arg : args) {
            switch (arg) {
                case ("1"):
                    levels.add(new BullsEye());
                    break;
                case ("2"):
                    levels.add(new EasyPeasy());
                    break;
                case ("3"):
                    levels.add(new EmpireState());
                    break;
                case ("4"):
                    levels.add(new FinalLevel());
                    break;
                default:
                    break;
            }
        }
        //if no levels entered, add basic 4 levels solely.
        if (levels.isEmpty()) {
            levels.add(new BullsEye());
            levels.add(new EasyPeasy());
            levels.add(new EmpireState());
            levels.add(new FinalLevel());
        }
        //run the game flow.
        gameFlow.runLevels(levels);
    }
}
