package core;

public class Grid {
    private final Slot[][] grid;

    public Grid() {
        this.grid = new Slot[6][7];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Slot(new Point(i, j), PlayerType.EMPTY);
            }
        }
    }

    public Grid(Slot[][] grid) {
        this.grid = grid;
    }

    public int calculateHeuristic(PlayerType playerType) {
        int totalCount = 0;
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getValue() != PlayerType.EMPTY && grid[i][j].getValue() != playerType) continue;
                totalCount += calculatePointValue(grid[i][j].getPoint(), playerType);
            }
        }

        return totalCount;
    }

    private int calculatePointValue(Point point, PlayerType playerType) {
        int totalCount = 0;

        // Left and Right
        int left = point.getColumn() - 1;
        int right = point.getColumn() + 1;
        int count = 1;
        int numNotEmpty = grid[point.getRow()][point.getColumn()].getValue() == playerType ? 1 : 0;

        while (left >= 0 && (grid[point.getRow()][left].getValue() == playerType || grid[point.getRow()][left].getValue() == PlayerType.EMPTY)) {
            if (grid[point.getRow()][left].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            left--;
        }

        while (right < grid[0].length && (grid[point.getRow()][right].getValue() == playerType || grid[point.getRow()][right].getValue() == PlayerType.EMPTY)) {
            if (grid[point.getRow()][right].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            right++;
        }

        if (count >= 4) totalCount += Math.pow(Math.min(count, 4), numNotEmpty);
//        if (numNotEmpty >= 4) totalCount += 10000;

        // Up and Down
        int up = point.getRow() - 1;
        int down = point.getRow() + 1;
        count = 1;
        numNotEmpty = grid[point.getRow()][point.getColumn()].getValue() == playerType ? 1 : 0;

        while (up >= 0 && (grid[up][point.getColumn()].getValue() == playerType || grid[up][point.getColumn()].getValue() == PlayerType.EMPTY)) {
            if (grid[up][point.getColumn()].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            up--;
        }

        while (down < grid.length && (grid[down][point.getColumn()].getValue() == playerType || grid[down][point.getColumn()].getValue() == PlayerType.EMPTY)) {
            if (grid[down][point.getColumn()].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            down++;
        }

        if (count >= 4) totalCount += Math.pow(Math.min(count, 4), numNotEmpty);
//        if (numNotEmpty >= 4) totalCount += 10000;

        // Diagonal Right Up and Left Down
        left = point.getColumn() - 1;
        right = point.getColumn() + 1;
        up = point.getRow() - 1;
        down = point.getRow() + 1;

        count = 1;
        numNotEmpty = grid[point.getRow()][point.getColumn()].getValue() == playerType ? 1 : 0;

        while (up >= 0 && right < grid[0].length && (grid[up][right].getValue() == playerType || grid[up][right].getValue() == PlayerType.EMPTY)) {
            if (grid[up][right].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            up--;
            right++;
        }

        while (down < grid.length && left >= 0 && (grid[down][left].getValue() == playerType || grid[down][left].getValue() == PlayerType.EMPTY)) {
            if (grid[down][left].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            down++;
            left--;
        }

        if (count >= 4) totalCount += Math.pow(Math.min(count, 4), numNotEmpty);
//        if (numNotEmpty >= 4) totalCount += 10000;

        // Diagonal Right Down and Left Up
        left = point.getColumn() - 1;
        right = point.getColumn() + 1;
        up = point.getRow() - 1;
        down = point.getRow() + 1;

        count = 1;
        numNotEmpty = grid[point.getRow()][point.getColumn()].getValue() == playerType ? 1 : 0;

        while (down < grid.length && right < grid[0].length && (grid[down][right].getValue() == playerType || grid[down][right].getValue() == PlayerType.EMPTY)) {
            if (grid[down][right].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            down++;
            right++;
        }

        while (up >= 0 && left >= 0 && (grid[up][left].getValue() == playerType || grid[up][left].getValue() == PlayerType.EMPTY)) {
            if (grid[up][left].getValue() == playerType) {
                numNotEmpty++;
            }
            count++;
            up--;
            left--;
        }

        if (count >= 4) totalCount += Math.pow(Math.min(count, 4), numNotEmpty);
//        if (numNotEmpty >= 4) totalCount += 10000;
        return totalCount;
    }

    public boolean determineWin(int lineNum, PlayerType playerType) {
        Point point = determineSlot(lineNum).getPoint();

        // Left and Right
        int left = point.getColumn() - 1;
        int right = point.getColumn() + 1;
        int count = 1;

        while (left >= 0 && grid[point.getRow()][left].getValue() == playerType) {
            count++;
            left--;
        }

        while (right < grid[0].length && grid[point.getRow()][right].getValue() == playerType) {
            count++;
            right++;
        }

        if (count >= 4) return true;

        // Up and Down
        int up = point.getRow() - 1;
        int down = point.getRow() + 1;
        count = 1;

        while (up >= 0 && grid[up][lineNum].getValue() == playerType) {
            count++;
            up--;
        }

        while (down < grid.length && grid[down][lineNum].getValue() == playerType) {
            count++;
            down++;
        }

        if (count >= 4) return true;

        // Diagonal Right Up and Left Down
        left = point.getColumn() - 1;
        right = point.getColumn() + 1;
        up = point.getRow() - 1;
        down = point.getRow() + 1;

        count = 1;

        while (up >= 0 && right < grid[0].length && grid[up][right].getValue() == playerType) {
            count++;
            up--;
            right++;
        }

        while (down < grid.length && left >= 0 && grid[down][left].getValue() == playerType) {
            count++;
            down++;
            left--;
        }

        if (count >= 4) return true;

        // Diagonal Right Down and Left Up
        left = point.getColumn() - 1;
        right = point.getColumn() + 1;
        up = point.getRow() - 1;
        down = point.getRow() + 1;

        count = 1;

        while (down < grid.length && right < grid[0].length && grid[down][right].getValue() == playerType) {
            count++;
            down++;
            right++;
        }

        while (up >= 0 && left >= 0 && grid[up][left].getValue() == playerType) {
            count++;
            up--;
            left--;
        }

        if (count >= 4) return true;

        return false;
    }

    public Point insert(int lineNum, PlayerType playerType) {
        Slot slot = determineSlot(lineNum);

        if (slot == null) {
            System.out.println("Invalid Position: 0x1");
            return null;
        }

        slot.setValue(playerType);
        return slot.getPoint();
    }

    private Slot determineSlot(int lineNum) {
        if (!validatePosition(lineNum)) {
            System.out.println("Invalid Position 0x2: " + lineNum);
            return null;
        }

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][lineNum].getValue() != PlayerType.EMPTY) { // grid[i][lineNum].getValue() == PlayerType.EMPTY shouldn't be necessary due to checking valid position
                return grid[i - 1][lineNum];
            } else if (grid[i][lineNum].getValue() == PlayerType.EMPTY && i == grid.length - 1) {
                return grid[i][lineNum];
            }
        }

        return null;
    }


    public boolean validatePosition(int lineNum) {
        if (lineNum < 0 || lineNum >= grid[0].length) {
            return false;
        }

        if (this.grid[0][lineNum].getValue() != PlayerType.EMPTY) {
            return false;
        }
        return true;
    }

    public boolean isLastSpot() {
        int count = 0;
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i].getValue() == PlayerType.EMPTY) count++;
        }

        return count == 1;
    }

    public Grid clone() {
        Slot[][] clonedGrid = new Slot[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                clonedGrid[i][j] = new Slot(new Point(i, j), grid[i][j].getValue());
            }
        }

        return new Grid(clonedGrid);
    }

    public Slot[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String character;
                switch(grid[i][j].getValue()) {
                    case CPU -> character = "C";
                    case HUMAN -> character = "H";
                    case EMPTY -> character = "_";
                    default -> character = "_";
                }
                stringBuilder.append(character).append(" ");
            }
            stringBuilder.append("\n");
        }
        for (int i = 0; i < grid[0].length; i++) {
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString();
    }
}
