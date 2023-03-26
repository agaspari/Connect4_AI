package core;

import java.util.Objects;

public class Slot {
    private final Point point;
    private PlayerType value;

    public Slot(Point point, PlayerType value) {
        this.point = point;
        this.value = value;
    }

    public PlayerType getValue() {
        return value;
    }

    public void setValue(PlayerType value) {
        this.value = value;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "point=" + point +
                ", value=" + value +
                '}';
    }
}
