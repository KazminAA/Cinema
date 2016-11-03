package models;

import java.time.LocalDateTime;

/**
 * Created by Alex on 16.08.2016.
 */
public class Ticket extends Entity {
    private int raw;
    private int col;
    private boolean chk;
    private boolean purchase;
    private LocalDateTime timecreate;
    private int sessionID;
    private int userID;

    public Ticket() {
    }

    public Ticket(int raw, int col, boolean chk, boolean purchase, LocalDateTime timecreate, int sessionID, int userID) {
        setRaw(raw);
        setCol(col);
        setChk(chk);
        setPurchase(purchase);
        setTimecreate(timecreate);
        setSessionID(sessionID);
        setUserID(userID);
    }

    public int getRaw() {
        return raw;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isChk() {
        return chk;
    }

    public void setChk(boolean chk) {
        this.chk = chk;
    }

    public boolean isPurchase() {
        return purchase;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }

    public LocalDateTime getTimecreate() {
        return timecreate;
    }

    public void setTimecreate(LocalDateTime timecreate) {
        this.timecreate = timecreate;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        if (!super.equals(o)) return false;

        Ticket ticket = (Ticket) o;

        if (getRaw() != ticket.getRaw()) return false;
        if (getCol() != ticket.getCol()) return false;
        return getSessionID() == ticket.getSessionID();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getRaw();
        result = 31 * result + getCol();
        result = 31 * result + getSessionID();
        return result;
    }
}
