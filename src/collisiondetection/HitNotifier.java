//Matan Shamir 206960239
package collisiondetection;

/**
 * class HitNotifier: this class is an interface of the notifiers in the game.
 * it holds methods of adding a hit listener to the list of listeners and removing
 * them from the list.
 *
 * @author Matan Shamir.
 */
public interface HitNotifier {
    /**
     * add a hit listener to your list to be able to notify it if a collision occurs.
     *
     * @param hl the new hitListener to add tok the list.
     */
    void addHitListener(HitListener hl);

    /**
     * remove a certain listener from your list.
     *
     * @param hl the hitListener to be removed from the list.
     */
    void removeHitListener(HitListener hl);
}
