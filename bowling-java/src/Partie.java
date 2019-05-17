import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Partie {
    public static final int NB_MANCHE = 10;
    public List<Manche> mancheList;

    public Partie(Manche... manches) {
        this.mancheList = new ArrayList<>();
        mancheList.addAll(Arrays.asList(manches));
    }

    public List<Manche> getMancheList() {
        return mancheList;
    }

    public void setMancheList(List<Manche> mancheList) {
        this.mancheList = mancheList;
    }

    public int getScore() {
        int sum = 0;
        for (int i = 0; i < mancheList.size(); i++) {
            int score = 0;
            if (i < mancheList.size()-1)
                score = mancheList.get(i).getScore(mancheList.get(i + 1));
            else
                score = mancheList.get(i).getScore();
            sum += score;
        }
        return sum;
    }
}
