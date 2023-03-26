package core;

public class AIData {
    private final int lineNum;
    private final int score;

    public AIData(int lineNum, int score) {
        this.lineNum = lineNum;
        this.score = score;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getScore() {
        return score;
    }
}
