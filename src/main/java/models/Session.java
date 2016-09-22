package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Alex on 17.08.2016.
 */
public class Session extends Entity {
    private Entity film;
    private LocalDateTime dateOfSeance;
    private Entity hall;
    private BigDecimal price;

    public Entity getFilmAsEntity() {
        return film;
    }

    public void setFilmAsEntity(Entity film) {
        this.film = film;
    }

    public Entity getHallAsEntity() {
        return hall;
    }

    public void setHallAsEntity(Entity hall) {
        this.hall = hall;
    }

    public LocalDateTime getDateOfSeance() {
        return dateOfSeance;
    }

    public void setDateOfSeance(LocalDateTime dateOfSeance) {
        this.dateOfSeance = dateOfSeance;
    }

    public Entity getHall() {
        return hall;
    }

    public void setHall(Entity hall) {
        this.hall = hall;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        if (!super.equals(o)) return false;

        Session session = (Session) o;

        if (getDateOfSeance() != null ? !getDateOfSeance().equals(session.getDateOfSeance()) : session.getDateOfSeance() != null)
            return false;
        return getHall() != null ? getHall().equals(session.getHall()) : session.getHall() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDateOfSeance() != null ? getDateOfSeance().hashCode() : 0);
        result = 31 * result + (getHall() != null ? getHall().hashCode() : 0);
        return result;
    }
}

