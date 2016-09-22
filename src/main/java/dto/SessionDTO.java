package dto;


import helpers.ValueHolder;
import models.Entity;
import service.impl.FilmServiceImpl;
import service.impl.HallServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Alex on 17.08.2016.
 */
public class SessionDTO extends Entity {
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

    public FilmDTO getFilm() {
        if (film instanceof ValueHolder) {
            film = FilmServiceImpl.getInstance().getById(film.getId());
        }
        return (FilmDTO) film;
    }

    public void setFilm(Entity film) {
        this.film = film;
    }

    public LocalDateTime getDateOfSeance() {
        return dateOfSeance;
    }

    public void setDateOfSeance(LocalDateTime dateOfSeance) {
        this.dateOfSeance = dateOfSeance;
    }

    public HallDTO getHall() {
        if (hall instanceof ValueHolder) {
            hall = HallServiceImpl.getInstance().getById(hall.getId());
        }
        return (HallDTO) hall;
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
    public String toString() {
        return "Session{" +
                "film=" + getFilm().getName() +
                ", dateOfSeance=" + getDateOfSeance().format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY H':'MM")).toString() +
                ", hall=" + getHall().getName() +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionDTO)) return false;
        if (!super.equals(o)) return false;

        SessionDTO session = (SessionDTO) o;

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

