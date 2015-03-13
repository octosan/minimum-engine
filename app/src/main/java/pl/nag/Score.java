package pl.nag;

/**
 * @author ts
 */
public class Score {

    private final int stars;

    public Score(float successful) {
        if (successful >= 1.00) {
            stars = 3;
        } else if (successful >= 0.75) {
            stars = 2;
        } else if (successful >= 0.50) {
            stars = 1;
        } else {
            stars = 0;
        }
    }

    public int getStars() {
        return stars;
    }
}
