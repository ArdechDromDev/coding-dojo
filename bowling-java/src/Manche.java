import java.util.ArrayList;
import java.util.List;

public class Manche {
    public static final int MAX_QUILLES = 10;
    public List<Integer> nbQUillesTombeeParLancer;
    private int scoreFirstShot;

    public Manche(int... shots) {
        this.nbQUillesTombeeParLancer = new ArrayList<>();
        for (int shot: shots) {
            nbQUillesTombeeParLancer.add(shot);
        }
    }

    public int getScore(Manche... manches) {
        int score = nbQUillesTombeeParLancer.stream().mapToInt(x -> x).sum();
        if (score == MAX_QUILLES) {
            if (isStrike()) {
                // strike
                if (manches.length >= 1)
                    score = score + manches[0].getFirstShot() + manches[0].getSecondShot();
                else
                    return 0;
            } else {
                if (manches.length >= 1)
                    score = score + manches[0].getFirstShot();
            }
        }
        return score;
    }

    private int getFirstShot() {
        return nbQUillesTombeeParLancer.get(0);
    }

    private int getSecondShot() {
        return nbQUillesTombeeParLancer.get(1);
    }

    private boolean isStrike() {
        return nbQUillesTombeeParLancer.get(0) == MAX_QUILLES;
    }

}
