package sticks;

import java.util.Random;

/**
 *
 * @author rcbgalido
 */
public class Computer {

    public int move(int totalPickedUpSticks) {
        if (totalPickedUpSticks >= 12 && totalPickedUpSticks <= 15) { //if totalPickedUpSticks is between 12-15
            return 16 - totalPickedUpSticks; // computer picks sticks to set totalPickedUpSticks to 16
        } else if (totalPickedUpSticks >= 17 && totalPickedUpSticks <= 20) { //if totalPickedUpSticks is between 17-20
            return 21 - totalPickedUpSticks; // computer picks sticks to win the game (set totalPickedUpSticks to 21)
        } else {
            return new Random().nextInt(4) + 1; //random number from 1-4
        }
    }
}
