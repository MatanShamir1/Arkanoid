//Matan Shamir 206960239
package game;

/**
 * class Counter: the class is meant from easier track of amount of objects in the game.
 * every listener holds a counter, and thus is able to have track of amount of objects
 * of a certain kind in the game: whether they're sprites or collidables.
 *
 * @author Matan Shamir.
 */
public class Counter {
    //the basic int counter field.
    private int counter;

    /**
     * class constructor.
     *
     * @param number the number to initiate the counter with.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * increase the counter with a given integer.
     *
     * @param number the number to be added to the current counter.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * decrease the counter with a given integer.
     *
     * @param number the number to be subtracted from the current counter.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * class getter.
     *
     * @return the current value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
