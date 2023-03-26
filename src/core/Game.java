package core;

import java.util.List;

public class Game {
    private final Grid grid;
    private int cpuScore = 0;
    private int humanScore = 0;

    public Game(Grid grid) {
        this.grid = grid;
    }

    public Game(Grid grid, int cpuScore, int humanScore) {
        this(grid);
        this.cpuScore = cpuScore;
        this.humanScore = humanScore;
    }

//    public void makeMove(Connection connection, PlayerType playerType) {
//        List<Tile> tilesCompleted = grid.completesTile(connection);
//        int connectionValue = tilesCompleted.stream().map(tile -> tile.getValue()).reduce(0, (subtotal, tileValue) -> subtotal + tileValue);
//
//        switch (playerType) {
//            case CPU -> cpuScore += connectionValue;
//            case HUMAN -> humanScore += connectionValue;
//        }
//        grid.connect(connection);
//    }

    public int getCpuScore() {
        return cpuScore;
    }

    public int getHumanScore() {
        return humanScore;
    }

//    public Game clone() {
//        return new Game(grid.clone(), cpuScore, humanScore);
//    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return "CPU Score: " + cpuScore + " Human Score: " + humanScore + "\n"  + grid;
    }
}
