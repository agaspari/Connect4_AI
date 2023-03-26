package core;

public class AI {
    private final Grid grid;
    private final int maxDepth;

    public AI(Grid grid, int maxDepth) {
        this.grid = grid;
        this.maxDepth = maxDepth;
    }

    public AIData determineMove() {
        long start = System.currentTimeMillis();

        Grid clone = grid.clone();

        AIData move = max(0, clone, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println("Time to Determine Move: " + (System.currentTimeMillis() - start) / 1000 + "s");

        return move;
    }

    private AIData max(int depth, Grid grid, int alpha, int beta) {
        int max = Integer.MIN_VALUE;
        int maxSlot = -1;

        if (depth == maxDepth || grid.isLastSpot()) {
            for (int lineNum = 0; lineNum < grid.getGrid()[0].length; lineNum++) {
                Grid clone = grid.clone();

                if (!clone.validatePosition(lineNum)) continue;
                if (clone.determineWin(lineNum, PlayerType.CPU)) {
                    clone.insert(lineNum, PlayerType.CPU);
                    return new AIData(lineNum, Integer.MAX_VALUE - 1);
                } else {
                    clone.insert(lineNum, PlayerType.CPU);
                    int score = clone.calculateHeuristic(PlayerType.CPU) - clone.calculateHeuristic(PlayerType.HUMAN);
                    if (score > max) {
                        max = score;
                        maxSlot = lineNum;
                    }
                }
            }
        } else {
            for (int lineNum = 0; lineNum < grid.getGrid()[0].length; lineNum++) {
                Grid clone = grid.clone();

                if (!clone.validatePosition(lineNum)) continue;
                if (clone.determineWin(lineNum, PlayerType.CPU)) {
                    clone.insert(lineNum, PlayerType.CPU);
                    return new AIData(lineNum, Integer.MAX_VALUE - 1);
                } else {
                    clone.insert(lineNum, PlayerType.CPU);
                    AIData minAI = min(depth + 1, clone, alpha, beta);
                    if (minAI.getLineNum() != -1) {
                        if (minAI.getScore() > max) {
                            max = minAI.getScore();
                            maxSlot = lineNum;
                        }
                    }
                    if (minAI.getScore() >= beta) {
                        break;
                    }
                    alpha = Math.max(alpha, minAI.getScore());
                }

            }
        }

        return new AIData(maxSlot, max);
    }

    private AIData min(int depth, Grid grid, int alpha, int beta) {
        int min = Integer.MAX_VALUE;
        int minSlot = -1;

        if (depth == maxDepth || grid.isLastSpot()) {
            for (int lineNum = 0; lineNum < grid.getGrid()[0].length; lineNum++) {
                Grid clone = grid.clone();

                if (!clone.validatePosition(lineNum)) continue;

                if (clone.determineWin(lineNum, PlayerType.HUMAN)) {
                    clone.insert(lineNum, PlayerType.HUMAN);
                    return new AIData(lineNum, Integer.MIN_VALUE + 1);
                } else {
                    clone.insert(lineNum, PlayerType.HUMAN);
                    int score = clone.calculateHeuristic(PlayerType.CPU) - clone.calculateHeuristic(PlayerType.HUMAN);
                    if (score < min) {
                        min = score;
                        minSlot = lineNum;
                    }
                }
            }
        } else {
            for (int lineNum = 0; lineNum < grid.getGrid()[0].length; lineNum++) {

                Grid clone = grid.clone();
                if (!clone.validatePosition(lineNum)) continue;
                if (clone.determineWin(lineNum, PlayerType.HUMAN)) {
                    clone.insert(lineNum, PlayerType.HUMAN);
                    return new AIData(lineNum, Integer.MIN_VALUE + 1);
                } else {
                    clone.insert(lineNum, PlayerType.HUMAN);
                    AIData maxAI = max(depth + 1, clone, alpha, beta);

                    if (maxAI.getLineNum() != -1) {
                        if (maxAI.getScore() < min) {
                            min = maxAI.getScore();
                            minSlot = lineNum;
                        }
                    }
                    if (maxAI.getScore() <= alpha) {
                        break;
                    }
                    beta = Math.min(beta, maxAI.getScore());

                }
            }
        }
        return new AIData(minSlot, min);
    }
}
