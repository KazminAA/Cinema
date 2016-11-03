package helpers;

/**
 * Created by lex on 12.10.16.
 */
public class SeatHolder {
    private final int raw;
    private final int col;

    public SeatHolder(int raw, int col) {
        this.raw = raw;
        this.col = col;
    }

    public int getRaw() {
        return raw;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatHolder)) return false;

        SeatHolder that = (SeatHolder) o;

        if (getRaw() != that.getRaw()) return false;
        return getCol() == that.getCol();

    }

    @Override
    public int hashCode() {
        int result = getRaw();
        result = 31 * result + getCol();
        return result;
    }

    @Override
    public String toString() {
        return raw + "/" + col;
    }
}
